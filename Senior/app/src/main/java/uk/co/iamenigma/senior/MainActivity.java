package uk.co.iamenigma.senior;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ContactButton contactButton;
    LocationManager locationManager;
    LocationListener locationListener;
    Intent callIntent;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactButton = new ContactButton();
        
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Check if the app already has permission

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Check if the app already has permission

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

        callIntent = new Intent(Intent.ACTION_CALL);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location", location.toString());
                String loc = location.toString();
                String newLoc = loc.substring(loc.indexOf("gps") + 4, loc.indexOf("acc") - 1);
                Toast.makeText(MainActivity.this, newLoc, Toast.LENGTH_SHORT).show();
                contactButton.sendLocation(newLoc, sharedPreferences.getString("phoneNumber", ""));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        sharedPreferences = this.getSharedPreferences("uk.co.iamenigma.senior", Context.MODE_PRIVATE);


        Intent intent = getIntent();
            if(!intent.getStringExtra("phoneNumber").equals("no")) {
                String phoneNumber = intent.getStringExtra("phoneNumber");
                Log.i("Log:", phoneNumber);
                //contactButton.setPhoneNumber(phoneNumber);
                Toast.makeText(MainActivity.this, "Phone Number set to " + phoneNumber, Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putString("phoneNumber", phoneNumber).apply();
            }

    }

    public void sendText(View view)
    {
        boolean worked = contactButton.sendText(sharedPreferences.getString("phoneNumber", ""));
        if (worked)
            Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
    }
    public void getLocation(View view)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Check if the app already has permission

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50, locationListener);
        }
    }



    public void onSettings(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void onCall(View view)
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Check if the app already has permission

            callIntent.setData(Uri.parse("tel:+" + sharedPreferences.getString("phoneNumber", "")));
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            startActivity(callIntent);

        } else {

            callIntent.setData(Uri.parse("tel:+" + sharedPreferences.getString("phoneNumber", "")));
            startActivity(callIntent);

        }


    }
}
