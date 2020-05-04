package com.project.fishbegin.mappoint;

import android.os.Parcel;
import android.os.Parcelable;

public class FreshWater implements Parcelable {
    public int _id;
    public String name;
    public String addr;
    public String target;

    public FreshWater(int _id, String name, String addr, String target) {
        this._id = _id;
        this.name = name;
        this.addr = addr;
        this.target = target;
    }

    protected FreshWater(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        addr = in.readString();
        target = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(addr);
        dest.writeString(target);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FreshWater> CREATOR = new Creator<FreshWater>() {
        @Override
        public FreshWater createFromParcel(Parcel in) {
            return new FreshWater(in);
        }

        @Override
        public FreshWater[] newArray(int size) {
            return new FreshWater[size];
        }
    };
}
