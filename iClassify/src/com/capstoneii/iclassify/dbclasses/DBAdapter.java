package com.capstoneii.iclassify.dbclasses;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	public static final int DATABASE_VERSION = 9;
	public static final String DATABASE_NAME = "itdb";

	public static final String USER_TABLE = "user_info";

	public static final String USER_ID = "_id";
	public static final String USER_NAME = "uname";
	public static final String USER_FNAME = "fname";
	public static final String USER_LNAME = "lname";
	public static final String USER_AGE = "age";
	public static final String USER_SEX = "sex";

	public static final String[] ALL_USER = new String[] { USER_ID, USER_NAME,
			USER_FNAME, USER_LNAME, USER_AGE, USER_SEX };

	public static final String USER_TABLE_SQL = "CREATE TABLE " + USER_TABLE
			+ "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ USER_NAME + " TEXT, " + USER_FNAME + " TEXT, " + USER_LNAME
			+ " TEXT, " + USER_AGE + " TEXT, " + USER_SEX + " TEXT )";

	public static final String SCORE_TABLE = "itboxscores";

	public static final String _ID = "_id";
	public static final String _RID = "rid";
	public static final String _CCID = "ccid";
	public static final String _NAME = "score_name";
	public static final String _QDETAILS = "quiz_details";
	public static final String _SCORE = "spoint";
	// public static final String _TOTAL = "stotal";
	public static final String _DATE = "sdate";

	public static final String[] ALL_KEYS = new String[] { _ID, _RID, _CCID,
			_NAME, _QDETAILS, _SCORE, _DATE };
	public static final String SCORE_TABLE_SQL = "CREATE TABLE " + SCORE_TABLE
			+ "(" + _ID + " INTEGER PRIMARY KEY autoincrement, " + _RID
			+ " INTEGER not null, " + _CCID + " INTEGER not null, " + _NAME
			+ " TEXT not null, " + _QDETAILS + " TEXT not null, " + _SCORE
			+ " TEXT not null, " + _DATE + " TEXT not null " + ");";

	public static final String _JSID = "_id";
	public static final String _CHID = "_kid";
	public static final String _JSNAME = "_jsname";
	public static final String _JSRETAKE = "_jsretake";
	public static final String _JSAVERAGE = "_jsaverage";
	public static final String JSQUIZ_TABLE = "jsquiz_table";

	public static final String[] ALL_JSKEYS = new String[] { _JSID, _CHID,
			_JSNAME, _JSRETAKE, _JSAVERAGE };
	public static final String JSQUIZ_TABLE_SQL = "CREATE TABLE "
			+ JSQUIZ_TABLE + "(" + _JSID
			+ " INTEGER PRIMARY KEY autoincrement, " + _CHID
			+ " INTEGER not null, " + _JSNAME + " TEXT not null, " + _JSRETAKE
			+ " TEXT not null, " + _JSAVERAGE + " TEXT not null " + ");";

	public static final String JS1_TABLE = "js_1q";

	public static final String QUE_ID = "qid";
	public static final String QUE_LESSON = "lid";
	public static final String QUE_ITEM = "qitem";
	public static final String QUE_ANS = "qans";
	public static final String QUE_OPT1 = "qopt1";
	public static final String QUE_OPT2 = "qopt2";
	public static final String QUE_OPT3 = "qopt3";
	public static final String QUE_OPT4 = "qopt4";

	public static final String JS1_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ JS1_TABLE + "(" + QUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ QUE_LESSON + " TEXT, " + QUE_ITEM + " TEXT, " + QUE_ANS
			+ " TEXT, " + QUE_OPT1 + " TEXT, " + QUE_OPT2 + " TEXT, "
			+ QUE_OPT3 + " TEXT, " + QUE_OPT4 + " TEXT  )";

	public static final String JS2_TABLE = "js_2q";

	public static final String JS2_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ JS2_TABLE + "(" + QUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ QUE_LESSON + " TEXT, " + QUE_ITEM + " TEXT, " + QUE_ANS
			+ " TEXT, " + QUE_OPT1 + " TEXT, " + QUE_OPT2 + " TEXT, "
			+ QUE_OPT3 + " TEXT, " + QUE_OPT4 + " TEXT  )";

	public static final String JS3_TABLE = "js_3q";

	public static final String JS3_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ JS3_TABLE + "(" + QUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ QUE_LESSON + " TEXT, " + QUE_ITEM + " TEXT, " + QUE_ANS
			+ " TEXT, " + QUE_OPT1 + " TEXT, " + QUE_OPT2 + " TEXT, "
			+ QUE_OPT3 + " TEXT, " + QUE_OPT4 + " TEXT  )";

	public static final String JS4_TABLE = "js_4q";

	public static final String JS4_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ JS4_TABLE + "(" + QUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ QUE_LESSON + " TEXT, " + QUE_ITEM + " TEXT, " + QUE_ANS
			+ " TEXT, " + QUE_OPT1 + " TEXT, " + QUE_OPT2 + " TEXT, "
			+ QUE_OPT3 + " TEXT, " + QUE_OPT4 + " TEXT  )";

	public static final String JS5_TABLE = "js_5q";

	public static final String JS5_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ JS5_TABLE + "(" + QUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ QUE_LESSON + " TEXT, " + QUE_ITEM + " TEXT, " + QUE_ANS
			+ " TEXT, " + QUE_OPT1 + " TEXT, " + QUE_OPT2 + " TEXT, "
			+ QUE_OPT3 + " TEXT, " + QUE_OPT4 + " TEXT  )";

	// CREATING TEMPORARY STORAGE BY USING SQLITE
	public static final String TEMP_QUE_TABLE = "js_temp";

	public static final String TEMP_ID = "_id";
	public static final String TEMP_SET = "temp_set";
	public static final String TEMP_LESSON = "temp_ref";
	public static final String TEMP_Q_ITEM = "temp_qitem";
	public static final String TEMP_ANS = "temp_ans";
	public static final String TEMP_UANS = "temp_user_ans";

	public static final String[] ALL_TEMPS = new String[] { TEMP_ID, TEMP_SET,
			TEMP_LESSON, TEMP_Q_ITEM, TEMP_ANS, TEMP_UANS };
	public static final String TEMP_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
			+ TEMP_QUE_TABLE + "(" + TEMP_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + TEMP_SET + " TEXT, "
			+ TEMP_LESSON + " TEXT, " + TEMP_Q_ITEM + " TEXT, " + TEMP_ANS
			+ " TEXT, " + TEMP_UANS + " TEXT )";

	public static final int COL_ROWID = 0;
	public static final int COL_SETID = 1;
	public static final int COL_REFID = 2;
	public static final int COL_QITEM = 3;
	public static final int COL_QANS = 4;
	public static final int COL_QUANS = 5;
	// Context of application who uses us.

	public static final int COL_ID = 1;
	public static final int COL_JSNAME = 2;
	public static final int COL_JSRETAKE = 3;
	public static final int COL_JSAVERAGE = 4;

	public static final int COL_ALIAS = 1;
	public static final int COL_FNAME = 2;
	public static final int COL_LNAME = 3;
	public static final int COL_AGE = 4;
	public static final int COL_SEX = 5;

	private final Context context;

	private Dbhandler dbhandler;
	private static SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		dbhandler = new Dbhandler(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = dbhandler.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		dbhandler.close();
	}

	/*
	 * public long addScores(int rid, String _name, int _score, String _date){
	 * ContentValues vl = new ContentValues(); vl.put(_RID, rid); vl.put(_NAME,
	 * _name); vl.put(_SCORE, _score); // vl.put(_TOTAL, _total); vl.put(_DATE,
	 * _date); return db.insert(SCORE_TABLE, null, vl); }
	 */

	public long addscores(int rid, int ccid, String _name, String _qdetails,
			int _score, String _date) {
		ContentValues vl = new ContentValues();
		vl.put(_RID, rid);
		vl.put(_CCID, ccid);
		vl.put(_NAME, _name);
		vl.put(_QDETAILS, _qdetails);
		vl.put(_SCORE, _score);
		// vl.put(_TOTAL, _total);
		vl.put(_DATE, _date);
		return db.insert(SCORE_TABLE, null, vl);
	}

	// GET ALL SCORE ROWS
	public Cursor getAllrows() {
		Cursor cr = db.query(true, SCORE_TABLE, ALL_KEYS, null, null, null,
				null, null, null);
		if (cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}

	// GET ALL SCORE ROWS WITH ID
	public Cursor getAllScoreWith(long rid) {
		String where = _RID + "=" + rid;
		Cursor cr = db.query(true, SCORE_TABLE, ALL_KEYS, where, null, null,
				null, null, null);
		if (cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}

	public long addjsquiz(int _cid, String _jsname, String _retake,
			String _jsaverage) {
		ContentValues vl = new ContentValues();
		vl.put(_CHID, _cid);
		vl.put(_JSNAME, _jsname);
		vl.put(_JSRETAKE, _retake);
		vl.put(_JSAVERAGE, _jsaverage);

		return db.insert(JSQUIZ_TABLE, null, vl);
	}

	public Cursor getAllquiz() {
		Cursor cr = db.query(true, JSQUIZ_TABLE, ALL_JSKEYS, null, null, null,
				null, _JSNAME, null);
		if (cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}

	// DELETE ROW WITH SCORE ID
	public void deleteQuiz(String Quizname) {
		// String where = _NAME + "=" + QuizSet;
		db.delete(JSQUIZ_TABLE, _JSNAME + "=?", new String[] { Quizname });
	}

	// Get a specific row (by rowId)
	public Cursor getAllChapterRow(long rowId) {
		String where = _JSID + "=" + rowId;
		Cursor c = db.query(true, JSQUIZ_TABLE, ALL_JSKEYS, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	/*
	 * //GET ALL THE SCORES WITH RETAKE AND _NAME public Cursor
	 * getAllScorewith(long ccid, String _name){ Cursor cr =
	 * db.rawQuery("SELECT * FROM " +SCORE_TABLE+ " WHERE rid=? AND ccid=?", new
	 * String[]{Long.toString(ccid), _name}); if(cr != null){ cr.moveToFirst();
	 * } return cr; }
	 */

	public Cursor getAllscorewithChapter(String _name) {
		// String where = _NAME + "=" + _name;
		Cursor cr = db.rawQuery("SELECT * FROM " + SCORE_TABLE + " WHERE "
				+ _NAME + "=?", new String[] { _name });
		if (cr != null) {
			cr.moveToFirst();
		}

		return cr;
	}

	// DELETE ROW WITH SCORE ID
	public boolean deleteScoreRow(long rowId) {
		String where = _ID + "=" + rowId;
		return db.delete(SCORE_TABLE, where, null) != 0;
	}

	// DELETE ROW WITH SCORE ID
	public void deleteScoreRowSet(String QuizSet) {
		// String where = _NAME + "=" + QuizSet;
		db.delete(SCORE_TABLE, _NAME + "=?", new String[] { QuizSet });
	}

	public void deletescorerowSet(long ccid, String _name) {
		db.delete(SCORE_TABLE, "ccid=? AND score_name=?",
				new String[] { Long.toString(ccid), _name });
	}

	public void deleteallquizset(String _name) {
		db.delete(SCORE_TABLE, "score_name=?", new String[] { _name });
	}

	// DELETE ALL SCORE ROWS
	public void deleteAllScoreRow() {
		Cursor c = getAllrows();
		long rowId = c.getColumnIndexOrThrow(_ID);
		if (c.moveToFirst()) {
			do {
				deleteScoreRow(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	public long addUser(String uname, String fname, String lname, String uage,
			String sex) {
		ContentValues values = new ContentValues();
		values.put(USER_NAME, uname);
		values.put(USER_FNAME, fname);
		values.put(USER_LNAME, lname);
		values.put(USER_AGE, uage);
		values.put(USER_AGE, sex);
		return db.insert(USER_TABLE, null, values);
	}

	public Cursor getAllUsers() {
		Cursor cr = db.query(true, USER_TABLE, ALL_USER, null, null, null,
				null, null, null);
		if (cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}

	public boolean Login(String uname) {
		Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE
				+ " WHERE uname=?", new String[] { uname });
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

	public Cursor getUserWith(String uname) {
		Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE
				+ " WHERE uname=?", new String[] { uname });
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	// DELETE ROW WITH SCORE ID
	public boolean deleteUserRow(long rowId) {
		String where = USER_ID + "=" + rowId;
		return db.delete(USER_TABLE, where, null) != 0;
	}

	// DELETE ALL SCORE ROWS
	public void deleteAllUserRow() {
		Cursor c = getAllrows();
		long rowId = c.getColumnIndexOrThrow(USER_ID);
		if (c.moveToFirst()) {
			do {
				deleteScoreRow(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// ADD TEMPORARY QUESTIONS FROM QUESTIONS
	public long addTempQuestion(String _lid, String _qitem, String _qans,
			String _quserans) {

		ContentValues vl = new ContentValues();
		vl.put(TEMP_LESSON, _lid);
		vl.put(TEMP_Q_ITEM, _qitem);
		vl.put(TEMP_ANS, _qans);
		vl.put(TEMP_UANS, _quserans);
		return db.insert(TEMP_QUE_TABLE, null, vl);

	}

	public void addtempQuestion(TempQuestion tquestion) {

		ContentValues values = new ContentValues();
		values.put(TEMP_SET, tquestion.getQset());
		values.put(TEMP_LESSON, tquestion.getLid());
		values.put(TEMP_Q_ITEM, tquestion.getQitem());
		values.put(TEMP_ANS, tquestion.getQans());
		values.put(TEMP_UANS, tquestion.getQuserans());
		db.insert(TEMP_QUE_TABLE, null, values);
	}

	// GET ALL ROWS
	public Cursor getAlltempRows() {
		Cursor cr = db.query(true, TEMP_QUE_TABLE, ALL_TEMPS, null, null, null,
				null, null, null);
		if (cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}

	// DELETE ROW WITH ID
	public boolean delTempRows(long rowId) {
		String where = TEMP_ID + "=" + rowId;
		return db.delete(TEMP_QUE_TABLE, where, null) != 0;
	}

	// DELETE ALL ROWS
	public void delAllTempRows() {
		Cursor c = getAlltempRows();
		long rowId = c.getColumnIndexOrThrow(TEMP_ID);
		if (c.moveToFirst()) {
			do {
				delTempRows(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = TEMP_ID + "=" + rowId;
		Cursor c = db.query(true, TEMP_QUE_TABLE, ALL_TEMPS, where, null, null,
				null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	/*
	 * public int getAllrowswithA(int a){ int sum = 0; Cursor cursor =
	 * db.rawQuery("SELECT sum(spoint) AS myTOTAL FROM "+ SCORE_TABLE
	 * +" WHERE rid=?",new String[]{Integer.toString(a)});
	 * if(cursor.moveToFirst()){ sum =
	 * cursor.getInt(cursor.getColumnIndex("myTOTAL")); } return sum; }
	 */

	public int getallRowswithName(String _name) {
		int sum = 0;
		Cursor cursor = db.rawQuery("SELECT sum(spoint) AS myCTOTAL FROM "
				+ SCORE_TABLE + " WHERE score_name=?", new String[] { _name });
		if (cursor.moveToFirst()) {
			sum = cursor.getInt(cursor.getColumnIndex("myCTOTAL"));
		}
		return sum;
	}

	/*
	 * public int getAlltotalwithA(int b){ int all = 0; Cursor cursor =
	 * db.rawQuery("SELECT sum(stotal) AS myMAX FROM "+ SCORE_TABLE
	 * +" WHERE rid=?",new String[]{Integer.toString(b)});
	 * if(cursor.moveToFirst()){ all =
	 * cursor.getInt(cursor.getColumnIndex("myMAX")); } return all; }
	 */
	// ADD QUESTION
	public void addQuestions1(Question question) {

		ContentValues values = new ContentValues();
		values.put(QUE_LESSON, question.getLid());
		values.put(QUE_ITEM, question.getQitem());
		values.put(QUE_ANS, question.getQans());
		values.put(QUE_OPT1, question.getOpta());
		values.put(QUE_OPT2, question.getOptb());
		values.put(QUE_OPT3, question.getOptc());
		values.put(QUE_OPT4, question.getOptd());
		db.insert(JS1_TABLE, null, values);

	}

	// GET ALL LIST OF QUESTIONS
	public List<Question> getAllQuestions1() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + JS1_TABLE;
		Cursor cursor = db.rawQuery(selectQuery + " ORDER BY RANDOM()", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setQid(cursor.getInt(0));
				quest.setLid(cursor.getString(1));
				quest.setQitem(cursor.getString(2));
				quest.setQans(cursor.getString(3));
				quest.setOpta(cursor.getString(4));
				quest.setOptb(cursor.getString(5));
				quest.setOptc(cursor.getString(6));
				quest.setOptd(cursor.getString(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	// /////////

	// ADD QUESTION
	public void addQuestions2(Question question) {

		ContentValues values = new ContentValues();
		values.put(QUE_LESSON, question.getLid());
		values.put(QUE_ITEM, question.getQitem());
		values.put(QUE_ANS, question.getQans());
		values.put(QUE_OPT1, question.getOpta());
		values.put(QUE_OPT2, question.getOptb());
		values.put(QUE_OPT3, question.getOptc());
		values.put(QUE_OPT4, question.getOptd());
		db.insert(JS2_TABLE, null, values);

	}

	// GET ALL LIST OF QUESTIONS
	public List<Question> getAllQuestions2() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + JS2_TABLE;
		Cursor cursor = db.rawQuery(selectQuery + " ORDER BY RANDOM()", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setQid(cursor.getInt(0));
				quest.setLid(cursor.getString(1));
				quest.setQitem(cursor.getString(2));
				quest.setQans(cursor.getString(3));
				quest.setOpta(cursor.getString(4));
				quest.setOptb(cursor.getString(5));
				quest.setOptc(cursor.getString(6));
				quest.setOptd(cursor.getString(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	// /////////

	// ADD QUESTION
	public void addQuestions3(Question question) {

		ContentValues values = new ContentValues();
		values.put(QUE_LESSON, question.getLid());
		values.put(QUE_ITEM, question.getQitem());
		values.put(QUE_ANS, question.getQans());
		values.put(QUE_OPT1, question.getOpta());
		values.put(QUE_OPT2, question.getOptb());
		values.put(QUE_OPT3, question.getOptc());
		values.put(QUE_OPT4, question.getOptd());
		db.insert(JS3_TABLE, null, values);

	}

	// GET ALL LIST OF QUESTIONS
	public List<Question> getAllQuestions3() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + JS3_TABLE;
		Cursor cursor = db.rawQuery(selectQuery + " ORDER BY RANDOM()", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setQid(cursor.getInt(0));
				quest.setLid(cursor.getString(1));
				quest.setQitem(cursor.getString(2));
				quest.setQans(cursor.getString(3));
				quest.setOpta(cursor.getString(4));
				quest.setOptb(cursor.getString(5));
				quest.setOptc(cursor.getString(6));
				quest.setOptd(cursor.getString(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	// /////////
	// ADD QUESTION
	public void addQuestions4(Question question) {

		ContentValues values = new ContentValues();
		values.put(QUE_LESSON, question.getLid());
		values.put(QUE_ITEM, question.getQitem());
		values.put(QUE_ANS, question.getQans());
		values.put(QUE_OPT1, question.getOpta());
		values.put(QUE_OPT2, question.getOptb());
		values.put(QUE_OPT3, question.getOptc());
		values.put(QUE_OPT4, question.getOptd());
		db.insert(JS4_TABLE, null, values);

	}

	// GET ALL LIST OF QUESTIONS
	public List<Question> getAllQuestions4() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + JS4_TABLE;
		Cursor cursor = db.rawQuery(selectQuery + " ORDER BY RANDOM()", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setQid(cursor.getInt(0));
				quest.setLid(cursor.getString(1));
				quest.setQitem(cursor.getString(2));
				quest.setQans(cursor.getString(3));
				quest.setOpta(cursor.getString(4));
				quest.setOptb(cursor.getString(5));
				quest.setOptc(cursor.getString(6));
				quest.setOptd(cursor.getString(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	// /////////

	// ADD QUESTION
	public void addQuestions5(Question question) {

		ContentValues values = new ContentValues();
		values.put(QUE_LESSON, question.getLid());
		values.put(QUE_ITEM, question.getQitem());
		values.put(QUE_ANS, question.getQans());
		values.put(QUE_OPT1, question.getOpta());
		values.put(QUE_OPT2, question.getOptb());
		values.put(QUE_OPT3, question.getOptc());
		values.put(QUE_OPT4, question.getOptd());
		db.insert(JS5_TABLE, null, values);

	}

	// GET ALL LIST OF QUESTIONS
	public List<Question> getAllQuestions5() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + JS5_TABLE;
		Cursor cursor = db.rawQuery(selectQuery + " ORDER BY RANDOM()", null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setQid(cursor.getInt(0));
				quest.setLid(cursor.getString(1));
				quest.setQitem(cursor.getString(2));
				quest.setQans(cursor.getString(3));
				quest.setOpta(cursor.getString(4));
				quest.setOptb(cursor.getString(5));
				quest.setOptc(cursor.getString(6));
				quest.setOptd(cursor.getString(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	// /////////

	public static class Dbhandler extends SQLiteOpenHelper {

		public Dbhandler(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {

			_db.execSQL(USER_TABLE_SQL);
			_db.execSQL(SCORE_TABLE_SQL);
			_db.execSQL(JSQUIZ_TABLE_SQL);
			_db.execSQL(JS1_TABLE_SQL);
			_db.execSQL(JS2_TABLE_SQL);
			_db.execSQL(JS3_TABLE_SQL);
			_db.execSQL(JS4_TABLE_SQL);
			_db.execSQL(JS5_TABLE_SQL);
			_db.execSQL(TEMP_TABLE_SQL);

		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {

			_db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + SCORE_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + JSQUIZ_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + JS1_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + JS2_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + JS3_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + JS4_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + JS5_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + TEMP_QUE_TABLE);
			onCreate(_db);

		}
	}

}