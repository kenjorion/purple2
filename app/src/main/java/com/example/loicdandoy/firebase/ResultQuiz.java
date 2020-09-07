package com.example.loicdandoy.firebase;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.ButterKnife;

public class ResultQuiz extends AppCompatActivity {

    // Firebase
    DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;

    //Recette
    Recipe recipe = new Recipe();

    TextView textViewResultat;
    Button b_resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);

        ButterKnife.bind(this);

        // Configuring Toolbar
        this.configureToolbar();

        //Connection base de donnée
        mDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();

        textViewResultat = findViewById(R.id.textViewResultat);
        b_resultat = findViewById(R.id.buttonResult);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        final int correct = intent.getIntExtra("correct", 0);
        int incorrect = intent.getIntExtra("incorrect", 0);

        if(correct >= 3){
            textViewResultat.setText("Félicitation !\nVous avez débloqué une recette !!");
            b_resultat.setText("Accéder à la recette !");
            //Ajouter l'id de l'utilisateur à ceux qui on déverouillé les recettes
            mDatabase.child("Quiz").child(recipe.quizId).child("userValidate").child(uid).setValue(uid);
        } else {
            textViewResultat.setText("Dommage !\nVous n'avez pas réussi le quiz, réessayez une autre fois !");
            b_resultat.setText("Retour à la page d'accueil");
        }

        b_resultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct >= 3) {
                    Intent intent = new Intent(getApplicationContext(), PageDetail.class);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

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

}
