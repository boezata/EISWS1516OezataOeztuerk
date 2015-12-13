package soe.boe.eis.thparkplatzapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

public class ListActivity extends Activity {

	private NetworkConnection net;
	private ParkingPlaceList parkingPlaces;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		
		net = DataTransfer.getInstance().getNetworkConnection();
		
		//TODO Hier Quelltext einfügen
		parkingPlaces = XMLFormatter.generateNewParkingPlaceObjectArray(net.getParkingPlaceList(DataTransfer.getInstance().getEmail(), DataTransfer.getInstance().getPassword()));
		
	}
	
}
