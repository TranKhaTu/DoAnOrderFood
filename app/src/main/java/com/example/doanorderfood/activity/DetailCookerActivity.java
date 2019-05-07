package com.example.doanorderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewBillAdapter;
import com.example.doanorderfood.adapter.ListviewDetailFoodAdapter;
import com.example.doanorderfood.model.Staff;
import com.example.doanorderfood.util.Const;
import com.example.doanorderfood.util.SharePreferenceUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailCookerActivity extends AppCompatActivity {

    @BindView(R.id.btnBackMenu)
    ImageButton btnBackMenu;
    @BindView(R.id.btnGoToBill)
    TextView btnGoToBill;
    @BindView(R.id.toolbarMenu)
    Toolbar toolbarMenu;
    @BindView(R.id.tvTableBill)
    TextView tvTableBill;
    @BindView(R.id.tvPeopleBill)
    TextView tvPeopleBill;
    @BindView(R.id.tvTimeBill)
    TextView tvTimeBill;
    @BindView(R.id.tvTotalBill)
    TextView tvTotalBill;
    @BindView(R.id.lvItemBill)
    ListView lvItemBill;
    private ListviewDetailFoodAdapter listviewDetailFoodAdapter;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooker_detail);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initData() {

    }

    private void initViews() {
        if (CookerActivity.historyBill.getType().equals("đã chế biến")) {
            btnGoToBill.setVisibility(View.GONE);
        } else {
            btnGoToBill.setVisibility(View.VISIBLE);
        }
        mDatabase = FirebaseDatabase.getInstance().getReference("HoaDon");
        databaseReference = mDatabase.child("HoaDonChildren");
        listviewDetailFoodAdapter = new ListviewDetailFoodAdapter(DetailCookerActivity.this, R.layout.item_listview_bill, CookerActivity.historyBill.getArrayList());
        lvItemBill.setAdapter(listviewDetailFoodAdapter);
        listviewDetailFoodAdapter.notifyDataSetChanged();

        tvTableBill.setText("Bàn số: " + CookerActivity.historyBill.getTable());
        tvPeopleBill.setText("Số người: " + CookerActivity.historyBill.getPeople());
        tvTimeBill.setText(CookerActivity.historyBill.getTime());
        tvTotalBill.setText(CookerActivity.historyBill.getTotal());
        btnBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnGoToBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.orderByChild("time").equalTo(CookerActivity.historyBill.getTime())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    //  int currentScore = child.child("checkOnline").getValue(Integer.class);
                                    child.getRef().child("type").setValue("đã chế biến");
                                    Toast.makeText(DetailCookerActivity.this, "Đã chế biến xong và đẩy cho nhân viên", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(DetailCookerActivity.this, CookerActivity.class);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent1.addCategory(Intent.CATEGORY_HOME);
                                    startActivity(intent1);
                                    finish();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });

    }
}
