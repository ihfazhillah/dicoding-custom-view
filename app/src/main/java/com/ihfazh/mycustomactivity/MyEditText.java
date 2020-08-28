package com.ihfazh.mycustomactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.LayoutDirection;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class MyEditText extends AppCompatEditText {
    private Drawable mClearButton;

    public MyEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (getCompoundDrawablesRelative()[2] != null){
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = mClearButton.getIntrinsicWidth() + getPaddingStart();
                        if (motionEvent.getX() < clearButtonEnd){
                            isClearButtonClicked = true;
                        }
                    } else {
                        clearButtonStart = (getWidth() - getPaddingEnd() - mClearButton.getIntrinsicWidth());
                        if (motionEvent.getX() > clearButtonStart){
                            isClearButtonClicked = true;
                        }
                    }

                    if (isClearButtonClicked){
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                            mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null);
                            showClearButton();
                            return true;
                        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                            mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null);
                            if (getText() != null){
                                getText().clear();
                            }
                            hideClearButton();
                            return true;

                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()){
                    showClearButton();
                } else {
                    hideClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButton, null);

    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan Nama Anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }
}
