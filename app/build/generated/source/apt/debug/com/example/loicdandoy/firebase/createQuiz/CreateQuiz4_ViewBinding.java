// Generated code from Butter Knife. Do not modify!
package com.example.loicdandoy.firebase.createQuiz;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.loicdandoy.firebase.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateQuiz4_ViewBinding implements Unbinder {
  private CreateQuiz4 target;

  private View view2131296303;

  @UiThread
  public CreateQuiz4_ViewBinding(CreateQuiz4 target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreateQuiz4_ViewBinding(final CreateQuiz4 target, View source) {
    this.target = target;

    View view;
    target.addQuestion = Utils.findRequiredViewAsType(source, R.id.editTextQuestion, "field 'addQuestion'", EditText.class);
    target.addReponse1 = Utils.findRequiredViewAsType(source, R.id.editTextResponse1, "field 'addReponse1'", EditText.class);
    target.addReponse2 = Utils.findRequiredViewAsType(source, R.id.editTextResponse2, "field 'addReponse2'", EditText.class);
    target.addReponse3 = Utils.findRequiredViewAsType(source, R.id.editTextResponse3, "field 'addReponse3'", EditText.class);
    target.addReponse4 = Utils.findRequiredViewAsType(source, R.id.editTextResponse4, "field 'addReponse4'", EditText.class);
    target.mSpinner = Utils.findRequiredViewAsType(source, R.id.spinnerCorrect, "field 'mSpinner'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.buttonAddQuestion, "method 'b_addQuestion'");
    view2131296303 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.b_addQuestion();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateQuiz4 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addQuestion = null;
    target.addReponse1 = null;
    target.addReponse2 = null;
    target.addReponse3 = null;
    target.addReponse4 = null;
    target.mSpinner = null;

    view2131296303.setOnClickListener(null);
    view2131296303 = null;
  }
}
