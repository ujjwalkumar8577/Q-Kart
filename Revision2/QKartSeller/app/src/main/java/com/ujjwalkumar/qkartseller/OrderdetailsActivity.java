package com.ujjwalkumar.qkartseller;

import androidx.appcompat.app.AppCompatActivity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class OrderdetailsActivity extends AppCompatActivity {
	
	
	private HashMap<String, Object> tmp = new HashMap<>();
	private double lat = 0;
	private double lng = 0;
	
	private ArrayList<HashMap<String, Object>> item = new ArrayList<>();
	
	private LinearLayout linearlayout;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ImageView imageviewback;
	private TextView textview5;
	private LinearLayout linear6;
	private LinearLayout linear14;
	private TextView textviewname;
	private TextView textviewaddress;
	private LinearLayout linear9;
	private ImageView imageviewcall;
	private ImageView imageviewlocate;
	private MapView mapview1;
	private GoogleMapController _mapview1_controller;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private ImageView imageview1;
	private TextView textview3;
	private ImageView imageview2;
	private TextView textview4;
	private ImageView imageview3;
	private TextView textview18;
	private ImageView imageview4;
	private TextView textview6;
	private ListView listview1;
	private TextView textview16;
	private TextView textviewamt;
	
	private Intent ino = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.orderdetails);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		imageviewback = (ImageView) findViewById(R.id.imageviewback);
		textview5 = (TextView) findViewById(R.id.textview5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		textviewname = (TextView) findViewById(R.id.textviewname);
		textviewaddress = (TextView) findViewById(R.id.textviewaddress);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		imageviewcall = (ImageView) findViewById(R.id.imageviewcall);
		imageviewlocate = (ImageView) findViewById(R.id.imageviewlocate);
		mapview1 = (MapView) findViewById(R.id.mapview1);
		mapview1.onCreate(_savedInstanceState);
		
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		textview18 = (TextView) findViewById(R.id.textview18);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview6 = (TextView) findViewById(R.id.textview6);
		listview1 = (ListView) findViewById(R.id.listview1);
		textview16 = (TextView) findViewById(R.id.textview16);
		textviewamt = (TextView) findViewById(R.id.textviewamt);
		
		imageviewback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ino.setAction(Intent.ACTION_VIEW);
				ino.setClass(getApplicationContext(), HomeActivity.class);
				ino.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(ino);
				finish();
			}
		});
		
		imageviewcall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ino.setAction(Intent.ACTION_CALL);
				ino.setData(Uri.parse("tel:".concat(tmp.get("contact").toString())));
				startActivity(ino);
			}
		});
		
		imageviewlocate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ino.setAction(Intent.ACTION_VIEW);
				ino.setData(Uri.parse("geo:".concat(tmp.get("lat").toString().concat(",".concat(tmp.get("lng").toString())))));
				startActivity(ino);
			}
		});
		
		_mapview1_controller = new GoogleMapController(mapview1, new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap _googleMap) {
				_mapview1_controller.setGoogleMap(_googleMap);
				_mapview1_controller.moveCamera(lat, lng);
				_mapview1_controller.zoomTo(15);
				_mapview1_controller.addMarker("id", lat, lng);
				_mapview1_controller.setMarkerIcon("id", R.drawable.ic_location_on_black);
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	private void initializeLogic() {
		tmp = new Gson().fromJson(getIntent().getStringExtra("map"), new TypeToken<HashMap<String, Object>>(){}.getType());
		item = new Gson().fromJson(tmp.get("itemmap").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		lat = Double.parseDouble(tmp.get("lat").toString());
		lng = Double.parseDouble(tmp.get("lng").toString());
		textviewname.setText(tmp.get("name").toString());
		textviewaddress.setText(tmp.get("address").toString());
		textviewamt.setText(tmp.get("amt").toString());
		if ((Double.parseDouble(tmp.get("status").toString()) > 1) || (Double.parseDouble(tmp.get("status").toString()) == 1)) {
			imageview1.setImageResource(R.drawable.status1);
		}
		if ((Double.parseDouble(tmp.get("status").toString()) > 2) || (Double.parseDouble(tmp.get("status").toString()) == 2)) {
			imageview2.setImageResource(R.drawable.status2);
		}
		if ((Double.parseDouble(tmp.get("status").toString()) > 3) || (Double.parseDouble(tmp.get("status").toString()) == 3)) {
			imageview3.setImageResource(R.drawable.status3);
		}
		if ((Double.parseDouble(tmp.get("status").toString()) > 4) || (Double.parseDouble(tmp.get("status").toString()) == 4)) {
			imageview4.setImageResource(R.drawable.status4);
		}
		listview1.setAdapter(new Listview1Adapter(item));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapview1.onDestroy();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		mapview1.onStart();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mapview1.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mapview1.onResume();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		mapview1.onStop();
	}
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.items, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final TextView textviewitemname = (TextView) _v.findViewById(R.id.textviewitemname);
			final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
			final TextView textviewitemprice = (TextView) _v.findViewById(R.id.textviewitemprice);
			
			android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable(); gd1.setColor(Color.parseColor("#FFCCBC")); gd1.setCornerRadius(30); linear1.setBackground(gd1);
			textviewitemname.setText(item.get((int)_position).get("name").toString());
			textviewitemprice.setText(item.get((int)_position).get("price").toString());
			
			return _v;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
