package com.Cadient.QMobile.view.fragment.mealtrack;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.DailyCalorie;
import com.Cadient.QMobile.model.Meal;
import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.model.remote.SuggestedMeal;
import com.Cadient.QMobile.utils.CustomTextWatcher;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.adapter.MealAdapter;
import com.Cadient.QMobile.view.fragment.AbstractFragment;
import com.Cadient.QMobile.view.fragment.CalendarFragment;
import com.Cadient.QMobile.view.fragment.IDataChangeListener;
import com.Cadient.QMobile.view.fragment.listener.NotifyCalendarListener;
import com.Cadient.QMobile.view.fragment.listener.RightDrawableOnTouchListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.Cadient.QMobile.utils.Ingestion.BREAKFAST;
import static com.Cadient.QMobile.utils.Ingestion.DINNER;
import static com.Cadient.QMobile.utils.Ingestion.LUNCH;
import static com.Cadient.QMobile.utils.Ingestion.SNACK1;

/**
 * Created by ivan on 10/7/14.
 */
public class MealTrackingFragment extends AbstractFragment<MainActivity> {

    private static final String TAG = MealTrackingFragment.class.getSimpleName();
    public static final int ITEMS_ON_PAGE = 20;

    private CalendarFragment calendarFragment;
    private String firstCharsOfMeal;
    private List<Meal> mealList = new ArrayList<Meal>();
    private Calendar calendarLocal;
    private DailyCalorie dailyCalorieCurrent;
    private MealAdapter mealAdapter;
    private RetrofitSpiceRequest requestDailyCalorie;
    private List<RetrofitSpiceRequest> predictiveRequestList = new ArrayList<RetrofitSpiceRequest>();
    private int pageNumber;
    private Boolean returnFlag = false;
    private BigInteger targetCalories = BigInteger.ZERO;

