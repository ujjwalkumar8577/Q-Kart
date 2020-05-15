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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.DecimalFormat;

public class ManageitemsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double t = 0;
	private String selleruid = "";
	private HashMap<String, Object> tmp = new HashMap<>();
	private HashMap<String, Object> seller = new HashMap<>();
	private HashMap<String, Object> itm = new HashMap<>();
	private double amt = 0;
	
	private ArrayList<HashMap<String, Object>> lmpitems = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> filtered = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> cart = new ArrayList<>();
	
	private LinearLayout linearlayout;
	private LinearLayout linear5;
	private LinearLayout linear202;
	private LinearLayout linear8;
	private ImageView imageviewback;
	private TextView textview5;
	private TextView textview16;
	private TextView textviewamt;
	private LinearLayout linearproceed;
	private ImageView imageview1;
	private LinearLayout linear7;
	private ListView listview1;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private TextView textviewshopname;
	private TextView textviewaddress;
	private TextView textviewcontact;
	
	private Intent ini = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private DatabaseReference db4 = _firebase.getReference("items");
	private ChildEventListener _db4_child_listener;
	private SharedPreferences sp1;
	private AlertDialog.Builder confirm;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.manageitems);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear202 = (LinearLayout) findViewById(R.id.linear202);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		imageviewback = (ImageView) findViewById(R.id.imageviewback);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview16 = (TextView) findViewById(R.id.textview16);
		textviewamt = (TextView) findViewById(R.id.textviewamt);
		linearproceed = (LinearLayout) findViewById(R.id.linearproceed);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		listview1 = (ListView) findViewById(R.id.listview1);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		textviewshopname = (TextView) findViewById(R.id.textviewshopname);
		textviewaddress = (TextView) findViewById(R.id.textviewaddress);
		textviewcontact = (TextView) findViewById(R.id.textviewcontact);
		auth = FirebaseAuth.getInstance();
		sp1 = getSharedPreferences("info", Activity.MODE_PRIVATE);
		confirm = new AlertDialog.Builder(this);
		
		imageviewback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ini.setAction(Intent.ACTION_VIEW);
				ini.setClass(getApplicationContext(), HomeActivity.class);
				ini.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(ini);
				finish();
			}
		});
		
		linearproceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ini.setAction(Intent.ACTION_VIEW);
				ini.setClass(getApplicationContext(), MycartActivity.class);
				ini.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				ini.putExtra("itemmap", new Gson().toJson(cart));
				ini.putExtra("sellermap", getIntent().getStringExtra("map"));
				ini.putExtra("amount", String.valueOf(amt));
				startActivity(ini);
			}
		});
		
		_db4_child_listener = new ChildEventListener() {
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
		db4.addChildEventListener(_db4_child_listener);
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	private void initializeLogic() {
		seller = new Gson().fromJson(getIntent().getStringExtra("map"), new TypeToken<HashMap<String, Object>>(){}.getType());
		amt = 0;
		selleruid = seller.get("uid").toString();
		textviewshopname.setText(seller.get("name").toString());
		textviewaddress.setText(seller.get("address").toString());
		textviewcontact.setText(seller.get("contact").toString());
		_loadlist();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	private void _loadlist () {
		db4.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lmpitems = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lmpitems.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				t = 0;
				for(int _repeat146 = 0; _repeat146 < (int)(lmpitems.size()); _repeat146++) {
					if (lmpitems.get((int)t).get("sellerid").toString().equals(selleruid)) {
						tmp = lmpitems.get((int)t);
						filtered.add(tmp);
					}
					else {
						
					}
					t++;
				}
				if (filtered.size() > 0) {
					listview1.setAdapter(new Listview1Adapter(filtered));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "This seller has not added any items yet.");
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	private void _setAmount (final double _amt) {
		textviewamt.setText(String.valueOf(_amt));
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
			
			android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable(); gd1.setColor(Color.parseColor("#FFCCBC")); gd1.setCornerRadius(30); linear2.setBackground(gd1);
			textviewqty.setVisibility(View.GONE);
			textviewitemname.setText(filtered.get((int)_position).get("name").toString());
			textviewitemprice.setText(filtered.get((int)_position).get("price").toString());
			textviewdetail.setText(filtered.get((int)_position).get("detail").toString());
			imageviewop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					itm = filtered.get((int)_position);
					if (textviewqty.getText().toString().equals("0")) {
						imageviewop.setImageResource(R.drawable.ic_delete_black);
						textviewqty.setText("1");
						amt = amt + Double.parseDouble(filtered.get((int)_position).get("price").toString());
						_setAmount(amt);
						cart.add(itm);
					}
					else {
						imageviewop.setImageResource(R.drawable.ic_add_circle_black);
						textviewqty.setText("0");
					}
				}
			});
			
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
