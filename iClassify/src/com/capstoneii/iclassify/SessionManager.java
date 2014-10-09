package com.capstoneii.iclassify;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	// Shared Preferences
		SharedPreferences pref;
		
		// Editor for Shared preferences
		Editor editor;
		
		// Context
		Context _context;
		
		// Shared pref mode
		int PRIVATE_MODE = 0;
		// Sharedpref file name
		private static final String PREF_NAME = "UserPref";
		
		// All Shared Preferences Keys
		private static final String IS_LOGIN = "IsLoggedIn";
		
		// User name (make variable public to access from outside)
		public static final String KEY_NAME = "name";
		
		// Email address (make variable public to access from outside)
		public static final String KEY_FNAME = "fname";
		
		// Email address (make variable public to access from outside)
		public static final String KEY_LNAME = "lname";

		// Email address (make variable public to access from outside)
		public static final String KEY_AGE = "age";	// Constructor

		// Email address (make variable public to access from outside)
		public static final String KEY_SEX = "sex";	// Constructor
				
				
		
		public SessionManager(Context context){
			this._context = context;
			pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
			editor = pref.edit();
		}

		/**
		 * Create login session
		 * */
		public void createLoginSession(String name, String fname, String lname, String age, String sex){
			// Storing login value as TRUE
			editor.putBoolean(IS_LOGIN, true);
			
			// Storing name in pref
			editor.putString(KEY_NAME, name);
			// Storing name in pref
			editor.putString(KEY_FNAME, fname);
			// Storing name in pref
			editor.putString(KEY_LNAME, lname);
			// Storing name in pref
			editor.putString(KEY_AGE, age);
			
			editor.putString(KEY_SEX, age);
			// commit changes
			editor.commit();
		}	
		
		
		// Get Login State
		public boolean isLoggedIn(){
			return pref.getBoolean(IS_LOGIN, false);
		}
		
		public void logoutUser(){
			// Clearing all data from Shared Preferences
			editor.clear();
			editor.commit();
		}
		
		
		/**
		 * Get stored session data
		 * */
		public HashMap<String, String> getUserDetails(){
			HashMap<String, String> user = new HashMap<String, String>();
			// user name
			user.put(KEY_NAME, pref.getString(KEY_NAME, null));
			
			// user email id
			user.put(KEY_FNAME, pref.getString(KEY_FNAME, null));
			
			// user email id
			user.put(KEY_LNAME, pref.getString(KEY_LNAME, null));

			// user email id
			user.put(KEY_AGE, pref.getString(KEY_AGE, null));

			// user email id
			user.put(KEY_SEX, pref.getString(KEY_SEX, null));
			
			
			// return user
			return user;
		}
}

