package com.capstoneii.iclassify.assessment.bayesian;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.capstoneii.iclassify.Question;

public class DBHelperNaive extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "naiveDB";
	// tasks table name
	private static final String TABLE_QUEST = "questNaive";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	
	public DBHelperNaive(Context context) {
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
	private void addQuestions()
	{
		Question q1=new Question("It provides a way of calculating the posterior probability."
				,"Decision Theorem", "Naive Theorem", "Bayes theorem", "Bayes theorem");
		this.addQuestion(q1);
		Question q2=new Question("What do you call when the classifier assume that the effect " +
				"of the value of a predictor on a given class is independent of the values of " +
				"other predictors?", 
				 "Conditional independence", "Conditional dependence", "No sure", "Conditional independence");
		this.addQuestion(q2);
		Question q3=new Question("What is the class with the highest posterior probability?",
				"Top prediction", "Income of prediction","Outcome of prediction","Outcome of prediction");
		this.addQuestion(q3);
		Question q4=new Question("In this process you add 1 to the count for every attribute value-class " +
				"combination when an attribute value doesn’t occur with every class value",	
				"Zero-frequency problem", "Zero Frequency Addition", "Attribute Addition","Zero-frequency problem");
		this.addQuestion(q4);
		Question q5=new Question("What are the two parameters that define density function for the normal distribution?",
				"Dencity and Latency Deviation","Mean and standard deviation","Mean and Normal deviation","Mean and standard deviation");
		this.addQuestion(q5);
		
		Question q6=new Question("The contribution of predictors can also be visualized by plotting ___________",
				"Nonograms","Nomograms","Nanograms","Nomograms");
		this.addQuestion(q6);
		
		Question q7=new Question("____________ information gain as a sum of information contributed by each" +
				" attribute can offer an explanation on how values of the predictors influence the class probability.",
				"Kononenko’s","Nomograms","True","Kononenko’s");
		this.addQuestion(q7);
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