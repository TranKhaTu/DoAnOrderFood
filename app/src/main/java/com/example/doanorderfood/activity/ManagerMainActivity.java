package com.example.doanorderfood.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewStaffAdapter;
import com.example.doanorderfood.model.Staff;
import com.example.doanorderfood.util.Const;
import com.example.doanorderfood.util.SharePreferenceUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ManagerMainActivity extends AppCompatActivity {

    //khoi tao view
    @BindView(R.id.addStaff)
    ImageView addStaff;
    @BindView(R.id.toolbarManager)
    Toolbar toolbarManager;
    @BindView(R.id.lvStaff)
    ListView lvStaff;
    @BindView(R.id.btnShowListStaffOnline)
    Button btnShowListStaffOnline;
    @BindView(R.id.line1Manager)
    View line1Manager;
    @BindView(R.id.btnShowListStaffOffline)
    Button btnShowListStaffOffline;
    @BindView(R.id.line2Manager)
    View line2Manager;
    @BindView(R.id.btnShowAllStaff)
    Button btnShowAllStaff;
    @BindView(R.id.line3Manager)
    View line3Manager;
    @BindView(R.id.profile_imageManager)
    CircleImageView profileImageManager;
    @BindView(R.id.tvNameManager)
    TextView tvNameManager;
    @BindView(R.id.btnStaffManager)
    Button btnStaffManager;
    @BindView(R.id.btnLogOutManager)
    Button btnLogOutManager;
    @BindView(R.id.drawerLayoutManager)

      //khai bao
    DrawerLayout drawerLayoutManager;
    private ActionBarDrawerToggle toggle;
    private ArrayList<Staff> arrStaff;
    private ListviewStaffAdapter adap;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    private Dialog editStaff;

    //chay dau tien
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        //get data tu firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("UserWaiter");
        databaseReference = mDatabase.child("User");
        //ví du o search facebook
        setSupportActionBar(toolbarManager);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayoutManager, 0, 0);
        drawerLayoutManager.setDrawerListener(toggle);
        arrStaff = new ArrayList<>();
        arrStaff.clear();
        toolbarManager.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutManager.openDrawer(Gravity.START);
            }
        });
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        line3Manager.setVisibility(View.VISIBLE);
        btnShowAllStaff.setTextColor(Color.parseColor("#ef4b4c"));
        //lang nghe data tu firebase de set du lieu vao listview
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrStaff.clear();
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Staff staff = noteSnapshot.getValue(Staff.class);
                    arrStaff.add(staff);
                    adap.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, arrStaff);
        lvStaff.setAdapter(adap);
        adap.notifyDataSetChanged();
        setLine(line3Manager, btnShowAllStaff);
        //ham onclic de xem thong tin nhan vien
        lvStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManagerMainActivity.this, ProfileActivity.class);
                intent.putExtra("staff", arrStaff.get(position));
                startActivity(intent);
            }
        });
        //ham longclick de chinh sua nhan vien
        lvStaff.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Creating the instance of PopupMenu
                final PopupMenu popup = new PopupMenu(ManagerMainActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_long_click_staff, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                initDialogEdit(arrStaff.get(position));
                                editStaff.show();
                                break;
                            case R.id.delete:
                                databaseReference.orderByChild("nameNhanVien").equalTo(arrStaff.get(position).getNameNhanVien())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                                    child.getRef().setValue(null);

                                                }
                                                arrStaff.remove(arrStaff.get(position));
                                                adap.notifyDataSetChanged();
                                                Toast.makeText(ManagerMainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                return true;
            }
        });

    }
    // chinh sua nhan vien
    private void initDialogEdit(final Staff staff) {
        editStaff = new Dialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        editStaff.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editStaff.setContentView(R.layout.dialog_edit_info_staff);
        editStaff.setCancelable(false);

        final EditText edtId = editStaff.findViewById(R.id.edtIdStaffEditStaff);
        final EditText edtName = editStaff.findViewById(R.id.edtNameStaffEditStaff);
        final EditText edtAddress = editStaff.findViewById(R.id.edtAddressStaffEditStaff);
        final EditText edtPhone = editStaff.findViewById(R.id.edtPhoneStaffEditStaff);
        final EditText edtSalary = editStaff.findViewById(R.id.edtSalaryStaffEditStaff);
        final EditText edtUser = editStaff.findViewById(R.id.edtUserStaffEditStaff);
        final EditText edtPassword = editStaff.findViewById(R.id.edtPassStaffEditStaff);
        final EditText edtDateOfBirth = editStaff.findViewById(R.id.edtDateBirthStaffEditStaff);
        final EditText edtDateStart = editStaff.findViewById(R.id.edtDateStartStaffEditStaff);
        final RadioGroup groupSex = editStaff.findViewById(R.id.groupSexEditStaff);
        Button btnDone = editStaff.findViewById(R.id.btnDoneEditStaff);
        Button btnExit = editStaff.findViewById(R.id.btnExitEditStaff);
        RadioButton radioButton_male = editStaff.findViewById(R.id.radioButton_male_EditStaff);
        RadioButton radioButton_female = editStaff.findViewById(R.id.radioButton_female_EditStaff);
        final RadioGroup groupPosition = editStaff.findViewById(R.id.groupPositionEditStaff);
        RadioButton radioButton_BB = editStaff.findViewById(R.id.radioButton_BB_EditStaff);
        RadioButton radioButton_QL = editStaff.findViewById(R.id.radioButton_QL_EditStaff);
        ImageView imAvatar = editStaff.findViewById(R.id.imAvatarEditStaff);

        edtId.setText(staff.getId());
        edtName.setText(staff.getName());
        edtAddress.setText(staff.getAddress());
        edtPhone.setText(staff.getPhone());
        edtSalary.setText(staff.getSalary());
        //String[] str = staff.getPassword().split("-");
        edtUser.setText(staff.getNameNhanVien());
        edtUser.setEnabled(false);
        edtUser.setKeyListener(null);
        edtPassword.setText(staff.getPassword());
        edtDateOfBirth.setText(staff.getDateOfBirth());
        edtDateStart.setText(staff.getDateStart());
        if (staff.getSex().equalsIgnoreCase("Nam")) {
            radioButton_male.setChecked(true);
            radioButton_female.setChecked(false);
        } else {
            radioButton_female.setChecked(true);
            radioButton_male.setChecked(false);
        }
        // Glide.with(this).load(Constants.PORT+staff.getImage()).into(imAvatar);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editStaff.dismiss();
            }
        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton radioSexButton;
                RadioButton radioPositionButton;
                String id = edtId.getText().toString();
                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                String salary = edtSalary.getText().toString();
                String user = edtUser.getText().toString();
                String pass = edtPassword.getText().toString();
                String birth = edtDateOfBirth.getText().toString();
                String dateStart = edtDateStart.getText().toString();

                String[] a1 = birth.trim().split("-");
                String dateBirth = a1[2] + "-" + a1[1] + "-" + a1[0];
                String[] b1 = dateStart.trim().split("-");
                String dateStart1 = b1[2] + "-" + b1[1] + "-" + b1[0];
                int isCheckedSex = groupSex.getCheckedRadioButtonId();
                radioSexButton = editStaff.findViewById(isCheckedSex);
                final String sex = radioSexButton.getText().toString();
                int isCheckedPosition = groupPosition.getCheckedRadioButtonId();
                radioPositionButton = editStaff.findViewById(isCheckedPosition);
                String position = radioPositionButton.getText().toString();

                if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()
                        || salary.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                    Snackbar snackbar = Snackbar
                            .make(edtAddress, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                    return;
                }
                if (edtDateOfBirth.getText().toString().equalsIgnoreCase("Ngày sinh")
                        || edtDateStart.getText().toString().equalsIgnoreCase("Ngày vào")) {
                    Snackbar snackbar = Snackbar
                            .make(edtAddress, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                    return;
                } else {
                    databaseReference.orderByChild("nameNhanVien").equalTo(edtUser.getText().toString())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                        child.getRef().child("address").setValue(edtAddress.getText().toString());
                                        staff.setAddress(edtAddress.getText().toString());
                                        child.getRef().child("dateOfBirth").setValue(edtDateOfBirth.getText().toString());
                                        staff.setDateOfBirth(edtDateOfBirth.getText().toString());
                                        child.getRef().child("dateStart").setValue(edtDateStart.getText().toString());
                                        staff.setDateStart(edtDateStart.getText().toString());
                                        child.getRef().child("id").setValue(edtId.getText().toString());
                                        staff.setId(edtId.getText().toString());
                                        child.getRef().child("name").setValue(edtName.getText().toString());
                                        staff.setName(edtName.getText().toString());
                                        child.getRef().child("password").setValue(edtPassword.getText().toString());
                                        staff.setPassword(edtPassword.getText().toString());
                                        child.getRef().child("phone").setValue(edtPhone.getText().toString());
                                        staff.setPhone(edtPhone.getText().toString());
                                        child.getRef().child("salary").setValue(edtSalary.getText().toString());
                                        staff.setSalary(edtSalary.getText().toString());
                                        child.getRef().child("sex").setValue(sex);
                                        staff.setSex(sex);

                                         adap.notifyDataSetChanged();
                                        Toast.makeText(ManagerMainActivity.this, "Cập nhật thành công ", Toast.LENGTH_SHORT).show();

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }
                editStaff.dismiss();
            }
        });
    }
    // xu ly su kien onclick show nhan vien online
    @OnClick({R.id.addStaff, R.id.btnShowListStaffOnline, R.id.btnShowListStaffOffline, R.id.btnShowAllStaff, R.id.btnStaffManager, R.id.btnLogOutManager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addStaff:
                Intent intent = new Intent(ManagerMainActivity.this, AddStaffActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShowListStaffOnline:
                arrStaff.clear();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                            Staff staff = noteSnapshot.getValue(Staff.class);
                            arrStaff.add(staff);
                            adap.notifyDataSetChanged();

                        }
                        showOnline();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
                //show nhan vien offline
            case R.id.btnShowListStaffOffline:
                arrStaff.clear();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                            Staff staff = noteSnapshot.getValue(Staff.class);
                            arrStaff.add(staff);
                            adap.notifyDataSetChanged();

                        }
                        showOff();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;
                //show toan bo nhan vien
            case R.id.btnShowAllStaff:
                arrStaff.clear();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                            Staff staff = noteSnapshot.getValue(Staff.class);

                            arrStaff.add(staff);
                            adap.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                showAll();
                break;
            case R.id.btnStaffManager:
                drawerLayoutManager.closeDrawers();
                break;
            case R.id.btnLogOutManager:
                Intent intent1 = new Intent(ManagerMainActivity.this, LoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
                finish();
                SharePreferenceUtils.insertStringData(ManagerMainActivity.this, Const.KEY_NAME, "");
                break;
        }
    }

    private void showAll() {
        adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, arrStaff);
        lvStaff.setAdapter(adap);

        lvStaff.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Creating the instance of PopupMenu
                final PopupMenu popup = new PopupMenu(ManagerMainActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_long_click_staff, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                initDialogEdit(arrStaff.get(position));
                                editStaff.show();
                                break;
                            case R.id.delete:
                                databaseReference.orderByChild("nameNhanVien").equalTo(arrStaff.get(position).getNameNhanVien())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                                    child.getRef().setValue(null);

                                                }
                                                arrStaff.remove(arrStaff.get(position));
                                                adap.notifyDataSetChanged();
                                                Toast.makeText(ManagerMainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                return true;
            }
        });
        //adap.notifyDataSetChanged();
        setLine(line3Manager, btnShowAllStaff);
    }

    private void showOff() {
        final ArrayList<Staff> staff = new ArrayList<>();

        for (Staff t : arrStaff) {
            if (t.getCheckOnline() == 0) {
                staff.add(t);
            }
        }
        adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, staff);
        lvStaff.setAdapter(adap);
        lvStaff.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Creating the instance of PopupMenu
                final PopupMenu popup = new PopupMenu(ManagerMainActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_long_click_staff, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                initDialogEdit(staff.get(position));
                                editStaff.show();
                                break;
                            case R.id.delete:
                                databaseReference.orderByChild("nameNhanVien").equalTo(staff.get(position).getNameNhanVien())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                                    child.getRef().setValue(null);

                                                }
                                                staff.remove(staff.get(position));
                                                adap.notifyDataSetChanged();
                                                Toast.makeText(ManagerMainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                return true;
            }
        });
        adap.notifyDataSetChanged();
        setLine(line2Manager, btnShowListStaffOffline);
    }

    private void showOnline() {
        final ArrayList<Staff> staff1 = new ArrayList<>();
        for (Staff t : arrStaff) {
            if (t.getCheckOnline() == 1) {
                staff1.add(t);
            }
        }
        adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, staff1);
        lvStaff.setAdapter(adap);
        lvStaff.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Creating the instance of PopupMenu
                final PopupMenu popup = new PopupMenu(ManagerMainActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_long_click_staff, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                initDialogEdit(staff1.get(position));
                                editStaff.show();
                                break;
                            case R.id.delete:
                                databaseReference.orderByChild("nameNhanVien").equalTo(staff1.get(position).getNameNhanVien())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            int currentScore = child.child("checkOnline").getValue(Integer.class);
                                                    child.getRef().setValue(null);
                                                }

                                                staff1.remove(staff1.get(position));
                                                adap.notifyDataSetChanged();
                                                Toast.makeText(ManagerMainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                return true;
            }
        });
        adap.notifyDataSetChanged();
        setLine(line1Manager, btnShowListStaffOnline);
    }

    private void setLine(View v, Button b) {
        line1Manager.setVisibility(View.INVISIBLE);
        line2Manager.setVisibility(View.INVISIBLE);
        line3Manager.setVisibility(View.INVISIBLE);

        btnShowAllStaff.setTextColor(Color.parseColor("#90000000"));
        btnShowListStaffOffline.setTextColor(Color.parseColor("#90000000"));
        btnShowListStaffOnline.setTextColor(Color.parseColor("#90000000"));

        v.setVisibility(View.VISIBLE);
        b.setTextColor(Color.parseColor("#ef4b4c"));

    }
}
