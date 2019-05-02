package com.example.doanorderfood.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanorderfood.R;
import com.example.doanorderfood.model.ItemMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewMenuCookerAdapter extends RecyclerView.Adapter<ListViewMenuCookerAdapter.MyViewHolder> {
    private ArrayList<ItemMenu> itemMenus;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ListViewMenuCookerAdapter(ArrayList<ItemMenu> itemMenus, Context context, OnItemClickListener onItemClickListener) {
        this.itemMenus = itemMenus;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_cooker, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        ItemMenu itemMenu = itemMenus.get(position);
        Glide.with(context).load(itemMenu.getImage()).into(myViewHolder.imgFood);
        myViewHolder.tvNameFood.setText("Tên món ăn: " + itemMenu.getName());
        myViewHolder.tvNumberFood.setText("Số lượng: " + itemMenu.getCount() + "");
    }

    @Override
    public int getItemCount() {
        return itemMenus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_food)
        ImageView imgFood;
        @BindView(R.id.tv_name_food)
        TextView tvNameFood;
        @BindView(R.id.tv_number_food)
        TextView tvNumberFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClickItem(int position);
    }

}
