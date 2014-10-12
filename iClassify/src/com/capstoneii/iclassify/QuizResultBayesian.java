package com.capstoneii.iclassify;

import java.text.DecimalFormat;
import java.util.HashMap;

import bayesdiscussflip.NativeBayesLayoutActivity;

import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.TempQuestion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class QuizResultBayesian extends Activity implements AnimationListener {
	
	ImageButton backbutton;
	TextView correct;
	TextView wrong;
	TextView mesg;
	Button bscorelog, bqresult;
	int correctans, wrongans;
	String me;
	int score;
	int setq;
	String ncourse;
	String qdetails;
	DBAdapter myDb;
	
	SessionCache QuizSession;
	String tdate;
	 
	TempQuestion tquestion;
	
	Animation  push_up_in, bounce_in1, bounce_in2, bounce_in3, fade_in;
	private TextView tvcorrect, tvwrong;
	private Cursor cr;

	int totalsumof;
	int sumOf;
	double jsper;
	int retake;
	
	String quizdetails;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_result);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources()
						.getColor(R.color.divider_color)));
		openDB();
		QuizSession = new SessionCache(getApplicationContext());
		
		backbutton = (ImageButton)findViewById(R.id.backbutton);  	      	
		backbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(QuizResultBayesian.this, NativeBayesLayoutActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				finish();	    
			}  	  	
		});
		
	    HashMap<String, String> totalSum = QuizSession.getTotalSum();
	    sumOf = Integer.parseInt(totalSum.get(SessionCache.JS_MAX_ITEM1));
	    retake = Integer.parseInt(totalSum.get(SessionCache.REPEATING1));
        
	    totalsumof = myDb.getallRowswithName("Flash 1");
        
        if(totalsumof != 0){
        	double psDiv = (double)totalsumof/sumOf;
        	jsper = psDiv*100.0;
        }else{
        	jsper = 0;
        }        
        DecimalFormat df = new DecimalFormat("00.00");
        String quizaverage = df.format(jsper)+"%";
        
        if(retake == 1){
        	quizdetails = "Quiz has been taken for the first time";
        }
        else if(retake == 4){
        	quizdetails = "Quiz overwrite the first quiz try";
        }
        else{
        	quizdetails = "Quiz has been taken "+retake+" times";
        }
        
        myDb.addjsquiz(1,"Flash Chapter 1", quizdetails, quizaverage);
	    
		tvcorrect = (TextView) findViewById(R.id.txtcorrect);
		tvwrong = (TextView) findViewById(R.id.txtwrong);		
		correct = (TextView) findViewById(R.id.tvCorrect);
		wrong = (TextView) findViewById(R.id.tvWrong);
		mesg =(TextView) findViewById(R.id.tvMesg);
		bscorelog = (Button) findViewById(R.id.bSlog);
		bqresult = (Button)	findViewById(R.id.bQview);
		
		Bundle g = getIntent().getExtras();
		setq= g.getInt("qno");
		score = g.getInt("score");
		ncourse = g.getString("course");
		qdetails = g.getString("quizdetails");
		
		wrongans = 10 - score;
		correct.setText( score + "");
		wrong.setText(wrongans + "");
		mesg.setText(score+"/10");
		
        tdate = totalSum.get(SessionCache.FL_QUIZ_TAKE);        
        
        QuizSession.StoredLastScore(mesg.getText().toString(), qdetails, ncourse+" 1");
        
        push_up_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up_in);
		fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
		bounce_in1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
		bounce_in2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
		bounce_in3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
			
		push_up_in.setAnimationListener(this);
		fade_in.setAnimationListener(this);
		bounce_in1.setAnimationListener(this);
		bounce_in2.setAnimationListener(this);
		bounce_in3.setAnimationListener(this);
		
		correct.setAnimation(bounce_in1);
		
		bscorelog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				/*
				Bundle b =new Bundle();
				b.putInt("refID", 3);
				//b.putString("showScore",ncourse);
				b.putString("Course", "JavaScript");
				b.putString("lastdate", tdate);
				Intent intent = new Intent(JsResult1Activity.this, ListCourseScoreActivity2.class);
				intent.putExtras(b);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				*/
				openDB();
				populateSwithdb();
			}
		});
		
		bqresult.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDB();
				populateQwithdb();
			}
		});	
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	@SuppressWarnings("deprecation")
	private void populateSwithdb(){
		
		final Dialog dialog = new Dialog(this, R.style.DialogAnim);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.activity_quizhistory);
		
		TextView tvQuizChapter = (TextView)dialog.findViewById(R.id.tvchapterName);
		TextView tvLastQuiz = (TextView)dialog.findViewById(R.id.tvlastquizhistory);
		ListView myList = (ListView) dialog.findViewById(R.id.listofhistory);
		cr = myDb.getAllscorewithChapter("Flash 1");
		startManagingCursor(cr);
		
		tvQuizChapter.setText(""+ncourse+" Chapter 1");
		tvLastQuiz.setText(" "+tdate+"");
		
		String [] fromFieldNames = 
				new String[] {DBAdapter._NAME, DBAdapter._QDETAILS, DBAdapter._DATE, DBAdapter._SCORE};
		int[] toViewIDs =
				new int[] { R.id.tvQuiztitle, R.id.tvQdetails, R.id.tvQdatetaken, R.id.tvQscore};
		
		SimpleCursorAdapter myCursorAdapter =
				new SimpleCursorAdapter(
						this,
						R.layout.history_layout,
						cr,
						fromFieldNames,
						toViewIDs);
		myList.setAdapter(myCursorAdapter);
		dialog.show();	
	}
	
	@SuppressWarnings("deprecation")
	private void populateQwithdb(){
		
		final Dialog dialog = new Dialog(this, R.style.DialogAnim);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.activity_summary);		
		ListView myList = (ListView) dialog.findViewById(R.id.listquest);		
		Cursor cr = myDb.getAlltempRows();		
		startManagingCursor(cr);
		
		String[] fromFieldNames = new String[] 
				{DBAdapter.TEMP_Q_ITEM, DBAdapter.TEMP_UANS, DBAdapter.TEMP_ANS};
		int[] toViewIDs = new int[]
				{R.id.questiontv, R.id.useranswertv, R.id.trueanswertv};
		
				SimpleCursorAdapter myCursorAdapter = 
				new SimpleCursorAdapter(
						this,		// Context
						R.layout.question_item,	// Row layout template
						cr,					// cursor (set of DB records to map)
						fromFieldNames,			// DB Column names
						toViewIDs				// View IDs to put information in
						);
		myList.setAdapter(myCursorAdapter);	
		myList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Cursor cursor = myDb.getRow(id);
				if (cursor.moveToFirst()) {
						long idDb = cursor.getLong(DBAdapter.COL_ROWID);
						int qset = Integer.parseInt(cursor.getString(DBAdapter.COL_SETID));
						int item = Integer.parseInt(cursor.getString(DBAdapter.COL_REFID));
						String qitem = cursor.getString(DBAdapter.COL_QITEM);
						String qans = cursor.getString(DBAdapter.COL_QANS);
						String quans = cursor.getString(DBAdapter.COL_QUANS);
						
						String Message = "Lesson"+ (item +1) +".";

						Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_LONG).show();
						Bundle b = new Bundle();
						b.putInt("item", item);
						Intent intent =new Intent(getApplicationContext(), NativeBayesLayoutActivity.class);
						intent.putExtras(b);
						startActivity(intent);
				}
				cursor.close();
			}
		});	
		dialog.show();	
	}

	@Override	
	public void onAnimationStart(Animation animation) {	
			
		if(animation == bounce_in1){	
			correct.setVisibility(View.VISIBLE);	
		}
		if(animation == bounce_in2){
			wrong.setVisibility(View.VISIBLE);	
		}
		if(animation == fade_in){
			bscorelog.setVisibility(View.VISIBLE);
			bqresult.setVisibility(View.VISIBLE);	
		}	
	}
		
	@Override
	public void onAnimationEnd(Animation animation) {		
		if(animation == bounce_in1){
			wrong.clearAnimation();
			wrong.startAnimation(bounce_in2);	
		}
		if(animation == bounce_in2){
			bscorelog.startAnimation(fade_in);
			bqresult.startAnimation(fade_in);
		}
		if(animation == bounce_in2){
			tvcorrect.startAnimation(bounce_in3);
			tvwrong.startAnimation(bounce_in3);
		}		
	}
		
	@Override
	public void onAnimationRepeat(Animation animation) {
	}
	
	@Override
	public void onBackPressed() {
		Toast.makeText(getApplicationContext(), "Press 'back' on the top", Toast.LENGTH_SHORT).show();
	}
}