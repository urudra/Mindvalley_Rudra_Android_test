package com.r.mindvalley;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private final ArrayList<Photo> photos = new ArrayList<>();
    private ApiService apiService;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener());
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new PhotoAdapter(photos);
        mRecyclerView.setAdapter(mAdapter);

        apiService = ServiceGenerator.createService(ApiService.class);

        // get data from server
        apiService.getList().enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    photos.addAll(response.body());
                    // FIXME: 7/9/16 fix it before you get down i trap
                    mAdapter.notifyDataSetChanged();
                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


    /**
     * To make handle interactions between {@link #mRecyclerView } and {@link #mSwipeRefreshLayout}
     * and refresh data items
     */
    private class OnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {

            Snackbar.make(mSwipeRefreshLayout,
                    "Do Some refresh ",
                    Snackbar.LENGTH_SHORT)
                    .setAction("Refresh", new OnRefreshClickListener())
                    .show();

        }

        /**
         * When {@link Snackbar#setAction(CharSequence, View.OnClickListener)} clicked then
         * cancel {@link #mSwipeRefreshLayout}
         * // FIXME: 7/9/16 Some HardCode added to this part, find some better solution
         */
        private class OnRefreshClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }
}