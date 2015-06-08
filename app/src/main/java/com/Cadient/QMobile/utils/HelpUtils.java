package com.Cadient.QMobile.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.model.remote.WeightTrackerEntry;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.fragment.CalendarFragment;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.roomorama.caldroid.WeekdayArrayAdapter;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Alexey Vereshchaga on 08.09.14.
 */
public class HelpUtils {

    public static final String REGISTRATION_KEY = "URL";

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String CALDROID_DATE_FORMAT = "yyyy-MM-dd";
    public static final String REGISTRATION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String AUTHORISATION_FAIL_KEY = "AUTHORISATION_FAIL";
    public static final String DATE_FORMAT_HEADER = "EEE, MMM d, yyyy";
    public static final String EXERCISE_DATE_BUNDLE_KEY = "date";


    //Food tracker
    public static final String MEAL_LOG_ADD_EDIT_TITILE_BUNDLE_KEY = "add_edit_key";
    public static final String MEAL_LOG_LOGGED_ID_BUNDLE_KEY = "id";
    public static final String MEAL_LOG_MEAL_ID_BUNDLE_KEY = "meal_id";
    public static final String MEAL_LOG_DATE_BUNDLE_KEY = "date";
    public static final String MEAL_LOG_MULTIPLIER_BUNDLE_KEY = "multiplier";
    public static final String MEAL_LOG_SLOT_ID_BUNDLE_KEY = "slot_id";
    public static final String MEAL_LOG_UNIT_ID_BUNDLE_KEY = "unit_id";
    public static final String MEAL_LOG_COMPATIBLE_UNITS_BUNDLE_KEY = "compatible_units";
    public static final String MEAL_LOG_COMPATIBLE_UNIT_ID_BUNDLE_KEY = "compatible_unit";
    public static final String MEAL_BUNDLE_KEY = "meal";
    public static final String MEAL_LOG_CURRENT_DATE_BUNDLE_KEY = "current_date";
    public static final String REGISTRATION_HEADER_VALUE = "registration_header_value";
    public static final String MEAL_LOG_SERVING_SIZE_AMOUNT_KEY = "serving_size_amount";
    public static ObjectMapper objectMapper;

    public static final int ANIMATION_DURATION = 2500;
    public static final int IMAGES[] = {R.drawable.login_v2, R.drawable.login_v3, R.drawable.login_v4, R.drawable.login_v5, R.drawable.login_v6};
    public static final String FRAGMENT_NAME_KEY = "com.Cadient.QMobile.fragment_name";

