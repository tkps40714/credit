package android.example.com.creditcoupon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity_detail extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize the views.
        TextView detail_Title = findViewById(R.id.detail_title);
        TextView detail_Content = findViewById(R.id.detail_content);
        ImageView detail_Image = findViewById(R.id._ImageDetail);


        // Set the text from the Intent extra.
        detail_Title.setText(getIntent().getStringExtra("title"));
        detail_Content.setText(getIntent().getStringExtra("content"));
        uri = getIntent().getStringExtra("uri");

        // Load the image using the Glide library and the Intent extra.
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(detail_Image);
    }

    public void share_to(View view) {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "要分享的標題");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "要分享的內文");
        startActivity(Intent.createChooser(shareIntent,"Share to your friends!!"));
    }

    public void open_web(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(uri));
        startActivity(i);
    }

    public void add_collection(View view) {
        Toast.makeText(this,"UNDO!!!!",Toast.LENGTH_LONG).show();
    }
}
