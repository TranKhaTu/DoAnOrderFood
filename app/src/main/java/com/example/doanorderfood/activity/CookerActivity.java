package com.example.doanorderfood.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doanorderfood.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CookerActivity extends AppCompatActivity {

    @BindView(R.id.btnBackMenu)
    ImageButton btnBackMenu;
    @BindView(R.id.btnGoToBill)
    TextView btnGoToBill;
    @BindView(R.id.toolbarMenu)
    Toolbar toolbarMenu;
    @BindView(R.id.rcv_menu)
    RecyclerView rcvMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooker);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    private void initData() {

    }

    private void initViews() {

    }
}
