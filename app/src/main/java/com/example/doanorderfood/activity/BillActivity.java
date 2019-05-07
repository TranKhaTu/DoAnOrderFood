package com.example.doanorderfood.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewBillAdapter;
import com.example.doanorderfood.model.HistoryBill;
import com.example.doanorderfood.model.ItemMenu;
import com.example.doanorderfood.model.Staff;
import com.example.doanorderfood.util.Const;
import com.example.doanorderfood.util.SharePreferenceUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
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
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    private DatabaseReference databaseReference1;
    private DatabaseReference mDatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        initViews();
        initData();
        if (WaiterActivity.CHECK_TABLE == false) {
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
        } else {
            getDataCheckTrue();
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

        clickEvents();
    }

    private void getDataCheckTrue() {
        Intent intent = getIntent();
        table = intent.getStringExtra("table");
        people = String.valueOf(intent.getIntExtra("numPeo",1));
        // table = intent.getStringExtra("table");
        tvTableBill.setText("Bàn số: " + table);
        tvPeopleBill.setText("Số người: " + people);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String[] str = timeStamp.split("_");
        String[] str2 = str[0].split("");
        String year = str2[1] + str2[2] + str2[3] + str2[4];
        String month = str2[5] + str2[6];
        String day = str2[7] + str2[8];
        tvTotalBill.setText("Tổng tiền:  " + 30000);
        String[] str3 = str[1].split("");
        String hour = str3[1] + str3[2];
        String minute = str3[3] + str3[4];
//        tvTotalBill.setText("30000");
        String sec = str3[5] + str3[6];
        tvTimeBill.setText("Thời gian: " + day + "/" + month + "/" + year + "_" + hour + ":" + minute + ":" + sec);
        arrItem = (ArrayList<ItemMenu>) intent.getSerializableExtra("list");
        listviewBillAdapter = new ListviewBillAdapter(BillActivity.this, R.layout.item_listview_bill, arrItem);
        lvItemBill.setAdapter(listviewBillAdapter);
        listviewBillAdapter.notifyDataSetChanged();
    }

    private void clickEvents() {
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CHECK_START_MENU = true;
                Intent intent = new Intent(BillActivity.this, MenuActivity.class);
                startActivityForResult(intent, 111);
            }
        });

        btnPrintBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogConfigPrint();
            }
        });

        btnPushBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogConfigPush();
            }
        });

        lvItemBill.setOnItemLongClickListener(this);
    }

    private void showDialogConfigPrint() {
        new AlertDialog.Builder(BillActivity.this)
                .setTitle("XÁC NHẬN")
                .setMessage("Xuất hóa đơn?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String query = "";
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                String[] str = timeStamp.split("_");
                String[] str2 = str[0].split("");
                String year = str2[1] + str2[2] + str2[3] + str2[4];
                String month = str2[5] + str2[6];
                String day = str2[7] + str2[8];

                String[] str3 = str[1].split("");
                String hour = str3[1] + str3[2];
                String minute = str3[3] + str3[4];
                String sec = str3[5] + str3[6];
                String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + sec;
                long total = 0;
                for (int i1 = 0; i1 < arrItem.size(); i1++) {
                    total += (convert(arrItem.get(i1).getPrice()) * arrItem.get(i1).getCount());
                }

                HistoryBill historyBill = new HistoryBill();

                historyBill.setTable(Integer.parseInt(table));
                historyBill.setPeople(Integer.parseInt(people));
                historyBill.setTime(time);
                historyBill.setTotal(String.valueOf(total));
                historyBill.setType("xuất hoá đơn");
                historyBill.setArrayList(arrItem);
                databaseReference.push().setValue(historyBill);
                        databaseReference1.orderByChild("position").equalTo(Integer.parseInt(table))
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                            child.getRef().child("check").setValue(0);
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                Toast.makeText(BillActivity.this, "Xuất hoá đơn", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(BillActivity.this, WaiterActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent1);
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void showDialogConfigPush() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this);
        builder.setTitle("XÁC NHẬN");
        builder.setMessage("Đẩy hóa đơn?");
        builder.setCancelable(false);
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Đẩy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int in) {
                if (arrItem.size() > 0) {
                    String query = "";
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                    String[] str = timeStamp.split("_");
                    String[] str2 = str[0].split("");
                    String year = str2[1] + str2[2] + str2[3] + str2[4];
                    String month = str2[5] + str2[6];
                    String day = str2[7] + str2[8];

                    String[] str3 = str[1].split("");
                    String hour = str3[1] + str3[2];
                    String minute = str3[3] + str3[4];
                    String sec = str3[5] + str3[6];
                    String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + sec;
                    long total = 0;
                    for (int i = 0; i < arrItem.size(); i++) {
                        total += (convert(arrItem.get(i).getPrice()) * arrItem.get(i).getCount());
                    }

                    HistoryBill historyBill = new HistoryBill();


                    historyBill.setTable(Integer.parseInt(table));
                    historyBill.setPeople(Integer.parseInt(people));
                    historyBill.setTime(time);
                    historyBill.setTotal(String.valueOf(total));
                    historyBill.setType("đẩy cho đầu bếp");
                    historyBill.setArrayList(arrItem);
                    databaseReference.push().setValue(historyBill);
                    databaseReference1.orderByChild("position").equalTo(Integer.parseInt(table))
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                        child.getRef().child("check").setValue(1);
                                        child.getRef().child("arrayList").setValue(arrItem);
                                        child.getRef().child("numberOfChair").setValue(Integer.parseInt(people));
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                    Toast.makeText(BillActivity.this, "Đã đẩy cho đầu bếp", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(BillActivity.this, WaiterActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent1.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(BillActivity.this, "Vui lòng nhập số lượng món ăn", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getPrice() {
        long total = 0;
        for (int i = 0; i < arrItem.size(); i++) {
            total += (convert(arrItem.get(i).getPrice()) * arrItem.get(i).getCount());
        }

        tvTotalBill.setText("Tổng tiền:  " + getMoney(total));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 111) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<ItemMenu> arr = new ArrayList<>();
                arr = (ArrayList<ItemMenu>) data.getSerializableExtra("result");
                String str = "";
                for (ItemMenu item : arrItem) {
                    str += item.getName();
                }
                Log.e("--------------", str);
                for (int index = 0; index < arr.size(); index++) {
                    if (!str.contains(arr.get(index).getName())) {
                        arrItem.add(arr.get(index));
                    } else {
                        for (int j = 0; j < arrItem.size(); j++) {
                            if (arrItem.get(j).getName().equals(arr.get(index).getName())) {
                                arrItem.get(j).setCount(arrItem.get(j).getCount() + arr.get(index).getCount());
                            }
                        }
                    }
                }
                CHECK_START_MENU = false;
                listviewBillAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private String getMoney(long x) {
        String str = x + "";
        String[] s1 = str.split("");
        String money = "";
        int count = 0;
        int c = 0;
        for (int i = s1.length - 1; i >= 0; i--) {
            count++;
            c++;
            if (count == 3) {
                if (s1[i].equals("")) {
                    money = s1[i] + money;
                    count = 0;
                } else if (c < s1.length - 1) {
                    money = "," + s1[i] + money;
                    count = 0;
                } else {
                    money = s1[i] + money;
                    count = 0;
                }

            } else {
                money = s1[i] + money;
            }
        }

        return money + " VND";
    }

    public long convert(String str) {
        str = str.trim();
        String[] s1 = str.split("VND");
        String str2 = s1[0];
        str2 = str2.trim();
        String money = "";
        String[] s2 = str2.split(",");
        for (int i = 0; i < s2.length; i++) {
            money = money + s2[i];
        }
        money = money.trim();
        return Long.parseLong(money);
    }

    private void initViewsCheckFalse() {
        tvTableBill.setText("Bàn số: " + table);
        tvPeopleBill.setText("Số người: " + people);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String[] str = timeStamp.split("_");
        String[] str2 = str[0].split("");
        String year = str2[1] + str2[2] + str2[3] + str2[4];
        String month = str2[5] + str2[6];
        String day = str2[7] + str2[8];

        String[] str3 = str[1].split("");
        String hour = str3[1] + str3[2];
        String minute = str3[3] + str3[4];
        String sec = str3[5] + str3[6];
        tvTimeBill.setText("Thời gian: " + day + "/" + month + "/" + year + "_" + hour + ":" + minute + ":" + sec);

        listviewBillAdapter = new ListviewBillAdapter(BillActivity.this, R.layout.item_listview_bill, arrItem);
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
        mDatabase = FirebaseDatabase.getInstance().getReference("HoaDon");
        databaseReference = mDatabase.child("HoaDonChildren");
        mDatabase1 = FirebaseDatabase.getInstance().getReference("Table");
        databaseReference1 = mDatabase1.child("TableChildren");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this);
        builder.setTitle("XÁC NHẬN");
        builder.setMessage("Xóa món ăn?");
        builder.setCancelable(false);
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrItem.remove(position);
                listviewBillAdapter.notifyDataSetChanged();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }
}
