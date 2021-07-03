package time.of.zikir;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.widget.Toast;
import android.view.*;
import time.of.zikir.R;

public class MainActivity extends Activity {

	WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        browser=(WebView)findViewById(R.id.webkit);
		browser.getSettings().setJavaScriptEnabled(true);

		browser. clearCache(true);
		browser.setWebViewClient(new WebViewClient());
		browser.getSettings().setBuiltInZoomControls(true);
		browser.getSettings().setDisplayZoomControls(false);
		browser.getSettings().setLoadWithOverviewMode(true);
        browser.loadUrl("file:///android_asset/index.html");









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
