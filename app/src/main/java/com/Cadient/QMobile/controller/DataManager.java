package com.Cadient.QMobile.controller;

import com.Cadient.QMobile.Environment;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.model.Activity;
import com.Cadient.QMobile.model.FoodListReadModel;
import com.Cadient.QMobile.model.MealForLog;
import com.Cadient.QMobile.model.SearchByCategory;
import com.Cadient.QMobile.model.SearchByKeyword;
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
import com.Cadient.QMobile.network.NutrioApi;
import com.Cadient.QMobile.network.RetrofitClient;
import com.Cadient.QMobile.network.request.activitylog.ActivitiesListRequest;
import com.Cadient.QMobile.network.request.activitylog.ActivitySearchAutoCompleteRequest;
import com.Cadient.QMobile.network.request.activitylog.CreateActivityLogEntriesRequest;
import com.Cadient.QMobile.network.request.activitylog.DeleteActivityLogEntriesRequest;
import com.Cadient.QMobile.network.request.activitylog.UpdateActivityLogEntriesRequest;
import com.Cadient.QMobile.network.request.authentication.CreateUserRequest;
import com.Cadient.QMobile.network.request.authentication.GenerateTokenRequest;
import com.Cadient.QMobile.network.request.authentication.ReadUserRequest;
import com.Cadient.QMobile.network.request.authentication.RegistrationResultRequest;
import com.Cadient.QMobile.network.request.authentication.SearchUserRequest;
import com.Cadient.QMobile.network.request.authentication.UpdateUserRequest;
import com.Cadient.QMobile.network.request.authentication.ValidateTokenRequest;
import com.Cadient.QMobile.network.request.calculator.CalculateBmiRequest;
import com.Cadient.QMobile.network.request.calculator.CalculateCalorieBurnRequest;
import com.Cadient.QMobile.network.request.calculator.CalculateDailyCalorieRequest;
import com.Cadient.QMobile.network.request.cookery.CreateFoodRequest;
import com.Cadient.QMobile.network.request.cookery.DeleteFoodRequest;
import com.Cadient.QMobile.network.request.cookery.FoodAnalyzerRequest;
import com.Cadient.QMobile.network.request.cookery.MealIdTranslationsRequest;
import com.Cadient.QMobile.network.request.cookery.ReadFoodRequest;
import com.Cadient.QMobile.network.request.cookery.ReadListOfFoodRequest;
import com.Cadient.QMobile.network.request.cookery.SearchRecipeByCategoriesRequest;
import com.Cadient.QMobile.network.request.cookery.SearchRecipeByKeywordsRequest;
import com.Cadient.QMobile.network.request.cookery.UpdateFoodRequest;
import com.Cadient.QMobile.network.request.foodlog.CreateFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.foodlog.DailyCalorieBankRequest;
import com.Cadient.QMobile.network.request.foodlog.DeleteFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.foodlog.FavoriteMealsRequest;
import com.Cadient.QMobile.network.request.foodlog.FoodLogDateRequest;
import com.Cadient.QMobile.network.request.foodlog.FrequentMealsRequest;
import com.Cadient.QMobile.network.request.foodlog.MealNameSuggestionsRequest;
import com.Cadient.QMobile.network.request.foodlog.ParseNaturalLanguageRequest;
import com.Cadient.QMobile.network.request.foodlog.ReadFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.foodlog.RecentMealsRequest;
import com.Cadient.QMobile.network.request.foodlog.TodayFoodLogRequest;
import com.Cadient.QMobile.network.request.foodlog.UpdateFoodLogEntriesRequest;
import com.Cadient.QMobile.network.request.user.CreateFavoriteUserMealRequest;
import com.Cadient.QMobile.network.request.user.CreateMealRatingRequest;
import com.Cadient.QMobile.network.request.user.CreateUserWeightLossRequest;
import com.Cadient.QMobile.network.request.user.CreateUserWeightRequest;
import com.Cadient.QMobile.network.request.user.DeleteFavoriteUserMealRequest;
import com.Cadient.QMobile.network.request.user.GetFavoriteUserMealRequest;
import com.Cadient.QMobile.network.request.user.ReadMealRatingRequest;
import com.Cadient.QMobile.network.request.user.ResetMealRatingRequest;
import com.Cadient.QMobile.network.request.user.UpdateMealRatingRequest;
import com.Cadient.QMobile.network.request.user.UpdateUserProfileRequest;
import com.Cadient.QMobile.network.request.user.UpdateUserWeightLossRequest;
import com.Cadient.QMobile.network.request.user.UserProfileRequest;
import com.Cadient.QMobile.network.request.weightlog.CreateWeightTrackerEntryRequest;
import com.Cadient.QMobile.network.request.weightlog.DeleteWeightTrackerEntryRequest;
import com.Cadient.QMobile.network.request.weightlog.ReadWeightTrackerEntryRequest;
import com.Cadient.QMobile.network.request.weightlog.UpdateWeightTrackerEntryRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

