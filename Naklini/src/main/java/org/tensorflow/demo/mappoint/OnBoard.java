package org.tensorflow.demo.mappoint;

import android.os.Parcel;
import android.os.Parcelable;

public class OnBoard implements Parcelable {
    public int _id;
    public String name;
    public String point_nm;
    public String dpwt; // 깊이
    public String material;
    public String tide_time;
    public String target;
    public String latitude;
    public String longitude;
    public String adr_knm; // 주소

    public OnBoard(int _id, String name, String point_nm, String dpwt, String material, String tide_time, String target, String latitude, String longitude, String adr_knm) {
        this._id = _id;
        this.name = name;
        this.point_nm = point_nm;
        this.dpwt = dpwt;
        this.material = material;
        this.tide_time = tide_time;
        this.target = target;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adr_knm = adr_knm;
    }

    protected OnBoard(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        point_nm = in.readString();
        dpwt = in.readString();
        material = in.readString();
        tide_time = in.readString();
        target = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        adr_knm = in.readString();
    }



    public static final Creator<OnBoard> CREATOR = new Creator<OnBoard>() {
        @Override
        public OnBoard createFromParcel(Parcel in) {
            return new OnBoard(in);
        }

        @Override
        public OnBoard[] newArray(int size) {
            return new OnBoard[size];
        }
    };

    @Override
    public String toString() {
        return "OnBoard{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", point_nm='" + point_nm + '\'' +
                ", dpwt='" + dpwt + '\'' +
                ", material='" + material + '\'' +
                ", tide_time='" + tide_time + '\'' +
                ", target='" + target + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", adr_knm='" + adr_knm + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(point_nm);
        dest.writeString(dpwt);
        dest.writeString(material);
        dest.writeString(tide_time);
        dest.writeString(target);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(adr_knm);
    }
}
