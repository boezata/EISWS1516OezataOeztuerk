package soe.boe.eis.thparkplatzapp;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkingPlaceAdapter extends ArrayAdapter<ParkingPlace> {

	private ArrayList<ParkingPlace> items;
	private static Activity specialContext;

	/**
	 * Constructor.
	 * 
	 * nothing to do
	 * 
	 * @param context
	 *            the context of the activity
	 * @param textViewResourceId
	 *            the id of the text ressource
	 * @param items
	 *            the items to show
	 * @param specialContext
	 *            the owner acvitvity
	 */
	@SuppressWarnings("static-access")
	public ParkingPlaceAdapter(Context context, int textViewResourceId, ArrayList<ParkingPlace> items, Activity specialContext) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.specialContext = specialContext;
	}

	/**
	 * nothing to do
	 * 
	 * @param position
	 *            the position of the selected item
	 * @param convertView
	 *            the item view to convert
	 * @param parent
	 *            the view group of the single item view
	 */
	@SuppressWarnings("static-access")
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) specialContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.row, null);
		}
		ParkingPlace parkingPlace = items.get(position);

		if (parkingPlace != null) {

			TextView nameLine = (TextView) v.findViewById(R.id.nameLine);
			TextView distanceLine = (TextView) v.findViewById(R.id.distanceLine);
			TextView parkingPlaceLine = (TextView) v.findViewById(R.id.parkingPlaceLine);
			ImageView navigationImage = (ImageView) v.findViewById(R.id.imageNavigate);
			ImageView infoImage = (ImageView) v.findViewById(R.id.imageViewInfo);
			if (nameLine != null) {
				nameLine.setText(parkingPlace.getName());
			}
			if (distanceLine != null) {
				distanceLine.setText("Entfernung: " + parkingPlace.getDistanceToFH() + "km");
			}
			if (parkingPlaceLine != null) {
				parkingPlaceLine
						.setText(parkingPlace.getFreeParkingPlaces() + "/" + parkingPlace.getParkingPlaces() + " frei");
			}
			if(navigationImage != null){
				navigationImage.setTag(position + "");
			}
			if(infoImage != null){
				infoImage.setTag(position + "");
			}

		}

		return v;

	}
	
}