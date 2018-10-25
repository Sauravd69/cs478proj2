package com.saurav.project2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CarDealer extends Activity {

    private String EXTRA_POSITION = "POSITION";
    private int index;

    //ArrayList of all dealers' address
    private ArrayList<String[]> allDealers = new ArrayList<String[]>();

    ConnectivityManager connManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_dealer);

        Intent intent = getIntent();
        index = intent.getIntExtra(EXTRA_POSITION,0);

        addAllDealers();

        ListView lv = (ListView) findViewById(R.id.listview);
        TextView textView = (TextView) findViewById(R.id.text);

        if(index == 0){textView.setText("List of Honda Dealer");}
        else if(index == 1){textView.setText("List of Toyota Dealer");}
        else if(index == 2){textView.setText("List of Mazda Dealer");}
        else if(index == 3){textView.setText("List of Dodge Dealer");}
        else if(index == 4){textView.setText("List of Chevrolet Dealer");}
        else if(index == 5){textView.setText("List of Kia Dealer");}
        else if(index == 6){textView.setText("List of Lexus Dealer");}
        else if(index == 7){textView.setText("List of Acura Dealer");}
        else if(index == 8){textView.setText("List of Audi Dealer");}
        else if(index == 9){textView.setText("List of BMW Dealer");}
        else if(index == 10){textView.setText("List of Cadillac Dealer");}
        else if(index == 11){textView.setText("List of Ford Dealer");}
        else if(index == 12){textView.setText("List of GMC Dealer");}
        else if(index == 13){textView.setText("List of Mercedes-Benz Dealer");}

        // Create the ArrayAdapter use the item row layout and the list data.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CarDealer.this, R.layout.activity_listview_item,
                 allDealers.get(index));

        // Set this adapter to inner ListView object.
        lv.setAdapter(adapter);

        //if user press on the address, then open google map and point on map that address
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                //getting location/address
                String[] d = allDealers.get(index);

                //check the connection
                if (networkInfo.isConnected())
                {
                    try{
                        String location = d[position];
                        location = location.replace(" ", "+");
                        Intent geoIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("geo:0,0?q=" + location));
                        startActivity(geoIntent);
                    }
                    catch (Exception e)
                    {}
                }
                else
                {
                    Toast.makeText(CarDealer.this,"Check wifi connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addAllDealers()
    {
        allDealers.add(getResources().getStringArray(R.array.hondaDealer));
        allDealers.add(getResources().getStringArray(R.array.toyotaDealer));
        allDealers.add(getResources().getStringArray(R.array.mazdaDealer));
        allDealers.add(getResources().getStringArray(R.array.dodgeDealer));
        allDealers.add(getResources().getStringArray(R.array.chevroletDealer));
        allDealers.add(getResources().getStringArray(R.array.kiaDealer));
        allDealers.add(getResources().getStringArray(R.array.lexusDealer));
        allDealers.add(getResources().getStringArray(R.array.acuraDealer));
        allDealers.add(getResources().getStringArray(R.array.audiDealer));
        allDealers.add(getResources().getStringArray(R.array.bmwDealer));
        allDealers.add(getResources().getStringArray(R.array.cadillacDealer));
        allDealers.add(getResources().getStringArray(R.array.fordDealer));
        allDealers.add(getResources().getStringArray(R.array.gmcDealer));
        allDealers.add(getResources().getStringArray(R.array.mercedesbenzDealer));
    }
}
