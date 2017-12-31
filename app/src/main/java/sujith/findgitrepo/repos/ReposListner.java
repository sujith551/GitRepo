package sujith.findgitrepo.repos;

import java.util.ArrayList;

/**
 * Created by sujith on 28/12/17.
 */

public class ReposListner {

    public interface RepoActivityListner {
        void setReposData();

        void showPreogress();

        void hidePreogress();

    }

    public interface RepoClickListner {
        void repoItemClickListner(RepoModel.ReposItem repo_item);
    }

}
