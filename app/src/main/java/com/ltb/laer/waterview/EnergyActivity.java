package com.ltb.laer.waterview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ltb.laer.waterview.model.TreeRecord;
import com.ltb.laer.waterview.model.Water;
import com.ltb.laer.waterview.util.Constant;
import com.ltb.laer.waterview.util.DataAccess;
import com.ltb.laer.waterview.util.SPUtils;
import com.ltb.laer.waterview.view.WaterView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnergyActivity extends AppCompatActivity {
    public static final String cate_0 = "0";
    public static final String cate_1 = "1";
    public static final String cate_2 = "2";
    public static final String cate_3 = "3";
    private WaterView mWaterView;
    private List<Water> mWaters = new ArrayList<>();
    private List<TreeRecord> list = new ArrayList<>();
    private DataAccess dataAccess = new DataAccess(this);
    private int totalWater;

//    {
//        for (int i = 0; i < 10; i++) {
//            mWaters.add(new Water((int) (i + Math.random() * 4), "item" + i));
//        }
//    }

    private void generateEnergy(int total) {
        int nums = 5;
        int[] split=new int[nums-1];
        Random random=new Random();
        for (int i = 0; i < split.length; i++) {
            split[i]=random.nextInt(total-1)+1;
        }
        Arrays.sort(split);
        for (int i = 0; i < nums; i++) {
            if(i==0){
                mWaters.add(new Water(split[i], "item" + i));
                continue;
            }else if(i==nums-1){
                mWaters.add(new Water(total-split[nums-2], "item" + i));
                continue;
            }
            mWaters.add(new Water(split[i]-split[i-1], "item" + i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        Intent intent = getIntent();
        int energys = intent.getIntExtra("energys", 50);
        generateEnergy(energys);

        mWaterView = findViewById(R.id.wv_water);
        mWaterView.setWaters(mWaters);
        totalWater = (int) SPUtils.get(this,Constant.sp_sum,0);
        dealWaterData(totalWater);

        final ImageButton ibtnForest = findViewById(R.id.ibtnForest);
        ibtnForest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnergyActivity.this, ForestActivity.class);
                startActivity(intent);
            }

        });

        final ImageButton ibtnPlus = findViewById(R.id.ibtnPlus);

        ibtnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnergyActivity.this, ChooseActivity.class);
                intent.putExtra("total", mWaterView.getmTotalConsumeWater());
                startActivity(intent);
            }
        });
        //查询数据
    }


    @Subscriber(tag = "deal_total_warter")
    public void dealWaterData(int data){
        totalWater = data;
        mWaterView.setmTotalConsumeWater(totalWater);
        TextView sum = (TextView) findViewById(R.id.sum);
        sum.setText("Sum:" + String.valueOf(totalWater));
        SPUtils.put(this, Constant.sp_sum,totalWater);
    }

    public void onRest(View view) {
        mWaterView.setWaters(mWaters);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
