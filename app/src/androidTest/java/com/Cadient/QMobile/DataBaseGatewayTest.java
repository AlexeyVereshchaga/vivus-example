package com.Cadient.QMobile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.Cadient.QMobile.database.DataBaseGateway;
import com.Cadient.QMobile.model.remote.MealRating;

import java.util.List;

import static com.Cadient.QMobile.database.DataBaseUtils.ID;
import static com.Cadient.QMobile.database.DataBaseUtils.MEAL_ID;
import static com.Cadient.QMobile.database.DataBaseUtils.MEAL_RATING_TABLE;

/**
 * Created by Alexey Vereshchaga on 01.09.14.
 */
public class DataBaseGatewayTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DataBaseGateway.getInstance().init(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        DataBaseGateway.getInstance().clearMealRatingObjects();
        super.tearDown();
    }

    public void testSaveMealRatingObjects() throws Exception {
        Integer mealID = 1000;

        MealRating mealRating = new MealRating();
        mealRating.setMealId(mealID);
        mealRating.setId(10l);
        mealRating.setValue(40);

        DataBaseGateway.getInstance().saveMealRatingObject(mealRating);

        String sql = "SELECT * FROM meal_rating WHERE meal_id = ?";
        Cursor cursor = DataBaseGateway.getInstance().getHelper().getWritableDatabase().rawQuery(sql, new String[]{mealID.toString()});
        cursor.moveToFirst();
        assertEquals(mealID, (Integer) cursor.getInt(cursor.getColumnIndex(MEAL_ID)));
    }

    public void testClearMealRatingObjects() throws Exception {
        addRowsToMealRatingTable();
        String sql = "SELECT * FROM meal_rating";
        Cursor cursor = DataBaseGateway.getInstance().getHelper().getWritableDatabase().rawQuery(sql, null);
        assertEquals(2, cursor.getCount());
        DataBaseGateway.getInstance().clearMealRatingObjects();
        cursor = DataBaseGateway.getInstance().getHelper().getWritableDatabase().rawQuery(sql, null);
        assertEquals(0, cursor.getCount());
    }

    public void testGetMealRatingObjects() throws Exception {
        addRowsToMealRatingTable();
        List<MealRating> list = DataBaseGateway.getInstance().getMealRatingObjects();
        assertEquals(2, list.size());
    }

    public void testGetMealRatingObjectsOffset() throws Exception {
        addRowsToMealRatingTable();
        List<MealRating> list = DataBaseGateway.getInstance().getMealRatingObjects(null, 1l);
        assertEquals(1, list.size());
        assertEquals(new Long(1), list.get(0).getId());
        list = DataBaseGateway.getInstance().getMealRatingObjects(1l, 1l);
        assertEquals(new Long(2), list.get(0).getId());
    }

    public void testGetMealRatingById() throws Exception {
        addRowsToMealRatingTable();
        MealRating mealRating = DataBaseGateway.getInstance().getMealRatingById(1l);
        assertEquals(new Long(1), mealRating.getId());
        assertNull(mealRating.getMealId());
    }

    private void addRowsToMealRatingTable() {
        SQLiteDatabase db = DataBaseGateway.getInstance().getHelper().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, "1");
        db.insert(MEAL_RATING_TABLE, null, cv);
        cv.put(ID, "2");
        db.insert(MEAL_RATING_TABLE, null, cv);
    }
}
