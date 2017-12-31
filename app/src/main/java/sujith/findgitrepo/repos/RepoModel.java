package sujith.findgitrepo.repos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sujith on 29/12/17.
 */

public class RepoModel {

    @SerializedName("total_count")
    public int repos_total_count;
    @SerializedName("items")
    public ArrayList<ReposItem> repo_items = new ArrayList<>();

    public static class ReposItem implements Parcelable {
        @SerializedName("id")
        public int repo_id;
        @SerializedName("name")
        public String repo_name;
        @SerializedName("full_name")
        public String repo_full_name;
        @SerializedName("private")
        public Boolean is_repo_private;
        @SerializedName("html_url")
        public String repo_html_url;
        @SerializedName("description")
        public String description;
        @SerializedName("contributors_url")
        public String repos_contributors_url;
        @SerializedName("created_at")
        public String repo_created_at;
        @SerializedName("updated_at")
        public String repo_updated_at;
        @SerializedName("watchers_count")
        public String repo_watchers_count;
        @SerializedName("forks")
        public int repo_forks;
        @SerializedName("owner")
        public RepoOwner repo_owner;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.repo_id);
            dest.writeString(this.repo_name);
            dest.writeString(this.repo_full_name);
            dest.writeValue(this.is_repo_private);
            dest.writeString(this.repo_html_url);
            dest.writeString(this.description);
            dest.writeString(this.repos_contributors_url);
            dest.writeString(this.repo_created_at);
            dest.writeString(this.repo_updated_at);
            dest.writeString(this.repo_watchers_count);
            dest.writeInt(this.repo_forks);
            dest.writeParcelable(this.repo_owner, flags);
        }

        public ReposItem() {
        }

        protected ReposItem(Parcel in) {
            this.repo_id = in.readInt();
            this.repo_name = in.readString();
            this.repo_full_name = in.readString();
            this.is_repo_private = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.repo_html_url = in.readString();
            this.description = in.readString();
            this.repos_contributors_url = in.readString();
            this.repo_created_at = in.readString();
            this.repo_updated_at = in.readString();
            this.repo_watchers_count = in.readString();
            this.repo_forks = in.readInt();
            this.repo_owner = in.readParcelable(RepoOwner.class.getClassLoader());
        }

        public static final Creator<ReposItem> CREATOR = new Creator<ReposItem>() {
            @Override
            public ReposItem createFromParcel(Parcel source) {
                return new ReposItem(source);
            }

            @Override
            public ReposItem[] newArray(int size) {
                return new ReposItem[size];
            }
        };
    }

    public static class RepoOwner implements Parcelable {
        @SerializedName("id")
        public int owner_id;
        @SerializedName("login")
        public String owner_login_name;
        @SerializedName("avatar_url")
        public String avatar_url;
        @SerializedName("repos_url")
        public String repos_url;
        @SerializedName("html_url")
        public String owner_html_url;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.owner_id);
            dest.writeString(this.owner_login_name);
            dest.writeString(this.avatar_url);
            dest.writeString(this.repos_url);
            dest.writeString(this.owner_html_url);
        }

        public RepoOwner() {
        }

        protected RepoOwner(Parcel in) {
            this.owner_id = in.readInt();
            this.owner_login_name = in.readString();
            this.avatar_url = in.readString();
            this.repos_url = in.readString();
            this.owner_html_url = in.readString();
        }

        public static Parcelable.Creator<RepoOwner> CREATOR = new Parcelable.Creator<RepoOwner>() {
            @Override
            public RepoOwner createFromParcel(Parcel source) {
                return new RepoOwner(source);
            }

            @Override
            public RepoOwner[] newArray(int size) {
                return new RepoOwner[size];
            }
        };
    }


}
