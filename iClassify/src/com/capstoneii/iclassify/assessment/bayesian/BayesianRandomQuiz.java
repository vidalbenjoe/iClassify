package com.capstoneii.iclassify.assessment.bayesian;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.capstoneii.iclassify.QuizResultActivity;
import com.capstoneii.iclassify.R;
import com.capstoneii.iclassify.SessionCache;
import com.capstoneii.iclassify.dbclasses.DBAdapter;
import com.capstoneii.iclassify.dbclasses.Question;
import com.capstoneii.iclassify.dbclasses.TempQuestion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BayesianRandomQuiz extends Activity {
	
	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 1;
	String ncourse = "Flash";
	Question question;
	SQLiteDatabase mdb;
	TextView tvQue, tvPage, tvRef;
	RadioButton rd1, rd2, rd3, rd4;
	Button bnext;
	SessionCache QuizSession;
	DBAdapter myDb;
	private Date date;
	String finalDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		setContentView(R.layout.activity_quiz);
		
		QuizSession = new SessionCache(getApplicationContext());
		openDB();
		
		myDb.delAllTempRows();
		quesList = myDb.getAllQuestions1();
		if(quesList.size() == 0){
			addQuestions1();
			quesList = myDb.getAllQuestions1();	
		}else{
			Log.d("database not empty", "queslist with setno");
		}
		
		question = quesList.get(qid);
		tvRef = (TextView)findViewById(R.id.tvRef);
		tvQue = (TextView)findViewById(R.id.tvQuestion);
		tvPage = (TextView)findViewById(R.id.tvPage);
		rd1 = (RadioButton) findViewById(R.id.rd1);
		rd2 = (RadioButton) findViewById(R.id.rd2);
		rd3 = (RadioButton) findViewById(R.id.rd3);
		rd4 = (RadioButton) findViewById(R.id.rd4);
		bnext = (Button) findViewById(R.id.bnext);
		
		bnext.setEnabled(false);
		setQuestionView();
		tvPage.setText(page+"");

		rd1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});
		
		rd2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});
		
		rd3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});
		
		rd4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bnext.setEnabled(true);
			}
		});

		bnext.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("SimpleDateFormat") @Override
			public void onClick(View v) {			
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());	
				grp.clearCheck();
				page++;				
				if(question.getQans().equals(answer.getText())){
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("1", lid, qitem, qans, quserans));
					score++;
					Log.d("score result", "Your score is "+score);					
				}else{
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("1", lid, qitem, qans, quserans));
					Log.d("wrong answer", answer.getText() +" is incorrect");			
				}
				
				if (qid < 10) {
					question = quesList.get(qid);
					setQuestionView();					
					grp.clearCheck();
					tvPage.setText(page+"");
					bnext.setEnabled(false);
				}else {
					  	Intent in = getIntent();
					    int retake = in.getExtras().getInt("retakeNum");
						date = new Date();
						SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
					    finalDate = timeFormat.format(date);
					    String subj = ncourse +" "+qset;
					    String qdetails = "Quiz Retake "+retake;
					    myDb.addscores(3, retake, subj, qdetails, score, finalDate);
					    //myDb.addScores(3, subj , score, finalDate);
					    
					    myDb.deleteQuiz("Flash Chapter 1");
					    
						Intent intent = new Intent(BayesianRandomQuiz.this,QuizResultActivity.class);									
						Bundle b = new Bundle();	
						b.putInt("qno", qset);
						b.putInt("score", score);
						b.putString("course", ncourse);// Your score
						b.putString("quizdetails", qdetails);
						intent.putExtras(b);
						startActivity(intent);
						BayesianRandomQuiz.this.finish();
						closeDB();
				}
			}
		});
	}
	private void setQuestionView() {
		tvRef.setText(question.getLid());
		tvQue.setText(question.getQitem());
		rd1.setText(question.getOpta());
		rd2.setText(question.getOptb());
		rd3.setText(question.getOptc());
		rd4.setText(question.getOptd());
		qid++;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();	
	}

	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
	}
	
	@SuppressLint("SimpleDateFormat") @Override
	public void onBackPressed() {
		
		final Dialog dialog = new Dialog(BayesianRandomQuiz.this,R.style.DialogAnim);
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.validate_message);
		
		date = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
	    finalDate = timeFormat.format(date);
		
		Button bOk = (Button) dialog.findViewById(R.id.buttonOk);
		Button bCancel = (Button) dialog.findViewById(R.id.buttonCancel);
		TextView question = (TextView) dialog.findViewById(R.id.tvalertmessage);
		
		question.setText("Are you sure? You want to Exit?");
		
		bOk.setOnClickListener(new OnClickListener() {	
			
			@Override
			public void onClick(View v) {				
				Intent in = getIntent();
			    int retake = in.getExtras().getInt("retakeNum");
			    
			    String subj = ncourse +" "+qset;
			    String qdetails = "Quiz Retake "+retake;
			    myDb.addscores(3, retake, subj, qdetails, score, finalDate);
			    
			    myDb.deleteQuiz("Flash Chapter 1");
			    
				Intent intent = new Intent(BayesianRandomQuiz.this,QuizResultActivity.class);									
				Bundle b = new Bundle();	
				b.putInt("qno", qset);
				b.putInt("score", score);
				b.putString("course", ncourse);// Your score
				b.putString("quizdetails", qdetails);
				intent.putExtras(b);
				startActivity(intent);
				BayesianRandomQuiz.this.finish();
				closeDB();
				dialog.dismiss();
			}
		});
		
		bCancel.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				dialog.dismiss();										
			}
		});
		
		dialog.show();
	}

	public void addQuestions1() {
			
		
		myDb.addQuestions1(new Question("1",
				"It engaged for integrating video, sound, graphics and animation.",
				"Flash","Sparkle","Flare","Flash","New Flash")); //1
		// 2
		myDb.addQuestions1(new Question("4",
				"__________ displays the menu bar, Timeline, Stage, Tools Panel,  Property Inspector.",
				"Flash","Vision", "Illustrator","Spark","Flash")); //3
		// 3
		myDb.addQuestions1(new Question("5",
				"An area that used or allocated one’s work or a panel that arrange for the better suit of the users.",
				"Workspace","White board","Workspace","Canvas","Property Layout")); //4
		// 4
		myDb.addQuestions1(new Question("13",
				"Flash indicates the selected frame number, the current frame rate (how many frames play per second), and the time that ________ has elapsed so far in the movie.",
				"Timeline","Timeline","Stage","Layers","Tools panel")); // 11
		// 5
		myDb.addQuestions1(new Question("10",
				"Accessible from a tab just to the right of the Properties Inspector.",
               "Library Panel","Property Panel","Library Panel","Property Panel","Layout Panel")); //9
		// 6
		myDb.addQuestions1(new Question("2",
				"Which of the following correct extension of flash?", 
				".fla","-fla","*flash","*fla",".fla")); //2
		// 7
		myDb.addQuestions1(new Question("10",
				"What is the possible shortcut for Library Panel?",
				"CTRL+ L","CTRL+ L","Windows + D","CTRL + ALT+ D","SHFT + L"));//9
		// 8
		myDb.addQuestions1(new Question("22",
				"A black dot in the Timeline indicates a single keyframe.",
				"Keyframe","Frame","Keyframe","Timeline","Tween"));//20
		// 9
		myDb.addQuestions1(new Question("12",
				"It is a panel that you can store your imported images.",
				"Library Panel","Memory Panel","Library Panel","Property Panel","Control Panel"));//10
		// 10
		myDb.addQuestions1(new Question("12",
				"Right format for importing image into Library Panel.",
				"JPEG","JPEG","PNG","GIF","BMP")); //10
		// 11
		myDb.addQuestions1(new Question("13",
				"It contains layers which help you to organize the artwork in your document.",
				"Timeline","Canvas","Workspace","Timeline","Layer")); // 11
		// 12
		myDb.addQuestions1(new Question("9",
				"_____________  that can set your stage.",
				"Property Inspector","Library Panel","Property Inspector","Tool Panel","Image Panel")); //8
		// 13
		myDb.addQuestions1(new Question("4",
				"It includes the command menus at the top of the screen and a variety of tools and panels for editing and adding elements to your movie.",
				"Adobe Professional","Adobe Professional","Adobe Flash Professional","Flash Professional","Professional")); //3
		// 14
		myDb.addQuestions1(new Question("1",
				"What is the latest version of Flash’s scripting language, which you can use to add interactivity?",
				"ActionScript 3.0","ActionScript 1.0", "ActionScript 2.0","ActionScript 3.0","ActionScript 4.0"));//1
		// 15
		myDb.addQuestions1(new Question("13",
				"It  indicates the selected frame number, the current frame rate (how many frames play per second), and the time that has elapsed so far in the movie.  ",
				"Flash","Fireworks","Photoshop","Flash","Java")); //11
        // 16
		myDb.addQuestions1(new Question("1",
				"It is use for graphics and animation and its application.",
				"Flash","Sparkle","Animation","Flash","Movie")); //1
		// 17
		myDb.addQuestions1(new Question("4",
				"It has the menu bar, Timeline, Stage, Tools Panel, Property Inspector",
				"Flash","Vision","New Flash","Spark","Flash")); //3
		// 18
		myDb.addQuestions1(new Question("5",
				"__________ that used or allocated one’s work or a panel that arrange for the better suit of the users.",
				"Workspace","White board","Workspace","Canvas","Property Layout"));//4
		// 19
		myDb.addQuestions1(new Question("13",
				"It indicates the selected frame number in flash. ",
				"Timeline","Timeline","Stage","Layers","Tools")); //11
		// 20
		myDb.addQuestions1(new Question("10",
				"It can found to the right of the Properties Inspector.",
				"Library Panel","Property Panel","Library Panel","Property Pallet","Layout Panel")); //9
		// 21
		myDb.addQuestions1(new Question("2",
				"It is the File Extension of Flash?",
				".fla","fla","*flash","*fla",".fla")); //2
		// 22
		myDb.addQuestions1(new Question("10",
				"It is the shortcut key for Library Panel?",
				"CTRL+ L","CTRL+ L", "Windows + D","CTRL + ALT+ D","SHFT + L"));//9
		// 23
		myDb.addQuestions1(new Question("22",
				"A black dot in the timeline that signify a single keyframe?",
				"Keyframe","Frame","Keyframe","Timeline","Tweening")); //20
		// 24
		myDb.addQuestions1(new Question("9",
				"______________ displays information about the currently selected sprite or cast member, or about the movie if you click on an occupied area of the Stage.",
				"Property Inspector","Property","Library Panel","Property Inspector","Library Property")); //10
		// 25
		myDb.addQuestions1(new Question("12",
				"It is the right format importing image into Library Panel.",
				"JPEG", "JPEG", "PNG", "GIF","BMP")); //10
		// 26
		myDb.addQuestions1(new Question("13",
				"The layers that will  help you to set up the artwork in your document.",
				"Timeline","Canvas","Workspace","Timeline","Layer"));//11
		// 27
		myDb.addQuestions1(new Question("9",
				"What panel that can set your stage?",
				"Property Inspector","Library Panel","Property Inspector","Tool Panel","Image Panel")); //8
		// 28
		myDb.addQuestions1(new Question("3",
                "A command menus at the top of the screen and a variety of tools and panels for editing and adding elements to your movie. ",
		        "Adobe Flash Professional","Adobe Professional","Adobe Flash Professional","Flash Professional","Professional")); //3
		// 29
		myDb.addQuestions1(new Question("1",
				"It is the latest version of Flash’s scripting language.",
				"ActionScript 3.0", "ActionScript 1.0", "ActionScript 2.0","ActionScript 3.0","ActionScript 4.0")); //1
		// 30
		myDb.addQuestions1(new Question("13",
				"An application that indicates the selected frame number?",
				"Flash", "Fireworks", "Photoshop", "Flash","Java"));//11
		// 31
		myDb.addQuestions1(new Question("10",
				" In the right of the Properties Inspector, what panel can be found?",
				"Library Panel", "Property Panel", "Library Panel", "Property Pallet","Layout Panel")); //9
		// 32
		myDb.addQuestions1(new Question("2",
				"In flash, which of the following is the correct File Extension?",
		    	".fla","fla'",".fla","*fla","fla")); //2
		// 33
		myDb.addQuestions1(new Question("10",
				"__________ is the correct shortcut key for Library Panel?",
				"CTRL+ L","SHFT + L","CTRL + ALT+ D","Windows + D","CTRL+ L")); //9
		// 34
		myDb.addQuestions1(new Question("22",
				"A _____________ is the black dot in the timeline",
				"Keyframe","Frame","Keyframe","Timeline","Tween"));//20
		// 35
		myDb.addQuestions1(new Question("4",
				"Its automatically displays information about the currently selected sprite?",
				"Property Inspector","Property","Library Panel","Library Property","Property Inspector")); //3
		// 36
		myDb.addQuestions1(new Question("12",
				"Which of the following is the correct format for importing image into Library Panel?",
				"JPEG","JPEG","BMP","GIF","PNG")); //10
		// 37
		myDb.addQuestions1(new Question("13",
				"What tools or layer will help to organize the artwork in your document?",
				"Timeline", "Canvas", "Workspace", "Timeline","Layer")); //11
                   	// 38
		myDb.addQuestions1(new Question("9",
				"It is the panel that can set your stage?",
				"Property Inspector", "Library Panel", "Property Inspector", "Tool Panel","Panel"));//8
		// 39
		myDb.addQuestions1(new Question("4",
				"A tools and panels in flash for editing and adding elements to your movie.",
				"Adobe Flash Professional", "Adobe Professional", "Flash Professional", "Adobe Flash Professional","Professional")); //3
		// 40
		myDb.addQuestions1(new Question("1",
				"Which of the following is the latest version of Flash’s scripting language?",
				"ActionScript 3.0","ActionScript 4.0","ActionScript 3.0","ActionScript 2.0","ActionScript 1.0")); //1
		// 41
		myDb.addQuestions1(new Question("10",
				"What can be found in the right Properties Inspector, that can import pictures?",
				"Library Panel","Property Panel","Library Panel","Property Pallet","Layout Panel")); //2
		// 42
		myDb.addQuestions1(new Question("2",
				"Which of the following is the correct File Extension of Flash?",
				".fla", ".fla","*flash","*fla","fla"));//2
		// 43
		myDb.addQuestions1(new Question("10",
				"Which of the following is the right shortcut key for Library Panel?",
				"CTRL+ L", "CTRL + ALT+ D", "Windows + D", "SHFT + L","CTRL+ L")); //9
		// 44

		myDb.addQuestions1(new Question("22",
				"It indicates a single keyframe in timeline.",
                "Keyframe","Keyframe","Frame","Timeline","Tween")); //20
		// 45
		myDb.addQuestions1(new Question("9",
				"___________ displays information about the currently selected sprite or cast member.",
				"Property Inspector", "Property", "Library Panel", "Library Property","Property Inspector")); //8
		// 46
		myDb.addQuestions1(new Question("12",
				"Which of the following is the correct format for importing image into Library Panel?",
				"JPEG", "16 Color Bitmap","GIF","JPEG","TIFF"));//10
		// 47
		myDb.addQuestions1(new Question("13",
				"Which of the following layer will help to organize the artwork in your document?",
				"Timeline","Workspace","Canvas","Timeline","Layer")); //11
		// 48
		myDb.addQuestions1(new Question("9",
				"What tools in panel that can set your stage and import picture, text etc.? ",
				"Property Inspector","Library Panel","Property Inspector","Tool Panel","Image Panel")); //8
		// 49
		myDb.addQuestions1(new Question("4",
				"__________ is an application for editing and adding elements to your movie?",
				"Adobe Flash Professional","Adobe Professional CS6","Adobe Flash Professional","Flash Professional","Professional")); //3
		// 50
		myDb.addQuestions1(new Question("1",
				"____________ is a latest version of Flash’s scripting language.",
				"ActionScript 3.0","ActionScript 0.0","ActionScript 1.0","ActionScript 2.0","ActionScript 3.0"));//1
		
	}
}