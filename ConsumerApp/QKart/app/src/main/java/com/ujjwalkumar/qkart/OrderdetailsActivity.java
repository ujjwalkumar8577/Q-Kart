package com.ujjwalkumar.qkart;

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
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private ImageView imageview1;
	private TextView textview3;
	private ImageView imageview4;
	private TextView textview4;
	private ImageView imageview5;
	private TextView textview18;
	private ImageView imageview6;
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
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		textview18 = (TextView) findViewById(R.id.textview18);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
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
		textviewname.setText(tmp.get("name").toString());
		textviewaddress.setText(tmp.get("address").toString());
		textviewamt.setText(tmp.get("amt").toString());
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
			
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final TextView textviewdetail = (TextView) _v.findViewById(R.id.textviewdetail);
			final TextView textviewitemname = (TextView) _v.findViewById(R.id.textviewitemname);
			final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
			final TextView textviewitemprice = (TextView) _v.findViewById(R.id.textviewitemprice);
			final TextView textviewqty = (TextView) _v.findViewById(R.id.textviewqty);
			final ImageView imageviewop = (ImageView) _v.findViewById(R.id.imageviewop);
			
			android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable(); gd1.setColor(Color.parseColor("#FFCCBC")); gd1.setCornerRadius(30); linear1.setBackground(gd1);
			textviewqty.setVisibility(View.GONE);
			imageviewop.setVisibility(View.GONE);
			textviewitemname.setText(item.get((int)_position).get("name").toString());
			textviewitemprice.setText(item.get((int)_position).get("price").toString());
			textviewdetail.setText(item.get((int)_position).get("detail").toString());
			
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
