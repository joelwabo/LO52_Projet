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
    public String type;
    public int id = -1;



    public Media( String mType, String mName, String mDescription, String mPath, String mCover)
    {
        this.type = mType; this.name = mName; this.description = mDescription; this.cover = mCover; this.path = mPath;
    }
    public Media( String mType, String mName, String mDescription, String mPath)
    {
        this.type = mType; this.name = mName; this.description = mDescription; this.path = mPath;
    }
    public Media( String mType,  String mName, String mDescription, String mPath, int mId)
    {
        this.type = mType; this.id = mId; this.name = mName; this.description = mDescription; this.path = mPath;
    }
    public Media() { }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(path);
        dest.writeString(cover);
        dest.writeString(type);
        dest.writeInt(id);
    }

    public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>()
    {
        @Override
        public Media createFromParcel(Parcel source)
        {
            return new Media(source);
        }

        @Override
        public Media[] newArray(int size)
        {
            return new Media[size];
        }
    };

    public Media(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.path = in.readString();
        this.cover = in.readString();
        this.type = in.readString();
        this.id = in.readInt();
    }

    //Les getteurs à faire

}
