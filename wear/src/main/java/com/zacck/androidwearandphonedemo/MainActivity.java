package com.zacck.androidwearandphonedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class MainActivity extends Activity implements DataApi.DataListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private TextView mTextView;
    GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                String userName = "Zacck";

                mTextView = (TextView) stub.findViewById(R.id.text);
                //Create a mapped request to enable data sending
                PutDataMapRequest mPutDataMapRequest = PutDataMapRequest.create("/data");
                //put some data into the request
                mPutDataMapRequest.getDataMap().putString("username",userName);

                //create a request
                PutDataRequest mPutDataRequest = mPutDataMapRequest.asPutDataRequest();

                PendingResult<DataApi.DataItemResult> mPendingResult = Wearable.DataApi.putDataItem(mClient, mPutDataRequest);





            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //called when trhe wear and phone happens
    }

    @Override
    public void onConnectionSuspended(int i) {
        //called when connection is lost

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        //called when data is changedd

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
