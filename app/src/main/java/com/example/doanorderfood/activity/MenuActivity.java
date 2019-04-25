package com.example.doanorderfood.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doanorderfood.R;
import com.example.doanorderfood.adapter.ListviewMenuAdapter;
import com.example.doanorderfood.model.ItemMenu;
import com.example.doanorderfood.util.Const;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.btnBackMenu)
    ImageButton btnBackMenu;
    @BindView(R.id.btnGoToBill)
    TextView btnGoToBill;
    @BindView(R.id.toolbarMenu)
    Toolbar toolbarMenu;
    @BindView(R.id.radioButton_drink)
    RadioButton radioButtonDrink;
    @BindView(R.id.radioButton_food)
    RadioButton radioButtonFood;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.lvMenu)
    ListView lvMenu;
    private ImageButton btnBack;
    private TextView tvGoToBill;
    private ArrayList<String> arrItemSpinnerDrink = new ArrayList<>();
    private ArrayList<String> arrItemSpinnerFood = new ArrayList<>();


    private ArrayAdapter<String> adapter;
    private ArrayList<ItemMenu> drink = new ArrayList<>();
    private ArrayList<ItemMenu> food = new ArrayList<>();

    private ArrayList<ItemMenu> arrItemChecked = new ArrayList<>();

    private boolean checkDrink = true;

    private String drinkLast;
    private String foodLast;
    private String numPeo;
    private ListviewMenuAdapter listviewMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        creatListDrink();
        creatListFood();
        initViews();
        initData();
    }

    private void creatListFood() {
        food.clear();
        for (int i = 0; i < 5; i++) {
            food.add(new ItemMenu("food", "Ga", "30000", "kg",
                    "1",
                    "https://tea-4.lozi.vn/v1/images/resized/tokkboki-gimbap-com-cari-ga-com-tron-6H0HLlNiAXxz0Xf7-134655-1473354329?w=480&type=o"));

            if (!arrItemSpinnerFood.toString().contains("food"))
                arrItemSpinnerFood.add("food");
        }
    }

    private void creatListDrink() {
        drink.clear();
        for (int i = 0; i < 8; i++) {
            drink.add(new ItemMenu("drink", "Nuoc Cam", "30000", "ml",
                    "1",
                    "https://kenh14cdn.com/2018/12/11/photo-1-1544498821657955357020.jpg"));
            if (!arrItemSpinnerDrink.toString().contains("drink"))
                arrItemSpinnerDrink.add("drink");
        }
        adapter = new ArrayAdapter<String>(MenuActivity.this
                , android.R.layout.simple_spinner_item, arrItemSpinnerDrink);
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyProcessEvent());
    }

    private void initData() {

    }

    private void initViews() {
        numPeo = getIntent().getStringExtra(Const.KEY_NUMBER_PEOPLE);
        setSupportActionBar(toolbarMenu);

        listviewMenuAdapter = new ListviewMenuAdapter(MenuActivity.this, R.layout.item_listview_menu, drink);
        lvMenu.setAdapter(listviewMenuAdapter);
        listviewMenuAdapter.notifyDataSetChanged();
        btnBack = findViewById(R.id.btnBackMenu);
        tvGoToBill = findViewById(R.id.btnGoToBill);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvGoToBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < drink.size(); i++) {
                    if (drink.get(i).getCount() != 0) {
                        arrItemChecked.add(drink.get(i));
                    }
                }

                for (int i = 0; i < food.size(); i++) {
                    if (food.get(i).getCount() != 0) {
                        arrItemChecked.add(food.get(i));
                    }
                }
                if (BillActivity.CHECK_START_MENU) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", arrItemChecked);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Intent intent = new Intent(MenuActivity.this, BillActivity.class);
                    intent.putExtra("list", arrItemChecked);
                    intent.putExtra("numPeo", numPeo);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                    finish();
                }
            }
        });

        radioButtonDrink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listviewMenuAdapter = new ListviewMenuAdapter(MenuActivity.this, R.layout.item_listview_menu, food);
                lvMenu.setAdapter(listviewMenuAdapter);
                listviewMenuAdapter.notifyDataSetChanged();
                checkDrink = false;

                adapter = new ArrayAdapter<String>(MenuActivity.this
                        , android.R.layout.simple_spinner_item, arrItemSpinnerFood);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_list_item_single_choice);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new MyProcessEvent());
            }
        });

        radioButtonFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ArrayList<ItemMenu> arr = new ArrayList<>();
                for (int j = 0; j < drink.size(); j++) {
                    if (drinkLast.equals(drink.get(j).getGroup())) {
                        arr.add(drink.get(j));
                    }
                }
                listviewMenuAdapter = new ListviewMenuAdapter(MenuActivity.this, R.layout.item_listview_menu, arr);
                lvMenu.setAdapter(listviewMenuAdapter);
                listviewMenuAdapter.notifyDataSetChanged();
                checkDrink = true;

                adapter = new ArrayAdapter<String>(MenuActivity.this
                        , android.R.layout.simple_spinner_item, arrItemSpinnerDrink);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_list_item_single_choice);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new MyProcessEvent());
            }
        });
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (checkDrink) {
                drinkLast = arrItemSpinnerDrink.get(i);
                ArrayList<ItemMenu> arr = new ArrayList<>();
                for (int j = 0; j < drink.size(); j++) {
                    if (arrItemSpinnerDrink.get(i).equals(drink.get(j).getGroup())) {
                        arr.add(drink.get(j));
                    }
                }
                listviewMenuAdapter = new ListviewMenuAdapter(MenuActivity.this, R.layout.item_listview_menu, arr);
                lvMenu.setAdapter(listviewMenuAdapter);
                listviewMenuAdapter.notifyDataSetChanged();
            } else {
                foodLast = arrItemSpinnerFood.get(i);
                ArrayList<ItemMenu> arr = new ArrayList<>();
                for (int j = 0; j < food.size(); j++) {
                    if (arrItemSpinnerFood.get(i).equals(food.get(j).getGroup())) {
                        arr.add(food.get(j));
                    }
                }
                listviewMenuAdapter = new ListviewMenuAdapter(MenuActivity.this, R.layout.item_listview_menu, arr);
                lvMenu.setAdapter(listviewMenuAdapter);
                listviewMenuAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (checkDrink) {
                ArrayList<ItemMenu> arr = new ArrayList<>();
                for (int j = 0; j < drink.size(); j++) {
                    if (arrItemSpinnerDrink.get(0).equals(drink.get(j).getGroup())) {
                        arr.add(drink.get(j));
                    }
                }
                listviewMenuAdapter = new ListviewMenuAdapter(MenuActivity.this, R.layout.item_listview_menu, arr);
                lvMenu.setAdapter(listviewMenuAdapter);
                listviewMenuAdapter.notifyDataSetChanged();
            }
        }
    }
}
