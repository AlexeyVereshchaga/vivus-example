package com.Cadient.QMobile.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.controller.DataManager;
import com.Cadient.QMobile.controller.HeaderController;
import com.Cadient.QMobile.controller.SpiceManagerContainer;
import com.Cadient.QMobile.controller.error.ErrorHandler;
import com.Cadient.QMobile.controller.transit.FragmentAction;
import com.Cadient.QMobile.database.DataBaseGateway;
import com.Cadient.QMobile.model.remote.dynamicserver.DynamicServer;
import com.Cadient.QMobile.network.RetrofitService;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.Observable;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.Cadient.QMobile.view.fragment.DashboardFragment;
import com.Cadient.QMobile.view.fragment.listener.NotifyCalendarListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.transit.core.ITransitManager;
import com.octo.android.robospice.SpiceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class BaseActivity extends FragmentActivity implements com.fsm.transit.bridge.FragmentActivity, SpiceManagerContainer {

    private DataManager dataManager = new DataManager(this);
    private SpiceManager spiceManager = new SpiceManager(RetrofitService.class);
    protected ITransitManager<FragmentAction> transitManager;
    protected ErrorHandler errorHandler;
    protected Observable observable;

    private NotifyCalendarListener calendarListener;
    private Calendar calendar;

    protected HeaderController headerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutRes());
        DataBaseGateway.getInstance().init(getApplicationContext());
        createErrorHandler();
        createTransitManager();
        getTransitManager().setCurrentContainer(getMainFragmentContainerRes());
        initTitleController();
//        //hide status bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (savedInstanceState == null) {
            getTransitManager().switchFragment(getFragmentForStart(), (Bundle) null, false);
        }
        if (calendarListener != null && getHeaderController().getArrowRight() != null && getHeaderController().getArrowLeft() != null) {
            getHeaderController().setArrowRight(calendarListener, true);
            getHeaderController().setArrowLeft(calendarListener, true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataManager().readDynamicServer(new Callback<DynamicServer>() {
            @Override
            public void success(DynamicServer dynamicServer, Response response) {
                ServerHelpUtils.writeCurrentServer(dynamicServer);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Log.i("Retrofit", mapper.writeValueAsString(dynamicServer));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    public void setCalendarListener(NotifyCalendarListener calendarListener) {
        this.calendarListener = calendarListener;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    public void setSpiceManager(SpiceManager spiceManager) {
        this.spiceManager = spiceManager;
    }

    /**
     * set layout resource for current activity (for setContentView method)
     *
     * @return layout resource
     */
    protected abstract int getActivityLayoutRes();

    /**
     * set FrameLayout id for fragment container
     *
     * @return id for transit manager
     */
    protected abstract int getMainFragmentContainerRes();

    /**
     * create concrete TransitManager object for activity page transitions
     */
    protected abstract void createTransitManager();

    /**
     * method provide access to transit manager object for current activity
     *
     * @return TransitManager object, what manipulate fragments
     */
    public ITransitManager<FragmentAction> getTransitManager() {
        return transitManager;
    }

    /**
     * set fragment for start after activity open
     *
     * @return Fragment for start
     */
    protected abstract Class<? extends Fragment> getFragmentForStart();

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    protected void onDestroy() {
        DataBaseGateway.getInstance().release();
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

    /**
     * create concrete ErrorHandler object for handling server and inner errors
     */
    protected abstract void createErrorHandler();

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    protected int getHeaderViewIdRes() {
        return 0;
    }

    protected void initTitleController() {
        if (getHeaderViewIdRes() != 0) {
            headerController = new HeaderController(this);
            getHeaderController().setViewHeader(findViewById(getHeaderViewIdRes()));
            getHeaderController().setTransitManager(transitManager);
            getHeaderController().init();
        }
    }

    public void notifyArrows() {
        //todo quick fix
        if (!getTransitManager().getCurrentFragment().getClass().getSimpleName().equals(DashboardFragment.class.getSimpleName())) {
            SimpleDateFormat format = new SimpleDateFormat(HelpUtils.DATE_FORMAT_HEADER);
            getHeaderController().getArrowRight().setVisibility(!HelpUtils.compareDateByDay(Calendar.getInstance().getTimeInMillis(),
                    getCalendar().getTimeInMillis()) ? View.VISIBLE : View.INVISIBLE);
            getHeaderController().getArrowLeft().setVisibility(!HelpUtils.compareDateByDay(getCalendar().getTimeInMillis(),
                    TheApplication.getInstance().getRegistrationDate().getTime()) ? View.VISIBLE : View.INVISIBLE);
            getHeaderController().setTitle(format.format(getCalendar().getTime()));
        }
    }

    public HeaderController getHeaderController() {
        return headerController;
    }

    /**
     * start main activity and finish current
     *
     * @param src
     */
    public void startMainActivity(Bundle src) {
        Log.d("START MAIN", "start");
        Intent intent = new Intent(this, MainActivity.class);
        if (src != null) intent.putExtras(src);
        startActivity(intent);
        finish();
    }

    public void startMainActivity() {
        startMainActivity(null);
    }

    public Observable getObservable() {
        return observable;
    }
}
