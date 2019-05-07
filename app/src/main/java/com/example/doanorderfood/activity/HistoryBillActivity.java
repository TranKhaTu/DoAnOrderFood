package com.example.doanorderfood.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewBillAdapter;
import com.example.doanorderfood.adapter.ListviewDialogHistoryBill;
import com.example.doanorderfood.adapter.ListviewHistoryBillAdapter;
import com.example.doanorderfood.model.HistoryBill;
import com.example.doanorderfood.model.ItemMenu;
import com.example.doanorderfood.model.Staff;
import com.example.doanorderfood.model.User;
import com.example.doanorderfood.util.Const;
import com.example.doanorderfood.util.SharePreferenceUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryBillActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Staff user;
    private TextView tvIdStaff;
    private ListView listView;
    private ImageView imBack;
    private ListviewHistoryBillAdapter adapter;
    private ArrayList<HistoryBill> historyBills;
    private Dialog dialogListItem;
    private ArrayList<ItemMenu> arrItem=new ArrayList<>();
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_bill);
        getData();

        findId();
        initViews();
    }

    private void initViews() {
        historyBills=new ArrayList<>();
        user= SharePreferenceUtils.getObjData(HistoryBillActivity.this,Const.KEY_STAFF);
        tvIdStaff.setText("Tên nhân viên: "+ user.getName());
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference("HoaDon");
        databaseReference = mDatabase.child("HoaDonChildren");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {

                    //  Table table = noteSnapshot.getValue(Table.class);
                    HistoryBill historyBill = noteSnapshot.getValue(HistoryBill.class);
                    if(historyBill.getType().equals("xuất hoá đơn"))
                    historyBills.add(historyBill);
//
//      arrTable.add(table);
                    adapter.notifyDataSetChanged();

//                    String number=noteSnapshot.child("check").getValue(String.class);
//                    Log.d("dong", "onDataChange: "+number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        adapter = new ListviewHistoryBillAdapter(HistoryBillActivity.this,R.layout.item_history_bill,historyBills);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
    }

    private void findId() {
        tvIdStaff = findViewById(R.id.tvIdStaffHistoryBill);
        listView = findViewById(R.id.lvHistoryBill);
        imBack = findViewById(R.id.btnBackHistoryBill);
    }

    private void getData() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dialogListItem = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialogListItem.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogListItem.setContentView(R.layout.dialog_list_item_history_bill);
        dialogListItem.show();
        ListView lvItem = dialogListItem.findViewById(R.id.lvItemDialogHistoryBill);
        ListviewDialogHistoryBill adap = new ListviewDialogHistoryBill(this,R.layout.item_dialog_history_bill,historyBills.get(position).getArrayList());
        lvItem.setAdapter(adap);
        adap.notifyDataSetChanged();
    }

}
