package com.padc.interactive_training.components;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.padc.interactive_training.utils.MMFontUtils;


/**
 * Created by htoomt on 10/4/2016.
 */

public class MMCollapsingToolbarLayout extends CollapsingToolbarLayout {

    TextView title;
    boolean reflected = false;

    public MMCollapsingToolbarLayout(Context context) {
        super(context);
        MMFontUtils.applyMMFontToCollapsingToolbar(this);
    }

    public MMCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        MMFontUtils.applyMMFontToCollapsingToolbar(this);
    }

    public MMCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        MMFontUtils.applyMMFontToCollapsingToolbar(this);
    }


}
