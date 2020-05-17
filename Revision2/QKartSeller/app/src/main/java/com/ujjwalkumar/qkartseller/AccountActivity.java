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
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.bumptech.glide.Glide;

public class AccountActivity extends AppCompatActivity {
	
	
	private LinearLayout linearlayout;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private LinearLayout linearedit;
	private ImageView imageviewback;
	private TextView textview5;
	private ImageView imageviewprofile;
	private TextView textviewname;
	private TextView textviewaddress;
	private TextView textviewcontact;
	private TextView textviewemail;
	private ImageView imageview1;
	private TextView textview15;
	
	private Intent inp = new Intent();
	private SharedPreferences sp1;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.account);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linearedit = (LinearLayout) findViewById(R.id.linearedit);
		imageviewback = (ImageView) findViewById(R.id.imageviewback);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageviewprofile = (ImageView) findViewById(R.id.imageviewprofile);
		textviewname = (TextView) findViewById(R.id.textviewname);
		textviewaddress = (TextView) findViewById(R.id.textviewaddress);
		textviewcontact = (TextView) findViewById(R.id.textviewcontact);
		textviewemail = (TextView) findViewById(R.id.textviewemail);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview15 = (TextView) findViewById(R.id.textview15);
		sp1 = getSharedPreferences("info", Activity.MODE_PRIVATE);
		
		linearedit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				inp.setAction(Intent.ACTION_VIEW);
				inp.setClass(getApplicationContext(), EditdetailsActivity.class);
				inp.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(inp);
				finish();
			}
		});
		
		imageviewback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				inp.setAction(Intent.ACTION_VIEW);
				inp.setClass(getApplicationContext(), HomeActivity.class);
				inp.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(inp);
				finish();
			}
		});
	}
	private void initializeLogic() {
		android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable(); gd1.setColor(Color.parseColor("#B0BEC5")); gd1.setCornerRadius(180); imageviewprofile.setBackground(gd1);
		android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable(); gd2.setColor(Color.parseColor("#FFFFFF")); gd2.setCornerRadius(50); linear4.setBackground(gd2);
		textviewname.setText(sp1.getString("name", ""));
		textviewcontact.setText(sp1.getString("contact", ""));
		textviewemail.setText(sp1.getString("email", ""));
		textviewaddress.setText(sp1.getString("address", ""));
		if (!sp1.getString("img", "").equals("")) {
			Glide.with(getApplicationContext()).load(Uri.parse(sp1.getString("img", ""))).into(imageviewprofile);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
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
