package com.matomefeed.dribbblemvp.views.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.matomefeed.dribbblemvp.R;
import com.matomefeed.dribbblemvp.models.entities.Shot;
import com.matomefeed.dribbblemvp.presenters.impl.DetailPresenterImpl;
import com.matomefeed.dribbblemvp.views.DetailView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by takusemba on 2016/11/01.
 */

public class DetailActivity extends RxAppCompatActivity implements DetailView {

    @BindView(R.id.shot_id)
    TextView shotId;

    @BindView(R.id.shot_title)
    TextView shotTitle;

    @BindView(R.id.shot_description)
    TextView shotDescription;

    @BindView(R.id.author_id)
    TextView authorId;

    @BindView(R.id.author_name)
    TextView authorName;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.center_loading_view)
    AVLoadingIndicatorView centerLoadingView;

    private int id;

    private DetailPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if (getIntent().getExtras().containsKey("shotId")) {
            id = getIntent().getExtras().getInt("shotId");
        }
        presenter = new DetailPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void init() {
        showCenterProgress();
        presenter.fetchShot(this, id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBar();
            }
        });
    }

    @Override
    public void showSnackBar() {
        Snackbar.make(linearLayout, getString(R.string.hi), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showCenterProgress() {
        centerLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCenterProgress() {
        centerLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showShot(Shot shot) {
        shotId.setText(String.valueOf(shot.id));
        shotTitle.setText(shot.title);
        shotDescription.setText(shot.description);
        authorId.setText(String.valueOf(shot.user.id));
        authorName.setText(shot.user.name);
    }
}
