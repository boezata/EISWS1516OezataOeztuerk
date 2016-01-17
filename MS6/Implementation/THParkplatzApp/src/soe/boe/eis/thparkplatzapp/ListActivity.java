package soe.boe.eis.thparkplatzapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListActivity extends Activity {

	private NetworkConnection net;
	private ParkingPlaceList parkingPlaces;
	private String tag = "soe.boe.eis.thparkplatzapp";
	private String name;
	private Looper looper;

	/**
	 * gets the networkconnection sets the policy  
	 * 
	 * @param savedInstanceState
	 * 				the Bundle for the activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);

		net = DataTransfer.getInstance().getNetworkConnection();

		loadDatas();

	}

	/**
	 * gets the datas of the parking places if datas avaiable show them  
	 */
	private void loadDatas() {

		parkingPlaces = XMLFormatter.generateNewParkingPlaceObjectArray(net
				.getParkingPlaceList(DataTransfer.getInstance().getEmail(), DataTransfer.getInstance().getPassword()));

		if (parkingPlaces != null) {

			setContentView(R.layout.activity_list);

			ListView lv = (ListView) findViewById(R.id.list);

			ParkingPlaceAdapter listAdapter = new ParkingPlaceAdapter(getApplicationContext(), R.layout.row,
					(ArrayList<ParkingPlace>) parkingPlaces.getList(), this);

			lv.setAdapter(listAdapter);

		} else {
			// TODO Hier Quelltext einfügen
		}

	}

	/**
	 * starts the loading of datas new  
	 * 
	 * @param view
	 * 				the View sended with
	 */
	public void tryAgainToLoad(final View view) {
		loadDatas();
	}

	/**
	 * starts the navigation from the actual position to the position of the selected parking Place
	 * 
	 * @param view
	 * 				the View sended with
	 */
	public void navigateTo(final View view) {

		ParkingPlace selectedParkingPlace = parkingPlaces.getList().get(Integer.parseInt(view.getTag() + ""));

		LocationManager manager;

		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		List<String> providers = manager.getAllProviders();

		for (String name : providers) {

			LocationProvider lp = manager.getProvider(name);
			Log.d(tag, lp.getName() + " --- isProviderEnable(): " + manager.isProviderEnabled(name));

			Log.d(tag, "requiresCell(): " + lp.requiresCell());
			Log.d(tag, "requiresNetwork(): " + lp.requiresNetwork());
			Log.d(tag, "requiresSatellite(): " + lp.requiresSatellite());

		}

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		name = manager.getBestProvider(criteria, true);

		LocationListener listener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// Auto-generated method stubs
			}

			@Override
			public void onProviderDisabled(String provider) {
				// Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// Auto-generated method stub

			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// Auto-generated method stub

			}

		};

		try {
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);
		} catch (Exception e) {
		}

		manager.requestSingleUpdate(name, listener, looper);

		Location pstart = manager.getLastKnownLocation(name);

		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse("http://maps.google.com/maps?saddr=" + pstart.getLatitude() + "," + pstart.getLongitude()
						+ "&daddr=" + selectedParkingPlace.getLatitude() + "," + selectedParkingPlace.getLongitude()));

		startActivity(intent);

	}

	/**
	 * shows the dialog with information and prepare information
	 * 
	 * @param view
	 * 				the View sended with
	 */
	public void showInformation(final View view) {

		ParkingPlace selectedParkingPlace = parkingPlaces.getList().get(Integer.parseInt(view.getTag() + ""));

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListActivity.this);

		String text = "Adresse:\n\n" + selectedParkingPlace.getAddressStreet() + ", "
				+ selectedParkingPlace.getAddressNumber() + "\n" + selectedParkingPlace.getAddressPostalCode() + " "
				+ selectedParkingPlace.getAddressCity() + "\n\n\n";

		text = text + "Öffnungszeiten:\n\n";

		text = text + "Montag:";

		if (selectedParkingPlace.getOpeningtimes().getMondayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getMondayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getMondayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getMondayClosing() + "Uhr\n";
		}

		text = text + "Dienstag:";

		if (selectedParkingPlace.getOpeningtimes().getTuesdayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getTuesdayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getTuesdayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getTuesdayClosing() + "Uhr\n";
		}

		text = text + "Mittwoch:";

		if (selectedParkingPlace.getOpeningtimes().getWednesdayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getWednesdayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getWednesdayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getWednesdayClosing() + "Uhr\n";
		}

		text = text + "Donnerstag:";

		if (selectedParkingPlace.getOpeningtimes().getThursdayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getThursdayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getThursdayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getThursdayClosing() + "Uhr\n";
		}

		text = text + "Freitag:";

		if (selectedParkingPlace.getOpeningtimes().getFridayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getFridayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getFridayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getFridayClosing() + "Uhr\n";
		}

		text = text + "Samstag:";

		if (selectedParkingPlace.getOpeningtimes().getSaturdayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getSaturdayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getSaturdayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getSaturdayClosing() + "Uhr\n";
		}

		text = text + "Sonntag:";

		if (selectedParkingPlace.getOpeningtimes().getSundayOpening().toString().equals("00:00:00")
				&& selectedParkingPlace.getOpeningtimes().getSundayClosing().toString().equals("23:59:59")) {
			text = text + " ganztags\n";
		} else {
			text = text + "\n   " + selectedParkingPlace.getOpeningtimes().getSundayOpening() + "-"
					+ selectedParkingPlace.getOpeningtimes().getSundayClosing() + "Uhr\n";
		}

		if (selectedParkingPlace.isParkingPlacesForHandicapped()) {
			text = text + "\n\n\nEs gibt Behindertenparkplätze.";
		}

		if (selectedParkingPlace.isParkingPlacesForWomen() && !selectedParkingPlace.isParkingPlacesForHandicapped()) {
			text = text + "\n\n";
		}

		if (selectedParkingPlace.isParkingPlacesForWomen()) {
			text = text + "\nEs gibt Frauenparkplätze.";
		}

		alertDialogBuilder.setTitle("Information");
		alertDialogBuilder.setMessage(text);
		// set positive button: Yes message
		alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		// show alert
		alertDialog.show();

	}

}
