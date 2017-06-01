package kaushik.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

import kaushik.demoapp.utils.MiscUtil;

public class AlbumViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mEmptyTextView;

    private ArrayList<ImageModel> mImageModels = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String albumName = null;
        if (getIntent().hasExtra(MiscUtil.INTENT_ALBUM_NAME_KEY)) {
            albumName = getIntent().getStringExtra(MiscUtil.INTENT_ALBUM_NAME_KEY);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mImageModels = MiscUtil.createImageModels(AlbumViewActivity.this,
                albumName);
        if (mImageModels != null && mImageModels.size() > 0) {
            mRecyclerView.setAdapter(new PictureViewAdapter(mImageModels));
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyTextView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(R.anim.slide_in_up, exitAnim);
    }
}
