// Generated code from Butter Knife. Do not modify!
package com.example.loicdandoy.firebase;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewRecipe_ViewBinding implements Unbinder {
  private NewRecipe target;

<<<<<<< HEAD
  private View view2131296302;

  private View view2131296304;
=======
  private View view2131361837;

  private View view2131361838;
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf

  @UiThread
  public NewRecipe_ViewBinding(NewRecipe target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewRecipe_ViewBinding(final NewRecipe target, View source) {
    this.target = target;

    View view;
    target.imageViewPreview = Utils.findRequiredViewAsType(source, R.id.imageViewRecipeImg, "field 'imageViewPreview'", ImageView.class);
    target.addTextName = Utils.findRequiredViewAsType(source, R.id.editTextName, "field 'addTextName'", EditText.class);
    target.addTextDescription = Utils.findRequiredViewAsType(source, R.id.editTextDescription, "field 'addTextDescription'", EditText.class);
    target.addTextTime = Utils.findRequiredViewAsType(source, R.id.editTextTime, "field 'addTextTime'", EditText.class);
    target.mSpinner = Utils.findRequiredViewAsType(source, R.id.spinnerType, "field 'mSpinner'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.buttonAddImgRecipe, "method 'onClickAddFile'");
<<<<<<< HEAD
    view2131296302 = view;
=======
    view2131361837 = view;
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickAddFile();
      }
    });
    view = Utils.findRequiredView(source, R.id.buttonCreateRecipe, "method 'createRecipe'");
<<<<<<< HEAD
    view2131296304 = view;
=======
    view2131361838 = view;
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.createRecipe();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    NewRecipe target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageViewPreview = null;
    target.addTextName = null;
    target.addTextDescription = null;
    target.addTextTime = null;
    target.mSpinner = null;

<<<<<<< HEAD
    view2131296302.setOnClickListener(null);
    view2131296302 = null;
    view2131296304.setOnClickListener(null);
    view2131296304 = null;
=======
    view2131361837.setOnClickListener(null);
    view2131361837 = null;
    view2131361838.setOnClickListener(null);
    view2131361838 = null;
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
  }
}
