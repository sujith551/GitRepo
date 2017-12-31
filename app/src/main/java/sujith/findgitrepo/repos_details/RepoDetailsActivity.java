package sujith.findgitrepo.repos_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sujith.findgitrepo.R;
import sujith.findgitrepo.contributor_details.ContributorActivity;
import sujith.findgitrepo.repos.RepoModel;
import sujith.findgitrepo.webview.WebViewActivity;

public class RepoDetailsActivity extends AppCompatActivity implements
        RepoDetialsActivityListner.RepoDetialsListner,
        RepoDetialsActivityListner.ContributorListner {

    String TAG = RepoDetailsActivity.class.getSimpleName();
    RepoModel.ReposItem repo_data;

    @BindView(R.id.repo_img)
    ImageView img_repo;
    @BindView(R.id.repo_name)
    TextView txt_repo_name;
    @BindView(R.id.repo_desc)
    TextView txt_repo_desc;
    @BindView(R.id.repo_link)
    TextView txt_repo_link;
    @BindView(R.id.repo_contributer_list)
    RecyclerView repo_contributer_list;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    RepoDetialsPresenter mRepoDetialsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRepoDetialsPresenter = new RepoDetialsPresenter(this, this);
        getIntentData();
        repo_contributer_list.setHasFixedSize(true);
        repo_contributer_list.setLayoutManager(new GridLayoutManager(this, 3));
        mRepoDetialsPresenter.mRepoContributors_Adapter = new
                RepoContributors_Adapter(this,
                mRepoDetialsPresenter.ContibutorsList);
        repo_contributer_list.setAdapter(mRepoDetialsPresenter.mRepoContributors_Adapter);
        mRepoDetialsPresenter.mRepoContributors_Adapter.setmContributorListner(this);
        setRepoData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void setRepoData() {
        if (repo_data != null) {
            if (repo_data.repo_full_name != null)
                txt_repo_name.setText(repo_data.repo_full_name);
            if (repo_data.description != null)
                txt_repo_desc.setText(repo_data.description);
            if (repo_data.repo_html_url != null) {
                txt_repo_link.setText(repo_data.repo_html_url);
                txt_repo_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(RepoDetailsActivity.this,
                                WebViewActivity.class).
                                putExtra("web_link", repo_data.repo_html_url).
                                putExtra("repo_name", repo_data.repo_full_name));
                    }
                });
            }
            if (repo_data.repo_owner != null && repo_data.repo_owner.avatar_url != null)
                Picasso.with(this)
                        .load(repo_data.repo_owner.avatar_url)
                        .into(img_repo);

            if (repo_data.repos_contributors_url != null) {
                mRepoDetialsPresenter.getContributors(this,
                        repo_data.repos_contributors_url);

            }

        }
    }

    @Override
    public void getIntentData() {
        Bundle data = getIntent().getExtras();
        if (!data.isEmpty()) {
            if (data.containsKey("repo_data")) {
                repo_data = data.getParcelable("repo_data");
                if (repo_data.repo_name != null)
                    getSupportActionBar().setTitle(repo_data.repo_name);
                else
                    getSupportActionBar().setTitle("");
            }
        }
    }

    @Override
    public void showProgressBar() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void contriButor(ContibutorsModel mContibutorsModel) {
        Log.i(TAG, mContibutorsModel.contributor_name);
        startActivity(new Intent(this, ContributorActivity.class).
                putExtra("contibutor_data", mContibutorsModel));
    }
}
