package sujith.findgitrepo.repos_details;

import android.content.Context;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import sujith.findgitrepo.core.ResponseListener;

/**
 * Created by sujith on 29/12/17.
 */

public class RepoDetialsPresenter {

    RepoDetialsActivityListner.RepoDetialsListner mRepoDetialsListner;
    Context mContext;
    ArrayList<ContibutorsModel> ContibutorsList = new ArrayList<>();
    RepoContributors_Adapter mRepoContributors_Adapter;

    public RepoDetialsPresenter(RepoDetialsActivityListner.RepoDetialsListner mRepoDetialsListner,
                                Context mContext) {
        this.mRepoDetialsListner = mRepoDetialsListner;
        this.mContext = mContext;
        ContibutorsList = new ArrayList<>();
    }

    public void getContributors(Context mContext, String url) {
        new GetContriButors(mContext, url);
    }

    class GetContriButors implements ResponseListener<ArrayList<ContibutorsModel>> {

        public GetContriButors(Context mContext, String url) {
            new RepoDetailsNetworkService().getRepoDetailsData(mContext, url, this);
            mRepoDetialsListner.showProgressBar();
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            mRepoDetialsListner.hideProgressBar();
        }

        @Override
        public void onResponse(ArrayList<ContibutorsModel> response) {
            mRepoDetialsListner.hideProgressBar();
            ContibutorsList.clear();
            ContibutorsList.addAll(response);
            if (mRepoContributors_Adapter != null)
                mRepoContributors_Adapter.notifyDataSetChanged();
        }
    }
}



