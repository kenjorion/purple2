package com.example.loicdandoy.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;

public class DoQuiz extends AppCompatActivity {

    // FIREBASE
    private DatabaseReference mDatabase;

    //QUESTION EN COURS
    Question questionCurrent = new Question();
    //RECIPE
    Recipe recipe = new Recipe();

    //bouton et text
    // Capture the layout's TextView and set the string as its text
    TextView textViewLabel;
    Button b_reponse1, b_reponse2, b_reponse3, b_reponse4;

    int reponseCorrect = 0, reponseFausses = 0, i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_quiz);

        ButterKnife.bind(this);

        // Configuring Toolbar
        this.configureToolbar();

        //Connection base de donnée
        mDatabase = FirebaseDatabase.getInstance().getReference();

        textViewLabel = findViewById(R.id.textViewQuestion);
        b_reponse1 = findViewById(R.id.buttonReponse1);
        b_reponse2 = findViewById(R.id.buttonReponse2);
        b_reponse3 = findViewById(R.id.buttonReponse3);
        b_reponse4 = findViewById(R.id.buttonReponse4);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");

        final String quizId = recipe.quizId;

        while (i < 2) {
            getQuestion(quizId, i);
            i++;
        }

        b_reponse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionCurrent.reponse1.equals(questionCurrent.correct)) {
                    reponseCorrect++;
                    Toast.makeText(getApplicationContext(),
                            "Correct !!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    reponseFausses++;
                    Toast.makeText(getApplicationContext(),
                            "Incorrect !!",
                            Toast.LENGTH_SHORT).show();
                }
                if (i < 5) {
                    getQuestion(quizId, i);
                    i++;
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultQuiz.class);
                    intent.putExtra("correct", reponseCorrect);
                    intent.putExtra("incorrect", reponseFausses);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                    finish();
                }
            }
        });

        b_reponse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionCurrent.reponse2.equals(questionCurrent.correct)) {
                    reponseCorrect++;
                    Toast.makeText(getApplicationContext(),
                            "Correct !!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    reponseFausses++;
                    Toast.makeText(getApplicationContext(),
                            "Incorrect !!",
                            Toast.LENGTH_SHORT).show();
                }
                if (i < 5) {
                    getQuestion(quizId, i);
                    i++;
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultQuiz.class);
                    intent.putExtra("correct", reponseCorrect);
                    intent.putExtra("incorrect", reponseFausses);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                    finish();
                }
            }
        });

        b_reponse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionCurrent.reponse3.equals(questionCurrent.correct)) {
                    reponseCorrect++;
                    Toast.makeText(getApplicationContext(),
                            "Correct !!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    reponseFausses++;
                    Toast.makeText(getApplicationContext(),
                            "Incorrect !!",
                            Toast.LENGTH_SHORT).show();
                }
                if (i < 5) {
                    getQuestion(quizId, i);
                    i++;
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultQuiz.class);
                    intent.putExtra("correct", reponseCorrect);
                    intent.putExtra("incorrect", reponseFausses);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                    finish();
                }
            }
        });

        b_reponse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionCurrent.reponse4.equals(questionCurrent.correct)) {
                    reponseCorrect++;
                    Toast.makeText(getApplicationContext(),
                            "Correct !!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    reponseFausses++;
                    Toast.makeText(getApplicationContext(),
                            "Incorrect !!",
                            Toast.LENGTH_SHORT).show();
                }
                if (i < 5) {
                    getQuestion(quizId, i);
                    i++;
                } else {
                    Intent intent = new Intent(getApplicationContext(), ResultQuiz.class);
                    intent.putExtra("correct", reponseCorrect);
                    intent.putExtra("incorrect", reponseFausses);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void getQuestion(final String quizId, int i) {
        mDatabase.child("Quiz").child(quizId).child("question" + i).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        // Get Quiz value
                        questionCurrent = dataSnapshot2.getValue(Question.class);

                        // [START_EXCLUDE]
                        if (questionCurrent == null) {
                            // User is null, error out
                            Log.e("DoQuiz", "User " + quizId + " is unexpectedly null");
                            Toast.makeText(getApplicationContext(),
                                    "Error: could not fetch .",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            textViewLabel.setText(questionCurrent.question);
                            b_reponse1.setText(questionCurrent.reponse1);
                            b_reponse2.setText(questionCurrent.reponse2);
                            b_reponse3.setText(questionCurrent.reponse3);
                            b_reponse4.setText(questionCurrent.reponse4);
                        }

                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError2) {
                        Log.w("DoQuiz", "getUser:onCancelled", databaseError2.toException());
                        // [START_EXCLUDE]
                        Toast.makeText(getApplicationContext(),
                                "Error",
                                Toast.LENGTH_SHORT).show();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END single_value_read]

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

