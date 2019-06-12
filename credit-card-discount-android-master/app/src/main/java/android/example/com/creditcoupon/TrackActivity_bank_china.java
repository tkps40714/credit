package android.example.com.creditcoupon;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackActivity_bank_china extends AppCompatActivity {
    private TextView mTextMessage;
    private ArrayList<Image> mImageData;
    private ImageAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_bank_china);

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

    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] imagesTitle = getResources()
                .getStringArray(R.array.image_china_titles);

        TypedArray ImageResources = getResources()
                .obtainTypedArray(R.array._images_china);

        String[] imagesContent = getResources()
                .getStringArray(R.array.image_china_content);
        String[] imagesUri = getResources()
                .getStringArray(R.array.image_china_uri);

        // Clear the existing data (to avoid duplication).
        mImageData.clear();

        // Create the ArrayList of Sports objects with the titles and
        // information about each sport
        for (int i = 0; i < imagesTitle.length; i++) {
            mImageData.add(new Image(imagesTitle[i],imagesContent[i],imagesUri[i], ImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        ImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }
}
