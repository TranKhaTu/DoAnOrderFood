package com.example.doanorderfood.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewStaffAdapter;
import com.example.doanorderfood.model.Staff;
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
    DrawerLayout drawerLayoutManager;
    private ActionBarDrawerToggle toggle;
    private ArrayList<Staff> arrStaff;
    private ListviewStaffAdapter adap;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mDatabase = FirebaseDatabase.getInstance().getReference("UserWaiter");
        databaseReference = mDatabase.child("User");
        setSupportActionBar(toolbarManager);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayoutManager, 0, 0);
        drawerLayoutManager.setDrawerListener(toggle);
        arrStaff = new ArrayList<>();
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
        adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, arrStaff);
        lvStaff.setAdapter(adap);
        adap.notifyDataSetChanged();
        setLine(line3Manager, btnShowAllStaff);
        lvStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManagerMainActivity.this,ProfileActivity.class);
                intent.putExtra("staff",arrStaff.get(position));
                startActivity(intent);
            }
        });

    }

    @OnClick({R.id.addStaff, R.id.btnShowListStaffOnline, R.id.btnShowListStaffOffline, R.id.btnShowAllStaff, R.id.btnStaffManager, R.id.btnLogOutManager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addStaff:
                Intent intent = new Intent(ManagerMainActivity.this, AddStaffActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShowListStaffOnline:
                ArrayList<Staff> staff1 = new ArrayList<>();
                for (Staff t : arrStaff) {
                    if (t.getCheckOnline() == 1) {
                        staff1.add(t);
                    }
                }
                adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, staff1);
                lvStaff.setAdapter(adap);
                adap.notifyDataSetChanged();
                setLine(line1Manager, btnShowListStaffOnline);
                break;
            case R.id.btnShowListStaffOffline:
                ArrayList<Staff> staff = new ArrayList<>();

                for (Staff t : arrStaff) {
                    if (t.getCheckOnline() == 0) {
                        staff.add(t);
                    }
                }
                adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, staff);
                lvStaff.setAdapter(adap);
                adap.notifyDataSetChanged();
                setLine(line2Manager, btnShowListStaffOffline);
                break;
            case R.id.btnShowAllStaff:
                adap = new ListviewStaffAdapter(this, R.layout.item_listview_staff, arrStaff);
                lvStaff.setAdapter(adap);
                adap.notifyDataSetChanged();
                setLine(line3Manager, btnShowAllStaff);
                break;
            case R.id.btnStaffManager:
                drawerLayoutManager.closeDrawers();
                break;
            case R.id.btnLogOutManager:
                break;
        }
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
