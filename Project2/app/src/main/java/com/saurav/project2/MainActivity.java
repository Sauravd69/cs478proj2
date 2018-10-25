package com.saurav.project2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private String EXTRA_POSITION = "POSITION";
    private GridView gridView;
    private ArrayList<String> carNames = new ArrayList<>();
    ConnectivityManager connManager;
    NetworkInfo networkInfo;

    private ArrayList<Integer> carImages = new ArrayList<>(Arrays.asList(R.drawable.hondasmall,
            R.drawable.toyotasmall,R.drawable.mazdasmall,R.drawable.dodgesmall,R.drawable.chevroletsmall,
            R.drawable.kiasmall,R.drawable.lexussmall,R.drawable.acurasmall,R.drawable.audismall,
            R.drawable.bmwsmall,R.drawable.cadillacsmall,R.drawable.fordsmall,R.drawable.gmcsmall,
            R.drawable.mercedesbenzsmall));

    private ArrayList<String> websites = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);

        //populate data from array to array list
        Collections.addAll(carNames,getResources().getStringArray(R.array.CarNames));
        Collections.addAll(websites,getResources().getStringArray(R.array.Websites));

        //register context menu for gridview
        registerForContextMenu(gridView);

        //creating my own adapter
        CustomAdapter customAdapter = new CustomAdapter(carNames, carImages, this);

        //set up adapter for gridview
        gridView.setAdapter(customAdapter);

        //1st functionality, short click on the cell
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ImageViewer.class);
                intent.putExtra(EXTRA_POSITION, (int) id);
                startActivity(intent);
            }
        });
    }

    //initializing context menu
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(contextMenu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, contextMenu);


        //
        //Another way to add context menu without creating menu under res folder
        //
        //we can set header title for context menu
        //menu.setHeaderTitle("Context Menu");
        //
        //adding 3 menu items in context menu
        //contextMenu.add(0, v.getId(), 0, "View Image");
        //contextMenu.add(0, v.getId(), 0, "Visit Website");
        //contextMenu.add(0,v.getId(),0,"List of Dealer");
        //
    }

    //2nd functionality, long click on the cell
    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        //getting information of the item(car) which is clicked(long)
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        switch (menuItem.getItemId()) {
            //if 1st menu item is selected which is to show image in full display
            case R.id.viewImage:
                Intent intent = new Intent(MainActivity.this, ImageViewer.class);
                intent.putExtra(EXTRA_POSITION, (int) info.position);
                startActivity(intent);
                return true;
            //if 2nd menu item is selected which is to go the official website of the selected car(item)
            case R.id.visitWebsite:
                connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                //check the connection
                if (networkInfo.isConnected())
                {
                    try{
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websites.get((int) info.position)));
                        startActivity(browserIntent);
                    }
                    catch (Exception e)
                    {}
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Check wifi connection",Toast.LENGTH_SHORT).show();
                }
                return true;
            //if 3rd menu item is selected which is to show 3 car dealers using list view for the selected car(item)
            case R.id.listofDealer:
                Intent intent2 = new Intent(MainActivity.this, CarDealer.class);
                intent2.putExtra(EXTRA_POSITION, (int) info.position);
                startActivity(intent2);
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }


    //
    //Without creating menu under res folder
    //
    /*@Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        //getting information of the item(car) which is clicked(long)
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        //if 1st menu item is selected which is to show image in full display
        if(menuItem.getTitle() == "View Image")
        {
            Intent intent = new Intent(MainActivity.this, ImageViewer.class);
            intent.putExtra(EXTRA_POSITION, (int) info.position);
            startActivity(intent);
        }
        //if 2nd menu item is selected which is to go the official website of the selected car(item)
        else if(menuItem.getTitle() == "Visit Website")
        {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websites.get((int) info.position)));
            startActivity(browserIntent);
        }
        //if 3rd menu item is selected which is to show 3 car dealers using list view for the selected car(item)
        else if(menuItem.getTitle() == "List of Dealer")
        {
            Intent intent = new Intent(MainActivity.this, CarDealer.class);
            intent.putExtra(EXTRA_POSITION, (int) info.position);
            startActivity(intent);
        }
        else {return false;}
        return true;
    }*/
}
