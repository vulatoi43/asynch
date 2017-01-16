package com.example.thanhvu.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView hinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hinh = (ImageView) findViewById(R.id.imageView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new loadhinh().execute("http://toananhdep.com/wp-content/uploads/2015/12/tuyen-tap-nhung-hinh-anh-girl-xinh-dang-yeu-nhat-viet-nam-9.jpg");
            }
        });
    }

    private class loadhinh extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL u = new URL(params[0]);
                Bitmap bmp = BitmapFactory.decodeStream(u.openConnection().getInputStream());
                hinh.setImageBitmap(bmp);
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this,"load xong",Toast.LENGTH_LONG);
        }
    }
}
