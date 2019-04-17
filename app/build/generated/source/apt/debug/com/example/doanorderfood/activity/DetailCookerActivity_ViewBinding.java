// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailCookerActivity_ViewBinding implements Unbinder {
  private DetailCookerActivity target;

  @UiThread
  public DetailCookerActivity_ViewBinding(DetailCookerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailCookerActivity_ViewBinding(DetailCookerActivity target, View source) {
    this.target = target;

    target.btnBackMenu = Utils.findRequiredViewAsType(source, R.id.btnBackMenu, "field 'btnBackMenu'", ImageButton.class);
    target.btnGoToBill = Utils.findRequiredViewAsType(source, R.id.btnGoToBill, "field 'btnGoToBill'", TextView.class);
    target.toolbarMenu = Utils.findRequiredViewAsType(source, R.id.toolbarMenu, "field 'toolbarMenu'", Toolbar.class);
    target.tvTableBill = Utils.findRequiredViewAsType(source, R.id.tvTableBill, "field 'tvTableBill'", TextView.class);
    target.tvPeopleBill = Utils.findRequiredViewAsType(source, R.id.tvPeopleBill, "field 'tvPeopleBill'", TextView.class);
    target.tvTimeBill = Utils.findRequiredViewAsType(source, R.id.tvTimeBill, "field 'tvTimeBill'", TextView.class);
    target.tvTotalBill = Utils.findRequiredViewAsType(source, R.id.tvTotalBill, "field 'tvTotalBill'", TextView.class);
    target.lvItemBill = Utils.findRequiredViewAsType(source, R.id.lvItemBill, "field 'lvItemBill'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailCookerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBackMenu = null;
    target.btnGoToBill = null;
    target.toolbarMenu = null;
    target.tvTableBill = null;
    target.tvPeopleBill = null;
    target.tvTimeBill = null;
    target.tvTotalBill = null;
    target.lvItemBill = null;
  }
}
