// Generated code from Butter Knife. Do not modify!
package com.example.doanorderfood.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.doanorderfood.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230810;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.edtEmail = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edtEmail'", EditText.class);
    target.edtPassword = Utils.findRequiredViewAsType(source, R.id.edt_password, "field 'edtPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btnLogin' and method 'onViewClicked'");
    target.btnLogin = Utils.castView(view, R.id.btn_login, "field 'btnLogin'", Button.class);
    view2131230810 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtEmail = null;
    target.edtPassword = null;
    target.btnLogin = null;
    target.progressBar = null;

    view2131230810.setOnClickListener(null);
    view2131230810 = null;
  }
}
