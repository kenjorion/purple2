package com.example.loicdandoy.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loicdandoy.firebase.auth.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    //recyclerView init
    private ArrayList<Recipe> myDataSet = new ArrayList<Recipe>();
    private MyAdapter mAdapter;

    // FIREBASE
    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;

    //Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1 - Configuring Toolbar
        this.configureToolbar();


        //Firebase
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Recipes");
        //Connection base de donnée
        mDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        final String uid = firebaseAuth.getCurrentUser().getUid();

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataSet, Glide.with(this));
        recyclerView.setAdapter(mAdapter);

        // Read from the database
        search("");
        /*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataSet.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Recipe recipe = ds.getValue(Recipe.class);
                    myDataSet.add(recipe);
                    mAdapter.notifyDataSetChanged();

                    Log.d("FireBase", recipe.name);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });
        */


        // Configure item click on RecyclerView
        ItemClickSupport.addTo(recyclerView, R.layout.my_text_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // 1 - Get recipe from adapter
                        Recipe recipe = mAdapter.getLabel(position);

                        //userValidate(recipe.quizId, recipe.userId, recipe);
                        verifUser(recipe.quizId, uid, recipe);
                    }
                });
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //mAdapter.getFilter().filter(newText);
                //Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_LONG).show();
                if (!newText.isEmpty()) {
                    search(newText);
                } else {
                    search("");
                }
                return true;
            }
        });

        //return super.onCreateOptionsMenu(menu);

        return true;
    }

    private void configureToolbar() {
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
                //Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                //Création Quizz
                Intent intent = new Intent(this, UserActivity.class);
                //Intent intent = new Intent(this, CreateQuiz1.class);
                startActivity(intent);
                return true;
            case R.id.menu_activity_main_search:
                Intent intentNewRecipe = new Intent(this, NewRecipe.class);
                startActivity(intentNewRecipe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Virifie si l'utilisateur a déjà réussi le quiz ou non
    public void verifUser(String quizId, String userID, final Recipe recipe) {
        mDatabase.child("Quiz").child(quizId).child("userValidate").child(userID).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // [START_EXCLUDE]
                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(getApplicationContext(),
                                    "Quiz non validé !!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DoQuiz.class);
                            intent.putExtra("recipe", recipe);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Quiz déjà validé !!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), PageDetail.class);
                            intent.putExtra("recipe", recipe);
                            startActivity(intent);
                        }

                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("DoQuiz", "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        Toast.makeText(getApplicationContext(),
                                "Error",
                                Toast.LENGTH_SHORT).show();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void search(final String newText) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataSet.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if (!newText.isEmpty()) {
                        String newText2 = newText.toLowerCase();

                        Recipe recipe = ds.getValue(Recipe.class);
                        if (recipe.name.toLowerCase().contains(newText2) || recipe.meal_type.toLowerCase().contains(newText2) || recipe.username.toLowerCase().contains(newText2)) {
                            myDataSet.add(recipe);
                        }
                        mAdapter.notifyDataSetChanged();

                        Log.d("FireBase", recipe.name);
                    } else {
                        Recipe recipe = ds.getValue(Recipe.class);
                        myDataSet.add(recipe);
                        mAdapter.notifyDataSetChanged();

                        Log.d("FireBase", recipe.name);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });
    }

}
