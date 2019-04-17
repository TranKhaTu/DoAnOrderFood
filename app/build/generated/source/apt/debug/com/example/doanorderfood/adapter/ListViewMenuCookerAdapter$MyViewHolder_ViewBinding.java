// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListViewMenuCookerAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private ListViewMenuCookerAdapter.MyViewHolder target;

  @UiThread
  public ListViewMenuCookerAdapter$MyViewHolder_ViewBinding(ListViewMenuCookerAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.imgFood = Utils.findRequiredViewAsType(source, R.id.img_food, "field 'imgFood'", ImageView.class);
    target.tvNameFood = Utils.findRequiredViewAsType(source, R.id.tv_name_food, "field 'tvNameFood'", TextView.class);
    target.tvNumberFood = Utils.findRequiredViewAsType(source, R.id.tv_number_food, "field 'tvNumberFood'", TextView.class);
    target.imCheck = Utils.findRequiredViewAsType(source, R.id.imCheck, "field 'imCheck'", ImageView.class);
    target.cardView = Utils.findRequiredViewAsType(source, R.id.cardview, "field 'cardView'", CardView.class);
    target.tvNumberTable = Utils.findRequiredViewAsType(source, R.id.tv_number_table, "field 'tvNumberTable'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListViewMenuCookerAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgFood = null;
    target.tvNameFood = null;
    target.tvNumberFood = null;
    target.imCheck = null;
    target.cardView = null;
    target.tvNumberTable = null;
    target.tvTime = null;
  }
}
