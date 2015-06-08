package com.Cadient.QMobile;

import com.Cadient.QMobile.model.Current;
import com.Cadient.QMobile.model.End;
import com.Cadient.QMobile.model.Goal;
import com.Cadient.QMobile.model.Height;
import com.Cadient.QMobile.model.Name;
import com.Cadient.QMobile.model.Start;
import com.Cadient.QMobile.model.Weight;
import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.Cadient.QMobile.model.remote.MealRating;
import com.Cadient.QMobile.model.remote.UserProfile;
import com.Cadient.QMobile.model.remote.UserWeight;
import com.Cadient.QMobile.model.remote.WeightLossGoalCycles;
import com.Cadient.QMobile.network.request.user.CreateFavoriteUserMealRequest;
import com.Cadient.QMobile.network.request.user.CreateMealRatingRequest;
import com.Cadient.QMobile.network.request.user.CreateUserWeightRequest;
import com.Cadient.QMobile.network.request.user.DeleteFavoriteUserMealRequest;
import com.Cadient.QMobile.network.request.user.GetFavoriteUserMealRequest;
import com.Cadient.QMobile.network.request.user.ReadMealRatingRequest;
import com.Cadient.QMobile.network.request.user.ResetMealRatingRequest;
import com.Cadient.QMobile.network.request.user.UpdateMealRatingRequest;
import com.Cadient.QMobile.network.request.user.UpdateUserProfileRequest;
import com.Cadient.QMobile.network.request.user.UpdateUserWeightLossRequest;
import com.Cadient.QMobile.network.request.user.UserProfileRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;

import retrofit.RetrofitError;

/**
 * Created by alexey on 25.08.14.
 */
public class UserTest extends ApplicationTest {

    public void testReadUserProfile() throws Exception {
        UserProfileRequest request = new UserProfileRequest();
        request.setService(service);
        UserProfile userProfileReturned = request.loadDataFromNetwork();

        assertNotNull(userProfileReturned);
    }

    public void testUpdateUserProfile() throws Exception {

        String email = createRandomEmail();

        UserProfile userProfile = new UserProfile();
        Goal goal = new Goal();
        goal.setWeight(new Weight("pounds", 150));
        userProfile.setGoal(goal);
        Current current = new Current();
        current.setHeight(new Height("inches", 72));
        current.setWeight(new Weight("pounds", 200));
        current.setGender('f');
        current.setBirthDate(19901212);
        userProfile.setCurrent(current);
        userProfile.setEmail(email);
        userProfile.setRecommendedBucketId(265);
        userProfile.setIntegrate_plan_and_log(true);
        Name name = new Name();
        name.setFirst("Leha");
        name.setLast("Pupkin");
        userProfile.setName(name);
        userProfile.setId(447293);
        UpdateUserProfileRequest updateUserProfileRequest = new UpdateUserProfileRequest(userProfile);
        updateUserProfileRequest.setService(service);

        UserProfile userProfileReturned = updateUserProfileRequest.loadDataFromNetwork();

        assertEquals(userProfileReturned.getEmail(), email);
    }

    public void testMealRatingCreate() throws Exception {
        MealRating mealRating = mealRatingRead(true);
        if (mealRating != null) {
            mealRatingReset(mealRating);
        }
        mealRatingCreate(false);
    }

    public void testMealRatingUpdate() throws Exception {
        MealRating mealRating = mealRatingRead(true);
        if (mealRating == null) {
            mealRating = mealRatingCreate(true);
        }
        mealRatingUpdate(mealRating);
    }

    public void testMealRatingRead() throws Exception {
        mealRatingCreate(true);
        mealRatingRead(false);
    }


    private MealRating mealRatingRead(boolean softly) throws Exception {
        MealRating mealRating = new MealRating();
        mealRating.setMealId(644);

        ReadMealRatingRequest readMealRatingRequest = new ReadMealRatingRequest(mealRating);
        readMealRatingRequest.setService(service);

        try {
            mealRating = readMealRatingRequest.loadDataFromNetwork();
        } catch (RetrofitError e) {
            if (!softly) {
                throw e;
            }
            mealRating = null;
        }
        return mealRating;
    }

    /////////////////////////////////////////////////
    private MealRating mealRatingCreate(boolean softly) throws Exception {
        MealRating mealRating = new MealRating();
        mealRating.setValue(60);
        mealRating.setMealId(644);


        CreateMealRatingRequest createMealRatingRequest = new CreateMealRatingRequest(mealRating);
        createMealRatingRequest.setService(service);
        try {
            mealRating = createMealRatingRequest.loadDataFromNetwork();
        } catch (RetrofitError e) {
            if (!softly) throw e;
        }
        if (!softly) {
            //check created rating
            assertNotNull(mealRating.getId());
            assertEquals(mealRating.getMealId(), new Integer(644));
            assertEquals(mealRating.getValue(), 60);
        }
        return mealRating;

    }

