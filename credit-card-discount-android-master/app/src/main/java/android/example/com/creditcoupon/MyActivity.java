package android.example.com.creditcoupon;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {
    private ArrayList<Image> mImageData;
    private FavorAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_main:
                        Intent intent_main = new Intent(MyActivity.this,MainActivity.class);
                        startActivity(intent_main);
                        break;
                    case R.id.navigation_track:
                        Intent intent_track = new Intent(MyActivity.this,TrackActivity.class);
                        startActivity(intent_track);
                        break;
                    case R.id.navigation_notice:
                        Intent intent_notice = new Intent(MyActivity.this,NoticeActivity.class);
                        startActivity(intent_notice);
                        break;
                    case R.id.navigation_my:
                        break;
                }
                return false;

            }
        });


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new
                GridLayoutManager(this, 1));
        mImageData = new ArrayList<>();
        mAdapter = new FavorAdapter(this, mImageData);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();

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
