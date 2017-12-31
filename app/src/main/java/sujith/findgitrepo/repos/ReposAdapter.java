package sujith.findgitrepo.repos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sujith.findgitrepo.R;

/**
 * Created by sujith on 28/12/17.
 */

public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<RepoModel.ReposItem> reposData = new ArrayList<RepoModel.ReposItem>();
    Context mContext;
    public ReposListner.RepoClickListner mRepoClickListner;

    public ReposAdapter(ArrayList<RepoModel.ReposItem> reposData, Context mContext) {
        this.reposData = reposData;
        this.mContext = mContext;
    }

    public void setReposClickListner(ReposListner.RepoClickListner mRepoClickListner) {
        this.mRepoClickListner = mRepoClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_repo, parent, false);
        vh = new RepoHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        RepoModel.ReposItem mRepoModel_item = reposData.get(position);
        RepoHolder mRepoHolder = (RepoHolder) holder;

        mRepoHolder.txt_repo_name_view.setText(mRepoModel_item.repo_name);
        mRepoHolder.txt_repo_desc_view.setText(mRepoModel_item.description);
        mRepoHolder.txt_repo_watcher_view.setText(mRepoModel_item.repo_watchers_count);

        Picasso.with(mContext)
                .load(mRepoModel_item.repo_owner.avatar_url)
                .into(mRepoHolder.repo_image_view);
    }

    @Override
    public int getItemCount() {
        return reposData.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.repo_image)
        ImageView repo_image_view;
        @BindView(R.id.repo_watcher)
        TextView txt_repo_watcher_view;
        @BindView(R.id.repo_desc)
        TextView txt_repo_desc_view;
        @BindView(R.id.repo_name)
        TextView txt_repo_name_view;

        public RepoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mRepoClickListner != null) {
                mRepoClickListner.repoItemClickListner(reposData.get(getAdapterPosition()));
            }
        }
    }

}
