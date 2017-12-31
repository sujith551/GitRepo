package sujith.findgitrepo.repos_details;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import sujith.findgitrepo.config.ServerConfig;
import sujith.findgitrepo.core.BaseService;
import sujith.findgitrepo.core.ResponseListener;
import sujith.findgitrepo.repos.RepoModel;

/**
 * Created by sujith on 29/12/17.
 */

public class RepoDetailsNetworkService extends BaseService {

    public void getRepoDetailsData(Context mContext, String url,
                                   ResponseListener<ArrayList<ContibutorsModel>> listener) {
        executeGetRequest(mContext, url, null, null,
                new TypeToken<ArrayList<ContibutorsModel>>() {
                }, listener);
    }


}
