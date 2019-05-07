package com.example.doanorderfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doanorderfood.MainActivity;
import com.example.doanorderfood.R;
import com.example.doanorderfood.model.Staff;
import com.example.doanorderfood.util.Const;
import com.example.doanorderfood.util.SharePreferenceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//check 3 truong hop
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("UserWaiter");
        databaseReference = mDatabase.child("User");
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        String email = edtEmail.getText().toString();
        final String password = edtPassword.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        {
            progressBar.setVisibility(View.VISIBLE);
            //authenticate user
            if (email.contains("nv_") || email.contains("nv")) { //nhan vien
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isLogin=false;
                        for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                            Staff staff = noteSnapshot.getValue(Staff.class);
                            progressBar.setVisibility(View.GONE);
                            if (staff.getNameNhanVien().equals(edtEmail.getText().toString())&&staff.getPassword().equals(edtPassword.getText().toString())) {
                                SharePreferenceUtils.insertStringData(LoginActivity.this, Const.KEY_NAME, edtEmail.getText().toString());
                                SharePreferenceUtils.insertObjDataStaff(LoginActivity.this, Const.KEY_STAFF, staff);
                                isLogin=true;
                                Intent intent = new Intent(LoginActivity.this, WaiterActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            } else {
                               isLogin=false;
                            }
                        }
                        if(!isLogin){
                            Toast.makeText(LoginActivity.this, "mật khẩu và tên tài khoản của bạn không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "mật khẩu và tên tài khoản của bạn không chính xác", Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (email.contains("daubep") || email.contains("daubep_")) { //dau bep
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE); //khi dowload thi se hien vong xoay(load du lieu)
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edtPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    SharePreferenceUtils.insertStringData(LoginActivity.this, Const.KEY_NAME, edtEmail.getText().toString());
                                    Intent intent = new Intent(LoginActivity.this, CookerActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            } else {
                //quan ly
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edtPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    SharePreferenceUtils.insertStringData(LoginActivity.this, Const.KEY_NAME, edtEmail.getText().toString());
                                    Intent intent = new Intent(LoginActivity.this, ManagerMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        }
    }
}
