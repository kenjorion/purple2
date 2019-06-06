package com.example.loicdandoy.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //recyclerView init
    private ArrayList<Recipe> myDataSet = new ArrayList<Recipe>();
    private MyAdapter mAdapter;

    //Stocker details recyclerView pour la page détail
    public static final String EXTRA_NAME = "com.example.loicdandoy.NAME";
    public static final String EXTRA_USERNAME = "com.example.loicdandoy.USERNAME";
    public static final String EXTRA_IMAGE = "com.example.loicdandoy.IMAGE";
    public static final String EXTRA_RECIPE_TIME = "com.example.loicdandoy.RECIPE_TIME";
    public static final String EXTRA_DESCRIPTION = "com.example.loicdandoy.DESCRIPTION";
    public static final String EXTRA_MEAL_TYPE = "com.example.loicdandoy.MEAL_TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1 - Configuring Toolbar
        this.configureToolbar();


        //Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recipes");


        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataSet, Glide.with(this));
        recyclerView.setAdapter(mAdapter);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataSet.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Recipe recipe = ds.getValue(Recipe.class);
                    myDataSet.add(recipe);
                    mAdapter.notifyDataSetChanged();

                    Log.d("FireBase", recipe.name );
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });


        // Configure item click on RecyclerView
        ItemClickSupport.addTo(recyclerView, R.layout.my_text_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // 1 - Get recipe from adapter
                        Recipe recipe = mAdapter.getLabel(position);
                        // afficher page détail
                        //Toast.makeText(getApplicationContext(), "ça marche !"+recipe.name, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PageDetail.class);
                        String name = recipe.name;
                        String username = recipe.username;
                        String description = recipe.description;
                        String recipeTime = recipe.recipeTime;
                        String image = recipe.recipeImg;
                        String mealType = recipe.meal_type;
                        intent.putExtra(EXTRA_NAME, name);
                        intent.putExtra(EXTRA_USERNAME, username);
                        intent.putExtra(EXTRA_IMAGE, image);
                        intent.putExtra(EXTRA_RECIPE_TIME, recipeTime);
                        intent.putExtra(EXTRA_DESCRIPTION, description);
                        intent.putExtra(EXTRA_MEAL_TYPE, mealType);

                        startActivity(intent);
                    }
                });
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                Intent intentNewRecipe = new Intent(this, NewRecipe.class);
                startActivity(intentNewRecipe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
