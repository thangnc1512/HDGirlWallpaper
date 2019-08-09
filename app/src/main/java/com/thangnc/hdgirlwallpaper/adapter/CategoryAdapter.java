package com.thangnc.hdgirlwallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thangnc.hdgirlwallpaper.R;
import com.thangnc.hdgirlwallpaper.holder.CategoryHolder;
import com.thangnc.hdgirlwallpaper.holder.LoadHolder;
import com.thangnc.hdgirlwallpaper.model.category.Category;
import com.thangnc.hdgirlwallpaper.utils.ItemClickListener;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean onLoadMore = true;

    private ItemClickListener itemClickListener;

    public boolean isOnLoadMore(){
        return onLoadMore;
    }

    public void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
    }



    Context context;
    List<Category> categories;


    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent,false);
            return new CategoryHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.loadmore, parent, false);
            return new LoadHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CategoryHolder){
            Category category = categories.get(position);
            ((CategoryHolder) holder).tvCateName.setText(category.getName());

        } else if (holder instanceof LoadHolder){

        }
    }

    int ITEM = 1;
    int LOAD_MORE = 2;
    @Override
    public int getItemViewType(int position) {
        if (onLoadMore){
            if (position == categories.size() -1 )
                return LOAD_MORE;
            else return ITEM;
        }else return ITEM;

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