    private void mealRatingUpdate(MealRating mealRating) throws Exception {
        //update rating
        mealRating.setValue(40);
        UpdateMealRatingRequest updateMealRatingRequest = new UpdateMealRatingRequest(mealRating);
        updateMealRatingRequest.setService(service);
        mealRating = updateMealRatingRequest.loadDataFromNetwork();
        //check updated rating
        assertNotNull(mealRating.getId());
        assertEquals(mealRating.getMealId(), new Integer(644));
        assertEquals(mealRating.getValue(), 40);

    }

    /**
     * delete rating
     */
    private void mealRatingReset(MealRating mealRating) throws Exception {
        ResetMealRatingRequest resetMealRatingRequest = new ResetMealRatingRequest(mealRating);
        resetMealRatingRequest.setService(service);
        try {
            resetMealRatingRequest.loadDataFromNetwork();
        } catch (SpiceException e) {
        }
    }

    public void testCreateUserMealFavorite() throws Exception {
        createUserMealFavorite();
    }

    public void testDeleteUserMealFavorite() throws Exception {
        deleteUserMealFavorite();
    }

    public void testReadUserMealFavorite() throws Exception {
        createUserMealFavorite();
        readUserMealFavorite(true);
    }

    private FavoriteUserMeal createUserMealFavorite() throws Exception {
        Integer mealId = 1233;
        FavoriteUserMeal favoriteUserMeal = readUserMealFavorite(false);
        if (favoriteUserMeal != null) {
            deleteUserMealFavorite();
        }
        favoriteUserMeal = new FavoriteUserMeal();
        favoriteUserMeal.setMealId(mealId);
        CreateFavoriteUserMealRequest createRequest = new CreateFavoriteUserMealRequest(favoriteUserMeal);
        createRequest.setService(service);
        FavoriteUserMeal favoriteUserMealReturned = createRequest.loadDataFromNetwork();

        assertEquals(mealId, favoriteUserMealReturned.getMealId());
        assertNotNull(favoriteUserMealReturned.getMeal());
        assertNull(favoriteUserMealReturned.getComments());

        return favoriteUserMealReturned;
    }

    private void deleteUserMealFavorite() throws Exception {
        FavoriteUserMeal favoriteUserMeal = readUserMealFavorite(false);
        if (favoriteUserMeal == null) {
            favoriteUserMeal = createUserMealFavorite();
        }

        DeleteFavoriteUserMealRequest deleteRequest = new DeleteFavoriteUserMealRequest(favoriteUserMeal);
        deleteRequest.setService(service);

        FavoriteUserMeal favoriteUserMealReturnned = deleteRequest.loadDataFromNetwork();

        assertEquals((Integer) 1233, favoriteUserMealReturnned.getMealId());
    }

    private FavoriteUserMeal readUserMealFavorite(boolean softly) throws Exception {
        FavoriteUserMeal favoriteUserMeal = new FavoriteUserMeal();
        favoriteUserMeal.setMealId(1233);
        FavoriteUserMeal favoriteUserMealReturned = null;
        GetFavoriteUserMealRequest getFavoriteUserMealRequest = new GetFavoriteUserMealRequest(favoriteUserMeal);
        getFavoriteUserMealRequest.setService(service);
        try {
            favoriteUserMealReturned = getFavoriteUserMealRequest.loadDataFromNetwork();
        } catch (RetrofitError error) {
            if (softly) {
                throw error;
            }
        }
        return favoriteUserMealReturned;
    }

    public void testUpdateUserLossWeight() throws Exception {
        WeightLossGoalCycles cycles = new WeightLossGoalCycles();
        cycles.setId(21039);
        Current current = new Current();
        current.setWeight(new Weight("pounds", 1000f));
        cycles.setCurrent(current);
        Goal goal = new Goal();
        Start start = new Start();
        start.setDate(20121227);
        End end = new End();
        end.setWeight(new Weight("pounds", 200f));
        end.setDate(20130627);
        goal.setStart(start);
        goal.setEnd(end);
        cycles.setGoal(goal);


        UpdateUserWeightLossRequest updateUserWeightLossRequest = new UpdateUserWeightLossRequest(cycles);
        updateUserWeightLossRequest.setService(service);

        cycles = updateUserWeightLossRequest.loadDataFromNetwork();

        assertNotNull(cycles.getId());
        assertEquals(1000f, cycles.getCurrent().getWeight().getAmount());
        assertNotNull(cycles.getGoal().getStart().getWeight().getAmount());
        assertEquals(20121227, cycles.getGoal().getStart().getDate());
        assertEquals(20130627, cycles.getGoal().getEnd().getDate());
        assertEquals(200f, cycles.getGoal().getEnd().getWeight().getAmount());
    }

    public void testCreateUserWeights() throws Exception {
        UserWeight userWeight = new UserWeight();
        userWeight.setPageNumber(1);
        userWeight.setPageSize(100);

        CreateUserWeightRequest createUserWeightRequest = new CreateUserWeightRequest(userWeight);
        createUserWeightRequest.setService(service);

        userWeight = createUserWeightRequest.loadDataFromNetwork();

        assertEquals(1, userWeight.getPageNumber());
        assertEquals(100, userWeight.getPageSize());
        assertTrue(userWeight.getUsers().length != 0);
    }
}
