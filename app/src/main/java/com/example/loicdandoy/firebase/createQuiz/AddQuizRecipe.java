package com.example.loicdandoy.firebase.createQuiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loicdandoy.firebase.MainActivity;
import com.example.loicdandoy.firebase.Question;
import com.example.loicdandoy.firebase.R;
import com.example.loicdandoy.firebase.Recipe;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQuizRecipe extends AppCompatActivity {

    // Firebase
    private DatabaseReference mDatabase;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    //IMAGE URI
    Uri imageUri;
    //RECETTE
    Recipe recipe = new Recipe();
    //QUESTION
    Question question1 = new Question();
    Question question2 = new Question();
    Question question3 = new Question();
    Question question4 = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);

        ButterKnife.bind(this);

        // Configuring Toolbar
        this.configureToolbar();

        //Connection base de donnée
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        TextView textView = findViewById(R.id.textViewResultat);
        textView.setText("Félicitation !\nVous venez d'ajouter une nouvelle recette !!");
        TextView textViewButton = findViewById(R.id.buttonResult);
        textViewButton.setText("Retour à la HomePage");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        imageUri = intent.getParcelableExtra("imageUri");
        question1 = intent.getParcelableExtra("question1");
        question2 = intent.getParcelableExtra("question2");
        question3 = intent.getParcelableExtra("question3");
        question4 = intent.getParcelableExtra("question4");

        if(question1 != null || question2 != null || question3 != null || question4 != null || recipe != null) {
            addRecipe();
            mDatabase.child("Quiz").child(recipe.quizId).child("question1").setValue(question1);
            mDatabase.child("Quiz").child(recipe.quizId).child("question2").setValue(question2);
            mDatabase.child("Quiz").child(recipe.quizId).child("question3").setValue(question3);
            mDatabase.child("Quiz").child(recipe.quizId).child("question4").setValue(question4);
            mDatabase.child("Quiz").child(recipe.quizId).child("userValidate").child(recipe.userId).setValue(true); //accès du créateur à sa recette
        } else {
            Toast.makeText(getApplicationContext(), "Votre recette n'a pas été créée, une erreur est survenue.", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.buttonResult)
    public void retour() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void configureToolbar() {
        //Get the toolbar (Serialise)
        Toolbar toolbar = findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //Ajout recette Firebase
    private void addRecipe() {

        if(imageUri != null)
        {

            final StorageReference ref = storageReference.child("img_recipes/"+ UUID.randomUUID().toString());
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // uri is your download path
                                            recipe.recipeImg = uri.toString();
                                            mDatabase.child("Recipes").child(UUID.randomUUID().toString()).setValue(recipe);
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    });
        }
    }

}
