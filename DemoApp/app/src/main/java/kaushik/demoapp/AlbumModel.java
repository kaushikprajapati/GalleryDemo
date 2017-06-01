package kaushik.demoapp;

import java.util.ArrayList;

/**
 * Created by kprajapati on 5/31/17.
 */

public class AlbumModel {
    private String mAlbumName;
    private ImageModel mImageModel;

    public AlbumModel(String albumName, ImageModel imageModel) {
        mAlbumName = albumName;
        mImageModel = imageModel;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public ImageModel getImageModel() {
        return mImageModel;
    }
}
