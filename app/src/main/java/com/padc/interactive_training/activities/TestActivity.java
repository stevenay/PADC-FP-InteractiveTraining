package com.padc.interactive_training.activities;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.padc.interactive_training.InteractiveTrainingApp;
import com.padc.interactive_training.R;

public class TestActivity extends AppCompatActivity {

    public static Intent newIntent(){
        Intent newIntet = new Intent(InteractiveTrainingApp.getContext(), TestActivity.class);
        return newIntet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        }

        //region object of moving implementation touch and drag Listener
        //touch and drag
        findViewById(R.id.tv_questionn_text).setOnTouchListener(new MyTouchListener());

        //drop and drop container
        findViewById(R.id.ll_question_content_container).setOnDragListener(new MyDragListener());
        findViewById(R.id.ll_answer_content_container_one).setOnDragListener(new MyDragListener());
        findViewById(R.id.ll_answer_content_container_two).setOnDragListener(new MyDragListener());
        //endregion

    }

    private final class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }

        }
    }

    class MyDragListener implements View.OnDragListener {
        //Shape declaration
        Drawable enterShape = getResources().getDrawable(R.drawable.orange_light_stroke_box);
        Drawable normalShape = getResources().getDrawable(R.drawable.grey_stroke_box);
        Drawable dropShape = getResources().getDrawable(R.drawable.black_stroke_box);


        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    owner.setBackgroundDrawable(normalShape);

                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    v.setBackgroundDrawable(dropShape);

                    checkDropContainer(container.getId());

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //currently no action
                    break;
                default:
                    break;
            }
            return true;
        }

        private void checkDropContainer(int dropContainer){
            switch (dropContainer){
                case R.id.ll_question_content_container:
                    Toast.makeText(getApplicationContext(), "Drop at Question container", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_answer_content_container_one:
                    Toast.makeText(getApplicationContext(), "Drop at Answer One", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_answer_content_container_two:
                    Toast.makeText(getApplicationContext(), "Drop at Answer two", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    }



}
