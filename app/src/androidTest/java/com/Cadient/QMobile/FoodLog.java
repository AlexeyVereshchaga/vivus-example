package com.Cadient.QMobile;

import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.model.remote.FoodLogCurrent;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.model.remote.FoodLogList;
import com.Cadient.QMobile.model.remote.ParserRemote;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.model.remote.SuggestedMeal;
import com.Cadient.QMobile.network.request.foodlog.DailyCalorieBankRequest;
import com.Cadient.QMobile.network.request.foodlog.FavoriteMealsRequest;
import com.Cadient.QMobile.network.request.foodlog.FoodLogDateRequest;
import com.Cadient.QMobile.network.request.foodlog.FrequentMealsRequest;
import com.Cadient.QMobile.network.request.foodlog.MealNameSuggestionsRequest;
import com.Cadient.QMobile.network.request.foodlog.ParseNaturalLanguageRequest;
import com.Cadient.QMobile.network.request.foodlog.RecentMealsRequest;
import com.Cadient.QMobile.network.request.foodlog.TodayFoodLogRequest;
import com.Cadient.QMobile.utils.HelpUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class FoodLog extends ApplicationTest {

    public void testRetrieveDailyCalorieBank() throws Exception {
        int days = 17;
        Calendar cal = Calendar.getInstance();
        cal.set(2014, Calendar.JULY, 30);
        Date startDate = cal.getTime();
        cal.set(2014, Calendar.SEPTEMBER, 4);
        Date endDate = cal.getTime();

        DailyCalorieBankRequest request = new DailyCalorieBankRequest(startDate, endDate);
        request.setService(service);
        DailyCalorieRemote calorieRemote = request.loadDataFromNetwork();
        assertEquals(days, calorieRemote.getDailyCalorieList().size());
    }

    public void testParseNaturalLanguage() throws Exception {
        String text = "I had two slices of cheese pizza, french fries, and half a can of Diet Coke for lunch";
        int numberOfItems = 3;
        ParserRemote parser = new ParserRemote();
        parser.setText(text);

        ParseNaturalLanguageRequest request = new ParseNaturalLanguageRequest(parser);
        request.setService(service);
        ParserRemote parserReturned = request.loadDataFromNetwork();

        assertEquals(text, parserReturned.getText());
        assertEquals(numberOfItems, parserReturned.getParser().getItems().size());
    }

    public void testSuggestMealName() throws Exception {
        Integer pageSize = 3;
        String term = "af";

        SuggestedMeal meal = new SuggestedMeal();
        meal.setTerm(term);
        meal.setPageSize(pageSize);

        MealNameSuggestionsRequest request = new MealNameSuggestionsRequest(meal);
        request.setService(service);

        SuggestedMeal mealReturned = request.loadDataFromNetwork();

        assertEquals(term, mealReturned.getTerm());
        assertEquals(pageSize.intValue(), mealReturned.getMeals().size());
    }

    public void testObtainTodayFoodLog() throws Exception {
        Date date = Calendar.getInstance().getTime();
        TodayFoodLogRequest request = new TodayFoodLogRequest(date);
        request.setService(service);
        FoodLogCurrent foodLogCurrent = request.loadDataFromNetwork();
        assertNotNull(foodLogCurrent);
    }

    public void testObtainFavoriteMeals() throws Exception {
        FavoriteMealsRequest request = new FavoriteMealsRequest(1, 10);
        request.setService(service);

        RecipeRemote recipeRemote = request.loadDataFromNetwork();

        assertNotNull(recipeRemote.getMeals());
    }

    public void testObtainFrequentMeals() throws Exception {

        FrequentMealsRequest request = new FrequentMealsRequest(1, 10);
        request.setService(service);

        SuggestedMeal suggestedMeal = request.loadDataFromNetwork();

        assertNotNull(suggestedMeal.getMeals());
    }

    public void testObtainRecentMeals() throws Exception {

        RecentMealsRequest request = new RecentMealsRequest(1, 10);
        request.setService(service);

        SuggestedMeal suggestedMeal = request.loadDataFromNetwork();

        assertNotNull(suggestedMeal.getMeals());
    }

    public void testReadFoodLogDates() throws Exception {
        Date date = HelpUtils.stringToDate("20141020");
        FoodLogDateRequest request = new FoodLogDateRequest(date);
        request.setService(service);

        FoodLogList foodLogList = request.loadDataFromNetwork();

        assertNotNull(foodLogList.getDateList());
    }
}
