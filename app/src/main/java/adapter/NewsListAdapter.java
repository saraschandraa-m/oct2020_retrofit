package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nexstacks.retrofit.R;

import java.util.ArrayList;

import model.Article;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListHolder> {

    private Context context;
    private ArrayList<Article> articles;

    public NewsListAdapter(Context context, ArrayList<Article> articles){
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsListHolder(LayoutInflater.from(context).inflate(R.layout.cell_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListHolder holder, int position) {
        Article item = articles.get(position);
        if(item != null){
            Glide.with(context).load(item.urlToImage).into(holder.mIvNewsImg);
            holder.mTvNewsTitle.setText(item.title);
            holder.mTvNewsDesc.setText(item.description);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class NewsListHolder extends RecyclerView.ViewHolder{

        private ImageView mIvNewsImg;
        private TextView mTvNewsTitle;
        private TextView mTvNewsDesc;

        public NewsListHolder(@NonNull View itemView) {
            super(itemView);

            mIvNewsImg = itemView.findViewById(R.id.iv_news_img);
            mTvNewsTitle = itemView.findViewById(R.id.tv_news_title);
            mTvNewsDesc = itemView.findViewById(R.id.tv_news_desc);
        }
    }
}
