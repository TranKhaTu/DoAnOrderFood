package com.example.doanorderfood.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.GridviewTableAdapter;
import com.example.doanorderfood.model.HistoryBill;
import com.example.doanorderfood.model.ItemMenu;
import com.example.doanorderfood.model.Staff;
import com.example.doanorderfood.model.Table;
import com.example.doanorderfood.util.Const;
import com.example.doanorderfood.util.SharePreferenceUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class WaiterActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    //anh xa view
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.btnShowListTableFree)
    Button btnShowListTableFree;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.btnShowListTableBooked)
    Button btnShowListTableBooked;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.btnShowListTable)
    Button btnShowListTable;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tvNameStaff)
    TextView tvNameStaff;
    @BindView(R.id.btnProfile)
    Button btnProfile;
    @BindView(R.id.btnListHistoryBill)
    Button btnListHistoryBill;
    @BindView(R.id.btnLogOut)
    Button btnLogOut;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.btnStatus)
    Button btnStatus;
    @BindView(R.id.switch_on)
    SwitchButton switchOn;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.btnListHistoryThanhToan)
    Button btnListHistoryThanhToan;

    private GridviewTableAdapter gridviewTableAdapter;
    private ArrayList<Table> arrTable;

    private SearchView searchView;
    private ActionBarDrawerToggle toggle;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    private int number;
    private String people;
    private Dialog dialogPeople;
    public static boolean CHECK_TABLE = false;
    private Staff staff;
    private ArrayList<ItemMenu> arrItemChecked = new ArrayList<>();

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        ButterKnife.bind(this);
        //khoi tao view
        initViews();
        //khoi tao data
        initData();
        //khoi tao so nguoi
        initDialogPeople();
    }

    private void initDialogPeople() {
        dialogPeople = new Dialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialogPeople.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogPeople.setContentView(R.layout.dialog_check_number);
        dialogPeople.setCancelable(false);
        final EditText edtPeople = dialogPeople.findViewById(R.id.edtPeopleDialog);
        Button btnExit = dialogPeople.findViewById(R.id.btnExitDialog);
        Button btnDone = dialogPeople.findViewById(R.id.btnDoneDialog);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPeople.dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                people = edtPeople.getText().toString();
                Intent intent = new Intent(WaiterActivity.this, MenuActivity.class);
                intent.putExtra(Const.KEY_NUMBER_PEOPLE, number + "-" + people);
                startActivity(intent);
                dialogPeople.dismiss();
            }
        });
    }

    private void initData() {
        arrTable = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("Table");
        databaseReference = mDatabase.child("TableChildren");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Table table = noteSnapshot.getValue(Table.class);
                    arrTable.add(table);
                    gridviewTableAdapter.notifyDataSetChanged();
//                    int table = noteSnapshot.child("check").getValue(Integer.class);
//                    String note = noteSnapshot.child("note").getValue(String.class);
//                    int number = noteSnapshot.child("number").getValue(Integer.class);
//                    int numberOfChair = noteSnapshot.child("numberOfChair").getValue(Integer.class);
//                    int position = noteSnapshot.child("position").getValue(Integer.class);


//                    String number=noteSnapshot.child("check").getValue(String.class);
                    Log.d("dong", "onDataChange: " + table);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, arrTable);
        gridview.setAdapter(gridviewTableAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Table table = arrTable.get(position);
                int check = table.getCheck();
                number = table.getNumber();
                if (check == 0) {
                    WaiterActivity.CHECK_TABLE = false;
                    dialogPeople.show();
                } else if (check == 1) {
                    WaiterActivity.CHECK_TABLE = true;
                    Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                    intent.putExtra("table", number + "");
                    intent.putExtra("numPeo", table.getNumberOfChair());
                    intent.putExtra("list", arrTable.get(position).getArrayList());
                    startActivity(intent);
                }
            }
        });
    }

    private void initViews() {
//
//        arrItemChecked.add(new ItemMenu("food", "Ga", "30000", "kg",
//                "1",
//                "https://tea-4.lozi.vn/v1/images/resized/tokkboki-gimbap-com-cari-ga-com-tron-6H0HLlNiAXxz0Xf7-134655-1473354329?w=480&type=o", 1));

        staff = SharePreferenceUtils.getObjData(WaiterActivity.this, Const.KEY_STAFF);
        if (staff.getCheckOnline() == 1) {
            switchOn.setChecked(true);
        } else
            switchOn.setChecked(false);
        tvNameStaff.setText(staff.getName());
        switchOn.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                int check = 0;
                if (isChecked) {
                    check = 1;
                    Toast.makeText(WaiterActivity.this, "Bạn đã bật chế độ Online", Toast.LENGTH_SHORT).show();
                } else {
                    check = 0;
                    Toast.makeText(WaiterActivity.this, "Bạn đã bật chế độ Offline", Toast.LENGTH_SHORT).show();
                }
                //get data tu firebase
                mDatabase = FirebaseDatabase.getInstance().getReference("UserWaiter");
                databaseReference = mDatabase.child("User");
                final int finalCheck = check;
                databaseReference.orderByChild("nameNhanVien").equalTo(staff.getNameNhanVien())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    int currentScore = child.child("checkOnline").getValue(Integer.class);
                                    child.getRef().child("checkOnline").setValue(finalCheck);
                                    SharePreferenceUtils.insertObjDataStaff(getApplicationContext(), Const.KEY_STAFF,
                                            (new Staff(staff.getName(), staff.getId(),
                                                    staff.getSex(), staff.getDateOfBirth(), staff.getAddress(),
                                                    staff.getPhone(), staff.getDateStart(), finalCheck, staff.getSalary(), staff.getNameNhanVien()
                                                    , staff.getPassword())));
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });
        arrTable = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        line3.setVisibility(View.VISIBLE);
        btnShowListTable.setTextColor(Color.parseColor("#ef4b4c"));
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            gridview.setClickable(false);
            cardview.setClickable(false);
        } else {
            gridview.setClickable(true);
            cardview.setClickable(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem itemSearch = menu.findItem(R.id.search_view);
        searchView = (SearchView) itemSearch.getActionView();
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, arrTable);
            gridview.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();

        } else if (newText.equalsIgnoreCase("all")) { //tat ca cac ban
            gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, arrTable);
            gridview.setAdapter(gridviewTableAdapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Table table = arrTable.get(position);
                    int check = table.getCheck();
                    number = table.getNumber();
                    if (check == 0) {
                        WaiterActivity.CHECK_TABLE = false;
                        dialogPeople.show();
                    } else if (check == 1) {
                        WaiterActivity.CHECK_TABLE = true;
                        Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                        intent.putExtra("table", number + "");
                        intent.putExtra("numPeo", table.getNumberOfChair());
                        intent.putExtra("list", table.getArrayList());
                        startActivity(intent);
                    }
                }
            });
            gridviewTableAdapter.notifyDataSetChanged();
        } else if (newText.equalsIgnoreCase("free")) { //ban trong
            final ArrayList<Table> tables = new ArrayList<>();
            for (Table t : arrTable) {
                if (t.getCheck() == 0) {
                    tables.add(t);
                }
            }
            gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, tables);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Table table = tables.get(position);
                    int check = table.getCheck();
                    number = table.getNumber();
                    if (check == 0) {
                        WaiterActivity.CHECK_TABLE = false;
                        dialogPeople.show();
                    } else if (check == 1) {
                        WaiterActivity.CHECK_TABLE = true;
                        Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                        intent.putExtra("table", number + "");
                        intent.putExtra("numPeo", table.getNumberOfChair());
                        intent.putExtra("list", table.getArrayList());
                        startActivity(intent);
                    }
                }
            });
            gridview.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        } else if (newText.equalsIgnoreCase("book")) { //chon ban(ban da dat)
            final ArrayList<Table> tables = new ArrayList<>();
            for (Table t : arrTable) {
                if (t.getCheck() == 1) {
                    tables.add(t);
                }
            }
            gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, tables);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Table table = tables.get(position);
                    int check = table.getCheck();
                    number = table.getNumber();
                    if (check == 0) {
                        WaiterActivity.CHECK_TABLE = false;
                        dialogPeople.show();
                    } else if (check == 1) {
                        WaiterActivity.CHECK_TABLE = true;
                        Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                        intent.putExtra("table", number + "");
                        intent.putExtra("numPeo", table.getNumberOfChair());
                        intent.putExtra("list", table.getArrayList());
                        startActivity(intent);
                    }
                }
            });
            gridview.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        } else {
            ArrayList<Table> tables = new ArrayList<>();
            for (Table t : arrTable) {
                if (String.valueOf(t.getNumber()).contains(newText) == true) {
                    tables.add(t);
                }
            }
            gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, tables);
            gridview.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        }
        return true;
    }

