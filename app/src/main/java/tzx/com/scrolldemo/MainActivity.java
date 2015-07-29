package tzx.com.scrolldemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;


public class MainActivity extends Activity {

    public static String TAG = "";
    LinearLayout l0,l1,l2;
    Scroller mScroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        //mScroller = new Scroller(this,new DecelerateInterpolator(2.0f));
        mScroller = new Scroller(this);
        l1 = new MyLinearLayout(this);
        l2 = new MyLinearLayout(this);

        l1.setBackgroundColor(Color.GRAY);
        l2.setBackgroundColor(Color.WHITE);

        l0 = new ContentLinearLayout(this);
        l0.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams p0 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(l0,p0);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.weight = 1;

        l1.setLayoutParams(p);
        l2.setLayoutParams(p);

        l0.addView(l1);
        l0.addView(l2);

        MyButton button1 = new MyButton(this);
        MyButton button2 = new MyButton(this);
        MyButton button3 = new MyButton(this);
        button1.setText("btn int layout1");
        button2.setText("btn int layout2");
        button3.setText("btn3");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroller.startScroll(100,100, -200, 0, 500);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroller.startScroll(mScroller.getCurrX(), mScroller.getCurrY(), -300, 0, 600);
            }
        });
        l1.addView(button1);
        l1.addView(button3);
        l2.addView(button2);

        l0.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        l2.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        l1.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    class MyButton extends Button{

        public MyButton(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Log.d("MyButton", this.toString() + " onDraw------");
        }
    }

    class MyLinearLayout extends LinearLayout{

        public MyLinearLayout(Context context) {
            super(context);
        }

        @Override
        public void computeScroll() {
            super.computeScroll();
            Log.d(TAG, this.toString() + " computeScroll-----------");
            if (mScroller.computeScrollOffset())//如果mScroller没有调用startScroll，这里将会返回false。
            {
                //因为调用computeScroll函数的是MyLinearLayout实例，
                //所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例
                //scrollTo(mScroller.getCurrX(), 20);
                Log.d(TAG, "getCurrX = " +  mScroller.getCurrX());

                //继续让系统重绘
                //postInvalidate();
                //继续让系统重绘
                //invalidate();
            }
        }
    }

    class ContentLinearLayout extends LinearLayout {
        public ContentLinearLayout(Context ctx) {
            super(ctx);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            Log.d("ContentLinearLayout", "contentview dispatchDraw");
            super.dispatchDraw(canvas);
        }
        //@Override
        //public void computeScroll() {
        //    super.computeScroll();
        //    Log.d(TAG, this.toString() + " computeScroll-----------");
        //    if (mScroller.computeScrollOffset())//如果mScroller没有调用startScroll，这里将会返回false。
        //    {
        //        //因为调用computeScroll函数的是MyLinearLayout实例，
        //        //所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例
        //        scrollTo(mScroller.getCurrX(), 0);
        //        Log.d(TAG, "getCurrX = " +  mScroller.getCurrX());
        //
        //        //继续让系统重绘
        //        //postInvalidate();
        //        //继续让系统重绘
        //        //getChildAt(0).invalidate();
        //    }
        //}
    }
}
