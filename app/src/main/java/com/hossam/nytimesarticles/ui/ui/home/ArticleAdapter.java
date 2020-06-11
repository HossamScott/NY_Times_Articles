package com.hossam.nytimesarticles.ui.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hossam.nytimesarticles.R;
import com.hossam.nytimesarticles.ui.network.model.ArticleResponse;
import com.hossam.nytimesarticles.ui.ui.details.ArticleDetails;

import java.util.ArrayList;

/**
 * Created by Hossam on 11/06/2020.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ArticleResponse.ResultsBean> articleArrayList;
    private long mLastClickTime = 0;

    public ArticleAdapter(Context context, ArrayList<ArticleResponse.ResultsBean> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_article_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder viewHolder, int i) {
        ArticleResponse.ResultsBean article = articleArrayList.get(i);
        viewHolder.tvTitle.setText(article.getTitle());
        viewHolder.author.setText(article.getByline());
        viewHolder.category.setText(article.getNytdsection());
        viewHolder.date.setText(article.getPublished_date());
        if (!article.getMedia().isEmpty() && !article.getMedia().get(0).getMediametadata().isEmpty()) {
            Glide.with(context)
                    .load(article.getMedia().get(0).getMediametadata().get(1).getUrl())
                    .into(viewHolder.imgViewCover);
        }

        viewHolder.itemView.setOnClickListener(view -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent intent = new Intent(context, ArticleDetails.class);
            intent.putExtra("title", article.getTitle());
            intent.putExtra("desc", article.getAbstractX());
            intent.putExtra("date", article.getPublished_date());
            if (!article.getMedia().isEmpty() && !article.getMedia().get(0).getMediametadata().isEmpty()) {
                intent.putExtra("image", article.getMedia().get(0).getMediametadata().get(2).getUrl());
            }
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgViewCover;
        private final TextView tvTitle, author, category, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewCover = (ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            author = (TextView) itemView.findViewById(R.id.author);
            category = (TextView) itemView.findViewById(R.id.category);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
