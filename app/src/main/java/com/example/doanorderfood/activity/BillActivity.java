package com.example.doanorderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewBillAdapter;
import com.example.doanorderfood.model.ItemMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillActivity extends AppCompatActivity {
    public static boolean CHECK_START_MENU = false;
    @BindView(R.id.btnPushBill)
    ImageView btnPushBill;
    @BindView(R.id.btnAddItem)
    ImageView btnAddItem;
    @BindView(R.id.btnPrintBill)
    ImageView btnPrintBill;
    @BindView(R.id.toolbarBill)
    Toolbar toolbarBill;
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
    private ListviewBillAdapter listviewBillAdapter;
    private ArrayList<ItemMenu> arrItem;
    private String table;
    private String people;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        initViews();
        initData();
        if (WaiterActivity.CHECK_TABLE == false)
        {
            getDataCheckFalse();
            initViewsCheckFalse();
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    getPrice();
                    handler.postDelayed(this, 1000);
                }
            };
            handler.post(runnable);
        }
        else {
            getDataCheckTrue();
        }

        clickEvents();
    }

    private void getDataCheckTrue() {
        Intent intent = getIntent();
        table = intent.getStringExtra("table");
    }

    private void clickEvents() {

    }

    private void getPrice() {

    }

    private void initViewsCheckFalse() {
        tvTableBill.setText("Bàn số: "+table);
        tvPeopleBill.setText("Số người: "+people);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String[] str = timeStamp.split("_");
        String[] str2 = str[0].split("");
        String year = str2[1]+str2[2]+str2[3]+str2[4];
        String month = str2[5]+str2[6];
        String day = str2[7]+str2[8];

        String[] str3 = str[1].split("");
        String hour = str3[1]+str3[2];
        String minute = str3[3]+str3[4];
        String sec = str3[5]+str3[6];
        tvTimeBill.setText("Thời gian: "+day+"/"+month+"/"+year+"_"+hour+":"+minute+":"+sec);

        listviewBillAdapter = new ListviewBillAdapter(BillActivity.this,R.layout.item_listview_bill,arrItem);
        lvItemBill.setAdapter(listviewBillAdapter);
        listviewBillAdapter.notifyDataSetChanged();
    }

    private void getDataCheckFalse() {
        arrItem = new ArrayList<>();
        Intent intent = getIntent();
        arrItem = (ArrayList<ItemMenu>) intent.getSerializableExtra("list");
        String numPeo = intent.getStringExtra("numPeo");
        String[] str = numPeo.split("-");
        table = str[0];
        people = str[1];
    }

    private void initData() {

    }

    private void initViews() {
    }
}
