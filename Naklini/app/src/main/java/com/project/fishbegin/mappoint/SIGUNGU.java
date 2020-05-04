package com.project.fishbegin.mappoint;

import android.os.Parcel;
import android.os.Parcelable;

public class SIGUNGU implements Parcelable {
    int _id;
    String SIGUNGU;
    int SIDO_id;

    public SIGUNGU(int _id, String SIGUNGU, int SIDO_id) {
        this._id = _id;
        this.SIGUNGU = SIGUNGU;
        this.SIDO_id = SIDO_id;
    }

    protected SIGUNGU(Parcel in) {
        _id = in.readInt();
        SIGUNGU = in.readString();
        SIDO_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(SIGUNGU);
        dest.writeInt(SIDO_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<com.project.fishbegin.mappoint.SIGUNGU> CREATOR = new Creator<com.project.fishbegin.mappoint.SIGUNGU>() {
        @Override
        public com.project.fishbegin.mappoint.SIGUNGU createFromParcel(Parcel in) {
            return new SIGUNGU(in);
        }

        @Override
        public com.project.fishbegin.mappoint.SIGUNGU[] newArray(int size) {
            return new SIGUNGU[size];
        }
    };

    @Override
    public String toString() {
        return "SIGUNGU{" +
                "_id=" + _id +
                ", SIGUNGU='" + SIGUNGU + '\'' +
                ", SIDO_id=" + SIDO_id +
                '}';
    }
}
