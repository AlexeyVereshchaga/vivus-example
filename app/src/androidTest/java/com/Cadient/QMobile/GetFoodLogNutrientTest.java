package com.Cadient.QMobile;

import com.Cadient.QMobile.model.remote.FoodLogNutrients;
import com.Cadient.QMobile.network.request.foodlog.GetFoodLogNutrientsRequest;

/**
 * Created by ivan on 9/2/14.
 */
public class GetFoodLogNutrientTest extends ApplicationTest {

    public void testGetFoodLogNutrients() throws Exception {
        FoodLogNutrients result = new FoodLogNutrients();
        result.setId("20140904");

        GetFoodLogNutrientsRequest request = new GetFoodLogNutrientsRequest(result);
        request.setService(service);

        result = request.loadDataFromNetwork();

        assertNotNull(result);
        assertEquals("20140904", result.getDate());

    }
}
