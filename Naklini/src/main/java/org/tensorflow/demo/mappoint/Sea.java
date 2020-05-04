package org.tensorflow.demo.mappoint;

import android.os.Parcel;
import android.os.Parcelable;

public class Sea implements Parcelable {
    public int _id;
    public String name;
    public String addr;
    public String target;

    public Sea(int _id, String name, String addr, String target) {
        this._id = _id;
        this.name = name;
        this.addr = addr;
        this.target = target;
    }


    protected Sea(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        addr = in.readString();
        target = in.readString();
    }

    public static final Creator<Sea> CREATOR = new Creator<Sea>() {
        @Override
        public Sea createFromParcel(Parcel in) {
            return new Sea(in);
        }

        @Override
        public Sea[] newArray(int size) {
            return new Sea[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(addr);
        dest.writeString(target);
    }
}
