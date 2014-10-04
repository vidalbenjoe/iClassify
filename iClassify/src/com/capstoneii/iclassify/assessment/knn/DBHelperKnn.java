package com.capstoneii.iclassify.assessment.knn;

import java.util.ArrayList;
import java.util.List;

import com.capstoneii.iclassify.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperKnn extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "knnDB";
	// tasks table name
	private static final String TABLE_QUEST = "questKnn";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	
	public DBHelperKnn(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
		db.execSQL(sql);		
		addQuestions();
		//db.close();
	}
	public void addQuestions()
	{
		Question q1=new Question("It was a method first described in the early 1950’s"
				,"Naive Bayesian", "Decision Tree", "K-Nearest-Neighbor Method", "K-Nearest-Neighbor Method");
		this.addQuestion(q1);
		Question q2=new Question("This are based on learning by analogy, that is," +
				" by comparing a given test tuple with training tuples that are similar to it", 
				 "Nearest-Neighbor Classifiers", "iClassify", "Decision Tree Classifier", "Nearest-Neighbor Classifiers");
		this.addQuestion(q2);
		Question q3=new Question("It is the task of predicting continuous (or ordered) values for given input.",
				"Nomograms", "Data Fragmentation","Numeric Prediction","Numeric Prediction");
		this.addQuestion(q3);
		Question q4=new Question("It has since been widely used in the area of pattern recognition",	
				"K-Nearest-Neighbor Method", "K-Nearest-Neighbor", "Hub","K-Nearest-Neighbor Method");
		this.addQuestion(q4);
		Question q5=new Question("K-Nearest-Neighbour Method is labor intensive when given large training sets, " +
				"and did not gain popularity until the _______ when increased computing power became available. ",
				"1960s","1950s","1970s","1960s");
		this.addQuestion(q5);
		
		Question q6=new Question("For k-nearest-neighbor classification, the unknown tuple is assigned the most common " +
				"class among its k nearest neighbors ",
				"Maybe","False","True","True");
		this.addQuestion(q6);
		
		Question q7=new Question("Nearest-neighbor classifiers can be extremely fast when classifying test tuples. ",
				"Maybe","False","True","False");
		this.addQuestion(q7);
		
		Question q8=new Question("It use distance-based comparisons that intrinsically assign equal weight to each attribute.",
				"Nearest-Neighbor Classifiers","Nearest-Neighbor Algorithm","K-Nearest Neigbor","Nearest-Neighbor Classifiers");
		this.addQuestion(q8);
		
		Question q9=new Question("A method to speed up classification time that compute the distance based on a " +
				"subset of the n attributes. ",
				"Full Distance Method","Partial Distance Method","Speed Up Distance Method","Partial Distance Method");
		this.addQuestion(q9);
		
		Question q10=new Question("A method to speed up classification time that removes training tuples that prove useless. ",
				"Editing Method","Edit Method","Remove useless Method","Editing Method");
		this.addQuestion(q10);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}
