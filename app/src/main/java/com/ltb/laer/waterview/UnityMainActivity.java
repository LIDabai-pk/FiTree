package com.ltb.laer.waterview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.unity3d.player.UnityPlayerActivity;

import java.util.Random;


public class UnityMainActivity extends UnityPlayerActivity {

    public static UnityMainActivity current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        current = null;
    }

    public static void goEnergyActivity() {
        if (current == null) {
            return;
        }
        int energys = new Random().nextInt(40) + 30;
        Toast.makeText(current, energys + " energy is generated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(current, EnergyActivity.class);
        intent.putExtra("energys", energys);
        current.startActivity(intent);
    }

    public static void goEnergyActivity(int energys) {
        if (current == null) {
            return;
        }
        Toast.makeText(current, energys + " energy is generated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(current, EnergyActivity.class);
        intent.putExtra("energys", energys);
        current.startActivity(intent);
    }
}
