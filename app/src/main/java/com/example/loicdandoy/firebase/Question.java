package com.example.loicdandoy.firebase;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    public String question;
    public String reponse1;
    public String reponse2;
    public String reponse3;
    public String reponse4;
    public String correct;

    public Question() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Question(String question, String reponse1, String reponse2, String reponse3, String reponse4, String correct) {
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
        this.correct = correct;
    }

    protected Question(Parcel in) {
        question = in.readString();
        reponse1 = in.readString();
        reponse2 = in.readString();
        reponse3 = in.readString();
        reponse4 = in.readString();
        correct = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(reponse1);
        dest.writeString(reponse2);
        dest.writeString(reponse3);
        dest.writeString(reponse4);
        dest.writeString(correct);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
