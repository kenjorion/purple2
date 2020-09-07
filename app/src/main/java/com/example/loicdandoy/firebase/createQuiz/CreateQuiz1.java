package com.example.loicdandoy.firebase.createQuiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.loicdandoy.firebase.Question;
import com.example.loicdandoy.firebase.R;
import com.example.loicdandoy.firebase.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuiz1 extends AppCompatActivity {

    //IMAGE URI
    Uri imageUri;
    //RECETTE
    Recipe recipe = new Recipe();
    //QUESTION
    Question question1 = new Question();

    // Add TextEdit
    @BindView(R.id.editTextQuestion)
    EditText addQuestion;
    @BindView(R.id.editTextResponse1)
    EditText addReponse1;
    @BindView(R.id.editTextResponse2)
    EditText addReponse2;
    @BindView(R.id.editTextResponse3)
    EditText addReponse3;
    @BindView(R.id.editTextResponse4)
    EditText addReponse4;

    //Spinner
    @BindView(R.id.spinnerCorrect)
    Spinner mSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private List<CharSequence> items;
    private String[] strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_quiz);

        ButterKnife.bind(this);

        // Configuring Toolbar
        this.configureToolbar();

        TextView textViewLabel = findViewById(R.id.textViewTitle);
        textViewLabel.setText("Question 1");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        imageUri = intent.getParcelableExtra("imageUri");

        // Spinner
        strings = getResources().getStringArray(R.array.Correction);
        items = new ArrayList<CharSequence>(Arrays.asList(strings));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String correction = parent.getItemAtPosition(position).toString();
                question1.correct = correction;
                Toast.makeText(getApplicationContext(), correction, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @OnClick(R.id.buttonAddQuestion)
    public void b_addQuestion() {
        createQuestion();
        if (question1.correct.equals(question1.reponse1) || question1.correct.equals(question1.reponse2) || question1.correct.equals(question1.reponse3) || question1.correct.equals(question1.reponse4)){
            Intent intent = new Intent(this, CreateQuiz2.class);
            intent.putExtra("recipe", recipe);
            intent.putExtra("imageUri", imageUri);
            intent.putExtra("question1", question1);
            startActivity(intent);
            finish();
        }
    }

    public void createQuestion(){
        question1.question = addQuestion.getText().toString();
        question1.reponse1 = addReponse1.getText().toString();
        question1.reponse2 = addReponse2.getText().toString();
        question1.reponse3 = addReponse3.getText().toString();
        question1.reponse4 = addReponse4.getText().toString();
        switch (question1.correct){
            case "Réponse 1":
                question1.correct = question1.reponse1;
                break;
            case "Réponse 2":
                question1.correct = question1.reponse2;
                break;
            case "Réponse 3":
                question1.correct = question1.reponse3;
                break;
            case "Réponse 4":
                question1.correct = question1.reponse4;
                break;
        }
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
