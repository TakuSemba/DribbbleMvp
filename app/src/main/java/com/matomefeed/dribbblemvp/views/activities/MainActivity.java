package com.matomefeed.dribbblemvp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.matomefeed.dribbblemvp.R;
import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.presenters.MainPresenter;
import com.matomefeed.dribbblemvp.presenters.impl.MainPresenterImpl;
import com.matomefeed.dribbblemvp.views.MainView;
import com.matomefeed.dribbblemvp.views.adapters.ShotRecyclerAdapter;
import com.matomefeed.dribbblemvp.views.components.OnListItemClickedListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RxAppCompatActivity implements MainView, OnListItemClickedListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;

    @BindView(R.id.center_loading_view)
    AVLoadingIndicatorView centerLoadingView;

    @BindView(R.id.footer_loading_view)
    AVLoadingIndicatorView footerLoadingView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private ShotRecyclerAdapter shotRecyclerAdapter;
    private MainPresenter presenter;
    private final static int COLUMN_NUM = 3;
    private int page = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void init() {
        shotRecyclerAdapter = new ShotRecyclerAdapter(this, new ArrayList<Shot>(), this);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, COLUMN_NUM);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(shotRecyclerAdapter);
        hideFooterProgress();
        showCenterProgress();
        presenter.fetchShots(this, page);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                View view = v.getChildAt(v.getChildCount() - 1);
                int diff = (view.getBottom() - (v.getHeight() + v.getScrollY()));
                if (diff <= 500 && !isLoading) {
                    showFooterProgress();
                    presenter.fetchShots(MainActivity.this, ++page);
                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                clearShots();
                presenter.fetchShots(MainActivity.this, page);
            }
        });
    }

    @Override
    public void showCenterProgress() {
        isLoading = true;
        centerLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCenterProgress() {
        isLoading = false;
        centerLoadingView.setVisibility(View.GONE);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showFooterProgress() {
        isLoading = true;
        footerLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFooterProgress() {
        isLoading = false;
        footerLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void addShots(List<Shot> shots) {
        shotRecyclerAdapter.addAll(shots);
    }

    @Override
    public void clearShots() {
        shotRecyclerAdapter.clear();
    }

    @Override
    public void onListItemClicked(int position) {
        Shot shot = shotRecyclerAdapter.getItem(position);
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("shotId", shot.id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
