package com.example.joelw.multimediaserver.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joelw on 04/12/2016.
 */

public class Media implements Parcelable {
    public String name;
    public String description;
    public String path;
    public String cover;
    public int id;



    public Media( String mCover, String mName, String mDescription, String mPath)
    {
        this.name = mName; this.description = mDescription; this.cover = mCover; this.path = mPath;
    }
    public Media( String mName, String mDescription, String mPath)
    {
        this.name = mName; this.description = mDescription; this.path = mPath;
    }
    public Media( int mId, String mName, String mDescription, String mPath)
    {
        this.id = mId; this.name = mName; this.description = mDescription; this.path = mPath;
    }
    public Media() { }

    protected Media(Parcel in) {
        name = in.readString();
        description = in.readString();
        path = in.readString();
        cover = in.readString();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(cover);
        dest.writeString(description);
        dest.writeString(path);
    }
    //Les getteurs à faire

}
