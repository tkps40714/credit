package android.example.com.creditcoupon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextMessage;

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
        initView();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

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
    private Button btnCode;
    private void initView(){
        btnCode = (Button) findViewById(R.id.btn_getcode);
        btnCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Builder builder = new Builder();
                builder.add("phone", "13691599815");

                Request request = new Request.Builder()
                        .url("http://140.116.96.199:8888/getCode/")
                        .post(builder.build())
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg = handler.obtainMessage();
                        msg.obj = response.body().string();
                        handler.sendMessage(msg);
                    }
                });
            }
        }).start();

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String m = (String) msg.obj;
            try{
            m = URLDecoder.decode(m, "utf-8");
            btnCode.setText(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return false;
    }
});
}
