// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CookerActivity_ViewBinding implements Unbinder {
  private CookerActivity target;

  private View view2131230799;

  private View view2131230800;

  private View view2131230797;

  private View view2131230806;

  private View view2131230793;

  @UiThread
  public CookerActivity_ViewBinding(CookerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CookerActivity_ViewBinding(final CookerActivity target, View source) {
    this.target = target;

    View view;
    target.addStaff = Utils.findRequiredViewAsType(source, R.id.addStaff, "field 'addStaff'", ImageView.class);
    target.toolbarManager = Utils.findRequiredViewAsType(source, R.id.toolbarManager, "field 'toolbarManager'", Toolbar.class);
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
    CookerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addStaff = null;
    target.toolbarManager = null;
    target.rcvCooker = null;
    target.btnShowListChuaCheBien = null;
    target.line1Manager = null;
    target.btnShowListDaCheBien = null;
    target.line2Manager = null;
    target.btnShowAllCooker = null;
    target.line3Manager = null;
    target.profileImageManager = null;
    target.tvNameManager = null;
    target.btnStaffManager = null;
    target.btnLogOutManager = null;
    target.drawerLayoutManager = null;

    view2131230799.setOnClickListener(null);
    view2131230799 = null;
    view2131230800.setOnClickListener(null);
    view2131230800 = null;
    view2131230797.setOnClickListener(null);
    view2131230797 = null;
    view2131230806.setOnClickListener(null);
    view2131230806 = null;
    view2131230793.setOnClickListener(null);
    view2131230793 = null;
  }
}
