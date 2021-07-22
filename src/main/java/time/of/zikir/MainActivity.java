package time.of.zikir;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.widget.Toast;
import android.view.*;
import time.of.zikir.R;
import android.content.*;
 
import java.io.*;
import android.app.*;
import android.graphics.*;

public class MainActivity extends Activity {
	
	
	
	
	public class WebAppInterface {
		Context mContext;

		
		WebAppInterface(Context c) {
			mContext = c;
		}

		
		@JavascriptInterface
		public void alert(String toast) {
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			
			
			
		}
		@JavascriptInterface
		public String outp(){
			return "1.3";
		}
		@JavascriptInterface
		public String read(){
			String otpt="";
			try {
				String filename="rafiz.txt";
				FileInputStream fin=openFileInput(filename);
				InputStreamReader inputStreamReader=new InputStreamReader(fin);
				BufferedReader br=new BufferedReader(inputStreamReader);
				StringBuilder stringBuffer = new StringBuilder();
				String line=null;
				while ((line = br.readLine()) != null) {
					stringBuffer.append(line);
				}
				fin.close();
				inputStreamReader.close();
				//Toast.makeText(getApplicationContext(),stringBuffer.toString(),Toast.LENGTH_LONG).show();
				otpt= stringBuffer.toString();
				} catch (IOException e) {
				e.printStackTrace();
			}
			return otpt;
		}
		@JavascriptInterface
		public void write(String inp){
			
			FileOutputStream fos;

			try {
				fos = openFileOutput("rafiz.txt", Context.MODE_PRIVATE);
				//default mode is PRIVATE, can be APPEND etc.
				fos.write(inp.getBytes());
				fos.close();
				Toast.makeText(getApplicationContext(),"সেটিং সংরক্ষন হয়ে গেছে।",Toast.LENGTH_LONG).show();
			} catch (FileNotFoundException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}
			
			
		}
	}
	ProgressDialog progress;
	WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        browser=(WebView)findViewById(R.id.webkit);
		browser.getSettings().setJavaScriptEnabled(true);

		browser. clearCache(true);
		
		browser.setWebViewClient(new MyWebViewClient());
		browser.getSettings().setBuiltInZoomControls(true);
		browser.getSettings().setDisplayZoomControls(false);
		browser.getSettings().setLoadWithOverviewMode(true);
        browser.loadUrl("file:///android_asset/index.html");
        browser.addJavascriptInterface(new WebAppInterface(this), "abdboy");

		






    }
private class MyWebViewClient extends WebViewClient {
@Override
public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
	if ("abdboy.github.io".equals(request.getUrl().getHost())||"mailto:abdboy@protonmail.ch".equals(request.getUrl().toString())) {
// This is not local
	Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
	startActivity(intent);
	return true;
	}
	
		if ("thisapp.me".equals(request.getUrl().getHost())) {
			//for share
	Intent intent2=new Intent();intent2.setAction(Intent.ACTION_SEND);
	intent2.setType("text/plain");
	intent2.putExtra(Intent.EXTRA_TEXT,"Download \"Time of Zikir\" app from https://abdboy.github.io/toz for latest time of Namaz, Shahri, Iftar from Islamic Foundation Bangladesh with custom calculation!");
	startActivity(Intent.createChooser(intent2,"Share via"));
	return true;
}
// Otherwise, this is local
	
	
return false;

}

		
		


}





    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if((keyCode==KeyEvent.KEYCODE_BACK)&&browser.canGoBack()){
			browser.goBack(); 
			//Toast.makeText(getApplicationContext(),"S",Toast.LENGTH_SHORT).show();
			return true;}
        return super.onKeyDown(keyCode, event);
	}



}
