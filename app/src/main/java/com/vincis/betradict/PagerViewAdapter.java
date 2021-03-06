package com.vincis.betradict;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vincis.betradict.admin.Add_answers;
import com.vincis.betradict.admin.update_wallet;
import com.vincis.betradict.frags.frag2;
import com.vincis.betradict.frags.frag3;
import com.vincis.betradict.frags.frag4;
import com.vincis.betradict.frags.scoreFragment;

class PagerViewAdapter extends FragmentPagerAdapter{
    Bundle bd;
    public PagerViewAdapter(FragmentManager fm,Bundle b) {
        super(fm);
        bd=b;
    }


    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i)
        {
            case 0:
                   fragment= new fragtransactiondetails();
                   fragment.setArguments(bd);
                   break;


                case 1:
                   fragment=new scoreFragment();
                   fragment.setArguments(bd);
                   break;
            case 2:
                fragment=new frag2();
                fragment.setArguments(bd);
                break;
      case 3:
                fragment=new update_wallet();
                fragment.setArguments(bd);
                break;
            case 4:
                fragment=new Add_answers();
                fragment.setArguments(bd);
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
