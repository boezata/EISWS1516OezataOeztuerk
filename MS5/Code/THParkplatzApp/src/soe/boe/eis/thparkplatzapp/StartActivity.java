package soe.boe.eis.thparkplatzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {

	private NetworkConnection net;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_start);
		
		Waiter pWaiter = new Waiter(this);
		
		pWaiter.start();
		
		net = new NetworkConnection();
		
		DataTransfer.getInstance().setNetworkConnection(net);
		
	}
	
	public void goFurther(){
		
		//TODO Hier Quelltext einfügen
		
		further();
		
	}

	private void further() {

		startActivity(new Intent(this, LoginActivity.class));
		
		finish();
		
	}

}

class Waiter extends Thread{

	StartActivity startActivity; 
	
	public Waiter(StartActivity pStartActivity) {
		startActivity = pStartActivity;
	}
	
	public void run () {
		
		try {
			Thread.sleep(5000);
		} catch (Exception e){
			
		}
		
		startActivity.goFurther();
		
	}
	
}
