package com.capstoneii.iclassify.library;


import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.TextView;

public class TypewriterTextView extends TextView {
	
	private final static float TEXT_CHARACTER_DELAY = 0.1f;
    private final static int TEXT_CHARACTER_DELAY_MS = (int)(TEXT_CHARACTER_DELAY * 1000);
    
    private int mCurrentCharacter;
    private long mLastTime;
    private CharSequence mText;

    /**
     * If any settings have changed.
     */
    protected boolean changed = false;
    public TypewriterTextView(Context context) {
        super(context);
    }
    
    public TypewriterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public TypewriterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setTypewriterText(CharSequence text) {
        mText = text;
        mCurrentCharacter = 0;
        mLastTime = 0;
        postInvalidate();
    }
    
    public void snapToEnd() {
        mCurrentCharacter = mText.length() - 1; 
    }    

	@Override
    public void onDraw(Canvas canvas) {
        final long time = SystemClock.uptimeMillis();
        final long delta = time - mLastTime;
        if (delta > TEXT_CHARACTER_DELAY_MS) {
            if (mText != null) {
                if (mCurrentCharacter <= mText.length()) {
                    CharSequence subtext = mText.subSequence(0, mCurrentCharacter);
                    setText(subtext, TextView.BufferType.SPANNABLE);
                    mCurrentCharacter++;
                    postInvalidateDelayed(TEXT_CHARACTER_DELAY_MS);
                }
            }
        }
        super.onDraw(canvas);
    }



}

