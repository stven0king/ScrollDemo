package tzx.com.scrolldemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by tanzhenxing on 2015/7/29.
 * 主要通过复写onMeasure方法与onLayout方法
 */
public class MultiViewGroup extends ViewGroup {
    private Context mContext;
    private static String TAG = "MultiViewGroup";
    public MultiViewGroup(Context context) {
        super(context);
        mContext = context;
        init();
    }
    public MultiViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }
    private void init() {
        // 初始化3个 LinearLayout控件
        LinearLayout oneLL = new LinearLayout(mContext);
        oneLL.setBackgroundColor(Color.RED);
        addView(oneLL);
        LinearLayout twoLL = new LinearLayout(mContext);
        twoLL.setBackgroundColor(Color.YELLOW);
        addView(twoLL);
        LinearLayout threeLL = new LinearLayout(mContext);
        threeLL.setBackgroundColor(Color.BLUE);
        addView(threeLL);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "--- start onMeasure --");
        // 设置该ViewGroup的大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        /*
        //计算自定义的ViewGroup中所有子控件的大小
        int childCount = getChildCount();
        Log.i(TAG, "--- onMeasure childCount is -->" + childCount);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 设置每个子视图的大小 ， 即全屏
            child.measure(MultiScreenActivity.screenWidth, MultiScreenActivity.scrrenHeight);
        }
        */
        // 计算自定义的ViewGroup中所有子控件的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        Log.i(TAG, "--- start onLayout --");
        int startLeft = 0; // 每个子视图的起始布局坐标
        int startTop = 10; // 间距设置为10px 相当于 android：marginTop= "10px"
        int childCount = getChildCount();
        Log.i(TAG, "--- onLayout childCount is -->" + childCount);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(startLeft, startTop,
                    startLeft + MultiScreenActivity.screenWidth,
                    startTop + MultiScreenActivity.scrrenHeight);
            startLeft = startLeft + MultiScreenActivity.screenWidth ; //校准每个子View的起始布局位置
            //三个子视图的在屏幕中的分布如下 [0 , 320] / [320,640] / [640,960]
        }
    }
}
