package com.thangnc.hdgirlwallpaper.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.thangnc.hdgirlwallpaper.R;

public class CategoryHolder extends RecyclerView.ViewHolder {
    public TextView tvCateName;
    public ImageView ivCate;
    public CardView cardView;

    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        tvCateName = itemView.findViewById(R.id.tvCateName);
        ivCate = itemView.findViewById(R.id.imgCategory);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
