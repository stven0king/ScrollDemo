package tzx.com.scrolldemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 58 on 2015/7/28.
 */
public class MultiScreenActivity extends Activity implements View.OnClickListener {
    public static int screenWidth  ;  // 屏幕宽度
    public static int scrrenHeight ;  //屏幕高度
    private Button bt_scrollLeft;
    private Button bt_scrollRight;
    private MultiViewGroup mulTiViewGroup  ;
    private int curscreen = 0;   // 当前位于第几屏幕  ，共3个"屏幕"， 3个LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.multiview);
        //获取屏幕分辨率的大小
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Display dp = wm.getDefaultDisplay();
        //screenWidth = dp.getWidth();
        //scrrenHeight = dp.getHeight();
        screenWidth = metrics.widthPixels ;
        scrrenHeight = metrics.heightPixels;
        //获取自定义视图的空间应用
        mulTiViewGroup = (MultiViewGroup) findViewById(R.id.mymultiViewGroup);

        bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
        bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);

        bt_scrollLeft.setOnClickListener(this);
        bt_scrollRight.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        System.out.println("screenWidth=" + screenWidth);
        System.out.println("scrrenHeight"+scrrenHeight);
        switch (v.getId()){
            case R.id.bt_scrollLeft:
                if (curscreen > 0){
                    curscreen -- ;
                    Toast.makeText(MultiScreenActivity.this, "第" + (curscreen + 1) + "屏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MultiScreenActivity.this, "当前已是第一屏",Toast.LENGTH_SHORT).show();
                }
                mulTiViewGroup.scrollTo(curscreen * screenWidth, 0);
                break;
            case R.id.bt_scrollRight:
                if (curscreen < 5){
                    curscreen ++ ;
                    Toast.makeText(MultiScreenActivity.this, "第" + (curscreen+1) + "屏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MultiScreenActivity.this, "当前已是最后一屏",Toast.LENGTH_SHORT).show();
                }
                mulTiViewGroup.scrollTo(curscreen * screenWidth, 0);
                break;
        }
    }


}
