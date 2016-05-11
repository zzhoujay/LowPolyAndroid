package com.zzhoujay.lowpolyandroid;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.zzhoujay.lowpoly.LowPoly;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("呵呵");
        progressDialog.setMessage("gggggg");

        final Long lastTime = System.currentTimeMillis();


        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    return LowPoly.generate(getResources().openRawResource(R.raw.image), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                assert imageView != null;
                progressDialog.dismiss();
                imageView.setImageBitmap(bitmap);
                Log.i("use time", "time:" + (System.currentTimeMillis() - lastTime));
            }
        }.execute();

        progressDialog.show();
    }
}
