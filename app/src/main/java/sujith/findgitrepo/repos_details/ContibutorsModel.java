package sujith.findgitrepo.repos_details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sujith on 29/12/17.
 */

public class ContibutorsModel implements Parcelable {
    @SerializedName("id")
    public int contributor_id;
    @SerializedName("login")
    public String contributor_name;
    @SerializedName("repos_url")
    public String repos_url;
    @SerializedName("avatar_url")
    public String avatar_url;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.contributor_id);
        dest.writeString(this.contributor_name);
        dest.writeString(this.repos_url);
        dest.writeString(this.avatar_url);
    }

    public ContibutorsModel() {
    }

    protected ContibutorsModel(Parcel in) {
        this.contributor_id = in.readInt();
        this.contributor_name = in.readString();
        this.repos_url = in.readString();
        this.avatar_url = in.readString();
    }

    public static final Parcelable.Creator<ContibutorsModel> CREATOR = new Parcelable.Creator<ContibutorsModel>() {
        @Override
        public ContibutorsModel createFromParcel(Parcel source) {
            return new ContibutorsModel(source);
        }

        @Override
        public ContibutorsModel[] newArray(int size) {
            return new ContibutorsModel[size];
        }
    };
}
