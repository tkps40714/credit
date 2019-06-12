package android.example.com.creditcoupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TrackActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_main:
                        Intent intent_main = new Intent(TrackActivity.this,MainActivity.class);
                        startActivity(intent_main);
                        break;
                    case R.id.navigation_track:
                        break;
                    case R.id.navigation_notice:
                        Intent intent_notice = new Intent(TrackActivity.this,NoticeActivity.class);
                        startActivity(intent_notice);
                        break;
                    case R.id.navigation_my:
                        Intent intent_my = new Intent(TrackActivity.this,MyActivity.class);
                        startActivity(intent_my);
                        break;
                }
                return false;

            }
        });
    }

    public void click_mount(View view) {
        Intent intent = new Intent(this, TrackActivity_bank_mount.class);
        startActivity(intent);
    }

    public void click_taiwan(View view) {
        Intent intent = new Intent(this, TrackActivity_bank_taiwan.class);
        startActivity(intent);
    }

    public void click_china(View view) {
        Intent intent = new Intent(this, TrackActivity_bank_china.class);
        startActivity(intent);
    }
}
