package com.example.loicdandoy.firebase;


import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    public String name;
    public String description;
    public String recipeImg;
    public String username;
    public String userId;
    public String quizId;
    public String meal_type;
    public String recipeTime;
    public String recipeDate;

    public Recipe() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Recipe(String name, String description, String recipeImg, String username, String userId, String quizId, String meal_type, String recipeTime, String recipeDate) {
        this.name = name;
        this.description = description;
        this.recipeImg = recipeImg;
        this.username = username;
        this.userId = userId;
        this.quizId = quizId;
        this.meal_type = meal_type;
        this.recipeTime = recipeTime;
        this.recipeDate = recipeDate;

    }

    protected Recipe(Parcel in) {
        name = in.readString();
        description = in.readString();
        recipeImg = in.readString();
        username = in.readString();
        userId = in.readString();
        quizId = in.readString();
        meal_type = in.readString();
        recipeTime = in.readString();
        recipeDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(recipeImg);
        dest.writeString(username);
        dest.writeString(userId);
        dest.writeString(quizId);
        dest.writeString(meal_type);
        dest.writeString(recipeTime);
        dest.writeString(recipeDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getQuizId() {
        return quizId;
    }
}
