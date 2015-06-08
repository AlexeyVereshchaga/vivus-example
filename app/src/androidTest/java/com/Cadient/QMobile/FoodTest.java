package com.Cadient.QMobile;

import com.Cadient.QMobile.model.Food;
import com.Cadient.QMobile.model.FoodListReadModel;
import com.Cadient.QMobile.model.FoodNutrient;
import com.Cadient.QMobile.model.SearchByCategory;
import com.Cadient.QMobile.model.SearchByKeyword;
import com.Cadient.QMobile.model.remote.FoodList;
import com.Cadient.QMobile.model.remote.FoodRemote;
import com.Cadient.QMobile.model.remote.MealTranslator;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.network.request.cookery.CreateFoodRequest;
import com.Cadient.QMobile.network.request.cookery.DeleteFoodRequest;
import com.Cadient.QMobile.network.request.cookery.FoodAnalyzerRequest;
import com.Cadient.QMobile.network.request.cookery.MealIdTranslationsRequest;
import com.Cadient.QMobile.network.request.cookery.ReadFoodRequest;
import com.Cadient.QMobile.network.request.cookery.ReadListOfFoodRequest;
import com.Cadient.QMobile.network.request.cookery.SearchRecipeByCategoriesRequest;
import com.Cadient.QMobile.network.request.cookery.SearchRecipeByKeywordsRequest;
import com.Cadient.QMobile.network.request.cookery.UpdateFoodRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexey Vereshchaga on 01.09.14.
 */
public class FoodTest extends ApplicationTest {

    public void testFoodCreate() throws Exception {
        String name = "Bread " + new Random(System.currentTimeMillis()).nextInt(10000);

        FoodRemote foodReturned = createFood(name);

        assertNotNull(foodReturned.getFood().getId());
        assertEquals(name, foodReturned.getFood().getName());
    }

    private FoodRemote createFood(String name) throws Exception {
        Food food = new Food();
        food.setName(name);
        food.setCategoryId(10);
        List<Integer> list = new ArrayList<Integer>();
        list.add(42);
        food.setCompatibleUnitsIds(list);

        final FoodNutrient firstNutrient = new FoodNutrient();
        final FoodNutrient secondNutrient = new FoodNutrient();
        final FoodNutrient thirdNutrient = new FoodNutrient();
        firstNutrient.setNutrientId(4l);
        firstNutrient.setAmount(161f);
        firstNutrient.setAmountRaw(161f);
        secondNutrient.setNutrientId(21l);
        secondNutrient.setAmount(127f);
        secondNutrient.setAmountRaw(127f);
        thirdNutrient.setNutrientId(22l);
        thirdNutrient.setAmount(139f);
        thirdNutrient.setAmountRaw(139f);

        List<FoodNutrient> foodNutrientList = new ArrayList<FoodNutrient>(3) {{
            add(firstNutrient);
            add(secondNutrient);
            add(thirdNutrient);
        }};
        food.setFoodNutrientList(foodNutrientList);
        food.setNeedsAttention(true);
        food.setSafeToEdit(true);
        food.setServingSizeAmountRaw("1");
        food.setServingSizeUnitId(42);
        food.setServingsPerContainer(2.5f);
        food.setUpc(894455000214l);
        food.setVolumePerServingAmount(202f);
        food.setVolumePerServingUnitId(16);
        food.setWeightPerServingAmount(13f);
        food.setWeightPerServingUnitId(9);

        FoodRemote foodRemote = new FoodRemote();
        foodRemote.setFood(food);

        CreateFoodRequest request = new CreateFoodRequest(foodRemote);
        request.setService(service);
        return request.loadDataFromNetwork();
    }

    public void testReadListOfFoodDefault() throws Exception {
        ReadListOfFoodRequest request = new ReadListOfFoodRequest();
        request.setService(service);
        FoodList foodListReturned = request.loadDataFromNetwork();
        List<Food> foods = foodListReturned.getFoodList();
        assertEquals(10, foods.size());
    }

    public void testReadListOfFood() throws Exception {
        int pageSize = 5;
        FoodListReadModel readModel = new FoodListReadModel();
        readModel.setPageSize(pageSize);
        ReadListOfFoodRequest request = new ReadListOfFoodRequest(readModel);
        request.setService(service);
        FoodList foodListReturned = request.loadDataFromNetwork();
        List<Food> foods = foodListReturned.getFoodList();
        assertEquals(pageSize, foods.size());
    }

