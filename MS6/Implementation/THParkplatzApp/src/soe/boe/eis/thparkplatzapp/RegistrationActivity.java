package soe.boe.eis.thparkplatzapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationActivity extends Activity {

	private NetworkConnection net;
	private String choosenMail;
	private String password;

	/**
	 * sets the policy, gets the NEtworkConnection
	 * 
	 * @param savedInstanceState
	 *            the Bundle of the avtivity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_registration1);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		
		net = DataTransfer.getInstance().getNetworkConnection();

	}

	/**
	 * gets the choosen mailadresse and starts to check the avaiablity
	 * 
	 * @param view
	 *            the View sended with by the button
	 */
	public void further1(final View view) {

		EditText ed = (EditText) findViewById(R.id.edRegistrationEMail);

		choosenMail = ed.getText().toString();

		final ProgressDialog ringProgressDialog = ProgressDialog.show(RegistrationActivity.this, getText(R.string.ringProgress1Title),
				getText(R.string.ringProgress1Message), true);
		
		ringProgressDialog.setCancelable(false);
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

	            int response = 0;
				
				try {
					response = net.checkAvaiabiltyOfMailAdress(choosenMail);
				} catch (Exception e) {
				}
				
				ringProgressDialog.dismiss();

				further1Reaction(response);
			
			}
		});
		
	}
	
	/**
	 * reacts to the response of the check of the mail address
	 * 
	 * @param response
	 *            the response of the check of the mail address
	 */
	public void further1Reaction (int response){
		
		Context context = this;

		if (response == 1) {

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

			alertDialogBuilder.setTitle(getText(R.string.registrationalert1title));

			alertDialogBuilder.setMessage(getText(R.string.registrationalert1message)).setCancelable(false)
					.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

		} else {

			if (response == 0) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				alertDialogBuilder.setTitle(getText(R.string.registrationalert2title));

				alertDialogBuilder.setMessage(getText(R.string.registrationalert2message)).setCancelable(false)
						.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();

			} else {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				alertDialogBuilder.setTitle(getText(R.string.registrationalert3title));

				alertDialogBuilder.setMessage(getText(R.string.registrationalert3message)).setCancelable(false)
						.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();

			}

			setContentView(R.layout.activity_registration2);

		}

	}

	/**
	 * saves and controls the passwords,prepares for the next view
	 * 
	 * @param view
	 *            the View sended with by the button
	 */
	public void further2(final View view) {

		EditText ed1 = (EditText) findViewById(R.id.edRegistrationPassword);
		EditText ed2 = (EditText) findViewById(R.id.edRegistrationPasswordRepeat);

		password = ed1.getText().toString();
		String passwordRepeat = ed2.getText().toString();

		if (password.equals(passwordRepeat)) {

			setContentView(R.layout.activity_registration3);

			Spinner sp = (Spinner) findViewById(R.id.spRegistrationChooseGroup);

			String[] list = new String[] { (String) getText(R.string.usergroupproff),
					(String) getText(R.string.usergroupworker), (String) getText(R.string.usergroupstudent),
					(String) getText(R.string.usergroupvisitor) };

			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
					list);

			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			sp.setAdapter(dataAdapter);

		} else {

			Context context = this;

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

			alertDialogBuilder.setTitle(getText(R.string.registrationalert4title));

			alertDialogBuilder.setMessage(getText(R.string.registrationalert4message)).setCancelable(false)
					.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

			ed1.setText("");
			ed2.setText("");

		}

	}

	/**
	 * navigates back to the start screen
	 * 
	 * @param view
	 *            the View sended with by the button
	 */
	public void back1(final View view) {

		startActivity(new Intent(this, LoginActivity.class));

		finish();

	}

	/**
	 * navigates back to the first screen
	 * 
	 * @param view
	 *            the View sended with by the button
	 */
	public void back2(final View view) {
		setContentView(R.layout.activity_registration1);
	}

	/**
	 * navigates back to the second screen
	 * 
	 * @param view
	 *            the View sended with by the button
	 */
	public void back3(final View view) {
		setContentView(R.layout.activity_registration2);
	}

	/**
	 * checks the group, gives the datas to the NEtworkConnection, reacts to the answer
	 * 
	 * @param view
	 *            the View sended with by the button
	 */
	public void registrationBT(final View view) {

		Spinner sp = (Spinner) findViewById(R.id.spRegistrationChooseGroup);

		Context context = this;
		AlertDialog alertDialog;

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		
		if (sp.getSelectedItemPosition() == 3 || choosenMail.contains("@smail.th-koeln.de")) {

			switch (net.registrateNewUser(choosenMail, password, sp.getSelectedItemPosition())) {
			case 0:
				alertDialogBuilder.setTitle(getText(R.string.registrationalert6title));

				alertDialogBuilder.setMessage(getText(R.string.registrationalert6message)).setCancelable(false)
						.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				alertDialog = alertDialogBuilder.create();
				alertDialog.show();

				break;

			case 1:
				setContentView(R.layout.activity_registration_ready);
				break;

			case 2:
				alertDialogBuilder.setTitle(getText(R.string.registrationalert7title));

				alertDialogBuilder.setMessage(getText(R.string.registrationalert7message)).setCancelable(false)
						.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

				alertDialog = alertDialogBuilder.create();
				alertDialog.show();

				break;
			}

		} else {

			alertDialogBuilder.setTitle(getText(R.string.registrationalert5title));

			alertDialogBuilder.setMessage(getText(R.string.registrationalert5message)).setCancelable(false)
					.setPositiveButton(getText(R.string.alertok), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});

			alertDialog = alertDialogBuilder.create();
			alertDialog.show();

		}

	}

	/**
	 * creates the menu
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
	 * reacts to the touch of an menu item 
	 * 
	 * @param item
	 *            the selected menu item
	 * @return an of the activity needed object
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
