package com.Cadient.QMobile.network;

import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.model.MealForLog;
import com.Cadient.QMobile.model.SearchModel;
import com.Cadient.QMobile.model.remote.ActivitiesList;
import com.Cadient.QMobile.model.remote.ActivityLogEntries;
import com.Cadient.QMobile.model.remote.BmiCalculator;
import com.Cadient.QMobile.model.remote.CalorieBurnCalculator;
import com.Cadient.QMobile.model.remote.DailyCalorieRemote;
import com.Cadient.QMobile.model.remote.DailyCalorieTarget;
import com.Cadient.QMobile.model.remote.dynamicserver.DynamicServer;
import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.Cadient.QMobile.model.remote.FoodList;
import com.Cadient.QMobile.model.remote.FoodLogCreatedEntry;
import com.Cadient.QMobile.model.remote.FoodLogCurrent;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.model.remote.FoodLogList;
import com.Cadient.QMobile.model.remote.FoodLogNutrients;
import com.Cadient.QMobile.model.remote.FoodRemote;
import com.Cadient.QMobile.model.remote.MealRating;
import com.Cadient.QMobile.model.remote.MealTranslator;
import com.Cadient.QMobile.model.remote.ParserRemote;
import com.Cadient.QMobile.model.remote.RecipeRemote;
import com.Cadient.QMobile.model.remote.RegistrationResult;
import com.Cadient.QMobile.model.remote.SessionHandOff;
import com.Cadient.QMobile.model.remote.SuggestedMeal;
import com.Cadient.QMobile.model.remote.UserProfile;
import com.Cadient.QMobile.model.remote.UserRemote;
import com.Cadient.QMobile.model.remote.UserWeight;
import com.Cadient.QMobile.model.remote.WeightLossGoalCycles;
import com.Cadient.QMobile.model.remote.WeightTrackerEntries;
import com.Cadient.QMobile.model.remote.WeightTrackerEntry;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by alexey on 12.08.14.
 */
public interface NutrioApi {

    @Headers({"Content-Type: application/json"})
    @GET("/config.json")
    void readDynamicServer(Callback<DynamicServer> callback);

    @GET("/api/user/info")
    RegistrationResult registrationResult();

    @GET("/proxy/api/v3/user_profiles/_NID_.json")
    UserProfile readUserProfile();

    @POST("/proxy/api/v2/users.json")
    UserRemote createUser(@Body UserRemote userRemote);

    @GET("/proxy/api/v2/users/{internalUserId}.json")
    UserRemote readUser(@Path("internalUserId") Integer internalUserId);

    @PUT("/proxy/api/v2/users/{internalUserId}.json")
    UserRemote updateUser(@Path("internalUserId") Integer internalUserId, @Body UserRemote userRemote);

    @GET("/proxy/api/v2/users.json")
    UserRemote searchUser(@Query("external_user_id") String externalUserId);

    @POST("/proxy/api/v2/user_handoff_tokens")
    SessionHandOff generateHandOffToken(@Body SessionHandOff sessionHandOff);

    @GET("/proxy/api/v2/user_handoff_tokens/{token}")
    SessionHandOff validateHandOffToken(@Path("token") String token);

    //BMI Calculator
    @POST("/proxy/api/v1/calculator/bmi")
    BmiCalculator calculateBmi(@Body BmiCalculator bmiCalculator);

    //User Profile
    @PUT("/proxy/api/v3/user_profiles/{internalUserId}.json")
    UserProfile updateUserProfile(@Path("internalUserId") Integer internalUserId, @Body UserProfile userProfile);


    //User Meal Ratings
    @POST("/proxy/api/v1/user_meal_ratings")
    MealRating createUserMealRating(@Body MealRating mealRating);

    @GET("/proxy/api/v1/user_meal_ratings/{mealId}.json")
    MealRating readUserMealRating(@Path("mealId") Integer mealId);

    @PUT("/proxy/api/v1/user_meal_ratings/{id}.json")
    MealRating updateUserMealRating(@Path("id") Long id, @Body MealRating mealRating);

    @DELETE("/proxy/api/v1/user_meal_ratings/{id}.json")
    MealRating resetUserMealRating(@Path("id") Long id);

    //Calorie Burn Calculator
    @POST("/proxy/api/v1/calculator/calories/burned")
    CalorieBurnCalculator calculateBurnCalorie(@Body CalorieBurnCalculator calorieBurnCalculator);

    //Daily Calorie Target Calculator
    @POST("/proxy/api/v1/calculator/calories/target")
    DailyCalorieTarget calculateDailyCalorie(@Body DailyCalorieTarget dailyCalorieTarget);

    //User Meal Favorites
    @POST("/proxy/api/v1/user_meal_favorites")
    FavoriteUserMeal createFavoriteUserMeal(@Body FavoriteUserMeal favoriteUserMeal);

    @DELETE("/proxy/api/v1/user_meal_favorites/{id}.json")
    FavoriteUserMeal deleteFavoriteUserMeal(@Path("id") Long id);

    @GET("/proxy/api/v1/user_meal_favorites/{id}.json")
    FavoriteUserMeal getFavoriteUserMeal(@Path("id") Integer id);


    //User Weight Loss Goal Cycles
    @POST("/proxy/api/v1/user_weight_loss_goal_cycles")
    WeightLossGoalCycles createUserWeightLoss(@Body WeightLossGoalCycles weightLossGoalCycles);

