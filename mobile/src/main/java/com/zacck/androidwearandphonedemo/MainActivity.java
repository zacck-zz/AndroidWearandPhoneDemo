package com.zacck.androidwearandphonedemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends AppCompatActivity implements DataApi.DataListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mPhoneClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the client and allow it to listen
        mPhoneClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //called when wear and phone connect
        Wearable.DataApi.addListener(mPhoneClient,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        //callled when a data item is chamged
        for(DataEvent mEvent : dataEventBuffer)
        {
            //if the data has been changed
            if(mEvent.getType() == DataEvent.TYPE_CHANGED)
            {
                DataItem mItem = mEvent.getDataItem();
                //check location of item
                if(mItem.getUri().getPath().compareTo("/data") == 0)
                {
                    DataMap mMap = DataMapItem.fromDataItem(mItem).getDataMap();

                    String uNAme = mMap.getString("username");
                    Log.i("Username Sent ", uNAme);


                }
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
