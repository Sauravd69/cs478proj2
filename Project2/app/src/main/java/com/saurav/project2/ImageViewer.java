package com.saurav.project2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ImageViewer extends AppCompatActivity {

    private String EXTRA_POSITION = "POSITION";
    private int index;
    private ImageView imageView;
    ConnectivityManager connManager;
    NetworkInfo networkInfo;
    private ArrayList<String> websites = new ArrayList<>();
    private ArrayList<Integer> carImages = new ArrayList<>(Arrays.asList(R.drawable.honda,
            R.drawable.toyota,R.drawable.mazda,R.drawable.dodge,R.drawable.chevrolet,R.drawable.kia,
            R.drawable.lexus,R.drawable.acura,R.drawable.audi,R.drawable.bmw,R.drawable.cadillac,
            R.drawable.ford,R.drawable.gmc,R.drawable.mercedesbenz));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        //populate dara from array to array list
        Collections.addAll(websites,getResources().getStringArray(R.array.Websites));

        imageView = (ImageView) findViewById(R.id.imageview);

        //getting position of item which is selected
        Intent intent = getIntent();
        index = intent.getIntExtra(EXTRA_POSITION,0);

        imageView.setImageResource(carImages.get(index));
    }

    //touch event to recognize if user touch on the image/screen's display
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                //if user touch and release his finger, then open the selected car's official website

                connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                //check the connection
                if (networkInfo.isConnected())
                {
                    try{
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websites.get(index)));
                        startActivity(browserIntent);
                    }
                    catch (Exception e)
                    {}
                }
                else {
                    Toast.makeText(ImageViewer.this,"Check wifi connection",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
