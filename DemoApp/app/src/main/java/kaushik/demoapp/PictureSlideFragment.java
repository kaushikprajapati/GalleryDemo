package kaushik.demoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kaushik.demoapp.utils.ImageUtils;

public class PictureSlideFragment extends Fragment {

    private static final String IMAGE_PATH_KEY= "imagePath";
    private static final String ORIENTATION_KEY= "orientation";

    public static PictureSlideFragment newInstance(String pathToImage, int orientation) {
        PictureSlideFragment f = new PictureSlideFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_PATH_KEY, pathToImage);
        args.putInt(ORIENTATION_KEY, orientation);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.picture_preview_item_view, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView.getLayoutParams().height = ImageUtils.getScreenWidth(getActivity());
        imageView.getLayoutParams().width = ImageUtils.getScreenWidth(getActivity());
        Picasso.with(imageView.getContext()).
                load(getArguments().getString(IMAGE_PATH_KEY)).
                rotate(getArguments().getInt(ORIENTATION_KEY)).
                fit().
                centerCrop().
                into(imageView);
        return rootView;
    }
}