    private EditText search;
    private ImageButton cleanSearch;
    private Button viewMealLogButton;
    private ListView list;
    private View dashboardTable;
    private View mealTrackTopTable;
    private TextView targetCaloriesTextView;
    private TextView consumedCaloriesTextView;
    private TextView burnedCaloriesTextView;
    private TextView underCaloriesTextView;
    private TextView headerUnderCaloriesTextView;
    private RadioGroup radioGroup;
    private RadioGroup rgIngestion;
    private RadioButton rbRecent;
    private RadioButton rbFrequent;
    private RadioButton rbFavorites;
    private RadioButton rbAll;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_meal_track;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendDailyCalorieRequest();
    }

    private void sendDailyCalorieRequest() {
        if (requestDailyCalorie != null) {
            getBaseActivity().getSpiceManager().cancel(requestDailyCalorie);
        }
        Date setDate = getBaseActivity().getCalendar().getTime();
        requestDailyCalorie = getBaseActivity().getDataManager().retrieveDailyCalorieBank(setDate, setDate,
                new BaseHandler<DailyCalorieRemote>(new DailyCalorieBankRemoteListener(getActivity()), DailyCalorieRemote.class));
    }

    @Override
    protected void assignViews(View view) {
        search = (EditText) view.findViewById(R.id.et_search);
        cleanSearch = (ImageButton) view.findViewById(R.id.btn_clean_search);
        viewMealLogButton = (Button) view.findViewById(R.id.btn_view_meal_log);
        list = (ListView) view.findViewById(R.id.list);
        dashboardTable = view.findViewById(R.id.dashboard_table2);
        mealTrackTopTable = view.findViewById(R.id.meal_track_top_table);
        targetCaloriesTextView = (TextView) view.findViewById(R.id.target_calories);
        consumedCaloriesTextView = (TextView) view.findViewById(R.id.consumed_calories);
        burnedCaloriesTextView = (TextView) view.findViewById(R.id.burned_calories);
        underCaloriesTextView = (TextView) view.findViewById(R.id.under_calories);
        headerUnderCaloriesTextView = (TextView) view.findViewById(R.id.header_of_under_calories);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        rgIngestion = (RadioGroup) view.findViewById(R.id.ingestion);
        rbRecent = (RadioButton) view.findViewById(R.id.recent);
        rbFrequent = (RadioButton) view.findViewById(R.id.frequent);
        rbFavorites = (RadioButton) view.findViewById(R.id.favorites);
        rbAll = (RadioButton) view.findViewById(R.id.all);
    }

    @Override
    protected void initView(View view) {
        if (calendarLocal != null) {
            getBaseActivity().setCalendar(calendarLocal);
        }
        if (dailyCalorieCurrent == null) {
            sendDailyCalorieRequest();
        } else {
            setCalorieTableValues(dailyCalorieCurrent);
        }
        if (mealAdapter == null) {
            mealAdapter = new MealAdapter(getActivity(), R.layout.universal_list_item);
        }
        mealAdapter.notifyDataSetChanged();
        cleanSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo change to only needed requests
                getBaseActivity().getSpiceManager().cancelAllRequests();
                mealAdapter.clear();
                InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
            }
        });

        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        list.setOnScrollListener(new SearchScrollListListener());
        list.setOnItemClickListener(new ItemClickListener());

        mealAdapter.registerCallback(new MealAdapter.Callback() {
            @Override
            public void notifyDataSetChanged() {
                setVisibilityOfViews(mealAdapter.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                HelpUtils.cancelAllRequests(getBaseActivity(), predictiveRequestList);
//                search.getText().clear();
//                pageNumber = 1;
//            }
//        });

        search.addTextChangedListener(new SearchTextWatcher());
        search.setOnTouchListener(new RightDrawableOnTouchListener(search) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                search.getText().clear();
                event.setAction(MotionEvent.ACTION_CANCEL);
                return false;
            }
        });

        list.setAdapter(mealAdapter);
        View.OnClickListener radioButtonListener = new RadioButtonClickListener();
        rbRecent.setOnClickListener(radioButtonListener);
        rbFrequent.setOnClickListener(radioButtonListener);
        rbFavorites.setOnClickListener(radioButtonListener);
        rbAll.setOnClickListener(radioButtonListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        initHeader();
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        calendarLocal = getBaseActivity().getCalendar();
        returnFlag = true;
    }

    private void initHeader() {
        if (getHeaderController() != null) {
            getHeaderController().setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            }, true, R.drawable.together);

            getHeaderController().setRightButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initCalendar();
                }
            }, true, R.drawable.exercise_calendar);
            getHeaderController().setArrowLeft(new ArrowListener(getBaseActivity()), true);
            getHeaderController().setArrowRight(new ArrowListener(getBaseActivity()), true);
        }
    }

    private class ArrowListener extends NotifyCalendarListener {

        protected ArrowListener(BaseActivity activity) {
            super(activity);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            //block button until successful request with not empty array
            viewMealLogButton.setOnClickListener(null);
            viewMealLogButton.setClickable(false);
            viewMealLogButton.setBackgroundResource(R.drawable.meal_track_btn_unclicable_bg);
            setDefaultDesignOfCalorieTable();
            sendDailyCalorieRequest();
        }
    }

    private void initCalendar() {
        calendarFragment = HelpUtils.constructCalendarFragment(getBaseActivity().getCalendar(), null, "1");
        CalendarCaldroidListener calendarCaldroidListener = new CalendarCaldroidListener(getBaseActivity());
        calendarCaldroidListener.setCalendarFragment(calendarFragment);
        calendarFragment.setCaldroidListener(calendarCaldroidListener);
        FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container, calendarFragment);
        t.addToBackStack("0");
        t.commit();
    }

    private SuggestedMeal createSuggestedMeal() {
        SuggestedMeal suggestedMeal = new SuggestedMeal();
        suggestedMeal.setPageNumber(pageNumber);
        suggestedMeal.setPageSize(ITEMS_ON_PAGE);
        suggestedMeal.setTerm(firstCharsOfMeal);
        return suggestedMeal;
    }

    private void setVisibilityOfViews(Integer visibility) {
        if (getBaseActivity().getTransitManager().getCurrentFragment() == this) {
            if (getHeaderController() != null) {
                getHeaderController().getGeneralHeaderView().setVisibility(visibility);
            }
            cleanSearch.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
            viewMealLogButton.setVisibility(visibility);
            dashboardTable.setVisibility(visibility);
            mealTrackTopTable.setVisibility(visibility);
        }
    }

    private List<Meal> filterMealsByName(String s) {
        List<Meal> meals = new ArrayList<Meal>();
        for (Meal meal : mealList) {
            String mealName = meal.getName().trim();
            //check start of the words from name of meal
            Pattern pattern = Pattern.compile("(^(" + s + ").*)" + "|(.*(\\s+" + s + ").*)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(mealName);
            if (matcher.matches()) {
                meals.add(meal);
            }
        }
        return meals;
    }

    private void startSelectCategoryFragment(Long calendarDateInMillis) {
        Bundle bundle = new Bundle();
        bundle.putLong(HelpUtils.MEAL_LOG_CURRENT_DATE_BUNDLE_KEY, calendarDateInMillis); //MEAL_LOG_CURRENT_DATE_BUNDLE_KEY
        MealLogSelectCategoryFragment fragment = new MealLogSelectCategoryFragment();
        fragment.setDataChangeListener(new IDataChangeListener() {
            @Override
            public void onChanged(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendarLocal = calendar;
                dailyCalorieCurrent = null;
            }
        });
        fragment.setArguments(bundle);
        FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fragment_container, fragment);
        t.addToBackStack("0");
        t.commit();
    }

    //todo make external classes

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Meal meal = null;
            try {
                meal = (Meal) list.getItemAtPosition(position);
            } catch (Exception e) {
                Log.e(TAG, "Error: " + e.getMessage(), e);
            }
            MealLogAddEditFragment fragment = new MealLogAddEditFragment();
            fragment.setDataChangeListener(new IDataChangeListener() {
                @Override
                public void onChanged(Date date) {
                    sendDailyCalorieRequest();
                }
            });
            fragment.setArguments(constructBundleForAdd(meal));
            FragmentTransaction t = getBaseActivity().getSupportFragmentManager().beginTransaction();
            t.replace(R.id.fragment_container, fragment);
            t.addToBackStack("0");
            t.commit();
            mealAdapter.clear();
            search.getText().clear();