    public void testReadFood() throws Exception {
        Long upc = 894455000209l;
        Integer foodId = 29320;

        ReadFoodRequest request = new ReadFoodRequest(foodId);
        request.setService(service);

        FoodRemote foodReturned = request.loadDataFromNetwork();

        assertEquals(foodId, foodReturned.getFood().getId());
        assertEquals(upc, foodReturned.getFood().getUpc());
    }

    public void testUpdateFood() throws Exception {
        Integer foodId = 39833;
        Long upc = new Random(System.currentTimeMillis()).nextLong();

        Food food = new Food();
        FoodRemote foodRemote = new FoodRemote();
        food.setId(foodId);
        food.setUpc(upc);
        foodRemote.setFood(food);

        UpdateFoodRequest request = new UpdateFoodRequest(foodRemote);
        request.setService(service);

        FoodRemote foodReturned = request.loadDataFromNetwork();

        assertEquals(foodId, foodReturned.getFood().getId());
        assertEquals(upc, foodReturned.getFood().getUpc());
    }

    public void testDeleteFood() throws Exception {
        String name = "Bread " + new Random(System.currentTimeMillis()).nextInt(10000);
        FoodRemote createdFood = createFood(name);
        Integer foodId = createdFood.getFood().getId();

        DeleteFoodRequest request = new DeleteFoodRequest(foodId);
        request.setService(service);

        FoodRemote foodReturned = request.loadDataFromNetwork();

        assertEquals(foodId, foodReturned.getFood().getId());
    }

    public void testSearchRecipeByKeywords() throws Exception {
        String term = "chicken";
        int pageSize = 3;

        SearchByKeyword recipeByKeyword = new SearchByKeyword();
        recipeByKeyword.setPageSize(pageSize);
        recipeByKeyword.setPageNumber(1);
        recipeByKeyword.setTerm(term);

        SearchRecipeByKeywordsRequest request = new SearchRecipeByKeywordsRequest(recipeByKeyword);
        request.setService(service);

        RecipeRemote recipeRemoteReturned = request.loadDataFromNetwork();

        assertEquals(term, recipeRemoteReturned.getTerm());
        assertEquals(pageSize, recipeRemoteReturned.getMeals().size());
    }

    public void testSearchRecipeByCategory() throws Exception {
        Integer[] categories = {1, 12};
        int pageSize = 3;

        SearchByCategory recipeByKeyword = new SearchByCategory();
        recipeByKeyword.setPageSize(pageSize);
        recipeByKeyword.setPageNumber(1);
        recipeByKeyword.setCategoryId(categories);

        SearchRecipeByCategoriesRequest request = new SearchRecipeByCategoriesRequest(recipeByKeyword);
        request.setService(service);

        RecipeRemote recipeRemoteReturned = request.loadDataFromNetwork();

        assertEquals(categories[0], recipeRemoteReturned.getCategoryIds()[0]);
        assertEquals(pageSize, recipeRemoteReturned.getMeals().size());
    }

    public void testAnalyzeFood() throws Exception {
        String term = "relish";
        int pageSize = 3;

        SearchByKeyword foodByKeyword = new SearchByKeyword();
        foodByKeyword.setPageSize(pageSize);
        foodByKeyword.setPageNumber(1);
        foodByKeyword.setTerm(term);

        FoodAnalyzerRequest request = new FoodAnalyzerRequest(foodByKeyword);
        request.setService(service);

        RecipeRemote foodReturned = request.loadDataFromNetwork();

        assertEquals(term, foodReturned.getTerm());
        assertEquals(pageSize, foodReturned.getMeals().size());
    }

    public void testTranslateMealId() throws Exception {
        String mealIdExternal = "ER:950";
        Integer mealId = 212;

        MealTranslator mealTranslator = new MealTranslator();
        mealTranslator.setExternalRecipeId(mealIdExternal);
        MealIdTranslationsRequest request = new MealIdTranslationsRequest(mealTranslator);
        request.setService(service);
        MealTranslator mealTranslatorReturned = request.loadDataFromNetwork();

        assertEquals(mealId, mealTranslatorReturned.getMeal().getId());
    }
}
