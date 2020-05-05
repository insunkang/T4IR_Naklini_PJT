package org.tensorflow.demo.pointdetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.GridLayout;

public class Species implements Parcelable {
    int _id;
    String name;
    String dist;
    String living;
    String size;
    String picture;

    public Species(int _id, String name){
        this._id = _id;
        this.name = name;
    }

    public Species(int _id, String name, String dist, String living, String size, String picture) {
        this._id = _id;
        this.name = name;
        this.dist = dist;
        this.living = living;
        this.size = size;
        this.picture = picture;
    }

    protected Species(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        dist = in.readString();
        living = in.readString();
        size = in.readString();
        picture = in.readString();
    }

    public static final Creator<Species> CREATOR = new Creator<Species>() {
        @Override
        public Species createFromParcel(Parcel in) {
            return new Species(in);
        }

        @Override
        public Species[] newArray(int size) {
            return new Species[size];
        }
    };


    @Override
    public String toString() {
        return "Species{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", dist='" + dist + '\'' +
                ", living='" + living + '\'' +
                ", size='" + size + '\'' +
                ", picture='" + picture + '\'' +
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
        dest.writeString(dist);
        dest.writeString(living);
        dest.writeString(size);
        dest.writeString(picture);
    }
}
