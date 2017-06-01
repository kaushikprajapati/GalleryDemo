package kaushik.demoapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.media.ExifInterface;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by kprajapati on 5/30/17.
 */

public class ImageUtils {
    /**
     * Get rotation degree & rotate picture based on that
     * @param exifInterface
     * @return rotation degree as <code>int</code>
     */
    public static int getRotationDegree(ExifInterface exifInterface) {
        switch (exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return 270;
            case ExifInterface.ORIENTATION_NORMAL:
            case ExifInterface.ORIENTATION_UNDEFINED:
                return 0;
        }
        return 0;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
