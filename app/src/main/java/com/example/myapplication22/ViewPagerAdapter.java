
package com.example.myapplication22;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(
            @NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new FirstFragment();
        }
        else if (position == 1) {
            return new SecondFragment();
        }
        else {
            return new ThirdFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
