package kaushik.demoapp;

import android.os.Bundle;
import android.support.media.ExifInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.io.IOException;
import java.util.ArrayList;

import kaushik.demoapp.utils.ImageUtils;
import kaushik.demoapp.utils.MiscUtil;

public class DialogFragmentWindow extends DialogFragment {

    ArrayList<ImageModel> mImageModels;

    public static DialogFragmentWindow newInstance(String albumName) {
        DialogFragmentWindow f = new DialogFragmentWindow();
        Bundle args = new Bundle();
        args.putString(MiscUtil.INTENT_ALBUM_NAME_KEY, albumName);
        f.setArguments(args);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_fragment, container);

        WrappedViewPager viewPager = (WrappedViewPager) view.findViewById(R.id.pager);
        if(getArguments().containsKey(MiscUtil.INTENT_ALBUM_NAME_KEY)) {
            mImageModels = createImageModels(getArguments().getString(MiscUtil.INTENT_ALBUM_NAME_KEY));
            viewPager.setAdapter(new PictureSlideAdapter(getChildFragmentManager()));
        }
        return view;
    }


    private ArrayList<ImageModel> createImageModels(String albumName) {
        ArrayList<ImageModel> imageModels = new ArrayList<>();
        String[] pictures = listAssetAlbums("Assets/" + albumName);
        for (String picture : pictures) {
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(getActivity().getAssets().open("Assets/" + albumName + "/" + picture));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageModels.add(new ImageModel(picture, "Assets/" + albumName + "/" + picture, albumName, exif));
        }
        return imageModels;
    }

    private String[] listAssetAlbums(String path) {
        String[] list;
        try {
            list = getActivity().getAssets().list(path);
        } catch (IOException e) {
            return null;
        }
        return list;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private class PictureSlideAdapter extends FragmentStatePagerAdapter {
        public PictureSlideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PictureSlideFragment.newInstance(mImageModels.get(position).getPathToImage(),
                    ImageUtils.getRotationDegree(mImageModels.get(position).getExifInterface()));
        }

        @Override
        public int getCount() {
            return mImageModels.size();
        }
    }
}
