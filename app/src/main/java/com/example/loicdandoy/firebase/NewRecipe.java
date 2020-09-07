package com.example.loicdandoy.firebase;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loicdandoy.firebase.createQuiz.CreateQuiz1;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class NewRecipe extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    //RECETTE
    Recipe addRecipe = new Recipe();

    // STATIC DATA FOR PICTURE
    private static final String PERMS = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CHOOSE_PHOTO = 200;
    // Uri of image selected by user
    private Uri uriImageSelected;

    // Add Image Preview
    @BindView(R.id.imageViewRecipeImg) ImageView imageViewPreview;

    // Add TextEdit
    @BindView(R.id.editTextName) EditText addTextName;
    @BindView(R.id.editTextDescription) EditText addTextDescription;
    @BindView(R.id.editTextTime) EditText addTextTime;

    //Spinner
    @BindView(R.id.spinnerType)
    Spinner mSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private List<CharSequence> items;
    private String[] strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);

        ButterKnife.bind(this);

        // Configuring Toolbar
        this.configureToolbar();

        firebaseAuth = FirebaseAuth.getInstance();
        addRecipe.userId = firebaseAuth.getCurrentUser().getUid();
        addRecipe.username = firebaseAuth.getCurrentUser().getDisplayName();

        // Spinner
        strings = getResources().getStringArray(R.array.mealType);
        items = new ArrayList<CharSequence>(Arrays.asList(strings));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String meal = parent.getItemAtPosition(position).toString();
                addRecipe.meal_type = meal;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 6 - Calling the appropriate method after activity result
        this.handleResponse(requestCode, resultCode, data);
    }

    //-----------
    // ACTION
    //-----------

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.buttonAddImgRecipe)
    // 3 - Ask permission when accessing to this listener
    @AfterPermissionGranted(RC_IMAGE_PERMS)
    public void onClickAddFile() {
        this.chooseImageFromPhone();
    }

    @OnClick(R.id.buttonCreateRecipe)
    public void createRecipe() {

        addRecipe.quizId = UUID.randomUUID().toString();

        Date date = new Date();
        addRecipe.recipeDate = DateFormat.getDateTimeInstance().format(date);

        addRecipe.name = addTextName.getText().toString();
        addRecipe.description = addTextDescription.getText().toString();
        addRecipe.recipeTime = addTextTime.getText().toString();

        if(TextUtils.isEmpty(addRecipe.name) || TextUtils.isEmpty(addRecipe.description) || TextUtils.isEmpty(addRecipe.recipeTime) || uriImageSelected == null) {
            Toast.makeText(this, "Il faut remplir tous les champs !", Toast.LENGTH_LONG).show();
            return;
        } else {
            //lance la création du Quizz
            Intent intent = new Intent(getApplicationContext(), CreateQuiz1.class);
            intent.putExtra("recipe", addRecipe);
            intent.putExtra("imageUri", uriImageSelected);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    // --------------------
    // FILE MANAGEMENT
    // --------------------

    private void chooseImageFromPhone(){
        if (!EasyPermissions.hasPermissions(this, PERMS)) {
            EasyPermissions.requestPermissions(this, "Cette application a besoin de permission pour fonctionner.", RC_IMAGE_PERMS, PERMS);
            return;
        }
        // 3 - Launch an "Selection Image" Activity
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RC_CHOOSE_PHOTO);
    }

    // 4 - Handle activity response (after user has chosen or not a picture)
    private void handleResponse(int requestCode, int resultCode, Intent data){
        if (requestCode == RC_CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) { //SUCCESS
                this.uriImageSelected = data.getData();
                Glide.with(this) //SHOWING PREVIEW OF IMAGE
                        .load(this.uriImageSelected)
                        .into(this.imageViewPreview);
            } else {
                Toast.makeText(this, "Aucune image choisie !", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
