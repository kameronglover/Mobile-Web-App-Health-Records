/**
 * In Class Assignment 03
 * Profile.java
 * Kameron Glover and Maya Hamilton
 */
/**
 * In Class 04
 * Profile.java
 * Kameron Glover and Maya Hamilton
 *
 */
package com.example.healthchecker;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * HW 03
 * Profile.java
 * Kameron Glover and Maya Hamilton
 */

public class Profile implements Parcelable{
    String name;
    String phone;
    int age;
    int value;

    public Profile(String name, int age, String phone, int value){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.value = value;

    }

    protected Profile(Parcel in) {
        name = in.readString();
        age = in.readInt();
        phone = in.readString();
        value = in.readInt();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(phone);
        dest.writeInt(value);
    }
}


