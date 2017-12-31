package sujith.findgitrepo.repos;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import sujith.findgitrepo.config.ServerConfig;
import sujith.findgitrepo.core.BaseService;
import sujith.findgitrepo.core.ResponseListener;

/**
 * Created by sujith on 29/12/17.
 */

public class ReposNetworkService extends BaseService {

    public void getReposData(Context mContext, String search, String sort, String order_type, ResponseListener<RepoModel> listener) {
        String url = ServerConfig.REPOSITORIES + "?q=" + search + "&sort=" + sort + "&order=" + order_type + "&per_page=10";
        executeGetRequest(mContext, url, null, null, new TypeToken<RepoModel>() {
        }, listener);
    }

    public void getReposData(Context mContext, String url, ResponseListener<ArrayList<RepoModel.ReposItem>> listener) {
        executeGetRequest(mContext, url, null, null, new TypeToken<ArrayList<RepoModel.ReposItem>>() {
        }, listener);
    }

}
