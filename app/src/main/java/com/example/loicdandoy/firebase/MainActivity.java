package com.example.loicdandoy.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.support.v7.widget.SearchView;
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loicdandoy.firebase.auth.UserActivity;
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

<<<<<<< HEAD
import butterknife.BindView;

=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
public class MainActivity extends AppCompatActivity {

    //recyclerView init
    private ArrayList<Recipe> myDataSet = new ArrayList<Recipe>();
    private MyAdapter mAdapter;

<<<<<<< HEAD
    // FIREBASE
    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;

    //Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Recipes");
=======
    //Stocker details recyclerView pour la page détail
    public static final String EXTRA_NAME = "com.example.loicdandoy.NAME";
    public static final String EXTRA_USERNAME = "com.example.loicdandoy.USERNAME";
    public static final String EXTRA_IMAGE = "com.example.loicdandoy.IMAGE";
    public static final String EXTRA_RECIPE_TIME = "com.example.loicdandoy.RECIPE_TIME";
    public static final String EXTRA_DESCRIPTION = "com.example.loicdandoy.DESCRIPTION";
    public static final String EXTRA_MEAL_TYPE = "com.example.loicdandoy.MEAL_TYPE";

>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1 - Configuring Toolbar
        this.configureToolbar();


        //Firebase
<<<<<<< HEAD
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Recipes");
        //Connection base de donnée
        mDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        final String uid = firebaseAuth.getCurrentUser().getUid();
=======
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recipes");

>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataSet, Glide.with(this));
        recyclerView.setAdapter(mAdapter);

        // Read from the database
<<<<<<< HEAD
        search("");
        /*
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataSet.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Recipe recipe = ds.getValue(Recipe.class);
                    myDataSet.add(recipe);
                    mAdapter.notifyDataSetChanged();

<<<<<<< HEAD
                    Log.d("FireBase", recipe.name);

=======
                    Log.d("FireBase", recipe.name );
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FireBase", "Failed to read value.", error.toException());
            }
        });
<<<<<<< HEAD
        */
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf


        // Configure item click on RecyclerView
        ItemClickSupport.addTo(recyclerView, R.layout.my_text_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // 1 - Get recipe from adapter
                        Recipe recipe = mAdapter.getLabel(position);
<<<<<<< HEAD

                        //userValidate(recipe.quizId, recipe.userId, recipe);
                        verifUser(recipe.quizId, uid, recipe);
=======
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
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
                    }
                });
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
<<<<<<< HEAD

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
=======
        return true;
    }

    private void configureToolbar(){
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
    }
<<<<<<< HEAD

=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
<<<<<<< HEAD
                //Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                //Création Quizz
                Intent intent = new Intent(this, UserActivity.class);
                //Intent intent = new Intent(this, CreateQuiz1.class);
                startActivity(intent);
=======
//                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                Intent intentProfile= new Intent(this, UserActivity.class);
                startActivity(intentProfile);
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
                return true;
            case R.id.menu_activity_main_search:
                Intent intentNewRecipe = new Intent(this, NewRecipe.class);
                startActivity(intentNewRecipe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
<<<<<<< HEAD

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

=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
}
