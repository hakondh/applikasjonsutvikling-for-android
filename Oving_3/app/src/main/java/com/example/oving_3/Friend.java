package com.example.oving_3;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {
    public String name;
    public String  birthdate;

    public Friend(String name, String birthdate){
        this.name = name;
        this.birthdate = birthdate;
    }

    // A constructor that can take Parcel and give object with the correct values
    private Friend(Parcel in) {
        name = in.readString();
        birthdate = in.readString();
    }

    public String getName(){
        return name;
    }

    public String getBirthdate(){
        return birthdate;
    }

    @Override
    public String toString() {
        return name + "\n" + birthdate;
    }

    //Overriding these methods to make the class parceable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(birthdate);
    }

    // These two methods are used to regenerate the object on receiving it
    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>() {
        public Friend createFromParcel(Parcel in) {
            return new Friend(in); // Uses the constructor that can take a Parcel
        }

        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}

