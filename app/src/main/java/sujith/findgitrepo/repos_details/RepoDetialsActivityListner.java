package sujith.findgitrepo.repos_details;

/**
 * Created by sujith on 29/12/17.
 */

public class RepoDetialsActivityListner {

    interface RepoDetialsListner {

        void getIntentData();

        void setRepoData();

        void showProgressBar();

        void hideProgressBar();

    }

    interface ContributorListner {
        void contriButor(ContibutorsModel mContibutorsModel);
    }


}
