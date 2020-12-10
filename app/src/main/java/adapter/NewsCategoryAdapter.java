package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nexstacks.retrofit.R;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryHolder> {

    private Context context;
    private String[] categoryNames;
    private CategoryClickListener listener;

    private int selectedPosition = -1;

    public void setListener(CategoryClickListener listener){
        this.listener = listener;
    }

    public NewsCategoryAdapter(Context context){
        this.context = context;
        this.categoryNames = context.getResources().getStringArray(R.array.news_categories);
    }

    @NonNull
    @Override
    public NewsCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCategoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryHolder holder, int position) {

        holder.mTvCategory.setText(categoryNames[position]);
        if(selectedPosition == position){
            holder.mLlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_category_select, null));
            holder.mTvCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));
        }else{
            holder.mLlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_category_unselect, null));
            holder.mTvCategory.setTextColor(ResourcesCompat.getColor(context.getResources(), R.color.colorGrey, null));
        }

        holder.mLlRoot.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            if(listener != null){
                listener.onCategoryClicked(categoryNames[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryNames.length;
    }

    class NewsCategoryHolder extends RecyclerView.ViewHolder{

        private LinearLayout mLlRoot;
        private TextView mTvCategory;

        public NewsCategoryHolder(@NonNull View itemView) {
            super(itemView);

            mLlRoot = itemView.findViewById(R.id.ll_root_category);
            mTvCategory = itemView.findViewById(R.id.tv_category_name);
        }
    }

    public interface CategoryClickListener{
        void onCategoryClicked(String category);
    }
}
