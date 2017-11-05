package in.blogspot.techdroidsz.zapchat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by Abhishek on 05-11-2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        String notification_title=remoteMessage.getNotification().getTitle();
        String notificatio_message=remoteMessage.getNotification().getBody();

        String click_action=remoteMessage.getNotification().getClickAction();

        String from_user_id=remoteMessage.getData().get("from_user_id");

        NotificationCompat.Builder mBuilder=
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notification_title)
                .setContentText(notificatio_message);

        Intent resultIntent=new Intent(click_action);
        resultIntent.putExtra("user_id",from_user_id);



        PendingIntent resultPendingIntent=
                PendingIntent.getActivity(this,0,resultIntent,FLAG_UPDATE_CURRENT);

         mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId=(int)System.currentTimeMillis();

        NotificationManager mNotifyMgr=
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(mNotificationId,mBuilder.build());
    }
}
