package com.Cadient.QMobile.view.fragment.mealtrack;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.Cadient.QMobile.model.remote.FoodLogCurrent;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.utils.ErrorCode;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.Ingestion;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.adapter.FoodLogEntriesAdapter;
import com.Cadient.QMobile.view.adapter.IOnViewClickListener;
import com.Cadient.QMobile.view.dialog.ConfirmDialog;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.Cadient.QMobile.view.fragment.IDataChangeListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ivan on 10/21/14.
 */
public class MealLogCategoryDetailFragment extends AbstractFragment<MainActivity> {

    public static final String BUNDLE_KEY_DETAIL = "meal_slot_id";

    private FoodLogEntries[] foodLogEntries;
    private FoodLogEntriesAdapter entriesAdapter;
    private List<RetrofitSpiceRequest> requestList = new ArrayList<RetrofitSpiceRequest>();
    private RetrofitSpiceRequest request;
    private Integer slotId;
    private IDataChangeListener dataChangeListener;

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String profileJSON = getArguments().getString(BUNDLE_KEY_DETAIL);
        if (profileJSON != null) {
            foodLogEntries = HelpUtils.getModel(profileJSON, FoodLogEntries[].class);
        }
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_category_detail;
    }

    @Override
    protected void assignViews(View view) {
        listView = (ListView) view.findViewById(R.id.meals_list);
    }

    @Override
    protected void initView(View view) {
        entriesAdapter = new FoodLogEntriesAdapter(getActivity(), R.layout.meal_categoty_detail_item);
        entriesAdapter.addAll(foodLogEntries);
        listView.setAdapter(entriesAdapter);

        entriesAdapter.setOnViewClickListener(new IOnViewClickListener() {

            @Override
            public void onFavoriteCheckedChange(FoodLogEntries entries, View v, boolean state) {
                changeFavoriteState(entries, v, state);
            }

            @Override
            public void onEditFoodLogEntries(FoodLogEntriesAdapter adapter, FoodLogEntries entries) {
                MealLogAddEditFragment fragment = new MealLogAddEditFragment();
                fragment.setArguments(constructBundle(entries));
                fragment.setDataChangeListener(new IDataChangeListener() {
                    @Override
                    public void onChanged(Date date) {
                        sendFoodLogRequest(date);
                        if (dataChangeListener != null) {
                            dataChangeListener.onChanged(HelpUtils.stringToDate(foodLogEntries[0].getDate().toString()));
                        }
                    }
                });
                FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
                t.replace(R.id.fragment_container, fragment);
                t.addToBackStack("0");
                t.commit();
//                getTransitManager().switchFragment(MealLogAddEditFragment.class, constructBundle(entries));
            }

            @Override
            public void onDeleteFoodLogEntries(final FoodLogEntriesAdapter adapter, final FoodLogEntries entries, View view) {
                ConfirmDialog.showDlg(getActivity(), getActivity().getString(R.string.confirm_dialog_meal_txt)).setOnDialogCloseListener(new ConfirmDialog.OnDialogCloseListener() {
                    @Override
                    public void OnDialogClose(ConfirmDialog.ConfirmDialogResult result) {
                        if (result == ConfirmDialog.ConfirmDialogResult.DELETE) {
                            if (requestList != null && !requestList.isEmpty()) {
                                for (RetrofitSpiceRequest request : requestList) {
                                    getBaseActivity().getSpiceManager().cancel(request);
                                }
                                requestList.clear();
                            }
                            requestList.add(getBaseActivity().getDataManager().deleteFoodLogEntries(entries,
                                    new BaseHandler<FoodLogEntries>(new BaseRemoteListener(getActivity()) {
                                    }, FoodLogEntries.class)
                            ));
                            if (dataChangeListener != null) {
                                dataChangeListener.onChanged(HelpUtils.stringToDate(foodLogEntries[0].getDate().toString()));
                            }
                            adapter.remove(entries);
                        }
                    }
                });
            }
        });
    }

    private Bundle constructBundle(FoodLogEntries foodLogEntry) {
        Bundle bundle = new Bundle();
        Float servingSizeAmount = foodLogEntry.getMeal().getServingSizeAmount();
        bundle.putInt(HelpUtils.MEAL_LOG_ADD_EDIT_TITILE_BUNDLE_KEY, R.string.meal_track_edit);
        bundle.putInt(HelpUtils.MEAL_LOG_UNIT_ID_BUNDLE_KEY, foodLogEntry.getMeal().getUnitId());
        bundle.putInt(HelpUtils.MEAL_LOG_COMPATIBLE_UNIT_ID_BUNDLE_KEY, foodLogEntry.getUnitId());
        bundle.putInt(HelpUtils.MEAL_LOG_SLOT_ID_BUNDLE_KEY, foodLogEntry.getSlotId());
        bundle.putString(HelpUtils.MEAL_LOG_DATE_BUNDLE_KEY, Integer.toString(foodLogEntry.getDate()));
        bundle.putInt(HelpUtils.MEAL_LOG_MEAL_ID_BUNDLE_KEY, foodLogEntry.getMealId());
        bundle.putLong(HelpUtils.MEAL_LOG_LOGGED_ID_BUNDLE_KEY, foodLogEntry.getId());
        bundle.putFloat(HelpUtils.MEAL_LOG_MULTIPLIER_BUNDLE_KEY, foodLogEntry.getMultiplier());
        bundle.putIntegerArrayList(HelpUtils.MEAL_LOG_COMPATIBLE_UNITS_BUNDLE_KEY, foodLogEntry.getMeal().getCompatibleUnits());
        bundle.putFloat(HelpUtils.MEAL_LOG_SERVING_SIZE_AMOUNT_KEY, servingSizeAmount != null ? servingSizeAmount : 1);
        return bundle;
    }

    private void changeFavoriteState(FoodLogEntries entries, View v, boolean state) {
        if (requestList != null && !requestList.isEmpty()) {
            for (RetrofitSpiceRequest request : requestList) {
                getBaseActivity().getSpiceManager().cancel(request);
            }
            requestList.clear();
        }
        if (state) {
            FavoriteUserMeal favoriteUserMeal = new FavoriteUserMeal();
            favoriteUserMeal.setMealId(entries.getMeal().getId());
            requestList.add(getBaseActivity().getDataManager().createFavoriteUserMeal(favoriteUserMeal,
                    new BaseHandler<FavoriteUserMeal>(new CreateDeleteFavoriteUserMeal(v, getActivity()), FavoriteUserMeal.class)));
        } else {
            FavoriteUserMeal favoriteUserMeal = new FavoriteUserMeal();
            favoriteUserMeal.setMealId(entries.getMeal().getId());

            requestList.add(getBaseActivity().getDataManager().readFavoriteUserMeal(favoriteUserMeal,
                    new BaseHandler<FavoriteUserMeal>(new GetFavoriteUserMeal(v, getActivity()), FavoriteUserMeal.class)));
        }
        for (FoodLogEntries foodLogEntry : foodLogEntries) {
            if (foodLogEntry.getMeal().getId().equals(entries.getMeal().getId())) {
                foodLogEntry.getMeal().getFavoriteUserMeal().setId(state ? 1l : null);
            }
        }
        entriesAdapter.clear();
        entriesAdapter.addAll(foodLogEntries);
    }

    private void sendFoodLogRequest(Date date) {
        if (request != null) {
            getBaseActivity().getSpiceManager().cancel(request);
        }
        request = getBaseActivity().getDataManager().obtainTodayFoodLog(date,
                new BaseHandler<FoodLogCurrent>(new TodayFoodLogListener(getActivity()), FoodLogCurrent.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getHeaderController() != null) {
            getHeaderController().setTitle(getCategoryMeal());
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

    private String getCategoryMeal() {
        slotId = foodLogEntries[0].getSlotId();
        if (foodLogEntries[0].getSlotId().equals(Ingestion.BREAKFAST.getSlot())) {
            return "Breakfast";
        } else if (foodLogEntries[0].getSlotId().equals(Ingestion.LUNCH.getSlot())) {
            return "Lunch";
        } else if (foodLogEntries[0].getSlotId().equals(Ingestion.DINNER.getSlot())) {
            return "Dinner";
        } else {
            return "Snacks";
        }
    }

    public void setDataChangeListener(IDataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    private class TodayFoodLogListener extends BaseRemoteListener<FoodLogCurrent> {
        protected TodayFoodLogListener(android.support.v4.app.FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(FoodLogCurrent result) {
            //todo need refactoring
            List<FoodLogEntries> entriesList = new ArrayList<FoodLogEntries>();
            if (result != null && result.getLogEntryList() != null) {
                if (slotId.equals(Ingestion.BREAKFAST.getSlot())
                        || slotId.equals(Ingestion.LUNCH.getSlot())
                        || slotId.equals(Ingestion.DINNER.getSlot())) {
                    for (FoodLogEntries entry : result.getLogEntryList()) {
                        if (entry.getSlotId().equals(slotId)) {
                            entriesList.add(entry);
                        }
                    }
                } else {
                    for (FoodLogEntries entry : result.getLogEntryList()) {
                        if (!entry.getSlotId().equals(Ingestion.BREAKFAST.getSlot())
                                && !entry.getSlotId().equals(Ingestion.LUNCH.getSlot())
                                && !entry.getSlotId().equals(Ingestion.DINNER.getSlot())) {
                            entriesList.add(entry);
                        }
                    }
                }
            }
            foodLogEntries = entriesList.toArray(new FoodLogEntries[entriesList.size()]);

            entriesAdapter.clear();
            entriesAdapter.addAll(foodLogEntries);
        }
    }

    private class CreateDeleteFavoriteUserMeal extends BaseRemoteListener<FavoriteUserMeal> {
        private CheckBox box;

        private CreateDeleteFavoriteUserMeal(View view, android.support.v4.app.FragmentActivity activity) {
            super(activity);
            this.box = (CheckBox) view;
        }

        @Override
        public void onFailure(Integer failureCode) {
            super.onFailure(failureCode);
            box.setChecked(!box.isChecked());
        }

        @Override
        public void onFinishTask() {
            super.onFinishTask();
            box.setBackgroundResource(R.drawable.meal_track_category_detail_btn_favorite_selector);
        }
    }

    private class GetFavoriteUserMeal extends BaseRemoteListener<FavoriteUserMeal> {

        private CheckBox box;
        private android.support.v4.app.FragmentActivity activity;

        private GetFavoriteUserMeal(View view, android.support.v4.app.FragmentActivity activity) {
            super(activity);
            this.activity = activity;
            this.box = (CheckBox) view;
        }

        @Override
        public void onSuccess(FavoriteUserMeal result) {
            getBaseActivity().getDataManager().deleteFavoriteUserMeal(result,
                    new BaseHandler<FavoriteUserMeal>(new CreateDeleteFavoriteUserMeal(box, activity), FavoriteUserMeal.class));
        }

        @Override
        public void onFailure(Integer failureCode) {
            super.onFailure(failureCode);
            if (!failureCode.equals(ErrorCode.CODE_200.getCode())) {
                box.setChecked(!box.isChecked());
            }
        }

        @Override
        public void onFinishTask() {
            super.onFinishTask();
            box.setBackgroundResource(R.drawable.meal_track_category_detail_btn_favorite_selector);
        }
    }
}
