package com.lencotion.serializableparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class Woman implements Parcelable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Woman(int age, String name) {
        this.age = age;
        this.name = name;
    }



    protected Woman(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public static final Creator<Woman> CREATOR = new Creator<Woman>() {
        @Override
        public Woman createFromParcel(Parcel in) {
            return new Woman(in);
        }

        @Override
        public Woman[] newArray(int size) {
            return new Woman[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(age);
        parcel.writeString(name);
    }
}
