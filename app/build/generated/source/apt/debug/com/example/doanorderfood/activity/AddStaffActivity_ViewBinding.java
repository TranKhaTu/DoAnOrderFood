// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddStaffActivity_ViewBinding implements Unbinder {
  private AddStaffActivity target;

  private View view2131230779;

  private View view2131230780;

  private View view2131230778;

  private View view2131230781;

  private View view2131230786;

  @UiThread
  public AddStaffActivity_ViewBinding(AddStaffActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddStaffActivity_ViewBinding(final AddStaffActivity target, View source) {
    this.target = target;

    View view;
    target.edtIdStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtIdStaffAddStaff, "field 'edtIdStaffAddStaff'", EditText.class);
    target.edtNameStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtNameStaffAddStaff, "field 'edtNameStaffAddStaff'", EditText.class);
    target.radioButtonMaleAddStaff = Utils.findRequiredViewAsType(source, R.id.radioButton_male_AddStaff, "field 'radioButtonMaleAddStaff'", RadioButton.class);
    target.radioButtonFemaleAddStaff = Utils.findRequiredViewAsType(source, R.id.radioButton_female_AddStaff, "field 'radioButtonFemaleAddStaff'", RadioButton.class);
    target.groupSex = Utils.findRequiredViewAsType(source, R.id.groupSex, "field 'groupSex'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.btnChooseDateOfBirthAddStaff, "field 'btnChooseDateOfBirthAddStaff' and method 'onViewClicked'");
    target.btnChooseDateOfBirthAddStaff = Utils.castView(view, R.id.btnChooseDateOfBirthAddStaff, "field 'btnChooseDateOfBirthAddStaff'", Button.class);
    view2131230779 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.edtAddressStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtAddressStaffAddStaff, "field 'edtAddressStaffAddStaff'", EditText.class);
    target.edtPhoneStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtPhoneStaffAddStaff, "field 'edtPhoneStaffAddStaff'", EditText.class);
    target.radioButtonQLAddStaff = Utils.findRequiredViewAsType(source, R.id.radioButton_QL_AddStaff, "field 'radioButtonQLAddStaff'", RadioButton.class);
    target.radioButtonBBAddStaff = Utils.findRequiredViewAsType(source, R.id.radioButton_BB_AddStaff, "field 'radioButtonBBAddStaff'", RadioButton.class);
    target.groupPosition = Utils.findRequiredViewAsType(source, R.id.groupPosition, "field 'groupPosition'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.btnChooseDateStartAddStaff, "field 'btnChooseDateStartAddStaff' and method 'onViewClicked'");
    target.btnChooseDateStartAddStaff = Utils.castView(view, R.id.btnChooseDateStartAddStaff, "field 'btnChooseDateStartAddStaff'", Button.class);
    view2131230780 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.edtSalaryStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtSalaryStaffAddStaff, "field 'edtSalaryStaffAddStaff'", EditText.class);
    target.edtUserStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtUserStaffAddStaff, "field 'edtUserStaffAddStaff'", EditText.class);
    target.edtPassStaffAddStaff = Utils.findRequiredViewAsType(source, R.id.edtPassStaffAddStaff, "field 'edtPassStaffAddStaff'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnChooseAvatarAddStaff, "field 'btnChooseAvatarAddStaff' and method 'onViewClicked'");
    target.btnChooseAvatarAddStaff = Utils.castView(view, R.id.btnChooseAvatarAddStaff, "field 'btnChooseAvatarAddStaff'", Button.class);
    view2131230778 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.imAvatarAddStaff = Utils.findRequiredViewAsType(source, R.id.imAvatarAddStaff, "field 'imAvatarAddStaff'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnDoneAddStaff, "field 'btnDoneAddStaff' and method 'onViewClicked'");
    target.btnDoneAddStaff = Utils.castView(view, R.id.btnDoneAddStaff, "field 'btnDoneAddStaff'", Button.class);
    view2131230781 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnExitAddStaff, "field 'btnExitAddStaff' and method 'onViewClicked'");
    target.btnExitAddStaff = Utils.castView(view, R.id.btnExitAddStaff, "field 'btnExitAddStaff'", Button.class);
    view2131230786 = view;
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
    AddStaffActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtIdStaffAddStaff = null;
    target.edtNameStaffAddStaff = null;
    target.radioButtonMaleAddStaff = null;
    target.radioButtonFemaleAddStaff = null;
    target.groupSex = null;
    target.btnChooseDateOfBirthAddStaff = null;
    target.edtAddressStaffAddStaff = null;
    target.edtPhoneStaffAddStaff = null;
    target.radioButtonQLAddStaff = null;
    target.radioButtonBBAddStaff = null;
    target.groupPosition = null;
    target.btnChooseDateStartAddStaff = null;
    target.edtSalaryStaffAddStaff = null;
    target.edtUserStaffAddStaff = null;
    target.edtPassStaffAddStaff = null;
    target.btnChooseAvatarAddStaff = null;
    target.imAvatarAddStaff = null;
    target.btnDoneAddStaff = null;
    target.btnExitAddStaff = null;

    view2131230779.setOnClickListener(null);
    view2131230779 = null;
    view2131230780.setOnClickListener(null);
    view2131230780 = null;
    view2131230778.setOnClickListener(null);
    view2131230778 = null;
    view2131230781.setOnClickListener(null);
    view2131230781 = null;
    view2131230786.setOnClickListener(null);
    view2131230786 = null;
  }
}
