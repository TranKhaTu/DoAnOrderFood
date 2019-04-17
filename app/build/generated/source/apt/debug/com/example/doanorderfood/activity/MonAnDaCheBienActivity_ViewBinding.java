// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MonAnDaCheBienActivity_ViewBinding implements Unbinder {
  private MonAnDaCheBienActivity target;

  private View view2131230799;

  private View view2131230800;

  private View view2131230797;

  @UiThread
  public MonAnDaCheBienActivity_ViewBinding(MonAnDaCheBienActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MonAnDaCheBienActivity_ViewBinding(final MonAnDaCheBienActivity target, View source) {
    this.target = target;

    View view;
    target.btnPushBill = Utils.findRequiredViewAsType(source, R.id.btnPushBill, "field 'btnPushBill'", ImageView.class);
    target.btnAddItem = Utils.findRequiredViewAsType(source, R.id.btnAddItem, "field 'btnAddItem'", ImageView.class);
    target.btnPrintBill = Utils.findRequiredViewAsType(source, R.id.btnPrintBill, "field 'btnPrintBill'", ImageView.class);
    target.toolbarBill = Utils.findRequiredViewAsType(source, R.id.toolbarBill, "field 'toolbarBill'", Toolbar.class);
    target.rcvCooker = Utils.findRequiredViewAsType(source, R.id.rcv_cooker, "field 'rcvCooker'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btnShowListChuaCheBien, "field 'btnShowListChuaCheBien' and method 'onViewClicked'");
    target.btnShowListChuaCheBien = Utils.castView(view, R.id.btnShowListChuaCheBien, "field 'btnShowListChuaCheBien'", Button.class);
    view2131230799 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line1Manager = Utils.findRequiredView(source, R.id.line1Manager, "field 'line1Manager'");
    view = Utils.findRequiredView(source, R.id.btnShowListDaCheBien, "field 'btnShowListDaCheBien' and method 'onViewClicked'");
    target.btnShowListDaCheBien = Utils.castView(view, R.id.btnShowListDaCheBien, "field 'btnShowListDaCheBien'", Button.class);
    view2131230800 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line2Manager = Utils.findRequiredView(source, R.id.line2Manager, "field 'line2Manager'");
    view = Utils.findRequiredView(source, R.id.btnShowAllCooker, "field 'btnShowAllCooker' and method 'onViewClicked'");
    target.btnShowAllCooker = Utils.castView(view, R.id.btnShowAllCooker, "field 'btnShowAllCooker'", Button.class);
    view2131230797 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line3Manager = Utils.findRequiredView(source, R.id.line3Manager, "field 'line3Manager'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MonAnDaCheBienActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnPushBill = null;
    target.btnAddItem = null;
    target.btnPrintBill = null;
    target.toolbarBill = null;
    target.rcvCooker = null;
    target.btnShowListChuaCheBien = null;
    target.line1Manager = null;
    target.btnShowListDaCheBien = null;
    target.line2Manager = null;
    target.btnShowAllCooker = null;
    target.line3Manager = null;

    view2131230799.setOnClickListener(null);
    view2131230799 = null;
    view2131230800.setOnClickListener(null);
    view2131230800 = null;
    view2131230797.setOnClickListener(null);
    view2131230797 = null;
  }
}
