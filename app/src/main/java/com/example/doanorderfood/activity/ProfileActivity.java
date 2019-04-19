package com.example.doanorderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanorderfood.R;
import com.example.doanorderfood.model.Staff;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imAvatar;
    private TextView tvName;
    private TextView tvID;
    private TextView tvSex;
    private TextView tvDateOfBirth;
    private TextView tvAddress;
    private TextView tvPhone;
    private TextView tvDateStart;
    private TextView tvSalary;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findId();
        Intent intent = getIntent();

        setDataManager();


        setClicks();

    }

    private void setClicks() {
        btnBack.setOnClickListener(this);
    }

    private void setDataManager() {
        Intent i = getIntent();
        Staff staff = (Staff) i.getSerializableExtra("staff");

        tvName.setText(staff.getName());
        tvID.setText(staff.getId());
        tvSex.setText(staff.getSex());
//        String[] arr = staff.getDateOfBirth().split("T");
//        String[] a = arr[0].split("-");
        tvDateOfBirth.setText(staff.getDateOfBirth());
        tvAddress.setText(staff.getAddress());
        tvPhone.setText(staff.getPhone());
        String[] arr1 = staff.getDateStart().split("T");
        String[] b = arr1[0].split("-");
        tvDateStart.setText(staff.getDateStart());
        tvSalary.setText(staff.getSalary());
    }

    private void findId() {
        imAvatar = findViewById(R.id.imAvatar);
        tvName = findViewById(R.id.tvNamePro);
        tvID = findViewById(R.id.tvIDPro);
        tvSex = findViewById(R.id.tvSexPro);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirthPro);
        tvAddress = findViewById(R.id.tvAddressPro);
        tvPhone = findViewById(R.id.tvPhonePro);
        tvDateStart = findViewById(R.id.tvDateStartPro);
        tvSalary = findViewById(R.id.tvSalaryPro);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
