package android.example.com.creditcoupon;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<Image> mImageData;
    private ImageAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    mTextMessage.setText(R.string.title_main);
                    return true;
                case R.id.navigation_track:
                    mTextMessage.setText(R.string.title_my);
                    return true;
                case R.id.navigation_notice:
                    mTextMessage.setText(R.string.title_notice);
                    return true;
                case R.id.navigation_my:
                    mTextMessage.setText(R.string.title_my);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();  onclck_http用

        //設定幾個圖一行
        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new
                GridLayoutManager(this, gridColumnCount));
        mImageData = new ArrayList<>();
        mAdapter = new ImageAdapter(this, mImageData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();


        mTextMessage = (TextView) findViewById(R.id.message);
        //final是為了scroll hide加
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        //scroll消去底下那層
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && navigation.isShown()) {
                    navigation.setVisibility(View.GONE);
                } else if (dy < 0 ) {
                    navigation.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_main:
                        break;
                    case R.id.navigation_track:
                        Intent intent_track = new Intent(MainActivity.this,TrackActivity.class);
                        startActivity(intent_track);
                        break;
                    case R.id.navigation_notice:
                        Intent intent_notice = new Intent(MainActivity.this,NoticeActivity.class);
                        startActivity(intent_notice);
                        break;
                    case R.id.navigation_my:
                        Intent intent_my = new Intent(MainActivity.this,MyActivity.class);
                        startActivity(intent_my);
                        break;
                }
                return false;

            }
        });

    }

    /**
     * Initialize the image data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.  // mount
        String[] imagesTitle_m = getResources()
                .getStringArray(R.array.image_mount_titles);
        String[] imagesContent_m = getResources()
                .getStringArray(R.array.image_mount_content);
        String[] imagesUri_m = getResources()
                .getStringArray(R.array.image_mount_uri);

        TypedArray ImageResources_m = getResources()
                .obtainTypedArray(R.array._images_mount);

        // Get the resources from the XML file.  // taiwan
        String[] imagesTitle_t = getResources()
                .getStringArray(R.array.image_taiwan_titles);
        String[] imagesContent_t = getResources()
                .getStringArray(R.array.image_taiwan_content);
        String[] imagesUri_t = getResources()
                .getStringArray(R.array.image_taiwan_uri);

        TypedArray ImageResources_t = getResources()
                .obtainTypedArray(R.array._images_taiwan);

        // Get the resources from the XML file.  // china
        String[] imagesTitle_c = getResources()
                .getStringArray(R.array.image_china_titles);
        String[] imagesContent_c = getResources()
                .getStringArray(R.array.image_china_content);
        String[] imagesUri_c = getResources()
                .getStringArray(R.array.image_china_uri);
        TypedArray ImageResources_c = getResources()
                .obtainTypedArray(R.array._images_china);

        // Clear the existing data (to avoid duplication).
        mImageData.clear();

        // Create the ArrayList of Sports objects with the titles and
        // information about each sport
        for (int i = 0; i < imagesTitle_m.length; i++) {
            mImageData.add(new Image(imagesTitle_m[i], imagesContent_m[i],imagesUri_m[i],ImageResources_m.getResourceId(i, 0)));
            mImageData.add(new Image(imagesTitle_t[i], imagesContent_t[i],imagesUri_t[i],ImageResources_t.getResourceId(i, 0)));
            mImageData.add(new Image(imagesTitle_c[i], imagesContent_c[i],imagesUri_c[i],ImageResources_c.getResourceId(i, 0)));
        } //imagesContent[i], imagesUri[i],

        // Recycle the typed array.
        ImageResources_m.recycle();
        ImageResources_t.recycle();
        ImageResources_c.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }


}
