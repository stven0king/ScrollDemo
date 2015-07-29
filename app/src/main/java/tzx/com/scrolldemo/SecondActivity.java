package tzx.com.scrolldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanzhenxing on 2015/7/28.
 */
public class SecondActivity extends Activity implements View.OnClickListener {
    private TextView tv;
    private Button bt_scrollLeft;
    private Button bt_scrollRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.tv_scroll);
        bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
        bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);
        bt_scrollRight.setOnClickListener(this);
        bt_scrollLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_scrollLeft:
                tv.scrollBy(20,0);
                int tvscrllX = tv.getScrollX();
                int tvscrllY = tv.getScrollY();
                System.out.println( " tvscrllX ---> " + tvscrllX + " --- tvscrllY ---> "+tvscrllY);
                bt_scrollLeft.scrollBy(20,0);
                break;
            case R.id.bt_scrollRight:
                tv.scrollTo(-100,0);
                int tvscrllXx = tv.getScrollX();
                int tvscrllYx = tv.getScrollY();
                System.out.println( " tvscrllX ---> " + tvscrllXx + " --- tvscrllY ---> "+tvscrllYx);
                break;
        }
    }
}
