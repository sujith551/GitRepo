package sujith.findgitrepo.repos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import sujith.findgitrepo.R;
import sujith.findgitrepo.repos_details.RepoDetailsActivity;

public class RepoListActivity extends AppCompatActivity
        implements ReposListner.RepoActivityListner,
        ReposListner.RepoClickListner {

    public String TAG = RepoListActivity.class.getSimpleName();

    @BindView(R.id.repo_list)
    RecyclerView repo_list;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.fab_filter_btn)
    FloatingActionButton fab_filter_btn;

    ReposListPresenter mReposListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repolist);
        ButterKnife.bind(this);

        mReposListPresenter = new ReposListPresenter(this, this);

        repo_list.setHasFixedSize(true);
        repo_list.setLayoutManager(new LinearLayoutManager(this));
        mReposListPresenter.mReposAdapter = new ReposAdapter(mReposListPresenter.RepoListData, this);
        repo_list.setAdapter(mReposListPresenter.mReposAdapter);
        mReposListPresenter.mReposAdapter.setReposClickListner(this);
        mReposListPresenter.getRepoData(mReposListPresenter.REPOS_SEARCH,
                mReposListPresenter.REPOS_SORT,
                mReposListPresenter.REPOS_ORDER_TYPE,
                this);

        fab_filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReposListPresenter.sortRepos(RepoListActivity.this);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repo, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView msearchView = (SearchView) item.getActionView();

        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!s.trim().isEmpty()) {
                    mReposListPresenter.REPOS_SEARCH = s.trim();
                    mReposListPresenter.getRepoData(mReposListPresenter.REPOS_SEARCH,
                            mReposListPresenter.REPOS_SORT,
                            mReposListPresenter.REPOS_ORDER_TYPE,
                            RepoListActivity.this);
                } else {
                    Toast.makeText(RepoListActivity.this,
                            "Search is not empay", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Log.i(TAG, "search val");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setReposData() {

    }

    @Override
    public void showPreogress() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePreogress() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void repoItemClickListner(RepoModel.ReposItem repo_item) {
        Log.i(TAG, "repos data" + repo_item.repo_name);
        startActivity(new Intent(this, RepoDetailsActivity.class).
                putExtra("repo_data", repo_item));
    }

}
