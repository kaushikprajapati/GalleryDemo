package kaushik.demoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaushik.demoapp.utils.ImageUtils;


public class PictureViewAdapter extends RecyclerView.Adapter<PictureViewAdapter.ViewHolder> {

    ArrayList<ImageModel> mImageModels;


    public PictureViewAdapter(ArrayList<ImageModel> imageModels) {
        mImageModels = imageModels;

    }

    @Override
    public PictureViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item_view,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PictureViewAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mImageModels.get(position).getTitle());
        Picasso.with(holder.mImageView.getContext()).
                load(mImageModels.get(position).getPathToImage()).
                rotate(ImageUtils.getRotationDegree(mImageModels.get(position).getExifInterface())).
                into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (mImageModels == null) {
            return 0;
        }
        return mImageModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTitleTextView = (TextView) v.findViewById(R.id.titleText);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

}
