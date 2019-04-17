// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import com.suke.widget.SwitchButton;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WaiterActivity_ViewBinding implements Unbinder {
  private WaiterActivity target;

  private View view2131230805;

  private View view2131230804;

  private View view2131230803;

  private View view2131230795;

  private View view2131230790;

  private View view2131230792;

  private View view2131230807;

  private View view2131230791;

  @UiThread
  public WaiterActivity_ViewBinding(WaiterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WaiterActivity_ViewBinding(final WaiterActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.gridview = Utils.findRequiredViewAsType(source, R.id.gridview, "field 'gridview'", GridView.class);
    view = Utils.findRequiredView(source, R.id.btnShowListTableFree, "field 'btnShowListTableFree' and method 'onViewClicked'");
    target.btnShowListTableFree = Utils.castView(view, R.id.btnShowListTableFree, "field 'btnShowListTableFree'", Button.class);
    view2131230805 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line1 = Utils.findRequiredView(source, R.id.line1, "field 'line1'");
    view = Utils.findRequiredView(source, R.id.btnShowListTableBooked, "field 'btnShowListTableBooked' and method 'onViewClicked'");
    target.btnShowListTableBooked = Utils.castView(view, R.id.btnShowListTableBooked, "field 'btnShowListTableBooked'", Button.class);
    view2131230804 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line2 = Utils.findRequiredView(source, R.id.line2, "field 'line2'");
    view = Utils.findRequiredView(source, R.id.btnShowListTable, "field 'btnShowListTable' and method 'onViewClicked'");
    target.btnShowListTable = Utils.castView(view, R.id.btnShowListTable, "field 'btnShowListTable'", Button.class);
    view2131230803 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line3 = Utils.findRequiredView(source, R.id.line3, "field 'line3'");
    target.profileImage = Utils.findRequiredViewAsType(source, R.id.profile_image, "field 'profileImage'", CircleImageView.class);
    target.tvNameStaff = Utils.findRequiredViewAsType(source, R.id.tvNameStaff, "field 'tvNameStaff'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnProfile, "field 'btnProfile' and method 'onViewClicked'");
    target.btnProfile = Utils.castView(view, R.id.btnProfile, "field 'btnProfile'", Button.class);
    view2131230795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnListHistoryBill, "field 'btnListHistoryBill' and method 'onViewClicked'");
    target.btnListHistoryBill = Utils.castView(view, R.id.btnListHistoryBill, "field 'btnListHistoryBill'", Button.class);
    view2131230790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnLogOut, "field 'btnLogOut' and method 'onViewClicked'");
    target.btnLogOut = Utils.castView(view, R.id.btnLogOut, "field 'btnLogOut'", Button.class);
    view2131230792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawerLayout, "field 'drawerLayout'", DrawerLayout.class);
    view = Utils.findRequiredView(source, R.id.btnStatus, "field 'btnStatus' and method 'onViewClicked'");
    target.btnStatus = Utils.castView(view, R.id.btnStatus, "field 'btnStatus'", Button.class);
    view2131230807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.switchOn = Utils.findRequiredViewAsType(source, R.id.switch_on, "field 'switchOn'", SwitchButton.class);
    target.cardview = Utils.findRequiredViewAsType(source, R.id.cardview, "field 'cardview'", CardView.class);
    view = Utils.findRequiredView(source, R.id.btnListHistoryThanhToan, "field 'btnListHistoryThanhToan' and method 'onViewClicked'");
    target.btnListHistoryThanhToan = Utils.castView(view, R.id.btnListHistoryThanhToan, "field 'btnListHistoryThanhToan'", Button.class);
    view2131230791 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    WaiterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.gridview = null;
    target.btnShowListTableFree = null;
    target.line1 = null;
    target.btnShowListTableBooked = null;
    target.line2 = null;
    target.btnShowListTable = null;
    target.line3 = null;
    target.profileImage = null;
    target.tvNameStaff = null;
    target.btnProfile = null;
    target.btnListHistoryBill = null;
    target.btnLogOut = null;
    target.drawerLayout = null;
    target.btnStatus = null;
    target.switchOn = null;
    target.cardview = null;
    target.btnListHistoryThanhToan = null;

    view2131230805.setOnClickListener(null);
    view2131230805 = null;
    view2131230804.setOnClickListener(null);
    view2131230804 = null;
    view2131230803.setOnClickListener(null);
    view2131230803 = null;
    view2131230795.setOnClickListener(null);
    view2131230795 = null;
    view2131230790.setOnClickListener(null);
    view2131230790 = null;
    view2131230792.setOnClickListener(null);
    view2131230792 = null;
    view2131230807.setOnClickListener(null);
    view2131230807 = null;
    view2131230791.setOnClickListener(null);
    view2131230791 = null;
  }
}
