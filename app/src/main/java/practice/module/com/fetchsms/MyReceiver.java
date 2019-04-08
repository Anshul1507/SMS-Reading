package practice.module.com.fetchsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    private String msg,phoneno;
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "Intent Received: "+intent.getAction());

        if(intent.getAction() == SMS_RECEIVED){
            //retrieves a map of extended data from the intent
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                Object[] mypdu = (Object[])bundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];

                for(int i = 0;i<mypdu.length;i++){
                    //for build versions >= API level 23
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        String format = bundle.getString("format");
                        //from pdu we get all object and smsmessage object using following code
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i],format);
                    }else {
                        // for less API VERSIONS than 23
                        message[i] = SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }
                    msg = message[i].getMessageBody();
                    phoneno = message[i].getOriginatingAddress();
                }
                Toast.makeText(context, "Mes" + msg +"\nNumber" + phoneno, Toast.LENGTH_LONG).show();
            }
        }

        }
}
