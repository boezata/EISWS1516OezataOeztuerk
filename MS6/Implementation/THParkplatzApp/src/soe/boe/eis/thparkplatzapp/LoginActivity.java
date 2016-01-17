package soe.boe.eis.thparkplatzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private NetworkConnection net;

	/**
	 * sets the ThreadPolicy and gets the NetworkConnection
	 * 
	 * @param savedInstanceState
	 *            the Bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		
		net = DataTransfer.getInstance().getNetworkConnection();
		
	}

	/**
	 * gets the datas to login and tries to login and reacts to it
	 * 
	 * @param view
	 *            the View sended by the button
	 */
	public void loginBT (final View view){
		
		EditText edMail = (EditText) findViewById(R.id.edLoginMailAddress);
		EditText edPassword = (EditText) findViewById(R.id.edLoginPassword);

		String email = edMail.getText().toString();
		String password = edPassword.getText().toString();
		
		int response = net.tryToLogin(email, password);
		
		switch (response){
		case 0: 
			/*falsche Daten*/ 
			break;
		case 1: 
			DataTransfer.getInstance().setEmail(email);
			DataTransfer.getInstance().setPassword(password);
			startActivity(new Intent(this, ListActivity.class));
			finish();
			break;
		case 2: 
			/*keine Netzwerkverbindung*/ 
			break;
		}
		
		
	}
	
	/**
	 * tries to ask for the forgotten password mail and tries to login and reacts to it
	 * 
	 * @param view
	 *            the View sended by the button
	 */
	public void passwordForgetBT (final View view) {
		
		EditText edMail = (EditText) findViewById(R.id.edLoginMailAddress);
		String email = edMail.getText().toString();
		
		switch (net.passwordForgottenProcess(email)){
		//TODO Hier Quelltext einfügen
		case 0: 
			/*falsche Daten*/ 
			break;
		//TODO Hier Quelltext einfügen
		case 1: 
			/*E-Mail sollte gesendet worden sein*/
			break;
		//TODO Hier Quelltext einfügen
		case 2: 
			/*keine Netzwerkverbindung*/ 
			break;
		}
		
	}
	
	/**
	 * starts the activity to register
	 * 
	 * @param view
	 *            the View sended by the button
	 */
	public void registrationBT (final View wiew) {
		
		startActivity(new Intent(this, RegistrationActivity.class));
		
		finish();
		
	}
	
	/**
	 * shows the menu
	 * 
	 * @param menu
	 *            the menu object
	 * @return <tt>true</tt>
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * reacts on the touched menu items
	 * 
	 * @param item
	 *            the selected item
	 * @return a by the acticity needed boolean
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();

		if (id == R.id.action_about) {
		
			// TODO Hier Quelltext einfügen
			return true;
			
		}
		
		return super.onOptionsItemSelected(item);
		
	}
}