//  ham xu ly onclick su kien
    @OnClick({R.id.btnShowListTableFree, R.id.btnShowListTableBooked, R.id.btnShowListTable, R.id.btnListHistoryBill, R.id.btnLogOut
            , R.id.btnProfile, R.id.btnStatus, R.id.btnListHistoryThanhToan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnStatus:
                break;
            case R.id.btnProfile:
                Intent intent = new Intent(WaiterActivity.this, ProfileActivity.class);
                intent.putExtra("staff", staff);
                startActivity(intent);
                break;
            case R.id.btnShowListTableFree:
                final ArrayList<Table> tables = new ArrayList<>();
                for (Table t : arrTable) {
                    if (t.getCheck() == 0) {
                        tables.add(t);
                    }
                }
                gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, tables);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Table table = tables.get(position);
                        int check = table.getCheck();
                        number = table.getNumber();
                        if (check == 0) {
                            WaiterActivity.CHECK_TABLE = false;
                            dialogPeople.show();
                        } else if (check == 1) {
                            WaiterActivity.CHECK_TABLE = true;
                            Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                            intent.putExtra("table", number + "");
                            intent.putExtra("numPeo", table.getNumberOfChair());
                            intent.putExtra("list", table.getArrayList());
                            startActivity(intent);
                        }
                    }
                });
                gridview.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
                setLine(line1, btnShowListTableFree);
                break;
            case R.id.btnShowListTableBooked:
                final ArrayList<Table> tables1 = new ArrayList<>();
                for (Table t : arrTable) {
                    if (t.getCheck() == 1) {
                        tables1.add(t);
                    }
                }
                gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, tables1);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Table table = tables1.get(position);
                        int check = table.getCheck();
                        number = table.getNumber();
                        if (check == 0) {
                            WaiterActivity.CHECK_TABLE = false;
                            dialogPeople.show();
                        } else if (check == 1) {
                            WaiterActivity.CHECK_TABLE = true;
                            Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                            intent.putExtra("table", number + "");
                            intent.putExtra("numPeo", table.getNumberOfChair());
                            intent.putExtra("list", table.getArrayList());
                            startActivity(intent);
                        }
                    }
                });
                gridview.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
                setLine(line2, btnShowListTableBooked);
                break;

            case R.id.btnListHistoryThanhToan:
                Intent intent2 = new Intent(WaiterActivity.this, HistoryBillActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnShowListTable:
                gridviewTableAdapter = new GridviewTableAdapter(this, R.layout.item_gridview, arrTable);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Table table = arrTable.get(position);
                        int check = table.getCheck();
                        number = table.getNumber();
                        if (check == 0) {
                            WaiterActivity.CHECK_TABLE = false;
                            dialogPeople.show();
                        } else if (check == 1) {
                            WaiterActivity.CHECK_TABLE = true;
                            Intent intent = new Intent(WaiterActivity.this, BillActivity.class);
                            intent.putExtra("table", number + "");
                            intent.putExtra("numPeo", table.getNumberOfChair());
                            intent.putExtra("list", table.getArrayList());
                            startActivity(intent);
                        }
                    }
                });
                gridview.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
                setLine(line3, btnShowListTable);
                break;
            case R.id.btnListHistoryBill:
                Intent in = new Intent(WaiterActivity.this, MonAnDaCheBienActivity.class);
                startActivity(in);
                break;
            case R.id.btnLogOut:
                Intent intent1 = new Intent(WaiterActivity.this, LoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
                finish();
                SharePreferenceUtils.insertStringData(WaiterActivity.this, Const.KEY_NAME, "");
                break;
        }
    }

    private void setLine(View v, Button b) {
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);

        btnShowListTable.setTextColor(Color.parseColor("#90000000"));
        btnShowListTableBooked.setTextColor(Color.parseColor("#90000000"));
        btnShowListTableFree.setTextColor(Color.parseColor("#90000000"));

        v.setVisibility(View.VISIBLE);
        b.setTextColor(Color.parseColor("#ef4b4c"));

    }
}
