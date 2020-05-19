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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Button;
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

public class ManageitemsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double t = 0;
	private double u = 0;
	private HashMap<String, Object> mp = new HashMap<>();
	private HashMap<String, Object> tmp = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> lmpitems = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> filtered = new ArrayList<>();
	
	private LinearLayout linearlayout;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private ImageView imageviewback;
	private TextView textview5;
	private ListView listview1;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear201;
	private LinearLayout linear18;
	private LinearLayout linear20;
	private TextView textview13;
	private TextView textview15;
	private EditText edittext1;
	private TextView textview18;
	private EditText edittext2;
	private TextView textview17;
	private EditText edittext3;
	private Button buttonclear;
	private Button buttonadd;
	
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
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		imageviewback = (ImageView) findViewById(R.id.imageviewback);
		textview5 = (TextView) findViewById(R.id.textview5);
		listview1 = (ListView) findViewById(R.id.listview1);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear201 = (LinearLayout) findViewById(R.id.linear201);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		textview13 = (TextView) findViewById(R.id.textview13);
		textview15 = (TextView) findViewById(R.id.textview15);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		textview18 = (TextView) findViewById(R.id.textview18);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		textview17 = (TextView) findViewById(R.id.textview17);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		buttonclear = (Button) findViewById(R.id.buttonclear);
		buttonadd = (Button) findViewById(R.id.buttonadd);
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
		
		buttonclear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
				edittext2.setText("");
				edittext3.setText("");
			}
		});
		
		buttonadd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext1.getText().toString().equals("")) {
					if (!edittext2.getText().toString().equals("")) {
						if (!edittext3.getText().toString().equals("")) {
							mp = new HashMap<>();
							mp.put("id", db4.push().getKey());
							mp.put("sellerid", sp1.getString("uid", ""));
							mp.put("name", edittext1.getText().toString());
							mp.put("detail", edittext2.getText().toString());
							mp.put("price", edittext3.getText().toString());
							db4.child(mp.get("id").toString()).updateChildren(mp);
							filtered.add(mp);
							listview1.setAdapter(new Listview1Adapter(filtered));
							((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
							edittext1.setText("");
							edittext2.setText("");
							edittext3.setText("");
							edittext1.requestFocus();
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "Enter item price");
						}
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Enter item details");
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter item name");
				}
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
				u = 0;
				for(int _repeat146 = 0; _repeat146 < (int)(lmpitems.size()); _repeat146++) {
					if (lmpitems.get((int)t).get("sellerid").toString().equals(sp1.getString("uid", ""))) {
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
					SketchwareUtil.showMessage(getApplicationContext(), "No items found");
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	
	private void _delete (final double _pos) {
		db4.child(filtered.get((int)_pos).get("id").toString()).removeValue();
		filtered.remove((int)(_pos));
		listview1.setAdapter(new Listview1Adapter(filtered));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				_v = _inflater.inflate(R.layout.manageitm, null);
			}
			
			final LinearLayout linearout = (LinearLayout) _v.findViewById(R.id.linearout);
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final TextView textviewitemname = (TextView) _v.findViewById(R.id.textviewitemname);
			final TextView textview2 = (TextView) _v.findViewById(R.id.textview2);
			final TextView textviewitemprice = (TextView) _v.findViewById(R.id.textviewitemprice);
			final ImageView imageviewdeleteitem = (ImageView) _v.findViewById(R.id.imageviewdeleteitem);
			final TextView textviewitemdetail = (TextView) _v.findViewById(R.id.textviewitemdetail);
			
			android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable(); gd1.setColor(Color.parseColor("#FFCCBC")); gd1.setCornerRadius(30); linearout.setBackground(gd1);
			textviewitemname.setText(filtered.get((int)_position).get("name").toString());
			textviewitemprice.setText(filtered.get((int)_position).get("price").toString());
			textviewitemdetail.setText(filtered.get((int)_position).get("detail").toString());
			imageviewdeleteitem.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					confirm.setTitle("Delete");
					confirm.setMessage("Do you want to delete ".concat(filtered.get((int)_position).get("name").toString().concat(" ?")));
					confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_delete(_position);
						}
					});
					confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					confirm.create().show();
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
