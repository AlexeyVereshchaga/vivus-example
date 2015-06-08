package com.Cadient.QMobile;

import com.Cadient.QMobile.model.remote.WeightTrackerEntry;
import com.Cadient.QMobile.network.request.weightlog.CreateWeightTrackerEntryRequest;
import com.Cadient.QMobile.network.request.weightlog.DeleteWeightTrackerEntryRequest;
import com.Cadient.QMobile.network.request.weightlog.UpdateWeightTrackerEntryRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class WeightLogTest extends ApplicationTest {

    public void testCreateWeightTrackerEntry() throws Exception {
        WeightTrackerEntry entry = new WeightTrackerEntry();
        entry.setId(6344562);
        entry.setDate(20140902);
        entry.setWeightInPounds(150.5f);

        CreateWeightTrackerEntryRequest request = new CreateWeightTrackerEntryRequest(entry);
        request.setService(service);

        entry = request.loadDataFromNetwork();

        assertNotNull(entry.getId());
        assertEquals(6344562, entry.getId());
        assertEquals(20140902, entry.getDate());
        assertEquals(150.5f, entry.getWeightInPounds());
    }

    public void testUpdateWeightTrackerEntry() throws Exception {
        WeightTrackerEntry entry = new WeightTrackerEntry();
        entry.setId(6344562);

//        entry.setDate(new Date());
        entry.setWeightInPounds(160.5f);

        UpdateWeightTrackerEntryRequest request = new UpdateWeightTrackerEntryRequest(entry);
        request.setService(service);

        entry = request.loadDataFromNetwork();

        assertNotNull(entry.getId());
        assertEquals(6344562, entry.getId());
        assertEquals(20140902, entry.getDate());
        assertEquals(160.5f, entry.getWeightInPounds());
    }

    public void testDeleteWeightTrackerEntry() throws Exception {
        WeightTrackerEntry entry = new WeightTrackerEntry();
        entry.setId(6344562);
        entry.setDate(20140902);
        entry.setWeightInPounds(150.5f);

        DeleteWeightTrackerEntryRequest request = new DeleteWeightTrackerEntryRequest(entry);
        request.setService(service);

        entry = request.loadDataFromNetwork();

        assertNotNull(entry.getId());
        assertEquals(6344562, entry.getId());
        assertEquals(20140902, entry.getDate());
        assertNull(entry.getWeightInPounds() == 0.0 ? null : entry.getWeightInPounds());
    }
}
