package sujith.findgitrepo.contributor_details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import sujith.findgitrepo.R;
import sujith.findgitrepo.repos.RepoModel;
import sujith.findgitrepo.repos.ReposAdapter;
import sujith.findgitrepo.repos.ReposListPresenter;
import sujith.findgitrepo.repos.ReposListner;
import sujith.findgitrepo.repos_details.ContibutorsModel;
import sujith.findgitrepo.repos_details.RepoDetailsActivity;

public class ContributorActivity extends AppCompatActivity implements
        ReposListner.RepoActivityListner,
        ReposListner.RepoClickListner {

    @BindView(R.id.img_contirbour)
    ImageView img_contirbour;
    @BindView(R.id.txt_contributor_name)
    TextView txt_contributor_name;
    @BindView(R.id.repo_list_view)
    RecyclerView repo_list;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    ReposListPresenter mReposListPresenter;
    ContibutorsModel mContibutorsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mReposListPresenter = new ReposListPresenter(this, this);
        setReposData();
        repo_list.setHasFixedSize(true);
        repo_list.setLayoutManager(new LinearLayoutManager(this));
        mReposListPresenter.mReposAdapter = new ReposAdapter(mReposListPresenter.RepoListData, this);
        repo_list.setAdapter(mReposListPresenter.mReposAdapter);
        mReposListPresenter.mReposAdapter.setReposClickListner(this);
        if (mContibutorsModel != null)
            mReposListPresenter.getRepoData(mContibutorsModel.repos_url,
                    this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void setReposData() {
        Bundle data = getIntent().getExtras();
        if (!data.isEmpty()) {
            if (data.containsKey("contibutor_data")) {
                mContibutorsModel = data.getParcelable("contibutor_data");
            }
        }
        if (mContibutorsModel != null) {
            if (mContibutorsModel.avatar_url != null)

                Picasso.with(this)
                        .load(mContibutorsModel.avatar_url)
                        .error(R.drawable.git_avatar)
                        .placeholder(R.drawable.git_avatar)
                        .resize(50,50)
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                getSupportActionBar().setLogo(
                                        new BitmapDrawable(getResources(), bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {
                                getSupportActionBar().setLogo(errorDrawable);
                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {
                                getSupportActionBar().setLogo(placeHolderDrawable);
                            }
                        });
            if (mContibutorsModel.contributor_name != null) {
                txt_contributor_name.setText(mContibutorsModel.contributor_name);
                getSupportActionBar().setTitle(mContibutorsModel.contributor_name + " Repos");
            }
            if (mContibutorsModel.avatar_url != null) {

            }
        }

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
        startActivity(new Intent(this, RepoDetailsActivity.class).
                putExtra("repo_data", repo_item));
    }
}
