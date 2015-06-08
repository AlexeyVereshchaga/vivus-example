package com.Cadient.QMobile;

import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.model.remote.ActivitiesList;
import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.network.request.activitylog.ActivitiesListRequest;
import com.Cadient.QMobile.network.request.activitylog.ActivitySearchAutoCompleteRequest;
import com.Cadient.QMobile.network.request.activitylog.CreateActivityLogEntriesRequest;
import com.Cadient.QMobile.network.request.activitylog.DeleteActivityLogEntriesRequest;
import com.Cadient.QMobile.network.request.activitylog.UpdateActivityLogEntriesRequest;

import java.util.Date;

/**
 * Created by ivan on 9/2/14.
 */
public class ActivityLogEntriesTest extends ApplicationTest {

//    public ActivityLogEntries testCreateActivityLogEntries() throws Exception {
//        int value = 10;
//        ActivityLogEntries entries = new ActivityLogEntries();
//        entries.setDate(20140929);
//        entries.setActivityId(272);
//        entries.setDuration(10);
//        entries.setMiles(10);
//        entries.setSteps(10);
//        entries.setHeartRate(10);
//        entries.setCalories(200);
//
//
//        CreateActivityLogEntriesRequest request = new CreateActivityLogEntriesRequest(entries);
//        request.setService(service);
//
//        entries = request.loadDataFromNetwork();
//
//        assertNotNull(entries.getId());
//        assertEquals(272, entries.getActivityId());
//        assertEquals(value, entries.getDuration());
//        assertEquals(20140929, entries.getDate());
//        assertEquals(value, entries.getMiles());
//        assertEquals(value, entries.getSteps());
//        assertEquals(value, entries.getHeartRate());
//        assertEquals(200, entries.getCalories());
//
//        return entries;
//    }
//
//    public void testUpdateActivityLogEntries() throws Exception {
//        int value = 20;
//        ActivityLogEntries entries = testCreateActivityLogEntries();
//
//        entries.setDate(20140928);
//        entries.setActivityId(272);
//        entries.setDuration(value);
//        entries.setMiles(value);
//        entries.setSteps(value);
//        entries.setHeartRate(value);
//        entries.setCalories(200);
//
//
//        UpdateActivityLogEntriesRequest request = new UpdateActivityLogEntriesRequest(entries);
//        request.setService(service);
//
//        assertNotNull(entries.getId());
//        assertEquals(272, entries.getActivityId());
//        assertEquals(value, entries.getDuration());
//        assertEquals(20140928, entries.getDate());
//        assertEquals(value, entries.getMiles());
//        assertEquals(value, entries.getSteps());
//        assertEquals(value, entries.getHeartRate());
//        assertEquals(200, entries.getCalories());
//
//    }

//    public void testDeleteActivityLogEntries() throws Exception {
//        ActivityLogEntries entries = testCreateActivityLogEntries();
//        DeleteActivityLogEntriesRequest request = new DeleteActivityLogEntriesRequest(entries);
//        request.setService(service);
//        entries = request.loadDataFromNetwork();
//        assertNotNull(entries.getId());
//    }

    public void testActivitySearchAutoComplete() throws Exception {
        ActivitySearchAutoCompleteRequest request = new ActivitySearchAutoCompleteRequest("b");
        request.setService(service);

        Activity[] result = request.loadDataFromNetwork();

        assertTrue(result.length != 0);
    }

    public void testGetActivities() throws Exception {
        String date = "20141013";
        ActivitiesListRequest request = new ActivitiesListRequest(new Date(date));
        request.setService(service);
        ActivitiesList activitiesList = request.loadDataFromNetwork();
        assertNotNull(activitiesList.getDateList());
    }
}
