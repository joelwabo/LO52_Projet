package com.example.joelw.multimediaserver.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

import com.example.joelw.multimediaserver.R;
import com.example.joelw.multimediaserver.SettingActivity;


public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    TabLayout mtabLayout;
    static String sUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mtabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Toast.makeText(this, "Veillez modifier l'adresse ip dans les paramètres", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
            startActivityForResult(settingIntent, 0);
            return true;
        }
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_favorite) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            SharedPreferences preferences = getSharedPreferences("preference", Context.MODE_WORLD_WRITEABLE);
            if (preferences.getString("IPSERVER", "") == ""){
                Toast toast = Toast.makeText(getApplicationContext(), "Please set the address and the port first", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                sUrl = "http://"+preferences.getString("IPSERVER", "0.0.0.0")+"/LO52/index.php";
            }
            //Innflate the main activitu with Movie, Music and picture data
            mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mtabLayout.getTabCount());

            mViewPager = (ViewPager) findViewById(R.id.viewPager);
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtabLayout));

            mtabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }
}
