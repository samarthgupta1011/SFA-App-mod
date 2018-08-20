package com.samarthgupta.sfa_app.NotificationService;

import android.app.Notification;
import static com.samarthgupta.sfa_app.POJO.GlobalAccess.baseUrl;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.samarthgupta.sfa_app.Activities.TasksActivity;
import com.samarthgupta.sfa_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MyService extends Service {

    Context context = this;
    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSocket.on("connected", onConnectedListener);
        mSocket.on("update", onProgressUpdate);
        mSocket.connect();
    }

    private Emitter.Listener onConnectedListener = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];
            try {
                Log.e("Listening", (String) data.get("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private Emitter.Listener onProgressUpdate = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];

            String clientName;
            String jobName;

            try {
                clientName = data.getString("client");
                jobName = data.getString("job");
            } catch (JSONException e) {
                return;
            }

            Log.e("Listening", "I am notifying that update took place");
            addNotification(context, clientName, jobName);

        }
    };

    private void addNotification(Context context, String client, String jobName) {

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.client)
                        .setContentTitle("Progress update from client "+ client)
                        .setContentText(jobName + " is updated.")
                        .setPriority(Notification.PRIORITY_MAX);

        Intent notificationIntent = new Intent(context, TasksActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager != null) {
            manager.notify(0, builder.build());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        mSocket.on("connected", onConnectedListener);
        mSocket.on("update", onProgressUpdate);
        mSocket.connect();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
