package com.lencotion.serializableparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class Human implements Parcelable {
    private String address;
    private boolean isMan;
    private Woman woman;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public Woman getWoman() {
        return woman;
    }

    public void setWoman(Woman woman) {
        this.woman = woman;
    }

    public Human(String address, boolean isMan, Woman woman) {
        this.address = address;
        this.isMan = isMan;
        this.woman = woman;
    }

    protected Human(Parcel in) {
        address = in.readString();
        isMan = in.readInt() == 1;
        //反序列化 Woman 属性,由于 Woman 是另一个可序列化对象，所以它的反序列化过程需要传递当前线程的上下文类加载器，否则会报无法找到该类的错误。
        woman = in.readParcelable(Woman.class.getClassLoader());
    }

    public static final Creator<Human> CREATOR = new Creator<Human>() {
        @Override
        public Human createFromParcel(Parcel in) {
            return new Human(in);
        }

        @Override
        public Human[] newArray(int size) {
            return new Human[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeInt(isMan ? 1 : 0);
        parcel.writeParcelable(woman, i);
    }
}
