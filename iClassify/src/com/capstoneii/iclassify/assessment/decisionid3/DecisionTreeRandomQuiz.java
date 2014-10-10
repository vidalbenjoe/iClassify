package com.capstoneii.iclassify.assessment.decisionid3;


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

public class DecisionTreeRandomQuiz extends Activity {
	
	List<Question> quesList;
	int score;
	int page = 1;
	int qid = 0;
	int qset = 4;
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

	int totalsumof;
	int sumOf;
	double jsper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_quiz);
		
		QuizSession = new SessionCache(getApplicationContext());
		openDB();
		
		myDb.delAllTempRows();
		quesList = myDb.getAllQuestions4();
		if(quesList.size() == 0){			
			addQuestions4();
			quesList = myDb.getAllQuestions4();		
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
			private Date date;
			@SuppressLint("SimpleDateFormat") @Override
			public void onClick(View v) {			
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				grp.clearCheck();
				page++;
				if(question.getQans().equals(answer.getText())){
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();;
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("4", lid, qitem, qans, quserans));
					score++;
					Log.d("score result", "Your score is "+score);
				}else{
					String lid = tvRef.getText().toString();
					String qitem = tvQue.getText().toString();
					String qans = question.getQans().toString();
					String quserans = answer.getText().toString();
					myDb.addtempQuestion(new TempQuestion("4", lid, qitem, qans, quserans));
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
						String finalDate = timeFormat.format(date);
						String subj = ncourse +" "+qset;
						String qdetails = "Quiz Retake "+retake;
						myDb.addscores(3, retake, subj, qdetails, score, finalDate);
						//myDb.addScores(3, subj , score, finalDate);
						myDb.deleteQuiz("Flash Chapter 4");
						
						Intent intent = new Intent(DecisionTreeRandomQuiz.this,QuizResultActivity.class);
						Bundle b = new Bundle();
						b.putInt("qno", qset);
						b.putInt("score", score);
						b.putString("course", ncourse);// Your score
						intent.putExtras(b);
						startActivity(intent);
						DecisionTreeRandomQuiz.this.finish();
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
		
		final Dialog dialog = new Dialog(DecisionTreeRandomQuiz.this,R.style.DialogAnim);

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
			    myDb.deleteQuiz("Flash Chapter");
				
				Intent intent = new Intent(DecisionTreeRandomQuiz.this,QuizResultActivity.class);
				Bundle b = new Bundle();
				b.putInt("qno", qset);
				b.putInt("score", score);
				b.putString("course", ncourse);// Your score
				intent.putExtras(b);
				startActivity(intent);
				DecisionTreeRandomQuiz.this.finish();
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
	public void addQuestions4() {	
			
		myDb.addQuestions4(new Question("0",
				"Which of the following would best describe about TLF?",
				"It is a more recent and more powerful text engine.",
				"It is the process of creating a continuous motion and shape change.","It is a more recent and more powerful text engine.","It display of a sequence of static images that minimally differ from each other.","It build a motion path animating the frames between that frame and the next keyframe.")); //1
						// 2
						myDb.addQuestions4(new Question("0",
				"It is more recent and more powerful text engine", 
				 "Text Layout Framework","Text Library Framework", "Text Layout Framework","Text Layer Framework","Text Layout Frame")); //1
						// 3
						myDb.addQuestions4(new Question("2",
				"What is the first step when you creating a TLF?",
				"Add New Layer","Add a Keyframe", "Adjust the Property Panel","Add New Layer","Insert Blank Frame")); //1
						// 4
						myDb.addQuestions4(new Question("32",
				"It is a link from a hypertext file or document to another location or file, typically activated by clicking on a highlighted word or image on the screen.",
				      "Hyperlink", "Acknowledgment", "Hyperlink","Superscript","Subscript")); // 21
						// 5
						myDb.addQuestions4(new Question("4",
				"What does it mean the Read Only after you set the Text Tool?",
				"It display the text that can’t be selected or edited.", "It didn’t display the text nor be selected or edited.", "It display the text box but doesn’t display in Stage.", "It display the text that can’t be selected or edited.","The user will not able to display the text.")); //4
						// 6
						myDb.addQuestions4(new Question("4",
								"Which of the following the best describe about selectable?", 
				   "It displays text that the user select to copy.", "It displays text that the user select to copy.","It can’t be selected nor copy.", " It displays text that the user can select and edit", "I didn’t displays the text.")); //4
						// 7
						myDb.addQuestions4(new Question("4",
				"It displays text that the user can select and edit.",
				"Editable", "Read Only","Selectable", "Editable","Edit"));//4
						// 8
						myDb.addQuestions4(new Question("6",
								"Language that display correctly..",
					"Asian Language", "English Language", "French Language", "Korean Language","Asian Language"));//7
						// 9
						myDb.addQuestions4(new Question("17",
							          "In what Panel you were able to change the font size and the font style?",
					"Property Inspector", "Library Inspector","Library Panel", "Menu Bar", "Property Inspector"));//18
						// 10
						myDb.addQuestions4(new Question("36",
					                                                  "It accepts input from the user through the keyboard and displays estimated monthly payments based on those inputs.",
					"Mortgage calculator", "Input", "Mortage", "Mortgage calculator","User Text")); //23
						// 11
						myDb.addQuestions4(new Question("9",
					                                   "It is a text that usually used by the Asian for able to correct them.",
					                                   "Vertical Text","Horizontal Text", "Vertical Text","Slant  Text","Text")); //7
						// 12
						myDb.addQuestions4(new Question("0",
							"Note: If you want to use more sophisticated controls for text formatting such as multiple columns or wrap-around text, you choose _________ .",
				 "TLF","TLF","Plain Text","Classic Text","Text")); //1
						// 13
						myDb.addQuestions4(new Question("4",
				"It can be used in text input fields, such as login and passwords.",
					"Editable Option", "Selectable Option", "Read Only Option", "Editable Option","Readable Option")); //4
						// 14
						myDb.addQuestions4(new Question("32",
					"It is a text references so your users can click it and be directed to a Web site with additional information.",
					"Hyperlink", "Hypertext", "Hyperlink", "Hypersub","Hyperclick"));//21
						// 15
						myDb.addQuestions4(new Question("19",
				"It provides details of this hypothetical property ,such as the number of rooms, number of beds, and so on.",
					"Text Box", "Text Caption", "Text Box", "Text Description ","Text ")); //13

				                                  // 16
						myDb.addQuestions4(new Question("9",
							"It is used for unusual displays.",
					                 "Vertical text","Text","Horizontal text","Vertical text","None of the above")); //7
						// 17
						myDb.addQuestions4(new Question("4",
							"It displays text that the user can select to copy.",
					"Selectable", "Editable", "Selectable", "Read only","None of the above")); //4
						// 18
						myDb.addQuestions4(new Question("19",
						"It provides details of this hypothetical property.",
					"Text box", "Text ", "Text font", "Text color","Text box"));//13
						// 19
						myDb.addQuestions4(new Question("34",
						"Selected words in your text box become underlined is called _______.",
					"hyperlinked", "hypertext", "hypertext mark", "hyperlinked","None of the above")); //22
						// 20
						myDb.addQuestions4(new Question("36",
							"It accepts input from the user and it display the output based on the input.",
				       "Mortgage Calculator","Calculator","Mortgage Calculator","Mortage Calculator","Mortrage Calculator")); //23
						// 21
						myDb.addQuestions4(new Question("25",
							"It is more visual interest and more pleasing design.",
					"Wrapping Text", "Text", "Design Text", "Wrapper Text","Wrapping Text")); //17
						// 22
						myDb.addQuestions4(new Question("12",
						"It becomes smaller and raised from the baseline called as ______.",
					"Superscript", "Subscript", "Superscript", "Symbol","Numbers"));//9
						// 23
						myDb.addQuestions4(new Question("0",
						"It controls for text formatting such as multiple columns or wrap-around text.",
					"TLF Text", "LTF Text", "FTL Text", "TLF Text","TFL Text")); //1

						// 24
						myDb.addQuestions4(new Question("4",
							"________  can create Mortgage Calculator?",
						                "Editable","Read Only","Selectable","Editable","None of the above")); //4
						// 25
						myDb.addQuestions4(new Question("10",
								"It is use to modify the way your text appear.",
					"Properties Inspector", "Library Panel", "Property Panel ", "Properties Inspector","Library Inspector")); //4
						// 26
						myDb.addQuestions4(new Question("22",
						"It means there is overflow text that is not visible.",
					"Red Cross", "Red Cross", "Blue Cross", "Orange Cross","Violet Cross"));//15
						// 27
						myDb.addQuestions4(new Question("32",
						"It doesn’t require any HTML or ActionScript coding.",
					"Hyperlink", "Hyperlink", "Hypertext", "Hypertmark text","None of the above")); //21
						// 28
						myDb.addQuestions4(new Question("34",
							"What do you need to include in the protocol before any URL for you to be able to choose a site on the Web?",
						        "http://","www","http","www.","http://")); //22
						// 29
						myDb.addQuestions4(new Question("35",
						"It is the selected words become _______ and remain underlined which is the standard visual cue for a hyperlinked item in a browser.",
					"blue", "blue", "yellow", "orange","red")); //22
						// 30
						myDb.addQuestions4(new Question("42",
						"It guides the users to know what kind of text is expected.",
					"Editable text box", "Selectable text", "Editable text", "Selectable text box","Editable text box"));//26
						// 31
						myDb.addQuestions4(new Question("32",
						"Hyperlink doesn’t require __________ or ActionScript coding.",
					"HTML", "HTML", "HTML 2", "HTML 3 ","HTML 5 ")); //21
						// 32
						myDb.addQuestions4(new Question("0",
							"Text Layout Framework is more recent and more powerful __________.",
						      "text engine","text","text style","text color","text engine")); //1
						// 33
						myDb.addQuestions4(new Question("4",
					"It also use for text input fields such as login and password.",
					"Editable", "Read Only", "Editable", "Selectable","None of the above")); //4
						// 34
						myDb.addQuestions4(new Question("4",
						"Read only displays text that can’t be selected or ________ by the end user.",
					"edited", "edited", "compile", "removable","organized"));//4
						// 35
						myDb.addQuestions4(new Question("9",
						"_______ Language used vertical text to display them correctly?",
					"Asian", "English", "Korean", "Filipino","Asian")); //7
						// 36
						myDb.addQuestions4(new Question("22",
							"It display when the text is not visible.",
						           "red cross","yellow cross","blue cross","red cross","red tag")); //15
						// 37
						myDb.addQuestions4(new Question("25",
						        "Using wrapping text you can create more ______________ and more pleasing design.",
				"more visual interest", "more colorful design", "more visual interest", "more different design","more different text style")); //17
						// 38
						myDb.addQuestions4(new Question("21",
						"The word become a hyperlinked when the word in you text box is _______________ . ",
					"underlined", "bold", "italic", "underlined","All of the above"));//22
						// 39
						myDb.addQuestions4(new Question("36",
						"By adding or creating the static text elements all text cannot be __________ or edited.",
					"change", "remove ", "add ", "change","edited")); //23
						// 40
						myDb.addQuestions4(new Question("40",
							"It display a semitransparent white background.",
				    "Editable text box","Editable text box","Selectable text box","Read Only text box","Text box")); //25
						// 41
						myDb.addQuestions4(new Question("42",
							"It guides the user to know what kind of text is expected.",
					"Editable text box", "Selectable text", "Editable text", "Selectable text box","Editable text box")); //26
						// 42
						myDb.addQuestions4(new Question("25",
						"It is used to create a more visual interest and more pleasing design. around photographs or graphic element.",
					"wrap text", "wrap text", "wrapping text", "wrapped text","None of the above"));//17
						// 43
						myDb.addQuestions4(new Question("4",
						"Which of the following doesn’t not belong to TLF group?",
					"None of the above", "Readable", "Selectable", "Editable","None of the above")); //4
						// 44
						myDb.addQuestions4(new Question("3",
						    " It is use as replacement if Times New Roman is not available to your computer.",
						                "TLF","TTL","TFF","TLF","FTF")); //3
						// 45
						myDb.addQuestions4(new Question("4",
						"It is a part of TLF that you can select and edit.",
					"Editable", "Edit", "Edited", "Editable","All of the above")); //4
						// 46
						myDb.addQuestions4(new Question("4",
						"It is a part of TLF that you can select and copy.",
					"Selectable", "Select", "Selectable", "Editable","None of the above"));//4
						// 47
						myDb.addQuestions4(new Question("4",
						"It is a part of TLF that you can’t be selected or edited.",
					"Read Only", "Read Only", "Selectable", "Editable ","None of the above")); //4
						// 48
						myDb.addQuestions4(new Question("9",
							"Using _____  you can create a vertical text. ",
						                "TLF","FTF","TTF","LTF","TLF")); //7
						// 49
						myDb.addQuestions4(new Question("18",
								"______ text is capable of displaying 1 to 3 columns.",
					"TLF", "Readable", "Read Only", "Editable","TLF")); //13
						// 50
						myDb.addQuestions4(new Question("32",
						"What is the correct of creating a hyperlink text?",
					"Lesson 5", "Lesson 6", "Lesson 5", "Lesson 4","Lesson 3"));//21
	}
}