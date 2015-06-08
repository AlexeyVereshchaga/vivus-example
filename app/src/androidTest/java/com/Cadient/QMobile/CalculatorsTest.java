package com.Cadient.QMobile;

import com.Cadient.QMobile.model.Current;
import com.Cadient.QMobile.model.Goal;
import com.Cadient.QMobile.model.Height;
import com.Cadient.QMobile.model.Weight;
import com.Cadient.QMobile.model.remote.BmiCalculator;
import com.Cadient.QMobile.model.remote.CalorieBurnCalculator;
import com.Cadient.QMobile.model.remote.DailyCalorieTarget;
import com.Cadient.QMobile.network.request.calculator.CalculateBmiRequest;
import com.Cadient.QMobile.network.request.calculator.CalculateCalorieBurnRequest;
import com.Cadient.QMobile.network.request.calculator.CalculateDailyCalorieRequest;

/**
 * Created by alexey on 25.08.14.
 */
public class CalculatorsTest extends ApplicationTest {

    public void testBmiCalculator() throws Exception {
        BmiCalculator calculator = new BmiCalculator();
        calculator.setHeight(new Height("cm", 150));
        calculator.setWeight(new Weight("pounds", 150));
        CalculateBmiRequest calculateBmiRequest = new CalculateBmiRequest(calculator);
        calculateBmiRequest.setService(service);
        BmiCalculator bmiCalculatorReturned = calculateBmiRequest.loadDataFromNetwork();

        assertNotNull(bmiCalculatorReturned.getBmi());
    }

    public void testCalorieBurnCalculator() throws Exception {

        int birthdate = 20141212;

        CalorieBurnCalculator calorieBurnCalculator = new CalorieBurnCalculator();
        calorieBurnCalculator.setWeight(new Weight("pounds", 170));
        calorieBurnCalculator.setHeight(new Height("inches", 72));
        calorieBurnCalculator.setGender('f');
        calorieBurnCalculator.setBirthDate(birthdate);
        calorieBurnCalculator.setActivityId(281);
        calorieBurnCalculator.setDuration(31);

        CalculateCalorieBurnRequest calculateCalorieBurnRequest = new CalculateCalorieBurnRequest(calorieBurnCalculator);
        calculateCalorieBurnRequest.setService(service);
        CalorieBurnCalculator calorieBurnCalculatorReturned = calculateCalorieBurnRequest.loadDataFromNetwork();

        assertEquals(calorieBurnCalculatorReturned.getBirthDate(), birthdate);
        assertNotNull(calorieBurnCalculatorReturned.getActivity());
        assertNotNull(calorieBurnCalculatorReturned.getCaloriesBurned());
    }

    public void testDailyCalorieCalculate() throws Exception {

        Integer birthdate = 19721112;

        DailyCalorieTarget dailyCalorieTarget = new DailyCalorieTarget();
        Current current = new Current();
        current.setWeight(new Weight("pounds", 200));
        current.setHeight(new Height("inches", 64));
        current.setBirthDate(birthdate);
        current.setGender('f');
        dailyCalorieTarget.setCurrent(current);
        Goal goal = new Goal();
        goal.setWeight(new Weight("pounds", 180));
        goal.setStartDate(20130101);
        goal.setEndDate(20130630);
        dailyCalorieTarget.setGoal(goal);

        CalculateDailyCalorieRequest calculateDailyCalorieRequest = new CalculateDailyCalorieRequest(dailyCalorieTarget);
        calculateDailyCalorieRequest.setService(service);

        DailyCalorieTarget dailyCalorieTarget1Returned = calculateDailyCalorieRequest.loadDataFromNetwork();

        assertNotNull(dailyCalorieTarget1Returned.getToday().getCalories().getTarget());
        assertEquals(dailyCalorieTarget.getCurrent().getBirthDate(), birthdate);
    }
}
