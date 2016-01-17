package soe.boe.eis.thparkplatzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {

	private NetworkConnection net;

	/**
	 * starts the activity, creates the waiter and the NetworkConnection object
	 * 
	 * @param savedInstanceState
	 * 				the bundle of the activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_start);
		
		Waiter pWaiter = new Waiter(this);
		
		pWaiter.start();
		
		net = new NetworkConnection();
		
		DataTransfer.getInstance().setNetworkConnection(net);
		
	}
	
	/**
	 * executes the private function further
	 */
	public void goFurther(){
		
		//TODO Hier Quelltext einfügen
		
		further();
		
	}

	/**
	 * starts the login activity
	 */
	private void further() {

		startActivity(new Intent(this, LoginActivity.class));
		
		finish();
		
	}

}

class Waiter extends Thread{

	StartActivity startActivity; 
	
	/**
	 * Constructer.
	 * 
	 * nothing to do
	 * 
	 * @param pStartActivity
	 *            the owner avtivity
	 */
	public Waiter(StartActivity pStartActivity) {
		startActivity = pStartActivity;
	}
	
	/**
	 * waits and than starts the further process
	 */
	public void run () {
		
		try {
			Thread.sleep(5000);
		} catch (Exception e){
			
		}
		
		startActivity.goFurther();
		
	}
	
}
