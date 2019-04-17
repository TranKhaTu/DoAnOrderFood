// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ManagerMainActivity_ViewBinding implements Unbinder {
  private ManagerMainActivity target;

  private View view2131230762;

  private View view2131230802;

  private View view2131230801;

  private View view2131230798;

  private View view2131230806;

  private View view2131230793;

  @UiThread
  public ManagerMainActivity_ViewBinding(ManagerMainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ManagerMainActivity_ViewBinding(final ManagerMainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.addStaff, "field 'addStaff' and method 'onViewClicked'");
    target.addStaff = Utils.castView(view, R.id.addStaff, "field 'addStaff'", ImageView.class);
    view2131230762 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.toolbarManager = Utils.findRequiredViewAsType(source, R.id.toolbarManager, "field 'toolbarManager'", Toolbar.class);
    target.lvStaff = Utils.findRequiredViewAsType(source, R.id.lvStaff, "field 'lvStaff'", ListView.class);
    view = Utils.findRequiredView(source, R.id.btnShowListStaffOnline, "field 'btnShowListStaffOnline' and method 'onViewClicked'");
    target.btnShowListStaffOnline = Utils.castView(view, R.id.btnShowListStaffOnline, "field 'btnShowListStaffOnline'", Button.class);
    view2131230802 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line1Manager = Utils.findRequiredView(source, R.id.line1Manager, "field 'line1Manager'");
    view = Utils.findRequiredView(source, R.id.btnShowListStaffOffline, "field 'btnShowListStaffOffline' and method 'onViewClicked'");
    target.btnShowListStaffOffline = Utils.castView(view, R.id.btnShowListStaffOffline, "field 'btnShowListStaffOffline'", Button.class);
    view2131230801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line2Manager = Utils.findRequiredView(source, R.id.line2Manager, "field 'line2Manager'");
    view = Utils.findRequiredView(source, R.id.btnShowAllStaff, "field 'btnShowAllStaff' and method 'onViewClicked'");
    target.btnShowAllStaff = Utils.castView(view, R.id.btnShowAllStaff, "field 'btnShowAllStaff'", Button.class);
    view2131230798 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.line3Manager = Utils.findRequiredView(source, R.id.line3Manager, "field 'line3Manager'");
    target.profileImageManager = Utils.findRequiredViewAsType(source, R.id.profile_imageManager, "field 'profileImageManager'", CircleImageView.class);
    target.tvNameManager = Utils.findRequiredViewAsType(source, R.id.tvNameManager, "field 'tvNameManager'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnStaffManager, "field 'btnStaffManager' and method 'onViewClicked'");
    target.btnStaffManager = Utils.castView(view, R.id.btnStaffManager, "field 'btnStaffManager'", Button.class);
    view2131230806 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnLogOutManager, "field 'btnLogOutManager' and method 'onViewClicked'");
    target.btnLogOutManager = Utils.castView(view, R.id.btnLogOutManager, "field 'btnLogOutManager'", Button.class);
    view2131230793 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.drawerLayoutManager = Utils.findRequiredViewAsType(source, R.id.drawerLayoutManager, "field 'drawerLayoutManager'", DrawerLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ManagerMainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addStaff = null;
    target.toolbarManager = null;
    target.lvStaff = null;
    target.btnShowListStaffOnline = null;
    target.line1Manager = null;
    target.btnShowListStaffOffline = null;
    target.line2Manager = null;
    target.btnShowAllStaff = null;
    target.line3Manager = null;
    target.profileImageManager = null;
    target.tvNameManager = null;
    target.btnStaffManager = null;
    target.btnLogOutManager = null;
    target.drawerLayoutManager = null;

    view2131230762.setOnClickListener(null);
    view2131230762 = null;
    view2131230802.setOnClickListener(null);
    view2131230802 = null;
    view2131230801.setOnClickListener(null);
    view2131230801 = null;
    view2131230798.setOnClickListener(null);
    view2131230798 = null;
    view2131230806.setOnClickListener(null);
    view2131230806 = null;
    view2131230793.setOnClickListener(null);
    view2131230793 = null;
  }
}
