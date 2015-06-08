package com.Cadient.QMobile.database;

import android.content.Context;
import android.util.Log;

import com.Cadient.QMobile.database.dao.CommonDao;
import com.Cadient.QMobile.model.remote.MealRating;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexey Vereshchaga on 29.08.14.
 */
public class DataBaseGateway {
    private static DataBaseGateway instance;
    private final String TAG = DataBaseGateway.class.getSimpleName();

    private DataBaseHelper databaseHelper;
    private CommonDao<MealRating> mealRatingDao;

    public DataBaseHelper getHelper() {
        return databaseHelper;
    }

    public static DataBaseGateway getInstance() {
        if (instance == null) {
            instance = new DataBaseGateway();
        }
        return instance;
    }

    public void init(Context context) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        }
    }

    public void release() {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
        }
        databaseHelper = null;
    }

    private CommonDao<MealRating> getMealRatingDao() throws SQLException {
        if (mealRatingDao == null) {
            mealRatingDao = new CommonDao<MealRating>(databaseHelper.getConnectionSource(), MealRating.class);
        }
        return mealRatingDao;
    }

    public void saveMealRatingObject(MealRating modelObject) {
        try {
            getMealRatingDao().add(modelObject);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void clearMealRatingObjects() {
        try {
            getMealRatingDao().removeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MealRating> getMealRatingObjects() {
        List<MealRating> result = null;
        try {
            result = getMealRatingDao().getAllList();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    public List<MealRating> getMealRatingObjects(Long offset, Long limit) {
        List<MealRating> result = null;
        try {
            result = getMealRatingDao().getList(offset, limit);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    public MealRating getMealRatingById(Long id) {
        MealRating mealRating = null;
        try {
            mealRating = getMealRatingDao().queryForId(id.toString());
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return mealRating;
    }
}
