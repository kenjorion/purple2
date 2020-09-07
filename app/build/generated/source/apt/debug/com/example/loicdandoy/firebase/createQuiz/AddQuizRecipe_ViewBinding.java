// Generated code from Butter Knife. Do not modify!
package com.example.loicdandoy.firebase.createQuiz;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.loicdandoy.firebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddQuizRecipe_ViewBinding implements Unbinder {
  private AddQuizRecipe target;

  private View view2131296311;

  @UiThread
  public AddQuizRecipe_ViewBinding(AddQuizRecipe target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddQuizRecipe_ViewBinding(final AddQuizRecipe target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.buttonResult, "method 'retour'");
    view2131296311 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.retour();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131296311.setOnClickListener(null);
    view2131296311 = null;
  }
}
