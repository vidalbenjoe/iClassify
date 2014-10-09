package com.capstoneii.iclassify;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionCache {

			// Shared Preferences
			SharedPreferences qpref;
			
			// Editor for Shared preferences
			Editor editor;
			
			// Context
			Context _context;
			
			// Shared pref mode
			int PRIVATE_MODE = 0;
			
			// Sharedpref file name
			private static final String PREF_NAME = "QuizPref";
			
			// All Shared Preferences Keys
			public static final String IS_TAKEN1 = "isquiz1taken";
			// All Shared Preferences Keys
			public static final String IS_TAKEN2 = "isquiz2taken";
			// All Shared Preferences Keys
			public static final String IS_TAKEN3 = "isquiz3taken";
			// All Shared Preferences Keys
			public static final String IS_TAKEN4 = "isquiz4taken";
			// All Shared Preferences Keys
			public static final String IS_TAKEN5 = "isquiz5taken";
			
			
			public static final String JS_MAX_ITEM1 = "max_quiz_1";

			public static final String JS_MAX_ITEM2 = "max_quiz_2";

			public static final String JS_MAX_ITEM3 = "max_quiz_3";

			public static final String JS_MAX_ITEM4 = "max_quiz_4";

			public static final String JS_MAX_ITEM5 = "max_quiz_5";
			

			public static final String JS_QUIZ_TAKE = "js_last_quiz";

			public static final String PHP_QUIZ_TAKE = "php_last_quiz";

			public static final String FL_QUIZ_TAKE = "fl_last_quiz";

			public static final String FW_QUIZ_TAKE = "fw_last_quiz";

			public static final String PS_QUIZ_TAKE = "ps_last_quiz";

			public static final String ALL_QUIZ_TAKE = "all_last_quiz";
			
			public static final String SEECOVER = "seeCover";
			
			public static final String REPEATING1 = "repeat_quiz1";
			
			public static final String REPEATING2 = "repeat_quiz2";
			
			public static final String REPEATING3 = "repeat_quiz3";
			
			public static final String REPEATING4 = "repeat_quiz4";
			
			public static final String REPEATING5 = "repeat_quiz5";

			
			public SessionCache(Context context){
				this._context = context;
				qpref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
				editor = qpref.edit();
			}
			public void tapCover(){
				editor.putBoolean(SEECOVER, true);
				editor.commit();
			}
			
			public boolean ifSeeCover(){
				return qpref.getBoolean(SEECOVER, false);
			}
			
			public void FinishSessionNum1(String repeat){
				// Storing login value as TRUE
				editor.putBoolean(IS_TAKEN1, true);
				editor.putString(REPEATING1, repeat);
				editor.commit();
			}
			
			public void FinishSessionNum2(String repeat){
				// Storing login value as TRUE
				editor.putBoolean(IS_TAKEN2, true);
				editor.putString(REPEATING2, repeat);
				editor.commit();
			}
			public void FinishSessionNum3(String repeat){
				// Storing login value as TRUE
				editor.putBoolean(IS_TAKEN3, true);
				editor.putString(REPEATING3, repeat);
				editor.commit();
			}
			public void FinishSessionNum4(String repeat){
				// Storing login value as TRUE
				editor.putBoolean(IS_TAKEN4, true);
				editor.putString(REPEATING4, repeat);
				editor.commit();
			}
			public void FinishSessionNum5(String repeat){
				// Storing login value as TRUE
				editor.putBoolean(IS_TAKEN5, true);
				editor.putString(REPEATING5, repeat);
				editor.commit();
			}
			
			public void RemoveQuizHistory(String prefkey){
				// Clearing all data from Shared Preferences
				editor.remove(prefkey);
				editor.commit();
			}
			
			public void StoreTotal1(String total1){
				
				editor.putString(JS_MAX_ITEM1, total1);
				editor.commit();
			}
			public void StoreTotal2(String total2){
				
				editor.putString(JS_MAX_ITEM2, total2);
				editor.commit();
			}
			public void StoreTotal3(String total3){
				
				editor.putString(JS_MAX_ITEM3, total3);
				editor.commit();
			}
			public void StoreTotal4(String total4){
				
				editor.putString(JS_MAX_ITEM4, total4);
				editor.commit();
			}
			public void StoreTotal5(String total5){
				
				editor.putString(JS_MAX_ITEM5, total5);
				editor.commit();
			}
			
			public void StoreJsLastQuizTaken(String taken){
				editor.putString(JS_QUIZ_TAKE, taken);
				editor.commit();
			}
			public void StoreFwLastQuizTaken(String taken){
				editor.putString(FW_QUIZ_TAKE, taken);
				editor.commit();
			}
			public void StoreFlLastQuizTaken(String taken){
				editor.putString(FL_QUIZ_TAKE, taken);
				editor.commit();
			}
			public void StorePhpLastQuizTaken(String taken){
				editor.putString(PHP_QUIZ_TAKE, taken);
				editor.commit();
			}
			public void StorePsLastQuizTaken(String taken){
				editor.putString(PS_QUIZ_TAKE, taken);
				editor.commit();
			}
			
			public void StoreAllLastQuizTaken(String taken){
				editor.putString(ALL_QUIZ_TAKE, taken);
				editor.commit();
			}
		
			public static final String _LASTSCORE = "_lastquiz";
			public static final String _LASTQUIZNAME = "_coursename";
			public static final String _LASTQDETAILS = "_lastqdetails";
			
			public void StoredLastScore(String _score, String _qdetails, String _quizname){
				editor.putString(_LASTSCORE, _score);
				editor.putString(_LASTQDETAILS, _qdetails);
				editor.putString(_LASTQUIZNAME, _quizname);
				editor.commit();
			}
		
			public HashMap<String, String> getTotalSum(){
				
				HashMap<String, String> totalsum = new HashMap<String, String>();
				
				totalsum.put(JS_MAX_ITEM1, qpref.getString(JS_MAX_ITEM1, null));
				totalsum.put(JS_MAX_ITEM2, qpref.getString(JS_MAX_ITEM2, null));
				totalsum.put(JS_MAX_ITEM3, qpref.getString(JS_MAX_ITEM3, null));
				totalsum.put(JS_MAX_ITEM4, qpref.getString(JS_MAX_ITEM4, null));
				totalsum.put(JS_MAX_ITEM5, qpref.getString(JS_MAX_ITEM5, null));
				
				totalsum.put(JS_QUIZ_TAKE, qpref.getString(JS_QUIZ_TAKE, null));
				totalsum.put(PHP_QUIZ_TAKE, qpref.getString(PHP_QUIZ_TAKE, null));
				totalsum.put(FL_QUIZ_TAKE, qpref.getString(FL_QUIZ_TAKE, null));
				totalsum.put(FW_QUIZ_TAKE, qpref.getString(FW_QUIZ_TAKE, null));
				totalsum.put(PS_QUIZ_TAKE, qpref.getString(PS_QUIZ_TAKE, null));
				totalsum.put(ALL_QUIZ_TAKE, qpref.getString(ALL_QUIZ_TAKE, null));
				
				totalsum.put(REPEATING1, qpref.getString(REPEATING1, null));
				totalsum.put(REPEATING2, qpref.getString(REPEATING2, null));
				totalsum.put(REPEATING3, qpref.getString(REPEATING3, null));
				totalsum.put(REPEATING4, qpref.getString(REPEATING4, null));
				totalsum.put(REPEATING5, qpref.getString(REPEATING5, null));

				totalsum.put(_LASTSCORE, qpref.getString(_LASTSCORE, null));
				totalsum.put(_LASTQUIZNAME, qpref.getString(_LASTQUIZNAME, null));
				totalsum.put(_LASTQDETAILS, qpref.getString(_LASTQDETAILS, null));
				
				return totalsum;
			}
			
			
		
			// Get Login State
			public boolean hasFlQuiz1(){
				return qpref.getBoolean(IS_TAKEN1, false);
			}
			// Get Login State
			public boolean hasFlQuiz2(){
				return qpref.getBoolean(IS_TAKEN2, false);
			}
			// Get Login State
			public boolean hasFlQuiz3(){
				return qpref.getBoolean(IS_TAKEN3, false);
			}
			// Get Login State
			public boolean hasFlQuiz4(){
				return qpref.getBoolean(IS_TAKEN4, false);
			}
			// Get Login State
			public boolean hasFlQuiz5(){
				return qpref.getBoolean(IS_TAKEN5, false);
			}
		 
}