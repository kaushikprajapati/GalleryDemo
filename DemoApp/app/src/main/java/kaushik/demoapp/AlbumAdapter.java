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


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private ArrayList<AlbumModel> mAlbums;
    private View.OnLongClickListener mOnLongClickListener;
    private View.OnClickListener mOnClickListener;

    public AlbumAdapter(ArrayList<AlbumModel> albumbs, View.OnClickListener onClickListener,
                        View.OnLongClickListener onLongClickListener) {
        mAlbums = albumbs;
        mOnClickListener = onClickListener;
        mOnLongClickListener = onLongClickListener;
    }

    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_image_card_item_view,
                parent, false);
        return new ViewHolder(v, mOnClickListener, mOnLongClickListener);
    }

    @Override
    public void onBindViewHolder(AlbumAdapter.ViewHolder holder, int position) {
        AlbumModel albumModel = mAlbums.get(position);
        holder.mTitleTextView.setText(albumModel.getAlbumName());
        ImageModel firstImageModel = albumModel.getImageModel();
        Picasso.with(holder.mImageView.getContext()).
                load(firstImageModel.getPathToImage()).
                rotate(ImageUtils.getRotationDegree(firstImageModel.getExifInterface())).
                fit().
                centerCrop().
                into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (mAlbums == null) {
            return 0;
        }
        return mAlbums.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public ImageView mImageView;
        public ViewHolder(View v, View.OnClickListener onClickListener,
                          View.OnLongClickListener longClickListener) {
            super(v);
            mTitleTextView = (TextView) v.findViewById(R.id.titleText);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
            v.setOnClickListener(onClickListener);
            v.setOnLongClickListener(longClickListener);
        }
    }
}
