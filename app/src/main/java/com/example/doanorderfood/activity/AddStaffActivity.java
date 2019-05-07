package com.example.doanorderfood.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doanorderfood.R;
import com.example.doanorderfood.model.Staff;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddStaffActivity extends AppCompatActivity {

    @BindView(R.id.edtIdStaffAddStaff)
    EditText edtIdStaffAddStaff;
    @BindView(R.id.edtNameStaffAddStaff)
    EditText edtNameStaffAddStaff;
    @BindView(R.id.radioButton_male_AddStaff)
    RadioButton radioButtonMaleAddStaff;
    @BindView(R.id.radioButton_female_AddStaff)
    RadioButton radioButtonFemaleAddStaff;
    @BindView(R.id.groupSex)
    RadioGroup groupSex;
    @BindView(R.id.btnChooseDateOfBirthAddStaff)
    Button btnChooseDateOfBirthAddStaff;
    @BindView(R.id.edtAddressStaffAddStaff)
    EditText edtAddressStaffAddStaff;
    @BindView(R.id.edtPhoneStaffAddStaff)
    EditText edtPhoneStaffAddStaff;
    @BindView(R.id.radioButton_QL_AddStaff)
    RadioButton radioButtonQLAddStaff;
    @BindView(R.id.radioButton_BB_AddStaff)
    RadioButton radioButtonBBAddStaff;
    @BindView(R.id.groupPosition)
    RadioGroup groupPosition;
    @BindView(R.id.btnChooseDateStartAddStaff)
    Button btnChooseDateStartAddStaff;
    @BindView(R.id.edtSalaryStaffAddStaff)
    EditText edtSalaryStaffAddStaff;
    @BindView(R.id.edtUserStaffAddStaff)
    EditText edtUserStaffAddStaff;
    @BindView(R.id.edtPassStaffAddStaff)
    EditText edtPassStaffAddStaff;
    @BindView(R.id.btnChooseAvatarAddStaff)
    Button btnChooseAvatarAddStaff;
    @BindView(R.id.imAvatarAddStaff)
    ImageView imAvatarAddStaff;
    @BindView(R.id.btnDoneAddStaff)
    Button btnDoneAddStaff;
    @BindView(R.id.btnExitAddStaff)
    Button btnExitAddStaff;
    private final int REQUEST_CHOOSE_PICTURE = 321;
    private int year;
    private int month;
    private int day;
    private RadioButton radioSexButton;
    private RadioButton radioPositionButton;
    static final int DATE_PICKER_ID = 1111;
    private boolean checkClick;

    private byte[] bytes;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mDatabase = FirebaseDatabase.getInstance().getReference("UserWaiter");
        databaseReference = mDatabase.child("User");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // Show selected date
            if (checkClick) {
                btnChooseDateOfBirthAddStaff.setText(new StringBuilder().append(day)
                        .append("-").append(month + 1).append("-").append(year)
                        .append(" "));
            } else {
                btnChooseDateStartAddStaff.setText(new StringBuilder().append(day)
                        .append("-").append(month + 1).append("-").append(year)
                        .append(" "));
            }

        }
    };

    @OnClick({R.id.btnChooseDateOfBirthAddStaff, R.id.btnChooseDateStartAddStaff, R.id.btnChooseAvatarAddStaff, R.id.btnDoneAddStaff, R.id.btnExitAddStaff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChooseDateOfBirthAddStaff:
                checkClick = true;
                showDialog(DATE_PICKER_ID);
                break;
            case R.id.btnChooseDateStartAddStaff:
                checkClick = false;
                showDialog(DATE_PICKER_ID);
                break;
            case R.id.btnChooseAvatarAddStaff:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CHOOSE_PICTURE);
                break;
            case R.id.btnDoneAddStaff:
                clickDone();
                break;
            case R.id.btnExitAddStaff:
                finish();
                break;
        }
    }

    private void clickDone() {
        String id = edtIdStaffAddStaff.getText().toString();
        String name = edtNameStaffAddStaff.getText().toString();
        String address = edtAddressStaffAddStaff.getText().toString();
        String phone = edtPhoneStaffAddStaff.getText().toString();
        String salary = edtSalaryStaffAddStaff.getText().toString();
        String user = edtUserStaffAddStaff.getText().toString();
        String pass = edtPassStaffAddStaff.getText().toString();
        String birth = btnChooseDateOfBirthAddStaff.getText().toString();
        String dateStart = btnChooseDateStartAddStaff.getText().toString();
        int isCheckedSex = groupSex.getCheckedRadioButtonId();
        radioSexButton = findViewById(isCheckedSex);
        String sex = radioSexButton.getText().toString();
        int isCheckedPosition = groupPosition.getCheckedRadioButtonId();
        radioPositionButton = findViewById(isCheckedPosition);
        String position = radioPositionButton.getText().toString();
        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()
                || salary.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(edtAddressStaffAddStaff, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.DKGRAY);
            snackbar.show();
            return;
        }
        if (btnChooseDateOfBirthAddStaff.getText().toString().equalsIgnoreCase("Ngày sinh")
                || btnChooseDateStartAddStaff.getText().toString().equalsIgnoreCase("Ngày vào")) {
            Snackbar snackbar = Snackbar
                    .make(edtAddressStaffAddStaff, "Bạn chưa nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.DKGRAY);
            snackbar.show();
            return;
        } else {
            Staff staff = new Staff();
            staff.setId(id);
            staff.setName(name);
            staff.setSex(sex);
            staff.setDateOfBirth(birth);
            staff.setAddress(address);
            staff.setPhone(phone);
            staff.setDateStart(dateStart);
            staff.setSalary(salary);
            staff.setCheckOnline(1);
            staff.setNameNhanVien(user);
            staff.setPassword(pass);

            databaseReference.push().setValue(staff);
            Toast.makeText(AddStaffActivity.this, "Thêm thành công.", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AddStaffActivity.this, ManagerMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHOOSE_PICTURE && resultCode == RESULT_OK) {
            try {
                Uri imageURI = data.getData();
                InputStream is = getContentResolver().openInputStream(imageURI);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                bitmap = resize(bitmap, 100, 100);
                bytes = getByteArrayFromBitmap(bitmap);
                imAvatarAddStaff.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float) maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float) maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

    public byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
