package com.example.loicdandoy.firebase;


public class Recipe {

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

}
