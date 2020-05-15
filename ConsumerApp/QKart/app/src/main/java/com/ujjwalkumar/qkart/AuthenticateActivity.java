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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.view.View;

public class AuthenticateActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double t = 0;
	
	private ArrayList<HashMap<String, Object>> lmp = new ArrayList<>();
	
	private LinearLayout linearlayout;
	private LinearLayout linearlogin;
	private LinearLayout linearsignup;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private TextView textview10;
	private TextView textview8;
	private LinearLayout linear19;
	private TextView textview9;
	private LinearLayout linear20;
	private EditText edittextle;
	private EditText edittextlp;
	private ImageView imageviewlogin;
	private TextView textview11;
	private TextView textviewsignup;
	private TextView textviewforgot;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private LinearLayout linear8;
	private TextView textview5;
	private TextView textview6;
	private LinearLayout linear10;
	private TextView textview7;
	private LinearLayout linear11;
	private EditText edittextse;
	private EditText edittextsp;
	private ImageView imageviewsignup;
	private TextView textview2;
	private TextView textviewlogin;
	
	private Intent ina = new Intent();
	private ObjectAnimator anix = new ObjectAnimator();
	private ObjectAnimator aniy = new ObjectAnimator();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private SharedPreferences sp1;
	private DatabaseReference db2 = _firebase.getReference("consumers");
	private ChildEventListener _db2_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.authenticate);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		linearlogin = (LinearLayout) findViewById(R.id.linearlogin);
		linearsignup = (LinearLayout) findViewById(R.id.linearsignup);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		textview10 = (TextView) findViewById(R.id.textview10);
		textview8 = (TextView) findViewById(R.id.textview8);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		textview9 = (TextView) findViewById(R.id.textview9);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		edittextle = (EditText) findViewById(R.id.edittextle);
		edittextlp = (EditText) findViewById(R.id.edittextlp);
		imageviewlogin = (ImageView) findViewById(R.id.imageviewlogin);
		textview11 = (TextView) findViewById(R.id.textview11);
		textviewsignup = (TextView) findViewById(R.id.textviewsignup);
		textviewforgot = (TextView) findViewById(R.id.textviewforgot);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview6 = (TextView) findViewById(R.id.textview6);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		textview7 = (TextView) findViewById(R.id.textview7);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		edittextse = (EditText) findViewById(R.id.edittextse);
		edittextsp = (EditText) findViewById(R.id.edittextsp);
		imageviewsignup = (ImageView) findViewById(R.id.imageviewsignup);
		textview2 = (TextView) findViewById(R.id.textview2);
		textviewlogin = (TextView) findViewById(R.id.textviewlogin);
		auth = FirebaseAuth.getInstance();
		sp1 = getSharedPreferences("info", Activity.MODE_PRIVATE);
		
		imageviewlogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sp1.edit().putString("email", edittextle.getText().toString()).commit();
				sp1.edit().putString("password", edittextlp.getText().toString()).commit();
				auth.signInWithEmailAndPassword(edittextle.getText().toString(), edittextlp.getText().toString()).addOnCompleteListener(AuthenticateActivity.this, _auth_sign_in_listener);
			}
		});
		
		textviewsignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linearlogin.setVisibility(View.GONE);
				linearsignup.setVisibility(View.VISIBLE);
			}
		});
		
		textviewforgot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittextle.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter your email");
				}
				else {
					auth.sendPasswordResetEmail(edittextle.getText().toString()).addOnCompleteListener(_auth_reset_password_listener);
				}
			}
		});
		
		imageviewsignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sp1.edit().putString("email", edittextse.getText().toString()).commit();
				sp1.edit().putString("password", edittextsp.getText().toString()).commit();
				auth.createUserWithEmailAndPassword(edittextse.getText().toString(), edittextsp.getText().toString()).addOnCompleteListener(AuthenticateActivity.this, _auth_create_user_listener);
			}
		});
		
		textviewlogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linearlogin.setVisibility(View.VISIBLE);
				linearsignup.setVisibility(View.GONE);
			}
		});
		
		_db2_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db2.addChildEventListener(_db2_child_listener);
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				ina.setAction(Intent.ACTION_VIEW);
				ina.setClass(getApplicationContext(), EditdetailsActivity.class);
				ina.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(ina);
				finish();
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					db2.addListenerForSingleValueEvent(new ValueEventListener() {
						@Override
						public void onDataChange(DataSnapshot _dataSnapshot) {
							lmp = new ArrayList<>();
							try {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								for (DataSnapshot _data : _dataSnapshot.getChildren()) {
									HashMap<String, Object> _map = _data.getValue(_ind);
									lmp.add(_map);
								}
							}
							catch (Exception _e) {
								_e.printStackTrace();
							}
							t = 0;
							for(int _repeat16 = 0; _repeat16 < (int)(lmp.size()); _repeat16++) {
								if (lmp.get((int)t).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
									break;
								}
								else {
									t++;
								}
							}
							sp1.edit().putString("uid", FirebaseAuth.getInstance().getCurrentUser().getUid()).commit();
							sp1.edit().putString("email", lmp.get((int)t).get("email").toString()).commit();
							sp1.edit().putString("name", lmp.get((int)t).get("name").toString()).commit();
							sp1.edit().putString("address", lmp.get((int)t).get("address").toString()).commit();
							sp1.edit().putString("lat", lmp.get((int)t).get("lat").toString()).commit();
							sp1.edit().putString("lng", lmp.get((int)t).get("lng").toString()).commit();
							sp1.edit().putString("contact", lmp.get((int)t).get("contact").toString()).commit();
							sp1.edit().putString("img", lmp.get((int)t).get("img").toString()).commit();
							ina.setAction(Intent.ACTION_VIEW);
							ina.setClass(getApplicationContext(), HomeActivity.class);
							ina.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							startActivity(ina);
							finish();
						}
						@Override
						public void onCancelled(DatabaseError _databaseError) {
						}
					});
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					SketchwareUtil.showMessage(getApplicationContext(), "Password reset mail sent");
				}
			}
		};
	}
	private void initializeLogic() {
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			ina.setAction(Intent.ACTION_VIEW);
			ina.setClass(getApplicationContext(), HomeActivity.class);
			ina.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(ina);
			finish();
		}
		else {
			android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable(); gd1.setColor(Color.parseColor("#FFFFFF")); gd1.setCornerRadius(50); linear14.setBackground(gd1);
			android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable(); gd2.setColor(Color.parseColor("#FFCCBC")); gd2.setCornerRadius(80); linear19.setBackground(gd2);
			android.graphics.drawable.GradientDrawable gd3 = new android.graphics.drawable.GradientDrawable(); gd3.setColor(Color.parseColor("#FFCCBC")); gd3.setCornerRadius(80); linear20.setBackground(gd3);
			android.graphics.drawable.GradientDrawable gd4 = new android.graphics.drawable.GradientDrawable(); gd4.setColor(Color.parseColor("#FFFFFF")); gd4.setCornerRadius(50); linear4.setBackground(gd4);
			android.graphics.drawable.GradientDrawable gd6 = new android.graphics.drawable.GradientDrawable(); gd6.setColor(Color.parseColor("#FFCCBC")); gd6.setCornerRadius(80); linear10.setBackground(gd6);
			android.graphics.drawable.GradientDrawable gd7 = new android.graphics.drawable.GradientDrawable(); gd7.setColor(Color.parseColor("#FFCCBC")); gd7.setCornerRadius(80); linear11.setBackground(gd7);
			linearlogin.setVisibility(View.VISIBLE);
			linearsignup.setVisibility(View.GONE);
			db2.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					lmp = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							lmp.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
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
