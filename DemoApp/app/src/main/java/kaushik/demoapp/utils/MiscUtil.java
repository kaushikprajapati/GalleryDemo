package kaushik.demoapp.utils;

import android.app.Activity;
import android.support.media.ExifInterface;

import java.io.IOException;
import java.util.ArrayList;

import kaushik.demoapp.ImageModel;

public class MiscUtil {

    public static final String ROOT_ASSET_FOLDER_NAME="Assets";
    public static final String PATH_SEPARATOR="/";
    public static final String INTENT_ALBUM_NAME_KEY = "albumName";
    /**
     * Lists all files/folders at asset path (e.g empty path lists all files under assets folder)
     *
     * @param path directory path as <code>String</code>
     * @return <code>String[]</code> of files/directories
     */
    public static String[] listAssetAlbums(Activity activity, String path) {
        String[] list;
        try {
            list = activity.getAssets().list(path);
        } catch (IOException e) {
            return null;
        }
        return list;
    }

    /**
     * Create image models for all images in album
     * @param activity Current Activity
     * @param albumName Album's name as <code>String</code>
     * @return ArrayList of <code>ImageModel</code>
     * @see ImageModel also
     */
    public static ArrayList<ImageModel> createImageModels(Activity activity, String albumName) {
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        String[] pictures = listAssetAlbums(activity, ROOT_ASSET_FOLDER_NAME+ PATH_SEPARATOR + albumName);
        for (String picture : pictures) {
            ExifInterface exif = null;
            String picturePath = ROOT_ASSET_FOLDER_NAME+ PATH_SEPARATOR
                    + albumName + PATH_SEPARATOR + picture;
            try {

                exif = new ExifInterface(activity.getAssets().open(picturePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageModels.add(new ImageModel(picture, picturePath, albumName, exif));
        }
        return imageModels;
    }

}
