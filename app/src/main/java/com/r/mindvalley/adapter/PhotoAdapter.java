package com.r.mindvalley.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r.mindvalley.R;
import com.r.mindvalley.holder.PhotoViewHolder;
import com.r.mindvalley.prototypes.Photo;

import java.util.ArrayList;

/**
 * Created by Laukik on 04-Sep-16.
 */
public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Photo> photos;

    public PhotoAdapter(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_view_holder, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PhotoViewHolder) holder).onBind(getItem(position));
    }

    /**
     * get {@link Photo} from list
     *
     * @param position
     * @return
     */
    private Photo getItem(int position) {
        return photos.get(position);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


}
