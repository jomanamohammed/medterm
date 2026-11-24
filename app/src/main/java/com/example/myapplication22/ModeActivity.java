
package com.example.myapplication22;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    Button btnSetMode;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btnSetMode = findViewById(R.id.btnSetMode);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        ViewPagerAdapter adapter =
                new ViewPagerAdapter(this);

        viewPager.setAdapter(adapter);

        String[] tabNames = {
                "First", "Second", "Third"
        };

        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) ->
                        tab.setText(tabNames[position])
        ).attach();

        btnSetMode.setOnClickListener(this::showPopupMenu);

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {

                        if (viewPager.getCurrentItem() != 0) {
                            viewPager.setCurrentItem(0, true);
                        } else {
                            finish();
                        }
                    }
                });
    }

    private void showPopupMenu(View view) {

        PopupMenu popupMenu =
                new PopupMenu(this, btnSetMode);

        popupMenu.getMenu().add("Light");
        popupMenu.getMenu().add("Night");
        popupMenu.getMenu().add("System");

        popupMenu.setOnMenuItemClickListener(item -> {

            String mode = item.getTitle().toString();

            if (mode.equals("Light")) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
            }
            else if (mode.equals("Night")) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
            }
            else if (mode.equals("System")) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }

            return true;
        });

        popupMenu.show();
    }
}
