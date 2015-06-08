package com.Cadient.QMobile;

import com.Cadient.QMobile.model.MealForLog;
import com.Cadient.QMobile.model.Unit;
import com.Cadient.QMobile.model.remote.FoodLogCreatedEntry;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.network.request.foodlog.CreateFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.foodlog.DeleteFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.foodlog.ReadFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.foodlog.UpdateFoodLogEntriesRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class FoodLogEntriesTest extends ApplicationTest {

    public void testCreateFoodLogEntries() throws Exception {
        Integer date = 20141101;
        Integer slot = 2;
        Long meal_id = 4720l;
        Float multiplier = 2.7f;
        MealForLog entries = new MealForLog();
        entries.setDate(date);
        entries.setSlotId(slot);
        entries.setId(meal_id);
        entries.setMultiplier(multiplier);
        Unit unit = new Unit();
        unit.setId(6);
        entries.setUnit(unit);

        CreateFoodLogEntriesRequest request = new CreateFoodLogEntriesRequest(entries);
        request.setService(service);
        FoodLogCreatedEntry entry = request.loadDataFromNetwork();
        assertNotNull(entry);
    }

//    public void testUpdateFoodLogEntries() throws Exception {
//        Integer date = 20140902;
//        Integer slot = 2;
//        Integer meal_id = 4720;
//        Float multipler = 2.7f;
//        Integer unitId = 6;
//        FoodLogEntries entries = testCreateFoodLogEntries();
//        entries.setDate(date);
//        entries.setSlotId(slot);
//        entries.setMealId(meal_id);
//        entries.setMultiplier(multipler);
//        entries.setPlannedForToday(false);
//        entries.setUnitId(unitId);
//
//
//        UpdateFoodLogEntriesRequest request = new UpdateFoodLogEntriesRequest(entries);
//        request.setService(service);
//
//        assertNotNull(entries.getId());
//        assertEquals(date, entries.getDate());
//        assertEquals(slot, entries.getSlotId());
//        assertEquals(meal_id, entries.getMealId());
//        assertEquals(multipler, entries.getMultiplier());
//        assertFalse(entries.isPlannedForToday());
//        assertEquals(unitId, entries.getUnitId());
//
//    }
//
//    public void testDeleteFoodLogEntries() throws Exception {
//        FoodLogEntries entries = testCreateFoodLogEntries();
//        DeleteFoodLogEntriesRequest request = new DeleteFoodLogEntriesRequest(entries);
//        request.setService(service);
//
//        entries = request.loadDataFromNetwork();
//
//        assertNotNull(entries);
//
//    }
//
//    public void testReadFoodLogEntries() throws Exception {
//        FoodLogEntries entries = testCreateFoodLogEntries();
//        ReadFoodLogEntriesRequest request = new ReadFoodLogEntriesRequest(entries);
//        request.setService(service);
//        entries = request.loadDataFromNetwork();
//        assertNotNull(entries);
//    }
}
