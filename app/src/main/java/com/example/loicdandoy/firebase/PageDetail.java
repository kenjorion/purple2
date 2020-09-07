package com.example.loicdandoy.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PageDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_detail);

        //1 - Configuring Toolbar
        this.configureToolbar();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe");

        String name = recipe.name;
        String username = recipe.username;
        String recipeTime = recipe.recipeTime;
        String mealType = recipe.meal_type;
        String description = recipe.description;
        String image = recipe.recipeImg;
        /*
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        String recipeTime = intent.getStringExtra(MainActivity.EXTRA_RECIPE_TIME);
        String description = intent.getStringExtra(MainActivity.EXTRA_DESCRIPTION);
        String mealType = intent.getStringExtra(MainActivity.EXTRA_MEAL_TYPE);
        String image = intent.getStringExtra(MainActivity.EXTRA_IMAGE);
        */

        // Capture the layout's TextView and set the string as its text
        TextView textViewLabel = findViewById(R.id.textViewName);
        textViewLabel.setText(name);
        TextView textViewStatus = findViewById(R.id.textViewUsername);
        textViewStatus.setText(username);
        TextView textViewRecipeTime = findViewById(R.id.textViewRecipeTime);
        textViewRecipeTime.setText(recipeTime);
        TextView textViewMealType = findViewById(R.id.textViewMealType);
        textViewMealType.setText(mealType);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescription.setText(description);
        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        Glide.with(this).load(image).into(imageViewDetail);

    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
