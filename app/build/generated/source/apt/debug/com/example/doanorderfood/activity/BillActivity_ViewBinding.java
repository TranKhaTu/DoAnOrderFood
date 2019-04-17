// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BillActivity_ViewBinding implements Unbinder {
  private BillActivity target;

  @UiThread
  public BillActivity_ViewBinding(BillActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BillActivity_ViewBinding(BillActivity target, View source) {
    this.target = target;

    target.btnPushBill = Utils.findRequiredViewAsType(source, R.id.btnPushBill, "field 'btnPushBill'", ImageView.class);
    target.btnAddItem = Utils.findRequiredViewAsType(source, R.id.btnAddItem, "field 'btnAddItem'", ImageView.class);
    target.btnPrintBill = Utils.findRequiredViewAsType(source, R.id.btnPrintBill, "field 'btnPrintBill'", ImageView.class);
    target.toolbarBill = Utils.findRequiredViewAsType(source, R.id.toolbarBill, "field 'toolbarBill'", Toolbar.class);
    target.tvTableBill = Utils.findRequiredViewAsType(source, R.id.tvTableBill, "field 'tvTableBill'", TextView.class);
    target.tvPeopleBill = Utils.findRequiredViewAsType(source, R.id.tvPeopleBill, "field 'tvPeopleBill'", TextView.class);
    target.tvTimeBill = Utils.findRequiredViewAsType(source, R.id.tvTimeBill, "field 'tvTimeBill'", TextView.class);
    target.tvTotalBill = Utils.findRequiredViewAsType(source, R.id.tvTotalBill, "field 'tvTotalBill'", TextView.class);
    target.lvItemBill = Utils.findRequiredViewAsType(source, R.id.lvItemBill, "field 'lvItemBill'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BillActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnPushBill = null;
    target.btnAddItem = null;
    target.btnPrintBill = null;
    target.toolbarBill = null;
    target.tvTableBill = null;
    target.tvPeopleBill = null;
    target.tvTimeBill = null;
    target.tvTotalBill = null;
    target.lvItemBill = null;
  }
}
