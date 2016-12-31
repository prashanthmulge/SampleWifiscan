package a.c;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



//import a.c.WifiActivity.WifiReceiver;
import android.app.Activity;
import android.content.BroadcastReceiver;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class WifiActivity extends Activity {
    private WifiManager mainWifi;
	//private WifiReceiver receiverWifi;
	BroadcastReceiver receiver;
TextView sig;
String s;
TestHttpPost t;
public Handler handler=new Handler();
private Timer myTimer;
TimerTask scanTask;
private long mStartTime;
static WebView view;
private Runnable mUpdateTimeTask = new Runnable() {
	   public void run() {
	       final long start = mStartTime;
	       long millis = SystemClock.uptimeMillis() - start;
	       int seconds = (int) (millis / 1000);
	       int minutes = seconds / 60;
	       seconds     = seconds % 60;
S();
	      sig.append("   sig");
	      Log.d("in", "running"); 
	   //   handler.removeCallbacks(this);
	    //  handler.postAtTime(this, 3000);
	      
	   }
	};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sig=(TextView)findViewById(R.id.Sig);
    //   view=(WebView)findViewById(R.id.webView1);
    t=new TestHttpPost(60,61,70);
    try {
		 s=t.executeHttpPost();
	//	 view.loadData(s, "text/html", null);
		// view.loadUrl(t.loadurl());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   Spanned g= Html.fromHtml(s);
		sig.setText(g);
		Toast.makeText(this, "My Service update", Toast.LENGTH_LONG).show();
		
	/*	myTimer = new Timer();
		scanTask=new TimerTask() {
			
	

		            	public void run() {
		// TODO Auto-generated method stub
		             	sig.append("sfg");
		             	S();
		             	
		     // android.widget.Toast.makeText(this, "Timer Started", Toast.LENGTH_LONG).show();

		  Log.d("in", "running");                                }
				
			};
			// mStartTime = System.currentTimeMillis();
		//	  handler.removeCallbacks(mUpdateTimeTask);
	      //      handler.postDelayed(mUpdateTimeTask, 3000);
	          //  handler.postDelayed(scanTask, 3000);
		//scanTask=
		
		/*myTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TimerMethod();
			}
		},0,40000);*/
//for(int i=0;i<4;i++)
	//  handler.postDelayed(scanTask, 3000);
		//myTimer.schedule(scanTask, 3000);
	          //  handler.removeCallbacks(mUpdateTimeTask);
	         //   handler.postDelayed(mUpdateTimeTask, 3000);
    }
    
    public void S()
    {
    	sig.append("infunc");
    	 handler.postDelayed(mUpdateTimeTask, 300);
    //	handler.removeCallbacks(scanTask);
    	
    }
   
}