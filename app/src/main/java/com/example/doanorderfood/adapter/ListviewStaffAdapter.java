package com.example.doanorderfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanorderfood.R;
import com.example.doanorderfood.model.Staff;

import java.util.ArrayList;

public class ListviewStaffAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Staff> arrItem;


    public ListviewStaffAdapter(Context context, int layout, ArrayList<Staff> arrItem) {
        this.context = context;
        this.layout = layout;
        this.arrItem = arrItem;
    }

    private class ViewHolder {
        TextView tvName;
        TextView tvID;
        TextView tvAge;
        ImageView imCheckOnline;

    }

    @Override
    public int getCount() {
        return arrItem.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewRow = view;
        if (viewRow == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflater.inflate(layout, viewGroup, false);
            ListviewStaffAdapter.ViewHolder viewHolder = new ListviewStaffAdapter.ViewHolder();

            viewHolder.tvName = viewRow.findViewById(R.id.tvNameStaff);
            viewHolder.tvID = viewRow.findViewById(R.id.tvID);
            viewHolder.tvAge = viewRow.findViewById(R.id.tvAge);
            viewHolder.imCheckOnline = viewRow.findViewById(R.id.checkOnline);

            viewRow.setTag(viewHolder);
        }

        final Staff staff = arrItem.get(i);
        final ListviewStaffAdapter.ViewHolder viewHolder = (ListviewStaffAdapter.ViewHolder) viewRow.getTag();
        viewHolder.tvName.setText("Tên :" + staff.getName());
        viewHolder.tvID.setText("Id :" + staff.getId());
        viewHolder.tvAge.setText("Ngày sinh  :" + staff.getDateOfBirth());
        if (staff.getCheckOnline() == 1) {
            viewHolder.imCheckOnline.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imCheckOnline.setVisibility(View.INVISIBLE);
        }

        return viewRow;
    }
}