package com.Cadient.QMobile.model;

import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 8/25/14.
 */
public class Meal extends BaseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("compatible_units_ids")
    private ArrayList<Integer> compatibleUnits;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_id")
    private String userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("type_id")
    private int typeID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit")
    private Unit unit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serving_size_amount")
    private Float servingSizeAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("prep_time")
    private Integer prepTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serving_size")
    private Integer servingSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit_id")
    private Integer unitId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serves_for_four_meal_id")
    private String servesForFourMealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("supports_upsize")
    private Boolean supportsUpsize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("single_serving_sibling_meal_id")
    private String singleServingSiblingMealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("is_favorite")
    private Boolean isFavorite;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("is_composite")
    private Boolean isComposite;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("needs_attention")
    private Boolean needsAttention;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("external_id")
    private String externalId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_meal_favorite")
    private FavoriteUserMeal favoriteUserMeal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("recipe_nutrients")
    private List<RecipeNutrient> recipeNutrients;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Recipe> recipes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String href;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String guid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("number_of_servings")
    private Integer numberOfServings;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("prep_time_in_minutes")
    private String prepTimeInMinutes;

    public ArrayList<Integer> getCompatibleUnits() {
        return compatibleUnits;
    }

    public void setCompatibleUnits(ArrayList<Integer> compatibleUnits) {
        this.compatibleUnits = compatibleUnits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getServesForFourMealId() {
        return servesForFourMealId;
    }

    public void setServesForFourMealId(String servesForFourMealId) {
        this.servesForFourMealId = servesForFourMealId;
    }

    public Boolean getSupportsUpsize() {
        return supportsUpsize;
    }

    public void setSupportsUpsize(Boolean supportsUpsize) {
        this.supportsUpsize = supportsUpsize;
    }

    public String getSingleServingSiblingMealId() {
        return singleServingSiblingMealId;
    }

    public void setSingleServingSiblingMealId(String singleServingSiblingMealId) {
        this.singleServingSiblingMealId = singleServingSiblingMealId;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Boolean getNeedsAttention() {
        return needsAttention;
    }

    public void setNeedsAttention(Boolean needsAttention) {
        this.needsAttention = needsAttention;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public FavoriteUserMeal getFavoriteUserMeal() {
        return favoriteUserMeal;
    }

    public void setFavoriteUserMeal(FavoriteUserMeal favoriteUserMeal) {
        this.favoriteUserMeal = favoriteUserMeal;
    }

    public List<RecipeNutrient> getRecipeNutrients() {
        return recipeNutrients;
    }

    public void setRecipeNutrients(List<RecipeNutrient> recipeNutrients) {
        this.recipeNutrients = recipeNutrients;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Boolean getIsComposite() {
        return isComposite;
    }

    public void setIsComposite(Boolean isComposite) {
        this.isComposite = isComposite;
    }

    public Float getServingSizeAmount() {
        return servingSizeAmount;
    }

    public void setServingSizeAmount(Float servingSizeAmount) {
        this.servingSizeAmount = servingSizeAmount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public String getPrepTimeInMinutes() {
        return prepTimeInMinutes;
    }

    public void setPrepTimeInMinutes(String prepTimeInMinutes) {
        this.prepTimeInMinutes = prepTimeInMinutes;
    }
}
