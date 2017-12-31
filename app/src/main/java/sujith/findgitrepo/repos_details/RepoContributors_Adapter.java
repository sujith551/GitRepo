package sujith.findgitrepo.repos_details;

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
 * Created by sujith on 29/12/17.
 */

public class RepoContributors_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mcontext;
    ArrayList<ContibutorsModel> contributorData = new ArrayList<>();
    RepoDetialsActivityListner.ContributorListner mContributorListner;

    public RepoContributors_Adapter(Context mcontext,
                                    ArrayList<ContibutorsModel> contributorData) {
        this.mcontext = mcontext;
        this.contributorData = contributorData;
    }

    public void setmContributorListner(RepoDetialsActivityListner.ContributorListner mContributorListner) {
        this.mContributorListner = mContributorListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_contributor, parent, false);
        vh = new RepoContributors_Adapter.ContributorHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContibutorsModel mContibutorsModel = contributorData.get(position);
        ContributorHolder mContributorHolder = (ContributorHolder) holder;
        if (mContributorHolder.img_contirbour != null)
            Picasso.with(mcontext)
                    .load(mContibutorsModel.avatar_url)
                    .into(mContributorHolder.img_contirbour);
        if (mContibutorsModel.contributor_name != null)
            mContributorHolder.txt_contributor_name.setText(mContibutorsModel.contributor_name);

    }

    @Override
    public int getItemCount() {
        return contributorData.size();
    }

    class ContributorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_contirbour)
        ImageView img_contirbour;
        @BindView(R.id.txt_contributor_name)
        TextView txt_contributor_name;

        public ContributorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mContributorListner != null) {
                mContributorListner.contriButor(contributorData.get(getAdapterPosition()));
            }
        }
    }
}
