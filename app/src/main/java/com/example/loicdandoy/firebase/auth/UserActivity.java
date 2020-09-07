package com.example.loicdandoy.firebase.auth;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
<<<<<<< HEAD
import android.support.v7.app.ActionBar;
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loicdandoy.firebase.ItemClickSupport;
import com.example.loicdandoy.firebase.MyAdapter;
import com.example.loicdandoy.firebase.NewRecipe;
import com.example.loicdandoy.firebase.PageDetail;
import com.example.loicdandoy.firebase.Recipe;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.loicdandoy.firebase.LoginActivity;
import com.example.loicdandoy.firebase.MainActivity;
import com.example.loicdandoy.firebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

<<<<<<< HEAD
=======
import static com.example.loicdandoy.firebase.MainActivity.EXTRA_DESCRIPTION;
import static com.example.loicdandoy.firebase.MainActivity.EXTRA_IMAGE;
import static com.example.loicdandoy.firebase.MainActivity.EXTRA_MEAL_TYPE;
import static com.example.loicdandoy.firebase.MainActivity.EXTRA_NAME;
import static com.example.loicdandoy.firebase.MainActivity.EXTRA_RECIPE_TIME;
import static com.example.loicdandoy.firebase.MainActivity.EXTRA_USERNAME;

>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
public class UserActivity extends AppCompatActivity {

    private static final String TAG = UserActivity.class.getSimpleName();

<<<<<<< HEAD
    private DatabaseReference mDatabase;

=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
    private FirebaseAuth auth;
    private TextView profileName;
    private ImageView image_gallery;
    private ArrayList<Recipe> myDataSet = new ArrayList<Recipe>();
    private MyAdapter mAdapter;
    private FirebaseAuth firebaseAuth;

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;
    private String imagepath = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if(!isUserLogin()){signOut();}
<<<<<<< HEAD
        setContentView(R.layout.activity_user);
        firebaseAuth = FirebaseAuth.getInstance();
        Switch switch1 =(Switch)findViewById(R.id.switch1);

        mDatabase = FirebaseDatabase.getInstance().getReference();
=======
        setContentView(R.layout.userprofile);
        firebaseAuth = FirebaseAuth.getInstance();
        Switch switch1 =(Switch)findViewById(R.id.switch1);

>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf

        //1 - Configuring Toolbar
        this.configureToolbar();

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataSet, Glide.with(this));
        recyclerView.setAdapter(mAdapter);


        //switch1
        final DatabaseReference ezzeearnRef = FirebaseDatabase.getInstance().getReference("Recipes");
        final String uid = firebaseAuth.getCurrentUser().getUid();

        //switch2
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipes");

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

<<<<<<< HEAD
                if (isChecked){
=======
                if (!isChecked){
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
                    ezzeearnRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            myDataSet.clear();
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Recipe recipe = ds.getValue(Recipe.class);

                                if (uid.equals(recipe.userId)) {

                                    myDataSet.add(recipe);
                                    Log.d("FireBase", recipe.name);

                                }
                                //                    Recipe recipe = ds.getValue(Recipe.class);
                                //
                                //                    myDataSet.add(recipe);
                                //                    Log.d("FireBase", recipe.name );
                            }
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("FireBase", "Failed to read value.", error.toException());
                        }
                    });
<<<<<<< HEAD
                } else {
                    myDataSet.clear();
                    mAdapter.notifyDataSetChanged();
                }

            }
        });
=======
            }
                else {
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
                }

    }
});
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf


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
<<<<<<< HEAD
                        intent.putExtra("recipe", recipe);
=======
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

>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
                        startActivity(intent);
                    }
                });

//        image_gallery = (ImageView) findViewById(R.id.user_gallery);

        profileName = (TextView) findViewById(R.id.user_name);
        displayLoginUserProfileName();

        Button logoutButton = (Button)findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(UserActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    signOut();
                                }else {

                                }
                            }
                        });
            }
        });

        Button deleteUserButton = (Button)findViewById(R.id.button_delete);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            signOut();
                        }else{

                        }
                    }
                });
            }
        });
<<<<<<< HEAD
=======
//        ezzeearnRef.addListenerForSingleValueEvent(eventListener);

//        Button main_button = (Button) findViewById(R.id.buttonTest);
//        main_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //lance activité principale
//                Intent mainActivity = new Intent(UserActivity.this, MainActivity.class);
//                startActivity(mainActivity);
//            }
//        });

//        Button gallery_button = (Button) findViewById(R.id.button_gallery);
//        gallery_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //Ouverture de l'app gallery photo
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                startActivityForResult(intent, 1);}
//        });
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
    }

    private boolean isUserLogin(){
        if(auth.getCurrentUser() != null){
            return true;
        }
        return false;
    }

    private void signOut(){
        Intent signOutIntent = new Intent(this, LoginActivity.class);
        startActivity(signOutIntent);
        finish();
    }

    private void displayLoginUserProfileName(){
        FirebaseUser mUser = auth.getCurrentUser();
        if(mUser != null){
            profileName.setText(TextUtils.isEmpty(mUser.getDisplayName())? "No name found" : mUser.getDisplayName());
        }
    }

    //Toolbar
<<<<<<< HEAD
    /*
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
<<<<<<< HEAD
    */

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
/*
    private void configureToolbar(){
=======

    private void configureToolbar(){
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
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
//                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                Intent intentProfile= new Intent(this, UserActivity.class);
                startActivity(intentProfile);
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
*/
=======
>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf

    //Gallery image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            imagepath = getPath(filePath);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image_gallery.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }//onActivityResult

    //Méthode qui récupère le chemin de l'image séléctionnée
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

<<<<<<< HEAD
}
=======
}

>>>>>>> 4401d053ab236bb9f87992277fe5b71ec1f636cf
