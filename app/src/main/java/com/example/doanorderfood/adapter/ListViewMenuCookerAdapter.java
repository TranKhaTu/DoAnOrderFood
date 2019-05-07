package com.example.doanorderfood.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doanorderfood.R;
import com.example.doanorderfood.model.Cooker;
import com.example.doanorderfood.model.HistoryBill;
import com.example.doanorderfood.model.ItemMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewMenuCookerAdapter extends RecyclerView.Adapter<ListViewMenuCookerAdapter.MyViewHolder> {
    private ArrayList<HistoryBill> historyBills;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ListViewMenuCookerAdapter(ArrayList<HistoryBill> historyBills, Context context, OnItemClickListener onItemClickListener) {
        this.historyBills = historyBills;
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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        HistoryBill historyBill = historyBills.get(position);
        Glide.with(context).load(historyBill.getArrayList().get(0).getImage()).into(myViewHolder.imgFood);
        int total = 0;
        String food = "";
        for (int i = 0; i < historyBill.getArrayList().size(); i++) {
            total += historyBill.getArrayList().get(i).getCount();
            if (i == historyBill.getArrayList().size() - 1) {
                food += historyBill.getArrayList().get(i).getCount() + " " + historyBill.getArrayList().get(i).getName();
            } else
                food += historyBill.getArrayList().get(i).getCount() + " " + historyBill.getArrayList().get(i).getName() + ",";

        }
        myViewHolder.tvNumberFood.setText(total + "");
        myViewHolder.tvNameFood.setText(food);
        myViewHolder.tvNumberTable.setText(historyBill.getTable() + "");
        myViewHolder.tvTime.setText(historyBill.getTime() + "");
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickItem(position);
            }
        });
        if (historyBill.getType().equals("đẩy cho đầu bếp")) {
            myViewHolder.imCheck.setVisibility(View.INVISIBLE);
        } else if (historyBill.getType().equals("đã chế biến")) {
            myViewHolder.imCheck.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return historyBills.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_food)
        ImageView imgFood;
        @BindView(R.id.tv_name_food)
        TextView tvNameFood;
        @BindView(R.id.tv_number_food)
        TextView tvNumberFood;
        @BindView(R.id.imCheck)
        ImageView imCheck;
        @BindView(R.id.cardview)
        CardView cardView;
        @BindView(R.id.tv_number_table)
        TextView tvNumberTable;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onClickItem(int position);
    }

}
