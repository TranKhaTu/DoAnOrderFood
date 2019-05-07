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
import android.widget.Toast;

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

        food.add(new ItemMenu("food", "Gà", "30000", "kg",
                "1",
                "http://st.galaxypub.vn/staticFile/Subject/2017/11/08/thit-ga_81528901.jpg", 0));

        if (!arrItemSpinnerFood.toString().contains("food"))
            arrItemSpinnerFood.add("food");

        food.add(new ItemMenu("food", "Thịt lợn", "40000", "kg",
                "1",
                "https://image.plo.vn/w653/Uploaded/2019/ycivopcg/2018_08_08/hinh-thit-heo-nhap-gia-re1_zaxb.jpg", 0));

        if (!arrItemSpinnerFood.toString().contains("food"))
            arrItemSpinnerFood.add("food");

        food.add(new ItemMenu("food", "Thịt vịt", "80000", "kg",
                "1",
                "https://media.cooky.vn/recipe/g2/14351/s800x500/recipe14351-635705816237136188.jpg", 0));

        if (!arrItemSpinnerFood.toString().contains("food"))
            arrItemSpinnerFood.add("food");
        food.add(new ItemMenu("food", "Cá trắm", "100000", "kg",
                "1",
                "http://cahoangde.com/wp-content/uploads/2017/12/563a0fd1525c139c4b2ef13cc462a2d8-600x399.jpg", 0));

        if (!arrItemSpinnerFood.toString().contains("food"))
            arrItemSpinnerFood.add("food");
        food.add(new ItemMenu("food", "Cá thu", "200000", "kg",
                "1",
                "data:image/jpeg;base64,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", 0));

        if (!arrItemSpinnerFood.toString().contains("food"))
            arrItemSpinnerFood.add("food");

    }

    private void creatListDrink() {
        drink.clear();
        drink.add(new ItemMenu("drink", "Nuoc Cam", "30000", "ml",
                "1",
                "https://kenh14cdn.com/2018/12/11/photo-1-1544498821657955357020.jpg", 0));
        if (!arrItemSpinnerDrink.toString().contains("drink"))
            arrItemSpinnerDrink.add("drink");

        drink.add(new ItemMenu("drink", "Nuoc mía", "10000", "ml",
                "1",
                "https://media.cooky.vn/recipe/g4/30792/s800x500/cooky-recipe-cover-r30792.png", 0));
        if (!arrItemSpinnerDrink.toString().contains("drink"))
            arrItemSpinnerDrink.add("drink");

        drink.add(new ItemMenu("drink", "Nuoc bơ", "40000", "ml",
                "1",
                "http://www.nuocda.com/wp-content/uploads/2016/08/sinh-to-bo-voi-nuoc-cot-dua-ngon-tuyet-hao.jpg", 0));
        if (!arrItemSpinnerDrink.toString().contains("drink"))
            arrItemSpinnerDrink.add("drink");

        drink.add(new ItemMenu("drink", "Trà sữa", "50000", "ml",
                "1",
                "https://tea-2.lozi.vn/v1/images/resized/tra-sua-bac-ha-tran-chau-size-l-1476415559-813270-1476415559?w=480&type=o", 0));
        if (!arrItemSpinnerDrink.toString().contains("drink"))
            arrItemSpinnerDrink.add("drink");

        drink.add(new ItemMenu("drink", "Cafe", "30000", "ml",
                "1",
                "https://epicure.vn/wp-content/uploads/2017/08/cach-pha-cafe-de-ban-hang.jpg", 0));
        if (!arrItemSpinnerDrink.toString().contains("drink"))
            arrItemSpinnerDrink.add("drink");

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
                    if (arrItemChecked.size() > 0) {
                        Intent intent = new Intent(MenuActivity.this, BillActivity.class);
                        intent.putExtra("list", arrItemChecked);
                        intent.putExtra("numPeo", numPeo);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                        finish();
                    } else {
                        Toast.makeText(MenuActivity.this, "Vui lòng chọn đồ ăn", Toast.LENGTH_SHORT).show();
                    }
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
