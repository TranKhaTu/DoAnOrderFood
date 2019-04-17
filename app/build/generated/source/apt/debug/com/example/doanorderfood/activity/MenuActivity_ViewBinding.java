// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MenuActivity_ViewBinding implements Unbinder {
  private MenuActivity target;

  @UiThread
  public MenuActivity_ViewBinding(MenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MenuActivity_ViewBinding(MenuActivity target, View source) {
    this.target = target;

    target.btnBackMenu = Utils.findRequiredViewAsType(source, R.id.btnBackMenu, "field 'btnBackMenu'", ImageButton.class);
    target.btnGoToBill = Utils.findRequiredViewAsType(source, R.id.btnGoToBill, "field 'btnGoToBill'", TextView.class);
    target.toolbarMenu = Utils.findRequiredViewAsType(source, R.id.toolbarMenu, "field 'toolbarMenu'", Toolbar.class);
    target.radioButtonDrink = Utils.findRequiredViewAsType(source, R.id.radioButton_drink, "field 'radioButtonDrink'", RadioButton.class);
    target.radioButtonFood = Utils.findRequiredViewAsType(source, R.id.radioButton_food, "field 'radioButtonFood'", RadioButton.class);
    target.group = Utils.findRequiredViewAsType(source, R.id.group, "field 'group'", RadioGroup.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.spinner, "field 'spinner'", Spinner.class);
    target.lvMenu = Utils.findRequiredViewAsType(source, R.id.lvMenu, "field 'lvMenu'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnBackMenu = null;
    target.btnGoToBill = null;
    target.toolbarMenu = null;
    target.radioButtonDrink = null;
    target.radioButtonFood = null;
    target.group = null;
    target.spinner = null;
    target.lvMenu = null;
  }
}
