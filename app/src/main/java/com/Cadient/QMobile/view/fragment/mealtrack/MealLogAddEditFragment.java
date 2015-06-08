package com.Cadient.QMobile.view.fragment.mealtrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.MealForLog;
import com.Cadient.QMobile.model.remote.FoodLogCreatedEntry;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.utils.GAUtils;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.Unit;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.Cadient.QMobile.view.fragment.IDataChangeListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Alexey Vereshchaga on 31.10.14.
 */
public class MealLogAddEditFragment extends AbstractFragment<MainActivity> {

    private static final String TAG = MealLogAddEditFragment.class.getSimpleName();

    private EditText etAmount;
    private TextView tvUnit;
    private Button btnLog;
    private LinearLayout llUnit;

    private RetrofitSpiceRequest request;
    private MealForLog mealForLog;
    private Long id;
    private ArrayList<Integer> compatibleUnits;
    private Integer compatibleUnitId;
    private Integer unitId;
    private IDataChangeListener dataChangeListener;
    private Float baseMultiplier;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_add_edit_meal_track;
    }

    @Override
    protected void assignViews(View view) {
        etAmount = (EditText) view.findViewById(R.id.et_amount);
        tvUnit = (TextView) view.findViewById(R.id.tv_unit);
        btnLog = (Button) view.findViewById(R.id.btn_food_track_log);
        llUnit = (LinearLayout) view.findViewById(R.id.ll_unit);
    }

    @Override
    protected void initView(View view) {
        if (getArguments() != null) {
            buildMealForLog();
            if (getArguments().getIntegerArrayList(HelpUtils.MEAL_LOG_COMPATIBLE_UNITS_BUNDLE_KEY) != null) {
                compatibleUnits = new ArrayList<Integer>();
                compatibleUnits.addAll(getArguments().getIntegerArrayList(HelpUtils.MEAL_LOG_COMPATIBLE_UNITS_BUNDLE_KEY));
            }
        }
        if (compatibleUnits == null || compatibleUnits.isEmpty() || compatibleUnits.size() == 1) {
            tvUnit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            llUnit.setOnClickListener(null);
        } else {
            tvUnit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right, 0);
            llUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList(HelpUtils.MEAL_LOG_COMPATIBLE_UNITS_BUNDLE_KEY, compatibleUnits);
                    bundle.putInt(HelpUtils.MEAL_LOG_COMPATIBLE_UNIT_ID_BUNDLE_KEY, compatibleUnitId);
                    MealLogSelectItemFragment fragment = new MealLogSelectItemFragment();
                    fragment.setSelectItemListener(new ISelectItemListener() {
                        @Override
                        public void checkUnit(Integer unitId) {
                            MealLogAddEditFragment.this.compatibleUnitId = unitId;
                        }
                    });
                    fragment.setArguments(bundle);
                    FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
                    t.replace(R.id.fragment_container, fragment);
                    t.addToBackStack("0");
                    t.commit();
//                    getTransitManager().switchFragment(MealLogSelectItemFragment.class, bundle);
                }
            });
        }
        unitId = getArguments().getInt(HelpUtils.MEAL_LOG_UNIT_ID_BUNDLE_KEY);
        baseMultiplier = getArguments().getFloat(HelpUtils.MEAL_LOG_SERVING_SIZE_AMOUNT_KEY, 1);
        if (compatibleUnitId == null) {
            compatibleUnitId = getArguments().getInt(HelpUtils.MEAL_LOG_COMPATIBLE_UNIT_ID_BUNDLE_KEY);
            Float multiplier = getArguments().getFloat(HelpUtils.MEAL_LOG_MULTIPLIER_BUNDLE_KEY, 1);
            etAmount.setText(compatibleUnitId != 0
                    ? HelpUtils.calculateAmountOfUnit(multiplier, compatibleUnitId, unitId, baseMultiplier).toString()
                    : "1");
            if (compatibleUnitId == 0) {
                compatibleUnitId = unitId;
            }
        }

        tvUnit.setText(Unit.getNameById(compatibleUnitId));
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = HelpUtils.validatePositiveDecimalNumber(etAmount.getText().toString());
                if (result == 0) {
                    Float multiplier = null;
                    try {
                        multiplier = Float.valueOf(etAmount.getText().toString())
                                * Unit.getCoefficientById(compatibleUnitId)
                                / (Unit.getCoefficientById(unitId) * baseMultiplier);
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "Error: " + e.getMessage(), e);
                    }
                    //meal for create
                    mealForLog.setMultiplier(multiplier);

                    // crutch for the server
                    if (!compatibleUnitId.equals(getArguments().getInt(HelpUtils.MEAL_LOG_UNIT_ID_BUNDLE_KEY))) {
                        mealForLog.setUnitId(new Long(compatibleUnitId));
                    }
                    if (request != null) {
                        getBaseActivity().getSpiceManager().cancel(request);
                    }
                    request = id == null ?
                            getBaseActivity().getDataManager().createFoodLogEntries(mealForLog,
                                    new BaseHandler<FoodLogCreatedEntry>(new CreateMealRemoteListener(getActivity()),
                                            FoodLogCreatedEntry.class)
                            ) :
                            getBaseActivity().getDataManager().updateFoodLogEntries(mealForLog,
                                    new BaseHandler<FoodLogEntries>(new EditMealRemoteListener(getActivity()),
                                            FoodLogEntries.class)
                            );
                    getBaseActivity().getTransitManager().back();
                } else {
                    Crouton.makeText(getActivity(), getResources().getString(result), Style.ALERT).show();
                }
            }
        });
    }

    private void buildMealForLog() {
        mealForLog = new MealForLog();
        mealForLog.setMealId(getArguments().getInt(HelpUtils.MEAL_LOG_MEAL_ID_BUNDLE_KEY));
        mealForLog.setSlotId((getArguments().getInt(HelpUtils.MEAL_LOG_SLOT_ID_BUNDLE_KEY)));
        mealForLog.setDate(Integer.valueOf(getArguments().getString(HelpUtils.MEAL_LOG_DATE_BUNDLE_KEY)));
        long id = getArguments().getLong(HelpUtils.MEAL_LOG_LOGGED_ID_BUNDLE_KEY, 0);
        this.id = id != 0 ? id : null;
        mealForLog.setId(this.id);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getHeaderController() != null && getArguments() != null) {
            getHeaderController().setTitle(getResources().getString(getArguments().getInt(HelpUtils.MEAL_LOG_ADD_EDIT_TITILE_BUNDLE_KEY, R.string.meal_track_add)));
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideDrawer();
                    getTransitManager().back();
                }
            }, true, R.drawable.arrow_back);
        }
        getBaseActivity().getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onPause() {
        super.onPause();
        getBaseActivity().getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void setDataChangeListener(IDataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    private class CreateMealRemoteListener extends BaseRemoteListener {
        protected CreateMealRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(Object result) {
            super.onSuccess(result);
            GAUtils.trackEvent(GAUtils.ADD_MEAL_ACTION);
            if (dataChangeListener != null) {
                dataChangeListener.onChanged(HelpUtils.stringToDate(getArguments().getString(HelpUtils.MEAL_LOG_DATE_BUNDLE_KEY)));
            }
        }
    }

    private class EditMealRemoteListener extends BaseRemoteListener {
        protected EditMealRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(Object result) {
            super.onSuccess(result);
            if (dataChangeListener != null) {
                dataChangeListener.onChanged(HelpUtils.stringToDate(getArguments().getString(HelpUtils.MEAL_LOG_DATE_BUNDLE_KEY)));
            }
        }
    }


}

