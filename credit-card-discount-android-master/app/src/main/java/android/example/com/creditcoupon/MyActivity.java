package android.example.com.creditcoupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
    }
}