//            getBaseActivity().getTransitManager().switchFragment(MealLogAddEditFragment.class, constructBundleForAdd(meal));
        }

        private Bundle constructBundleForAdd(Meal meal) {
            Bundle bundle = null;
            if (meal != null) {
                int slot;
                bundle = new Bundle();
                bundle.putInt(HelpUtils.MEAL_LOG_ADD_EDIT_TITILE_BUNDLE_KEY, R.string.meal_track_add);
                bundle.putIntegerArrayList(HelpUtils.MEAL_LOG_COMPATIBLE_UNITS_BUNDLE_KEY, meal.getCompatibleUnits());
                bundle.putInt(HelpUtils.MEAL_LOG_MEAL_ID_BUNDLE_KEY, meal.getId());
                bundle.putString(HelpUtils.MEAL_LOG_DATE_BUNDLE_KEY, HelpUtils.parseDateToString(getBaseActivity().getCalendar().getTime(), HelpUtils.DATE_FORMAT));
                Integer unitId = meal.getUnit() != null ? meal.getUnit().getId() : meal.getUnitId();
                bundle.putInt(HelpUtils.MEAL_LOG_UNIT_ID_BUNDLE_KEY, unitId);
                bundle.putFloat(HelpUtils.MEAL_LOG_SERVING_SIZE_AMOUNT_KEY, meal.getServingSizeAmount() != null ? meal.getServingSizeAmount() : 1);
                switch (rgIngestion.getCheckedRadioButtonId()) {
                    case R.id.breakfast:
                        slot = BREAKFAST.getSlot();
                        break;
                    case R.id.lunch:
                        slot = LUNCH.getSlot();
                        break;
                    case R.id.dinner:
                        slot = DINNER.getSlot();
                        break;
                    case R.id.snacks:
                        slot = SNACK1.getSlot();
                        break;
                    default:
                        slot = SNACK1.getSlot();
                        break;
                }
                bundle.putInt(HelpUtils.MEAL_LOG_SLOT_ID_BUNDLE_KEY, slot);
            }
            return bundle;
        }
    }

    private class CalendarCaldroidListener extends MealCalendarCaldroidListener {
        public CalendarCaldroidListener(BaseActivity baseActivity) {
            super(baseActivity);
        }

        @Override
        public void onSelectDate(Date date, View view) {
            getTransitManager().back();
            startSelectCategoryFragment(date.getTime());
        }
    }

    private class SearchScrollListListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (radioGroup.getCheckedRadioButtonId() == R.id.all
                    && totalItemCount != 0
                    && firstVisibleItem + visibleItemCount == totalItemCount
                    && mealAdapter.getCount() == ITEMS_ON_PAGE * pageNumber) {
                pageNumber++;
                getBaseActivity().getDataManager().suggestMealName(
                        createSuggestedMeal(), new BaseHandler<SuggestedMeal>(new BaseRemoteListener<SuggestedMeal>(getActivity()) {
                            @Override
                            public void onSuccess(SuggestedMeal result) {
                                mealAdapter.addAll(result.getMeals());
                            }
                        }, SuggestedMeal.class)
                );
            }
        }
    }

    private class DailyCalorieBankRemoteListener extends BaseRemoteListener<DailyCalorieRemote> {
        protected DailyCalorieBankRemoteListener(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public void onSuccess(DailyCalorieRemote result) {
            if (result.getDailyCalorieList() != null && !result.getDailyCalorieList().isEmpty()) {
                dailyCalorieCurrent = result.getDailyCalorieList().get(0);
                setCalorieTableValues(dailyCalorieCurrent);
            } else {
                targetCaloriesTextView.setText(targetCalories.toString());
                headerUnderCaloriesTextView.setBackgroundResource(R.drawable.border_of_table_field_blue);
                headerUnderCaloriesTextView.setText(getBaseActivity().getResources().getString(R.string.table_under));
                underCaloriesTextView.setText(targetCalories.abs().toString());
            }
        }
    }

    private void setCalorieTableValues(DailyCalorie dailyCalorie) {
        targetCalories = HelpUtils.roundToBigInteger(dailyCalorie.getTargetCalories());
        BigInteger consumed = HelpUtils.roundToBigInteger(dailyCalorie.getCaloriesConsumed());
        BigInteger burned = HelpUtils.roundToBigInteger(dailyCalorie.getCaloriesBurned());
        BigInteger overOrUnder = targetCalories.subtract(consumed).add(burned);

        targetCaloriesTextView.setText(targetCalories.toString());
        consumedCaloriesTextView.setText(consumed.toString());
        burnedCaloriesTextView.setText(burned.toString());
        headerUnderCaloriesTextView.setBackgroundResource(overOrUnder.compareTo(BigInteger.ZERO) >= 0 ?
                R.drawable.border_of_table_field_blue : R.drawable.border_of_table_field_red);
        headerUnderCaloriesTextView.setText(overOrUnder.compareTo(BigInteger.ZERO) >= 0
                ? getBaseActivity().getResources().getString(R.string.table_under) : getBaseActivity().getResources().getString(R.string.table_over));
        underCaloriesTextView.setText(overOrUnder.abs().toString());

        if (dailyCalorie.getCaloriesConsumed() != null) {
            viewMealLogButton.setClickable(true);
            viewMealLogButton.setBackgroundResource(R.drawable.meal_track_btn_bg_selector);
            viewMealLogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startSelectCategoryFragment(getBaseActivity().getCalendar().getTimeInMillis());
                }
            });
        }
    }

    private void setDefaultDesignOfCalorieTable() {
        headerUnderCaloriesTextView.setBackgroundResource(R.drawable.border_of_table_field_blue);
        headerUnderCaloriesTextView.setText(getBaseActivity().getResources().getString(R.string.table_under));
        targetCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
        consumedCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
        burnedCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
        underCaloriesTextView.setText(getResources().getString(R.string.table_init_calories));
    }

    private class SearchTextWatcher extends CustomTextWatcher {

        @Override
        public void afterTextChanged(final Editable s) {
            if (s.toString().isEmpty()) {
                search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.exercise_search, 0, 0, 0);
            } else {
                search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.exercise_search, 0, R.drawable.close_small, 0);
            }
            if (!returnFlag) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.all:
                        if (!s.toString().isEmpty()) {
                            firstCharsOfMeal = s.toString();
                            pageNumber = 1;
                            HelpUtils.cancelAllRequests(getBaseActivity(), predictiveRequestList);
                            RetrofitSpiceRequest request = getBaseActivity().getDataManager().suggestMealName(
                                    createSuggestedMeal(), new BaseHandler<SuggestedMeal>(new BaseRemoteListener<SuggestedMeal>(getActivity()) {
                                        @Override
                                        public void onSuccess(SuggestedMeal result) {
                                            mealAdapter.clear();
                                            mealAdapter.addAll(result.getMeals());
                                        }
                                    }, SuggestedMeal.class)
                            );
                            predictiveRequestList.add(request);
                        }
                        break;

                    //filtering of frequent, recent, favorite by name
                    case R.id.frequent:
                        if (mealList == null || mealList.isEmpty()) {
                            getBaseActivity().getDataManager().obtainFrequentMeals(
                                    new BaseHandler<SuggestedMeal>(new BaseRemoteListener<SuggestedMeal>(getActivity()) {
                                        @Override
                                        public void onSuccess(SuggestedMeal result) {
                                            fillAdapter(result.getMeals(), s.toString());
                                        }
                                    }, SuggestedMeal.class)
                            );
                        } else {
                            mealAdapter.clear();
                            mealAdapter.addAll(filterMealsByName(s.toString()));
                        }
                        break;
                    case R.id.recent:
                        if (mealList == null || mealList.isEmpty()) {
                            getBaseActivity().getDataManager().obtainRecentMeals(
                                    new BaseHandler<SuggestedMeal>(new BaseRemoteListener<SuggestedMeal>(getActivity()) {
                                        @Override
                                        public void onSuccess(SuggestedMeal result) {
                                            fillAdapter(result.getMeals(), s.toString());
                                        }
                                    }, SuggestedMeal.class)
                            );
                        } else {
                            mealAdapter.clear();
                            mealAdapter.addAll(filterMealsByName(s.toString()));
                        }
                        break;
                    case R.id.favorites:
                        if (mealList == null || mealList.isEmpty()) {
                            getBaseActivity().getDataManager().obtainFavoriteMeals(
                                    new BaseHandler<RecipeRemote>(new BaseRemoteListener<RecipeRemote>(getActivity()) {
                                        @Override
                                        public void onSuccess(RecipeRemote result) {
                                            fillAdapter(result.getMeals(), s.toString());
                                        }
                                    }, RecipeRemote.class)
                            );
                        } else {
                            mealAdapter.clear();
                            mealAdapter.addAll(filterMealsByName(s.toString()));
                        }
                        break;
                }
            }
            returnFlag = false;
        }

        private void fillAdapter(List<Meal> mealList, String s) {
            if (mealList != null && !mealList.isEmpty()) {
                MealTrackingFragment.this.mealList = mealList;
                mealAdapter.clear();
                mealAdapter.addAll(filterMealsByName(s));
            }
        }
    }

    private class RadioButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            HelpUtils.cancelAllRequests(getBaseActivity(), predictiveRequestList);
            mealList.clear();
            mealAdapter.clear();
            search.getText().clear();
            pageNumber = 1;
            RetrofitSpiceRequest request = null;
            switch (v.getId()) {
                case R.id.frequent:
                    request = getBaseActivity().getDataManager().obtainFrequentMeals(
                            new BaseHandler<SuggestedMeal>(new BaseRemoteListener<SuggestedMeal>(getActivity()) {
                                @Override
                                public void onSuccess(SuggestedMeal result) {
                                    addMeals(result.getMeals());
                                }
                            }, SuggestedMeal.class)
                    );
                    break;
                case R.id.recent:
                    request = getBaseActivity().getDataManager().obtainRecentMeals(
                            new BaseHandler<SuggestedMeal>(new BaseRemoteListener<SuggestedMeal>(getActivity()) {
                                @Override
                                public void onSuccess(SuggestedMeal result) {
                                    addMeals(result.getMeals());
                                }
                            }, SuggestedMeal.class)
                    );
                    break;
                case R.id.favorites:
                    request = getBaseActivity().getDataManager().obtainFavoriteMeals(
                            new BaseHandler<RecipeRemote>(new BaseRemoteListener<RecipeRemote>(getActivity()) {
                                @Override
                                public void onSuccess(RecipeRemote result) {
                                    addMeals(result.getMeals());
                                }
                            }, RecipeRemote.class)
                    );
                    break;
            }
            if (request != null) {
                predictiveRequestList.add(request);
            }
        }

        private void addMeals(List<Meal> meals) {
            if (meals != null && !meals.isEmpty()) {
                mealList.clear();
                mealAdapter.clear();
                mealList.addAll(meals);
                if (!search.getText().toString().isEmpty()) {
                    meals = filterMealsByName(search.getText().toString());
                }
                mealAdapter.addAll(meals);
            }
        }
    }
}
