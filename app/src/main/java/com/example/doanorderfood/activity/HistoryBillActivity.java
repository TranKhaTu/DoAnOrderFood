package com.example.doanorderfood.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewDialogHistoryBill;
import com.example.doanorderfood.adapter.ListviewHistoryBillAdapter;
import com.example.doanorderfood.model.HistoryBill;
import com.example.doanorderfood.model.ItemMenu;
import com.example.doanorderfood.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryBillActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private User user;
    private TextView tvIdStaff;
    private ListView listView;
    private ImageView imBack;
    private ListviewHistoryBillAdapter adapter;
    private ArrayList<HistoryBill> arrBill;
    private Dialog dialogListItem;
    private ArrayList<ItemMenu> arrItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_bill);
        getData();

        findId();
        initViews();
    }

    private void initViews() {
        tvIdStaff.setText("Mã nhân viên: "+ user.getId());
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listView.setOnItemClickListener(this);
    }

    private void findId() {
        tvIdStaff = findViewById(R.id.tvIdStaffHistoryBill);
        listView = findViewById(R.id.lvHistoryBill);
        imBack = findViewById(R.id.btnBackHistoryBill);
    }

    private void getData() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("u");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void getListItemBill(final Object arg) {
        arrItem = new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        String name = object.getString("tenMonAn");
                        String count = object.getString("soLuong");
                        ItemMenu itemMenu = new ItemMenu("",name,"","","","");
                        itemMenu.setCount(Integer.parseInt(count));
                        arrItem.add(itemMenu);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                initDialog();
                if (!isFinishing())
                {
                    dialogListItem.show();
                }
            }
        });
    }

    private void initDialog() {
        dialogListItem = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialogListItem.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogListItem.setContentView(R.layout.dialog_list_item_history_bill);

        ListView lvItem = dialogListItem.findViewById(R.id.lvItemDialogHistoryBill);
        ListviewDialogHistoryBill adap = new ListviewDialogHistoryBill(this,R.layout.item_dialog_history_bill,arrItem);
        lvItem.setAdapter(adap);
        adap.notifyDataSetChanged();
    }

    private void getListBill(final Object arg) {
        arrBill = new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        String idBill = object.getString("idHoaDon");
                        String idStaff = object.getString("idNhanVien");
                        int table = object.getInt("tenBan");
                        String time = object.getString("thoiGianThanhToan");
                        String total = object.getString("tongTien");
                        int people = object.getInt("soNguoi");
                        HistoryBill historyBill = new HistoryBill(idBill,table,people,total,time);
                        arrBill.add(historyBill);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new ListviewHistoryBillAdapter(HistoryBillActivity.this,R.layout.item_history_bill,arrBill);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
