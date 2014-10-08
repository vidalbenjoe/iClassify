package com.capstoneii.iclassify.assessment.decisionid3;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.capstoneii.iclassify.Question;

public class DBHelperIdTree extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "IdTreeDB";
	// tasks table name
	private static final String TABLE_QUEST = "questIdtree";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	
	
	

	public static final String SCORE_TABLE = "scoretableiclassify";
	public static final String _ID = "_id";
	public static final String _RID = "rid";
	public static final String _CCID = "ccid";
	public static final String _NAME = "score_name";
	public static final String _QDETAILS = "quiz_details";
	public static final String _SCORE = "spoint";
	//public static final String _TOTAL = "stotal";
	public static final String _DATE = "sdate";
	
	public static final String[] ALL_KEYS = new String[] { _ID, _RID, _CCID, _NAME, _QDETAILS, _SCORE,  _DATE};
	public static final String SCORE_TABLE_SQL = "CREATE TABLE " + SCORE_TABLE + "("
			+ _ID + " INTEGER PRIMARY KEY autoincrement, "		
			+ _RID + " INTEGER not null, "
			+ _CCID + " INTEGER not null, "
			+ _NAME + " TEXT not null, "
			+ _QDETAILS + " TEXT not null, "
			+ _SCORE + " TEXT not null, "
			+ _DATE + " TEXT not null " + ");";
	
	
	
	public DBHelperIdTree(Context context) {
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
		Question q1=new Question("It is a flow-chart-like tree structure, where each node denotes a test on " +
				"an attribute value, each branch represents an outcome of the test, and tree leaves represent classes or class distributions. "
				,"Naive Bayesian", "Decision Tree", "K Nearest Neighbor", "Decision Tree");
		this.addQuestion(q1);
		Question q2=new Question("It acts as a form of data reduction for logic-based data mining methods," +
				" which repeatedly make value comparisons on sorted data ", 
				 "Decision Tree Introduction", "Decision Tree Induction", "Comparisons Method", "Decision Tree Induction");
		this.addQuestion(q2);
		Question q3=new Question("It constructs a flowchart like structure where each internal (nonleaf) node denotes a test on " +
				"an attribute, each branch corresponds to an outcome of the test, and each external (leaf) node denotes a class prediction",
				"Decision Tree", "Decision Tree Induction","Classifier","Decision Tree Induction");
		this.addQuestion(q3);
		Question q4=new Question("It is a flowchart-like tree structure, where each internal node (nonleaf node) denotes a test on an attribute, " +
				"each branch represents an outcome of the test, and each leaf node (or terminal node) holds a class label.",	
				"Decision Tree Flow Chart", "Decision Tree", "Decision Tree Terminal","Decision Tree");
		this.addQuestion(q4);
		Question q5=new Question("It is the number of instances gets smaller as you traverse down the tree",
				"Decision Tree Fragmentation","Data Fragmentation","Instance Fragmentation","Data Fragmentation");
		this.addQuestion(q5);
		
		Question q6=new Question("This approach considers the cost complexity of a tree to be a function of the number " +
				"of leaves in the tree and the error rate of the tree",
				"Tree Pruning","CART","Nanograms","CART");
		this.addQuestion(q6);
		
		Question q7=new Question("It is an approach that a tree is “pruned” by halting its construction early. ",
				"Prerunning","Prepruning","Postpruning","Prepruning");
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
	
	public long addScores(int rid, String _name, int _score, String _date){
		ContentValues vl = new ContentValues();
		vl.put(_RID, rid);
		vl.put(_NAME, _name);
		vl.put(_SCORE, _score);
	//	vl.put(_TOTAL, _total);
		vl.put(_DATE, _date);
		return dbase.insert(SCORE_TABLE, null, vl);
	}
	
	
	public long addscores(int rid, int ccid, String _name, String _qdetails, int _score, String _date){
		ContentValues vl = new ContentValues();
		vl.put(_RID, rid);
		vl.put(_CCID, ccid);
		vl.put(_NAME, _name);
		vl.put(_QDETAILS, _qdetails);
		vl.put(_SCORE, _score);
	//	vl.put(_TOTAL, _total);
		vl.put(_DATE, _date);
		return dbase.insert(SCORE_TABLE, null, vl);
	}
	
	//GET ALL SCORE ROWS WITH ID
			public Cursor getAllScoreWith(long rid){
				String where = _RID + "=" + rid;
				Cursor cr = dbase.query(true, SCORE_TABLE, ALL_KEYS, where, null, null, null, null, null);
				if (cr != null) {
					cr.moveToFirst();
				}
				return cr;
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