package com.example.rootchecker;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String APP_NAME_SUPERUSER = "com.noshufou.android.su";
	private TextView txtMain;
	private TextView txtSub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String message = "";

        txtMain = (TextView)findViewById(R.id.txtMain);
        txtSub  = (TextView)findViewById(R.id.txtSub);

    	try {
    		getPackageManager().getApplicationInfo(APP_NAME_SUPERUSER, 0);
    		message = "インストール済";
    	} catch (NameNotFoundException e) {
    		e.printStackTrace();
    		message = "未インストール(NameNotFoundException)";
    	} catch (Exception e) {
    		e.printStackTrace();
    		message = "Exception - " + e.getMessage();
    	}
    	txtMain.setText("Superuser:" + message);

    	try {
			Process process = Runtime.getRuntime().exec("su");
			process.destroy();
	    	message = "成功";
		} catch (IOException e) {
			e.printStackTrace();
	    	message = "IOException - " + e.getMessage();
		} catch (Exception e){
			e.printStackTrace();
	    	message = "Exception - " + e.getMessage();
		}
    	txtSub.setText("suコマンド:" + message);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


}
