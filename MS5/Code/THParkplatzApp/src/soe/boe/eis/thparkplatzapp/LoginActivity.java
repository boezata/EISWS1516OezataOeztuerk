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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		
		net = DataTransfer.getInstance().getNetworkConnection();
		
	}

	public void loginBT (final View view){
		
		EditText edMail = (EditText) findViewById(R.id.edLoginMailAddress);
		EditText edPassword = (EditText) findViewById(R.id.edLoginPassword);

		String email = edMail.getText().toString();
		String password = edPassword.getText().toString();
		
		int response = net.tryToLogin(email, password);
		
		switch (response){
		//TODO Hier Quelltext einfügen
		case 0: 
			/*falsche Daten*/ 
			break;
		case 1: 
			DataTransfer.getInstance().setEmail(email);
			DataTransfer.getInstance().setPassword(password);
			startActivity(new Intent(this, ListActivity.class));
			finish();
			break;
		//TODO Hier Quelltext einfügen
		case 2: 
			/*keine Netzwerkverbindung*/ 
			break;
		}
		
		
	}
	
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
	
	public void registrationBT (final View wiew) {
		
		startActivity(new Intent(this, RegistrationActivity.class));
		
		finish();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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
