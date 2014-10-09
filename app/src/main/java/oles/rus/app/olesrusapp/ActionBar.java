package oles.rus.app.olesrusapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.ExecutionException;


public class ActionBar extends View
{
    private Paint paint;
    private Paint paint2;
    private float percent;
    private float barWidth;
    private float avatarWidth;
    private float padding;
    private Bitmap avatar;
    private Paint paintAvatar;
    private ScoreGetCom scoreCom;


    public ActionBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        percent = 0.5f;
        paint = new Paint();
        paint.setARGB(255, 255, 0, 255);
        paint2 = new Paint();
        paint2.setARGB(255, 255, 0, 0);
        paintAvatar = new Paint();
        barWidth = Float.NEGATIVE_INFINITY;
        avatarWidth = Float.NEGATIVE_INFINITY;
        padding = Float.NEGATIVE_INFINITY;
        this.setAvatarImage(AvatarActivity.getUser().getAvatar());
        scoreCom = new ScoreGetCom();
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        try
        {
            this.setPercent(this.scoreCom.execute().get());
        } catch (InterruptedException e)
        {
        } catch (ExecutionException e)
        {
        }
        float padding = this.getPadding(canvas);

        float widthForAvatar = getWidthForAvatar(canvas);
        float widthForProgressBar = this.getWidthForProgressBar(canvas);
        int height = canvas.getHeight();

        float right = widthForAvatar + widthForProgressBar * percent + padding + padding;
        canvas.drawRect(widthForAvatar + padding + padding, 0, right, height, paint);
        canvas.drawRect(right, 0, widthForAvatar + widthForProgressBar + padding + padding, height, paint2);

        canvas.drawBitmap(
                Bitmap.createScaledBitmap(avatar, (int) widthForAvatar, height, true),
                padding,
                0,
                paintAvatar
        );

    }

    protected float getWidthForProgressBar(Canvas canvas)
    {
        if (this.barWidth == Float.NEGATIVE_INFINITY)
        {
            this.barWidth = canvas.getWidth() * 0.70f;
        }
        return this.barWidth;
    }

    protected float getWidthForAvatar(Canvas canvas)
    {
        if (this.avatarWidth == Float.NEGATIVE_INFINITY)
        {
            this.avatarWidth = canvas.getWidth() * 0.20f;
        }
        return this.avatarWidth;
    }

    protected float getPadding(Canvas canvas)
    {
        if (this.padding == Float.NEGATIVE_INFINITY)
        {
            this.padding = canvas.getWidth() * 0.05f;
        }
        return this.padding;
    }

    public void setPercent(float percent)
    {
        this.percent = percent;
    }

    public void setAvatarImage(int avatarIndex)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap avatar = null;
        switch (avatarIndex)
        {
            case 5:
                avatar = BitmapFactory.decodeResource(getResources(), R.drawable.a5, options);
                break;
            case 4:
                avatar = BitmapFactory.decodeResource(getResources(), R.drawable.a4, options);
                break;
            case 3:
                avatar = BitmapFactory.decodeResource(getResources(), R.drawable.a3, options);
                break;
            case 2:
                avatar = BitmapFactory.decodeResource(getResources(), R.drawable.a2, options);
                break;
            case 1:
            case 0:
            default:
                avatar = BitmapFactory.decodeResource(getResources(), R.drawable.a1, options);

        }

        this.avatar = avatar;
    }
}
