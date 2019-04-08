package practice.module.com.smsreading;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.smsList);

        if (fetchInbox() != null) {
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fetchInbox());
            lv.setAdapter(adapter);
        }
    }

    public ArrayList<String> fetchInbox() {
        ArrayList<String> sms = new ArrayList<String>();
        Uri uri = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[]{"_id", "address", "body"};
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, reqCols, null, null, null);
        cursor.moveToFirst();
        String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
        String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));

        sms.add("Sender=>" + address + "\nMessage=>" + body);
        return sms;
    }
}