    /**
     * Login validation method.
     * Username -  (this will be the users email address)
     *
     * @param value
     * @return
     */
    public static int validateLogin(String value) {
        if (TextUtils.isEmpty(value)) {
            return R.string.login_empty;
        } else if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return 0;
        } else {
            return R.string.login_invalid;
        }
    }

    public static BigDecimal round(BigDecimal d, int decimalPlace) {
        return d.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Password validation method.
     * Password credentials should be between 5 and 20 alphanumeric characters.
     *
     * @param value
     * @return
     */
    public static int validatePass(String value) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{5,20}");
        return pattern.matcher(value).matches() ? 0 : R.string.password_invalid;
    }

    public static int validatePositiveNumber(String value) {
        Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
        if (TextUtils.isEmpty(value)) {
            return R.string.exercise_track_error_duration_message;
        } else if (pattern.matcher(value).matches()) {
            return 0;
        } else {
            return R.string.exercise_track_error_duration_message;
        }
    }

    public static int validatePositiveDecimalNumber(String value) {
//        Pattern pattern = Pattern.compile("((\\d+(\\.*\\d*))|(\\d*\\.+\\d+))");
        BigDecimal decimal = null;
        try {
            decimal = new BigDecimal(value);
        } catch (Error e) {
            e.printStackTrace();
        }
        if (decimal == null || decimal.equals(BigDecimal.ZERO)) {
            return R.string.meal_amount_error_message;
        } else {
            return 0;
        }
    }

    public static boolean compareDateByDay(long current, long comparable) {
        boolean result = false;
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTimeInMillis(current);
        Calendar calendarComparable = Calendar.getInstance();
        calendarComparable.setTimeInMillis(comparable);
        if (calendarCurrent.get(Calendar.YEAR) == calendarComparable.get(Calendar.YEAR) &&
                calendarCurrent.get(Calendar.MONTH) == calendarComparable.get(Calendar.MONTH) &&
                calendarCurrent.get(Calendar.DAY_OF_MONTH) == calendarComparable.get(Calendar.DAY_OF_MONTH)) {
            result = true;
        }
        return result;
    }

    public static boolean findLater(long current, long comparable) {
        boolean result = false;
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTimeInMillis(current);
        Calendar calendarComparable = Calendar.getInstance();
        calendarComparable.setTimeInMillis(comparable);
        if (calendarCurrent.getTime().after(calendarComparable.getTime())) {
            result = true;
        }
        return result;
    }

    public static Date parseStringToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        Date result = null;
        try {
            result = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseRegistrStringToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(REGISTRATION_DATE_FORMAT);
        Date result = null;
        try {
            result = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String parseDateToString(Date date, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static void hideKeyBoard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            Object inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                ((InputMethodManager) inputMethodManager).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void constructCalendarWeekdayHeader(Context context, CalendarFragment calendarFragment) {
        ArrayAdapter<CharSequence> adapter = WeekdayArrayAdapter.createFromResource(context, R.array.calendar_days_array, R.layout.calendar_day_of_week);
        Button leftButton = calendarFragment.getLeftArrowButton();
        Button rightButton = calendarFragment.getRightArrowButton();
        TextView title = calendarFragment.getMonthTitleTextView();
        title.setTextColor(context.getResources().getColor(android.R.color.black));
        RelativeLayout view = (RelativeLayout) rightButton.getParent();
        view.setBackgroundColor(context.getResources().getColor(R.color.calendar_header_background));

        leftButton.setBackgroundResource(R.drawable.calendar_title_arrow_left);
        rightButton.setBackgroundResource(R.drawable.calendar_title_arrow_right);

        RelativeLayout.LayoutParams paramsLeft = (RelativeLayout.LayoutParams) leftButton.getLayoutParams();
        paramsLeft.setMargins(Math.round(context.getResources().getDimension(R.dimen.calendar_header_arrow_margin)), 0, 0, 0);

        RelativeLayout.LayoutParams paramsRight = (RelativeLayout.LayoutParams) rightButton.getLayoutParams();
        paramsRight.setMargins(0, 0, Math.round(context.getResources().getDimension(R.dimen.calendar_header_arrow_margin)), 0);

        calendarFragment.getWeekdayGridView().setAdapter(adapter);
    }

    public static CalendarFragment constructCalendarFragment(final Calendar calendar, CaldroidListener caldroidListener) {
        return constructCalendarFragment(calendar, caldroidListener, null);
    }

    public static CalendarFragment constructCalendarFragment(final Calendar calendar, CaldroidListener caldroidListener, String name) {
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt(CaldroidFragment.MONTH, calendar.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, calendar.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
        args.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
        if (name != null) {
            args.putString(HelpUtils.FRAGMENT_NAME_KEY, name);
        }
        Calendar maxDate = Calendar.getInstance();
        args.putString(CaldroidFragment.MAX_DATE, HelpUtils.parseDateToString(maxDate.getTime(), HelpUtils.CALDROID_DATE_FORMAT));
        if (TheApplication.getInstance().getRegistrationDate().getTime() != 0) {
            args.putString(CaldroidFragment.MIN_DATE, HelpUtils.parseDateToString(TheApplication.getInstance().getRegistrationDate(), HelpUtils.CALDROID_DATE_FORMAT));
        }
        calendarFragment.setBackgroundResourceForDates(new HashMap<Date, Integer>() {
            {
                if (!compareDateByDay(calendar.getTimeInMillis(), Calendar.getInstance().getTimeInMillis())) {
                    put(new Date(calendar.getTimeInMillis()), R.drawable.calendar_selected_date);
                }
            }
        });
        calendarFragment.setTextColorForDates(new HashMap<Date, Integer>() {
            {
                put(new Date(calendar.getTimeInMillis()), android.R.color.white);
            }
        });
        if (caldroidListener != null) {
            calendarFragment.setCaldroidListener(caldroidListener);
        }
        calendarFragment.setArguments(args);
        return calendarFragment;
    }

    public static int dateToInteger(Date date) {
        String fDate = new SimpleDateFormat(DATE_FORMAT).format(date);
        return Integer.valueOf(fDate);
    }

    public static Date stringToDate(String stringDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(stringDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setTimeZone(Calendar.getInstance().getTimeZone());
        }
        return objectMapper;
    }

    public static <T> String getJsonString(T model) {
        String result = null;
        try {
            StringWriter writer = new StringWriter();
            getObjectMapper().writeValue(writer, model);
            result = writer.toString();
        } catch (IOException e) {
            Log.e("", "Can't write value from mapped data", e);
        }
        return result;
    }

    public static <T> T getModel(String value, Class<T> tClass) {
        try {
            return getObjectMapper().readValue(value, tClass);
        } catch (IOException e) {
            Log.e("", "Can't read value from mapped data", e);
            return null;
        }
    }

    public static void cancelAllRequests(BaseActivity activity, List<RetrofitSpiceRequest> requestList) {
        if (requestList != null && !requestList.isEmpty()) {
            for (RetrofitSpiceRequest request : requestList) {
                activity.getSpiceManager().cancel(request);
            }
            requestList.clear();
        }
    }

    public static Date parseIntToDate(int date) {
        Calendar calendar = Calendar.getInstance();
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static WeightTrackerEntry findWeightEntryLastLogged(List<WeightTrackerEntry> entryList) {
        WeightTrackerEntry resultEntry = null;
        Collections.sort(entryList, new Comparator<WeightTrackerEntry>() {
            @Override
            public int compare(WeightTrackerEntry lhs, WeightTrackerEntry rhs) {
                return (int) (lhs.getConvertDate().getTime() - rhs.getConvertDate().getTime());
            }
        });
        for (WeightTrackerEntry entry : entryList) {
            if (entry.getWeightInPounds() != null) {
                resultEntry = entry;
            }
        }
        return resultEntry;
    }

    public static WeightTrackerEntry findWeightEntryByDay(Calendar calendar, List<WeightTrackerEntry> entryList) {
        WeightTrackerEntry result = null;
        for (WeightTrackerEntry entry : entryList) {
            if (HelpUtils.compareDateByDay(entry.getConvertDate().getTime(), calendar.getTimeInMillis())) {
                result = entry;
                break;
            }
        }
        return result;
    }

    public static BigDecimal findWeightEntryByGoal(List<WeightTrackerEntry> entryList) {
        WeightTrackerEntry result = null;
        Collections.sort(entryList, new Comparator<WeightTrackerEntry>() {
            @Override
            public int compare(WeightTrackerEntry lhs, WeightTrackerEntry rhs) {
                if (rhs.getDate() == lhs.getDate())
                    return 0;
                return rhs.getDate() < lhs.getDate() ? -1 : 1;
            }
        });
        for (WeightTrackerEntry entry : entryList) {
            if (entry.getGoalWeightInPounds() != null) {
                result = entry;
                break;
            }
        }
        return result != null ? result.getGoalWeightInPounds() : new BigDecimal("0");
    }

    public static Float calculateAmountOfUnit(Float multiplier, Integer compatibleUnitId, Integer baseUnitId, Float baseMultiplier) {
        Float amount = multiplier * Unit.getCoefficientById(baseUnitId) / Unit.getCoefficientById(compatibleUnitId) * (baseMultiplier != null ? baseMultiplier : 1);
        float result = amount;
        if (result - ((int) result) == 0) {
            return result;
        } else {
            return roundFloat(amount, 3);
        }
    }

    public static float roundFloat(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static void animateAndSlideShow(final ImageView slidingImage) {
        final int image = HelpUtils.IMAGES[getRandomNumber()];
        slidingImage.setImageResource(image);
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(slidingImage, View.ALPHA, 0, 1);
        alphaAnimation.setDuration(HelpUtils.ANIMATION_DURATION);
        alphaAnimation.start();

        alphaAnimation.addListener(new AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                slidingImage.setImageResource(image);
                final ObjectAnimator alphaAnimation1 = ObjectAnimator.ofFloat(slidingImage, View.ALPHA, 1, 0);
                alphaAnimation1.setDuration(HelpUtils.ANIMATION_DURATION);
                alphaAnimation1.addListener(new AnimatorListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateAndSlideShow(slidingImage);
                    }
                });
                alphaAnimation1.start();
            }
        });
    }

    private static int getRandomNumber() {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(HelpUtils.IMAGES.length);
    }

    public static BigInteger roundToBigInteger(BigDecimal bigDecimal) {
        return bigDecimal == null
                ? BigInteger.ZERO
                : bigDecimal.round(new MathContext(bigDecimal.precision() - bigDecimal.scale())).toBigInteger();
    }

    public static String roundBigDecimal(BigDecimal bigDecimal, int decimalPlaces) {
        BigDecimal decimal = bigDecimal == null
                ? BigDecimal.ZERO
                : bigDecimal.round(new MathContext(bigDecimal.precision() - bigDecimal.scale() + decimalPlaces));
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(decimalPlaces);
        format.setMinimumFractionDigits(0);
        format.setGroupingUsed(false);
        return format.format(decimal);
    }


}