/**
 * Created by alexey on 15.08.14.
 */
public class DataManager {

    private SpiceManagerContainer spiceManagerContainer;

    private String lastRequestCacheKey;

    public DataManager(SpiceManagerContainer spiceManagerContainer) {
        this.spiceManagerContainer = spiceManagerContainer;
    }

    public void readDynamicServer(Callback<DynamicServer> callback) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Environment.DYNAMIC_SERVER_URL)
                .setClient(new RetrofitClient())
                .setConverter(new JacksonConverter())
                .build();
        NutrioApi service = restAdapter.create(NutrioApi.class);
        service.readDynamicServer(callback);
    }

    public RetrofitSpiceRequest readUserProfile(BaseHandler<UserProfile> handler) {
        RetrofitSpiceRequest request = new UserProfileRequest();
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public void getRegistrationResult(BaseHandler<RegistrationResult> handler) {
        spiceManagerContainer.getSpiceManager().execute(new RegistrationResultRequest(), handler);
    }

    public void createUser(UserRemote userRemote, BaseHandler<UserRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CreateUserRequest(userRemote), handler);
    }

    public void readUser(UserRemote userRemote, BaseHandler<UserRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ReadUserRequest(userRemote), handler);
    }

    public void updateUser(UserRemote userRemote, BaseHandler<UserRemote> handler) {
        RetrofitSpiceRequest request = new UpdateUserRequest(userRemote);
        spiceManagerContainer.getSpiceManager().execute(request,
                lastRequestCacheKey == null ? ((UpdateUserRequest) request).createCacheKey() : lastRequestCacheKey,
                DurationInMillis.ALWAYS_EXPIRED, handler);
    }

    public void searchUser(UserRemote userRemote, BaseHandler<UserRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new SearchUserRequest(userRemote), handler);
    }

    public void generateHandOffToken(Integer userId, BaseHandler<SessionHandOff> handler) {
        spiceManagerContainer.getSpiceManager().execute(new GenerateTokenRequest(userId), handler);
    }

    public void validateToken(SessionHandOff sessionHandOff, BaseHandler<SessionHandOff> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ValidateTokenRequest(sessionHandOff), handler);
    }

    public void calculateBmi(BmiCalculator bmiCalculator, BaseHandler<BmiCalculator> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CalculateBmiRequest(bmiCalculator), handler);
    }

    public void calculateCalorieBurn(CalorieBurnCalculator calorieBurnCalculator, BaseHandler<CalorieBurnCalculator> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CalculateCalorieBurnRequest(calorieBurnCalculator), handler);
    }

    public void calculateDailyCalorie(DailyCalorieTarget dailyCalorieTarget, BaseHandler<DailyCalorieTarget> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CalculateDailyCalorieRequest(dailyCalorieTarget), handler);
    }

    public void updateUserProfile(UserProfile profile, BaseHandler<UserProfile> handler) {
        spiceManagerContainer.getSpiceManager().execute(new UpdateUserProfileRequest(profile), handler);
    }

    public void createMealRating(MealRating mealRating, BaseHandler<MealRating> handler) {
        RetrofitSpiceRequest request = new CreateMealRatingRequest(mealRating);
        spiceManagerContainer.getSpiceManager().execute(request,
                lastRequestCacheKey == null ? ((CreateMealRatingRequest) request).createCacheKey() : lastRequestCacheKey,
                DurationInMillis.ALWAYS_EXPIRED, handler);
    }

    public void readMealRating(MealRating mealRating, BaseHandler<MealRating> handler) {
        RetrofitSpiceRequest request = new ReadMealRatingRequest(mealRating);
        spiceManagerContainer.getSpiceManager().execute(request,
                lastRequestCacheKey == null ? ((ReadMealRatingRequest) request).createCacheKey() : lastRequestCacheKey,
                DurationInMillis.ALWAYS_EXPIRED, handler);
    }

    public void updateMealRating(MealRating mealRating, BaseHandler<MealRating> handler) {
        spiceManagerContainer.getSpiceManager().execute(new UpdateMealRatingRequest(mealRating), handler);
    }

    public void resetMealRating(MealRating mealRating, BaseHandler<MealRating> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ResetMealRatingRequest(mealRating), handler);
    }

    public RetrofitSpiceRequest createFavoriteUserMeal(FavoriteUserMeal favoriteUserMeal, BaseHandler<FavoriteUserMeal> handler) {
        RetrofitSpiceRequest request = new CreateFavoriteUserMealRequest(favoriteUserMeal);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest deleteFavoriteUserMeal(FavoriteUserMeal favoriteUserMeal, BaseHandler<FavoriteUserMeal> handler) {
        RetrofitSpiceRequest request = new DeleteFavoriteUserMealRequest(favoriteUserMeal);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest readFavoriteUserMeal(FavoriteUserMeal favoriteUserMeal, BaseHandler<FavoriteUserMeal> handler) {
        RetrofitSpiceRequest request = new GetFavoriteUserMealRequest(favoriteUserMeal);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public void createUserWeightLoss(WeightLossGoalCycles weightLossGoalCycles, BaseHandler<WeightLossGoalCycles> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CreateUserWeightLossRequest(weightLossGoalCycles), handler);
    }

    public void updateUserWeightLoss(WeightLossGoalCycles weightLossGoalCycles, BaseHandler<WeightLossGoalCycles> handler) {
        spiceManagerContainer.getSpiceManager().execute(new UpdateUserWeightLossRequest(weightLossGoalCycles), handler);
    }

    public void createUserWeight(UserWeight userWeight, BaseHandler<UserWeight> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CreateUserWeightRequest(userWeight), handler);
    }

    //Meals, Recipes, Foods
    public void createFood(FoodRemote food, BaseHandler<FoodRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CreateFoodRequest(food), handler);
    }

    public void readListOfFoodDefault(BaseHandler<FoodList> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ReadListOfFoodRequest(), handler);
    }

    public void readListOfFood(FoodListReadModel readModel, BaseHandler<FoodList> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ReadListOfFoodRequest(readModel), handler);
    }

    public void readFood(Integer id, BaseHandler<FoodRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ReadFoodRequest(id), handler);
    }

    public void updateFood(FoodRemote foodRemote, BaseHandler<FoodRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new UpdateFoodRequest(foodRemote), handler);
    }

    public void deleteFood(Integer id, BaseHandler<FoodRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new DeleteFoodRequest(id), handler);
    }

    public void analyzeFood(SearchByKeyword entity, BaseHandler<RecipeRemote> handler) {
        RetrofitSpiceRequest request = new FoodAnalyzerRequest(entity);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
    }

    public void translateMealId(MealTranslator entity, BaseHandler<MealTranslator> handler) {
        spiceManagerContainer.getSpiceManager().execute(new MealIdTranslationsRequest(entity), handler);
    }

    //Weight Tracker Entries
    public void createWeightTrackerEntry(WeightTrackerEntry entry, BaseHandler<WeightTrackerEntry> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CreateWeightTrackerEntryRequest(entry), handler);
    }

    public RetrofitSpiceRequest readWeightTrackerEntry(BaseHandler<WeightTrackerEntries> handler) {
        RetrofitSpiceRequest request = new ReadWeightTrackerEntryRequest();
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public void updateWeightTrackerEntry(WeightTrackerEntry entry, BaseHandler<WeightTrackerEntry> handler) {
        spiceManagerContainer.getSpiceManager().execute(new UpdateWeightTrackerEntryRequest(entry), handler);
    }

    public void deleteWeightTrackerEntry(WeightTrackerEntry entry, BaseHandler<WeightTrackerEntry> handler) {
        spiceManagerContainer.getSpiceManager().execute(new DeleteWeightTrackerEntryRequest(entry), handler);
    }

    //Activity Log Entries
    public void createActivityLogEntries(ActivityLogEntries entries, BaseHandler<ActivityLogEntries> handler) {
        spiceManagerContainer.getSpiceManager().execute(new CreateActivityLogEntriesRequest(entries), handler);
    }

    public void updateActivityLogEntries(ActivityLogEntries entries, BaseHandler<ActivityLogEntries> handler) {
        spiceManagerContainer.getSpiceManager().execute(new UpdateActivityLogEntriesRequest(entries), handler);
    }

    public void deleteActivityLogEntries(ActivityLogEntries entries, BaseHandler<ActivityLogEntries> handler) {
        spiceManagerContainer.getSpiceManager().execute(new DeleteActivityLogEntriesRequest(entries), handler);
    }

    public RetrofitSpiceRequest getActivitiesList(Date date, BaseHandler<ActivitiesList> handler) {
        RetrofitSpiceRequest request = new ActivitiesListRequest(date);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    //Recipes
    public void searchRecipeByKeywords(SearchByKeyword entity, BaseHandler<RecipeRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new SearchRecipeByKeywordsRequest(entity), handler);
    }

    public void searchRecipeByCategories(SearchByCategory entity, BaseHandler<RecipeRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new SearchRecipeByCategoriesRequest(entity), handler);
    }

    //Food Log Entries
    public void readFoodLogEntries(FoodLogEntries entity, BaseHandler<FoodLogEntries> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ReadFoodLogEntriesRequest(entity), handler);
    }

    public RetrofitSpiceRequest createFoodLogEntries(MealForLog entity, BaseHandler<FoodLogCreatedEntry> handler) {
        RetrofitSpiceRequest request = new CreateFoodLogEntriesRequest(entity);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest updateFoodLogEntries(MealForLog entity, BaseHandler<FoodLogEntries> handler) {
        RetrofitSpiceRequest request = new UpdateFoodLogEntriesRequest(entity);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest deleteFoodLogEntries(FoodLogEntries entity, BaseHandler<FoodLogEntries> handler) {
        RetrofitSpiceRequest request = new DeleteFoodLogEntriesRequest(entity);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest obtainTodayFoodLog(Date date, BaseHandler<FoodLogCurrent> handler) {
        RetrofitSpiceRequest request = new TodayFoodLogRequest(date);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest obtainFavoriteMeals(BaseHandler<RecipeRemote> handler) {
        RetrofitSpiceRequest request = new FavoriteMealsRequest();
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest obtainFrequentMeals(BaseHandler<SuggestedMeal> handler) {
        RetrofitSpiceRequest request = new FrequentMealsRequest();
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest obtainRecentMeals(BaseHandler<SuggestedMeal> handler) {
        RetrofitSpiceRequest request = new RecentMealsRequest();
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest readFoodLogForDate(Date date, BaseHandler<FoodLogList> handler) {
        RetrofitSpiceRequest request = new FoodLogDateRequest(date);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    //Daily Calorie Bank
    public RetrofitSpiceRequest retrieveDailyCalorieBank(Date startDate, Date endDate, BaseHandler<DailyCalorieRemote> handler) {
        RetrofitSpiceRequest request = new DailyCalorieBankRequest(startDate, endDate);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    //Food Log Natural Language Parsing
    public void parsingNaturalLanguage(ParserRemote entity, BaseHandler<ParserRemote> handler) {
        spiceManagerContainer.getSpiceManager().execute(new ParseNaturalLanguageRequest(entity), handler);
    }

    //Food Log Recipe Search Autocomplete
    public RetrofitSpiceRequest suggestMealName(SuggestedMeal entity, BaseHandler<SuggestedMeal> handler) {
        RetrofitSpiceRequest request = new MealNameSuggestionsRequest(entity);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }

    public RetrofitSpiceRequest suggestActivity(String term, BaseHandler<Activity[]> handler) {
        RetrofitSpiceRequest request = new ActivitySearchAutoCompleteRequest(term);
        spiceManagerContainer.getSpiceManager().execute(request, handler);
        return request;
    }
}
