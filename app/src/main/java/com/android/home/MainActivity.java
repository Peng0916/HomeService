package com.android.home;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    //轮播图片
    private int[] imageId = new int[]{
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    private ArrayList<View> items = new ArrayList<>();
    private ViewPager viewPager;
    private Runnable apagerAction;
    private int aitem;
    private boolean isFrist = true;
    private int mCurrentItem = 0;
    private boolean isScrolling = false; // 滚动框是否滚动着

    private boolean isCycle = false; // 是否循环
    //轮播方法
    private int currentPosition = 0; // 轮播当前位置
    private RadioGroup mgroup;
    private int[] radioButtonID = new int[]
            {R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4};
    private long releaseTime = 0; // 手指松开、页面不滚动时间，防止手机松开后短时间进行切换

    //生活商行类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //long t = Runtime.getRuntime().maxMemory();


        viewPager = (ViewPager) findViewById(R.id.view_pager);//轮播
        mgroup = (RadioGroup) findViewById(R.id.radioGroup1);


        //轮播方法
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //重点
            @Override
            public void onPageSelected(final int position) {
                mCurrentItem = position % items.size();
                viewPager.setCurrentItem(mCurrentItem);
                mgroup.check(radioButtonID[mCurrentItem]);
                items.get(position).findViewById(R.id.header_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, position, Toast.LENGTH_LONG).show();
                    }
                });
//                int Max = items.size() - 1;
//                int action = position;
//                currentPosition = action;
//                if (isCycle) {
//                    if (position == 0) {
//                        currentPosition = Max - 1;
//                    } else if (position == Max) {
//                        currentPosition = 1;
//
//                    }
//                    action = currentPosition - 1;
//                }
//                Toast.makeText(MainActivity.this, action +"action",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    isScrolling = true;
                    return;

                } else if (state == 0) {
                    if (viewPager != null) {
                        releaseTime = System.currentTimeMillis();
                        viewPager.setCurrentItem(currentPosition, false);

                    }
                    isScrolling = false;
                }

            }
        });

        //适配器关联视图并显示数据
        viewPager.setAdapter(new PagerAdapter() {
            //创建方法
            @Override
            public Object instantiateItem(View container, int position) {
                View view = items.get(position % items.size());
                viewPager.addView(view);
                return view;
            }

            //销毁方法
            @Override
            public void destroyItem(View container, int position, Object object) {
                View view = items.get(position & items.size());
                viewPager.removeView(view);
            }

            @Override
            public int getCount() {
                return imageId.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        //初始化viewPager方法
        initAllItems();

        apagerAction = new Runnable() {
            @Override
            public void run() {
                if (aitem != 0) {
                    if (isFrist == true) {
                        mCurrentItem = 0;
                        isFrist = false;
                    } else {
                        if (mCurrentItem == items.size()) {
                            mCurrentItem = 0;
                        } else {
                            mCurrentItem++;
                        }
                    }
                    viewPager.setCurrentItem(mCurrentItem);

                }
                viewPager.postDelayed(apagerAction, 2500);
            }
        };
        viewPager.postDelayed(apagerAction, 1000);
    }

    private void initAllItems() {
        //初始化item
        for (int i = 0; i < imageId.length; i++) {
            //5张图片自动轮播功能
            items.add(initAllItem(imageId[i]));
        }
    }

    private View initAllItem(int i) {
        View view = getLayoutInflater().inflate(R.layout.header, null);
        ImageView imageview = (ImageView) view.findViewById(R.id.header_view);
        imageview.setImageResource(i);
        return view;
    }
}
