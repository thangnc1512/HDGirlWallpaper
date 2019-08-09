package com.thangnc.hdgirlwallpaper.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.thangnc.hdgirlwallpaper.R;
import com.thangnc.hdgirlwallpaper.adapter.CategoryAdapter;
import com.thangnc.hdgirlwallpaper.api.ApiService;
import com.thangnc.hdgirlwallpaper.api.ApiUtils;
import com.thangnc.hdgirlwallpaper.model.category.Category;
import com.thangnc.hdgirlwallpaper.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private List<Category> categories;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rvCatecory;
    private LinearLayoutManager linearLayoutManager;

    private int page = 1;
    private int per_page = 5;
    private SwipeRefreshLayout swipe;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        linearLayoutManager = new LinearLayoutManager(getContext());
        swipe = view.findViewById(R.id.swipe);
        rvCatecory = view.findViewById(R.id.rvCategory);
        rvCatecory.setAdapter(categoryAdapter);
        rvCatecory.setLayoutManager(linearLayoutManager);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                categories.clear();
                getCate(page, per_page);
            }
        });
        rvCatecory.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getCate(page+1, per_page);
            }
        });
        getCate(page, per_page);
        return view;
    }

    private void getCate(int page, int per_page) {
        ApiUtils.getInstance().getCategory(page, per_page).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                swipe.setRefreshing(false);
                if (response.body().size() == 0){
                    rvCatecory.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        @Override
                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                        }
                    });
                    categoryAdapter.setOnLoadMore(false);
                }
                categories.addAll(response.body());
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                swipe.setRefreshing(false);
            }
        });
    }


}
