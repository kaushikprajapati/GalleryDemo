package kaushik.demoapp;

import android.support.media.ExifInterface;

public class ImageModel {
    private String mPathToImage;
    private String mTitle;
    private String mAlbumName;
    private ExifInterface mExifInterface;

    public ImageModel(String title, String pathToImage, String albumName, ExifInterface exifInterface) {
        mPathToImage = pathToImage;
        mTitle = title;
        mExifInterface = exifInterface;
        mAlbumName = albumName;
    }

    public String getPathToImage() {
        return "file:///android_asset/" + mPathToImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public ExifInterface getExifInterface() {
        return mExifInterface;
    }

    @Override
    public String toString() {
        return mTitle + " " + mPathToImage ;
    }
}
