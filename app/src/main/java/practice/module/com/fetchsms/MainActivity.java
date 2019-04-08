package practice.module.com.fetchsms;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if the permission is not granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
           //if the permission is not been granted then check
           //if the user has denied the permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
                // do nothing as user has denied
            }else{
                // a popup will appear asking for permission
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS);

            }
        }
    }

    public void onRequestPermissionResult(int requestCode,String permssions[],int[] grantResults){
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS:
            {
                //check whether the length of grantResults is greater than 0 and is equal to PERMISSION_GRANTED
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //broadcast receiver works in background
                    Toast.makeText(this, "Permission Permitted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Please Permit the permission for further functioning", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
