package team9.munchiematch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class RecipeView extends LinearLayout {
    private TextView recipeTitle;
    private ImageView recipePicture;

    private String picturePath;

    public RecipeView(Context context) {
        super(context);
        init(null, 0);
    }

    public RecipeView(Context context, String title, String picturePath) {
        super(context);
        this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        this.setGravity(Gravity.CENTER);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setMinimumHeight(225);
        this.setMinimumWidth(225);

        recipeTitle = new TextView(context);
        recipeTitle.setText(title);

        recipePicture = new ImageView(context);
        recipePicture.setAdjustViewBounds(true);
        recipePicture.setMinimumHeight(200);
        recipePicture.setMinimumWidth(255);

        this.addView(recipeTitle);
        this.addView(recipePicture);

        this.picturePath = picturePath;

        init(null, 0);
    }

    public RecipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RecipeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setPic(picturePath);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RecipeView, defStyle, 0);


        a.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO -- Find where to place set pic
        // Why doesn't the Recipe View get drawn?
    }

    private void setPic(String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = recipePicture.getWidth();
        int targetH = recipePicture.getHeight();
        Log.e("RV", Integer.toString(targetH));
        Log.e("RV", Integer.toString(targetW));
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        recipePicture.setImageBitmap(bitmap);
    }
}
