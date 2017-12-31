package sujith.findgitrepo.repos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;


import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;

import sujith.findgitrepo.R;
import sujith.findgitrepo.config.ServerConfig;
import sujith.findgitrepo.core.ResponseListener;

/**
 * Created by sujith on 28/12/17.
 */

public class ReposListPresenter {

    ReposListner.RepoActivityListner mRepoActivityListner;
    Context mContext;
    public ArrayList<RepoModel.ReposItem> RepoListData = new ArrayList<>();
    public ReposAdapter mReposAdapter;
    String REPOS_SEARCH = "android";
    String REPOS_ORDER_TYPE = "desc";
    public String[] REPOS_SORT_DATA = new String[3];
    String REPOS_SORT;

    public ReposListPresenter(ReposListner.RepoActivityListner mRepoActivityListner, Context mContext) {
        this.mRepoActivityListner = mRepoActivityListner;
        this.mContext = mContext;
        REPOS_SORT_DATA = mContext.getResources().getStringArray(R.array.filter_repo);
        REPOS_SORT = REPOS_SORT_DATA[0];

    }

    public void getRepoData(String query, String sort, String order_type, Context mContext) {
        new GetReposList(query, sort, order_type, mContext);
    }

    public void getRepoData(String url, Context mContext) {
        new GetContributorReposList(url, mContext);
    }

    class GetReposList implements ResponseListener<RepoModel> {

        public GetReposList(String serach_query, String sort, String order_type, Context mContext) {
            mRepoActivityListner.showPreogress();
            new ReposNetworkService().getReposData(mContext, serach_query, sort, order_type, this);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            mRepoActivityListner.hidePreogress();
        }

        @Override
        public void onResponse(RepoModel response) {

            mRepoActivityListner.hidePreogress();
            RepoListData.clear();
            RepoListData.addAll(response.repo_items);
            if (mReposAdapter != null)
                mReposAdapter.notifyDataSetChanged();
            mRepoActivityListner.hidePreogress();
        }
    }

    class GetContributorReposList implements ResponseListener<ArrayList<RepoModel.ReposItem>> {

        public GetContributorReposList(String url, Context mContext) {
            mRepoActivityListner.showPreogress();
            new ReposNetworkService().getReposData(mContext, url, this);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            mRepoActivityListner.hidePreogress();
        }

        @Override
        public void onResponse(ArrayList<RepoModel.ReposItem> response) {
            mRepoActivityListner.hidePreogress();
            RepoListData.clear();
            RepoListData.addAll(response);
            if (mReposAdapter != null)
                mReposAdapter.notifyDataSetChanged();
            mRepoActivityListner.hidePreogress();
        }
    }

    public void sortRepos(final Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Sort Repositories");

        int checked_item = Arrays.asList(REPOS_SORT_DATA).indexOf(REPOS_SORT);

        builder.setSingleChoiceItems(REPOS_SORT_DATA, checked_item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                REPOS_SORT = REPOS_SORT_DATA[i];
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getRepoData(REPOS_SEARCH, REPOS_SORT, REPOS_ORDER_TYPE, mContext);
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

}

