package com.Cadient.QMobile.controller.transit;

import com.Cadient.QMobile.view.fragment.login.LoginFragment;
import com.Cadient.QMobile.view.fragment.login.SplashFragment;
import com.fsm.transit.bridge.FragmentActivity;
import com.fsm.transit.core.AbstractTransitManger;
import com.fsm.transit.core.TransitData;
import com.fsm.transit.core.TransitResultData;


/**
 * Created with IntelliJ IDEA.
 * User: elvis
 * Date: 3/26/14
 * Time: 10:29 AM
 * <p/>
 * <p>This class represent all transition between fragments in StartActivity</p>
 */
public class StartTransitManager extends AbstractTransitManger<FragmentAction> {
    public StartTransitManager(FragmentActivity activity) {
        super(activity);
    }

    {
        transitionsMap.put(new TransitData<FragmentAction>(LoginFragment.class, FragmentAction.SPLASH_ACTION), new TransitResultData<FragmentAction>(SplashFragment.class, false));
        transitionsMap.put(new TransitData<FragmentAction>(SplashFragment.class, FragmentAction.AUTHORISATION_FAIL_ACTION), new TransitResultData<FragmentAction>(LoginFragment.class, false));
    }
}
