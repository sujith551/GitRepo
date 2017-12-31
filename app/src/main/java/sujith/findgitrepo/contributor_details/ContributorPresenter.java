package sujith.findgitrepo.contributor_details;

import android.content.Context;

import sujith.findgitrepo.repos.ReposListner;

/**
 * Created by sujith on 30/12/17.
 */

public class ContributorPresenter {
    Context mContext;
    ReposListner.RepoActivityListner mRepoActivityListner;

    public ContributorPresenter(Context mContext,
                                ReposListner.RepoActivityListner mRepoActivityListner) {
        this.mContext = mContext;
        this.mRepoActivityListner = mRepoActivityListner;
    }

}
