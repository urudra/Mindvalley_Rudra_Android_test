package com.r.mindvalley.holder;

import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.r.mindvalley.MainActivity;
import com.r.mindvalley.R;
import com.r.mindvalley.prototypes.Category;
import com.r.mindvalley.prototypes.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Laukik on 04-Sep-16.
 */
public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private final TextView mTitleTextView;
    private final CardView mCardView;
    private final SwipeDismissBehavior<CardView> mSwipeDismissBehavior;
    private final AppCompatImageView mImageView;
    private final TextView mUserTextView;
    private final AppCompatImageView mUserImageView;


    private final RecyclerView mCategoriesRecyclerView;



    public PhotoViewHolder(View view) {
        super(view);
        mCardView = (CardView) view.findViewById(R.id.photo_item_card_view);
        mTitleTextView = (TextView) view.findViewById(R.id.photo_item_title_text_view);
        mImageView = (AppCompatImageView) view.findViewById(R.id.photo_item_image_view);
        // User
        mUserTextView = (TextView) view.findViewById(R.id.photo_item_user_text_view);
        mUserImageView = (AppCompatImageView) view.findViewById(R.id.photo_item_user_image_view);
        // Categories
        mCategoriesRecyclerView = (RecyclerView) view.findViewById(R.id.photo_item_categories_recycler_view);
        mCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mSwipeDismissBehavior = new SwipeDismissBehavior<>();
        mSwipeDismissBehavior.setListener(new OnCardSwipeDismissListener());

    }

    /**
     * To bind {@link Photo} item into {@link RecyclerView.ViewHolder}
     *
     * @param item
     */
    public void onBind(Photo item) {
        mTitleTextView.setText(item.getId());
        mImageView.setBackgroundColor(item.getColorInt());
        // load image
        Picasso.with(mCardView.getContext())
                .load(item.getUrls().getRegular())
                .into(mImageView);
        // {@link Photo#getUser()} information
        mUserTextView.setText(item.getUser().getName());

        Picasso.with(mCardView.getContext())
                .load(item.getUser().getProfileImage().getMedium())
                .into(mUserImageView);
        mCategoriesRecyclerView.setAdapter(new CategoryAdapter(item.getCategories()));

    }

    /**
     * To swipe cards for remove
     */
    private class OnCardSwipeDismissListener implements SwipeDismissBehavior.OnDismissListener {
        @Override
        public void onDismiss(View view) {
            Toast.makeText(view.getContext(), "Card Swiped !!!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDragStateChanged(int state) {
            Log.d(MainActivity.TAG, "onDragStateChanged() called with: " + "state = [" + state + "]");
        }
    }

    /**
     * To Adaptive {@link Category} and {@link Photo#getCategories()}
     * int {@link PhotoViewHolder}
     */
    private class CategoryAdapter extends RecyclerView.Adapter {
        private final ArrayList<Category> categories;

        public CategoryAdapter(ArrayList<Category> categories) {
            this.categories = categories;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_category_view, parent, false);
            return new CategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((CategoryViewHolder) holder).oBind(getItem(position));
        }

        private Category getItem(int position) {
            return categories.get(position);
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        /**
         * To bind {@link Category} into {@link CategoryAdapter}
         */
        private class CategoryViewHolder extends RecyclerView.ViewHolder {

            private final TextView mTitleTextView;

            public CategoryViewHolder(View view) {
                super(view);
                mTitleTextView = (TextView) view.findViewById(R.id.category_item_title_text_view);
            }

            public void oBind(Category item) {
                mTitleTextView.setText(item.getTitle());
            }
        }
    }


}
