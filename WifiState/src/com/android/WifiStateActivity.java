package com.android;

import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class WifiStateActivity extends Activity {
    /** Called when the activity is first created. */
	TextView textConnected,signal, textIp, textSsid, textBssid, textMac, textSpeed, textRssi;
	int i=0,j;
	 List<ScanResult> wifiList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textConnected = (TextView)findViewById(R.id.Connected);
        textIp = (TextView)findViewById(R.id.Ip);
       
        textSsid = (TextView)findViewById(R.id.Ssid);
        textBssid = (TextView)findViewById(R.id.Bssid);
        textMac = (TextView)findViewById(R.id.Mac);
        textSpeed = (TextView)findViewById(R.id.Speed);
        textRssi = (TextView)findViewById(R.id.Rssi);
       signal=(TextView)findViewById(R.id.Sig);
        DisplayWifiState(i);
       
        this.registerReceiver(this.myWifiReceiver,
          new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        
    }
    private BroadcastReceiver myWifiReceiver = new BroadcastReceiver(){

   @Override
   public void onReceive(Context arg0, Intent arg1) {
    // TODO Auto-generated method stub
    NetworkInfo networkInfo = (NetworkInfo) arg1.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
  //  mainWifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);
  //  wifiList = mainWifi.getScanResults();
   // List<ScanResult> wifiList = mainWifi.getScanResults();
    if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
     DisplayWifiState(i);
     i++;
    
    }
   }};
	private WifiManager mainWifi;
	private void DisplayWifiState(int j) {
		// TODO Auto-generated method stub
		ConnectivityManager myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	    NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    WifiManager myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	  WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
	//List<ScanResult> wifiList = myWifiManager.getScanResults();
//if(wifiList.get(j)!= null)
//{   ScanResult scanResult = wifiList.get(j);}
        try {
			signal.append(String.valueOf(wifiList.get(j).level));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      //  signal.append(String.valueOf(myWifiManager.getScanResults().get(j).level));
	  textMac.setText(myWifiInfo.getMacAddress());
	  
	    if (myNetworkInfo.isConnected()){
	     int myIp = myWifiInfo.getIpAddress();
	      
	     textConnected.setText("--- CONNECTED ---");
	      
	     int intMyIp3 = myIp/0x1000000;
	     int intMyIp3mod = myIp%0x1000000;
	      
	     int intMyIp2 = intMyIp3mod/0x10000;
	     int intMyIp2mod = intMyIp3mod%0x10000;
	      
	     int intMyIp1 = intMyIp2mod/0x100;
	     int intMyIp0 = intMyIp2mod%0x100;
	      
	     textIp.setText(String.valueOf(intMyIp0)
	       + "." + String.valueOf(intMyIp1)
	       + "." + String.valueOf(intMyIp2)
	       + "." + String.valueOf(intMyIp3)
	       );
	      
	     textSsid.setText(myWifiInfo.getSSID());
	     textBssid.setText(myWifiInfo.getBSSID());
	     
	     textSpeed.setText(String.valueOf(myWifiInfo.getLinkSpeed()) + " " + WifiInfo.LINK_SPEED_UNITS);
	     textRssi.setText(String.valueOf(myWifiInfo.getRssi()));
	     
	    }
	    else{
	     textConnected.setText("--- DIS-CONNECTED! ---");
	     textIp.setText("---");
	     textSsid.setText("---");
	     textBssid.setText("---");
	     textSpeed.setText("---");
	     textRssi.setText("---");
	    }
	}
}