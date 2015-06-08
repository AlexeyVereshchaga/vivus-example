package com.Cadient.QMobile.controller.transit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.Cadient.QMobile.utils.HelpUtils;
import com.fsm.transit.bridge.FragmentActivity;
import com.fsm.transit.core.AbstractTransitManger;


/**
 * Created with IntelliJ IDEA.
 * User: elvis
 * Date: 3/26/14
 * Time: 10:29 AM
 * <p/>
 * <p>This class represent all transition between fragments in MainActivity</p>
 */
public class MainTransitManager extends AbstractTransitManger<FragmentAction> {
    public MainTransitManager(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public void switchBranch(Class<? extends Fragment> fragmentClass) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        switchFragment(fragmentClass);
    }

    @Override
    protected String getBackStackName(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        return ((bundle != null && bundle.getString(HelpUtils.FRAGMENT_NAME_KEY) != null)
                ? fragmentClass.getName() + bundle.getString(HelpUtils.FRAGMENT_NAME_KEY)
                : fragmentClass.getName());
    }
}
