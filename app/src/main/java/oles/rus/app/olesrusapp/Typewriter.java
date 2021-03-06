package oles.rus.app.olesrusapp;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class Typewriter extends TextView
{

    private CharSequence mText;
    private static final int BLINKS_BEFORE = 4;
    private int mIndex;
    private long mDelay = 150;
    private static final int CURSOR_DUR = 6;
    private static final int NEW_LINE_MULTIPLIER = 3;
    private boolean finished = false;


    public Typewriter(Context context) {
        super(context);
    }

    public Typewriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            if(mIndex <= mText.length() && mIndex >= 0) {
                long delay = mDelay;
                if (mIndex < mText.length() && mText.charAt(mIndex) == '\n')
                {
                    delay *= NEW_LINE_MULTIPLIER;
                }
                setText(mText.subSequence(0, mIndex++) + "\u2588");
                if (!finished)
                    mHandler.postDelayed(characterAdder, delay);
            } else if(mIndex > mText.length()){
                if (mIndex % CURSOR_DUR < CURSOR_DUR/2)
                {
                    setText(mText + "\u2588");
                } else {
                    setText(mText);
                }
                mIndex++;
                if (!finished)
                    mHandler.postDelayed(characterAdder, mDelay);
            } else {
                if (Math.abs(mIndex) % CURSOR_DUR < CURSOR_DUR/2)
                {
                    setText("\u2588");
                } else {
                    setText(" ");
                }
                mIndex++;
                if (!finished)
                    mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = -BLINKS_BEFORE*CURSOR_DUR;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }
    
    public void finish()
    {
        mIndex = mText.length();
        setText(mText);
        finished = true;
    }
}