    @PUT("/proxy/api/v1/user_weight_loss_goal_cycles/{id}.json")
    WeightLossGoalCycles updateUserWeightLoss(@Path("id") Integer id, @Body WeightLossGoalCycles weightLossGoalCycles);

    //User Weights (Batch)
    @POST("/proxy/api/v1/user_weights")
    UserWeight createUserWeights(@Body UserWeight userWeight);

    //Foods
    @POST("/proxy/api/v1/foods")
    FoodRemote createFood(@Body FoodRemote food);

//    @GET("/v1/foods")
//    FoodList readListOfFoodDefault();

    @GET("/proxy/api/v1/foods")
    FoodList readListOfFood(@QueryMap Map<String, Object> options);

    @GET("/proxy/api/v1/foods/{foodId}.json")
    FoodRemote readFood(@Path("foodId") Integer foodId);

    @PUT("/proxy/api/v1/foods/{foodId}.json")
    FoodRemote updateFood(@Path("foodId") Integer foodId, @Body FoodRemote entity);

    @DELETE("/proxy/api/v1/foods/{foodId}.json")
    FoodRemote deleteFood(@Path("foodId") Integer foodId);

    //Weight Log
    @POST("/proxy/api/v2/log/weight/entries")
    WeightTrackerEntry createWeightTrackerEntry(@Body WeightTrackerEntry entry);

    @GET("/proxy/api/v2/log/weight/entries")
    WeightTrackerEntries readWeightTrackerEntry();

    @PUT("/proxy/api/v2/log/weight/entries/{id}.json")
    WeightTrackerEntry updateWeightTrackerEntry(@Path("id") Long id, @Body WeightTrackerEntry entry);

    @DELETE("/proxy/api/v2/log/weight/entries/{id}.json")
    WeightTrackerEntry deleteWeightTrackerEntry(@Path("id") Long id);

    //Activity Log
    @POST("/proxy/api/v2/log/activity/entries")
    ActivityLogEntries createActivityLogEntries(@Body ActivityLogEntries entry);

    @PUT("/proxy/api/v1/log/activity/entries/{id}.json")
    ActivityLogEntries updateActivityLogEntries(@Path("id") Long id, @Body ActivityLogEntries entry);

    @DELETE("/proxy/api/v2/log/activity/entries/{id}.json")
    ActivityLogEntries deleteActivityLogEntries(@Path("id") Long id);

    @GET("/proxy/api/v2/log/activity/entries")
    ActivitiesList getActivitiesList(@Query("yyyymmdd") Integer date);

    //Search recipe
    @POST("/proxy/api/v1/search/recipes_by_keywords")
    RecipeRemote searchRecipeByKeywords(@Body SearchModel entity);

    @POST("/proxy/api/v1/search/recipes_by_categories")
    RecipeRemote searchRecipeByCategories(@Body SearchModel entity);

    //Food log
    @POST("/proxy/api/v3/log/food/entries")
    FoodLogCreatedEntry createFoodLogEntries(@Body MealForLog entity);

    @PUT("/proxy/api/v3/log/food/entries/{id}")
    FoodLogEntries updateFoodLogEntries(@Path("id") Long id, @Body MealForLog entity);

    @GET("/proxy/api/v1/log/food/entries/{id}.json")
    FoodLogEntries readFoodLogEntries(@Path("id") Long id);

    @DELETE("/proxy/api/v3/log/food/entries/{id}.json")
    FoodLogEntries deleteFoodLogEntries(@Path("id") Long id);

    @GET("/proxy/api/v2/log/food/entries.json")
    FoodLogCurrent obtainTodayFoodLog(@Query("yyyymmdd") Integer date);

    @GET("/proxy/api/v1/log/food/nutrients/{id}.json")
    FoodLogNutrients getFoodLogNutrients(@Path("id") String id);

    @GET("/proxy/api/v2/user_meal_favorites")
    RecipeRemote obtainFavoriteMeals();

    @GET("/proxy/api/v2/dashboard/food/frequent")
    SuggestedMeal obtainFrequentMeals();

    @GET("/proxy/api/v2/dashboard/food/recent")
    SuggestedMeal obtainRecentMeals();

    @GET("/proxy/api/v1/dashboard/food/days")
    FoodLogList readFoodLogForDate(@Query("yyyymmdd") String date);

    //Analyze Food
    @POST("/proxy/api/v1/search/foods_by_keywords")
    RecipeRemote analyzeFood(@Body SearchModel entity);

    //Translate Meal Id
    @POST("/proxy/api/v1/meal_translations")
    MealTranslator translateMealId(@Body MealTranslator entity);


    @GET("/proxy/api/v1/log/bank/days.json")
    DailyCalorieRemote retrieveDailyCalorieBank(@Query("start_date") Integer startDate, @Query("end_date") Integer endDate);

    @POST("/proxy/api/v1/log/food/parsers")
    ParserRemote parseNaturalLanguage(@Body ParserRemote entity);

    @POST("/proxy/api/v2/suggest/edibles")
    SuggestedMeal suggestMealName(@Body SuggestedMeal entity);

    @GET("/proxy/api/v2/activities")
    Activity[] suggestActivities(@Query("term") String term);
}
