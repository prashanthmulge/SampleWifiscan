package code.droid;

import java.util.List;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class Wifiscan2Activity extends Activity implements OnClickListener {
	private static final String TAG = "WiFiDemo";
	WifiManager wifi;
	BroadcastReceiver receiver;

	TextView textStatus;
	Button buttonScan;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Setup UI
		textStatus = (TextView) findViewById(R.id.textStatus);
		buttonScan = (Button) findViewById(R.id.buttonScan);
		buttonScan.setOnClickListener(this);

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
/*
WifiConfiguration wc=new WifiConfiguration();
if ((wifi.isWifiEnabled() == true)) {

    Toast.makeText(this,"MOBILE Is Connected TO WI-FI!", Toast.LENGTH_LONG );
    		}

            else {

              AlertDialog.Builder WIFIOFF = new Builder(Wifiscan2Activity.this);
             WIFIOFF.setCancelable(false);
              WIFIOFF.setTitle("Connection Error");
              WIFIOFF.setMessage(" Please Enable Your WIFI/INTERNET !");
              WIFIOFF.setPositiveButton("Ok",
              new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog,int which) {
           startActivity(new Intent( Settings.ACTION_WIFI_SETTINGS));

                          }
                      });
              WIFIOFF.show();

          }
wc.SSID="\"fuckers\"";

wc.preSharedKey="\"9538222107\"";
wc.status = WifiConfiguration.Status.ENABLED;
wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
// connect to and enable the connection
int res = wifi.addNetwork(wc);
 wifi.enableNetwork(res, true);        
 

;*/
Toast.makeText(this,"MOBILE Is Connected TO WI-FI!", Toast.LENGTH_LONG );
		// Get WiFi status
		WifiInfo info = wifi.getConnectionInfo();
		textStatus.setText("");
		textStatus.append("\n\nWiFi Status: " + info.toString());

		// List available networks
		List<WifiConfiguration> configs = wifi.getConfiguredNetworks();
		for (WifiConfiguration config : configs) {
		//	textStatus.append("\n\n" + config.toString());
		}

		// Register Broadcast Receiver
		if (receiver == null)
			receiver = new WiFiScanReceiver(this);

	registerReceiver(receiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		Log.d(TAG, "onCreate()");
	}

	@Override
	public void onStop() {
		unregisterReceiver(receiver);
	}

	public void onClick(View view) {
		Toast.makeText(this, "On Click Clicked. Toast to that!!!",
				Toast.LENGTH_LONG).show();

		if (view.getId() == R.id.buttonScan) {
			Log.d(TAG, "onClick() wifi.startScan()");
			registerReceiver(receiver, new IntentFilter(
					WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

			wifi.startScan();
			
		}
	}

	public void update(ScanResult results) {
		// TODO Auto-generated method stub
		//for(ScanResult result:results){
		textStatus.append("SSID:"+results.SSID+" ");
		textStatus.append("level:"+results.level+"\n");
	//	}
	/*	try {
			Thread t=new Thread()
			{
				public void run()
				{
					String url = "http://192.168.1.2/test.php";  
					Intent i = new Intent(Intent.ACTION_VIEW);  
					i.setData(Uri.parse(url));  
					startActivity(i);
				}
			};
			t.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		
	}

}