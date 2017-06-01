package kaushik.demoapp;

import android.content.Intent;
import android.support.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import kaushik.demoapp.utils.MiscUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener {
    private RecyclerView mRecyclerView;
    private TextView mEmptyTextView;

    private ArrayList<AlbumModel> mAlbums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().hide();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAlbums = creatAlbumModels();
        if(mAlbums != null && mAlbums.size() > 0) {
            mRecyclerView.setAdapter(new AlbumAdapter(mAlbums, this, this));
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyTextView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
        }
    }


    private ArrayList<AlbumModel> creatAlbumModels() {
        ArrayList<AlbumModel> albumModels = new ArrayList<>();
        String[] albums = MiscUtil.listAssetAlbums(MainActivity.this, MiscUtil.ROOT_ASSET_FOLDER_NAME);
        for(int i = 0; i < albums.length; i++) {
            String[] pictures = MiscUtil.listAssetAlbums(MainActivity.this, MiscUtil.ROOT_ASSET_FOLDER_NAME +
                    MiscUtil.PATH_SEPARATOR + albums[i]);
            ImageModel imageModel = null;
            for(int j=0; j< 1; j++) {
                ExifInterface exif = null;
                String picturePath = MiscUtil.ROOT_ASSET_FOLDER_NAME +
                        MiscUtil.PATH_SEPARATOR
                        +albums[i]+ MiscUtil.PATH_SEPARATOR
                        +pictures[j];
                try {

                    exif = new ExifInterface(getAssets().open(picturePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageModel = new ImageModel(pictures[j], picturePath, albums[i], exif);
            }
            albumModels.add(new AlbumModel(albums[i], imageModel));
        }
        return albumModels;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AlbumViewActivity.class);
        intent.putExtra(MiscUtil.INTENT_ALBUM_NAME_KEY,
                mAlbums.get(mRecyclerView.getChildLayoutPosition(v)).getAlbumName());
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        DialogFragmentWindow.newInstance(mAlbums.get(mRecyclerView.getChildLayoutPosition(v)).getAlbumName()).
                show(getSupportFragmentManager(), "sometag");
        return true;
    }
}
