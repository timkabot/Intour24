package com.innopolis.intour24;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.innopolis.intour24.presenter.Impl.GroupPresenterImpl;

/**
 * Created by ekaterina on 6/8/17.
 */

public class NetworkError extends AppCompatActivity {
    private static Context context;
    private static Activity activity;

    private static final String TAG = "NEDebug";

    public static void onNetworkAccured(Activity base){
        NetworkError.context = base.getApplicationContext();
        NetworkError.activity = base;

        activity.setContentView(R.layout.network_error);

        Button button = (Button)activity.findViewById(R.id.networkRetry);
        button.setOnClickListener(v -> {
            Log.d(TAG, "Retry button clicked");
            activity.recreate();
        });
    }


}
