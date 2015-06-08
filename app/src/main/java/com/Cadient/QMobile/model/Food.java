package com.Cadient.QMobile.model;

import com.Cadient.QMobile.model.remote.FavoriteUserMeal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 01.09.14.
 */
public class Food extends BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_id")
    private Integer mealId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_id")
    private Integer userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("food_nutrients")
    private List<FoodNutrient> foodNutrientList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("unit")
    private Unit unit;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("type_id")
    private Integer typeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonProperty("deleted_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deletedAt;
    @JsonProperty("created_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt;
    @JsonProperty("updated_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long upc;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("servings_per_container")
    private Float servingsPerContainer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serving_size_amount")
    private Float servingSizeAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serving_size_amount_raw")
    private String servingSizeAmountRaw;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("serving_size_unit_id")
    private Integer servingSizeUnitId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("weight_per_serving_amount")
    private Float weightPerServingAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("weight_per_serving_unit_id")
    private Integer weightPerServingUnitId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("volume_per_serving_amount")
    private Float volumePerServingAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("volume_per_serving_unit_id")
    private Integer volumePerServingUnitId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("safe_to_edit")
    private Boolean safeToEdit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("needs_attention")
    private Boolean needsAttention;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("compatible_units_ids")
    private List<Integer> compatibleUnitsIds;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("meal_wrapper_id")
    private Integer mealWrapperId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_meal_favorite")
    private FavoriteUserMeal userMealFavorite;

    public FavoriteUserMeal getUserMealFavorite() {
        return userMealFavorite;
    }

    public void setUserMealFavorite(FavoriteUserMeal userMealFavorite) {
        this.userMealFavorite = userMealFavorite;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getMealWrapperId() {
        return mealWrapperId;
    }

    public void setMealWrapperId(Integer mealWrapperId) {
        this.mealWrapperId = mealWrapperId;
    }

    public List<FoodNutrient> getFoodNutrientList() {
        return foodNutrientList;
    }

    public void setFoodNutrientList(List<FoodNutrient> foodNutrientList) {
        this.foodNutrientList = foodNutrientList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getUpc() {
        return upc;
    }

    public void setUpc(Long upc) {
        this.upc = upc;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Float getServingsPerContainer() {
        return servingsPerContainer;
    }

    public void setServingsPerContainer(Float servingsPerContainer) {
        this.servingsPerContainer = servingsPerContainer;
    }

    public String getServingSizeAmountRaw() {
        return servingSizeAmountRaw;
    }

    public void setServingSizeAmountRaw(String servingSizeAmountRaw) {
        this.servingSizeAmountRaw = servingSizeAmountRaw;
    }

    public Integer getServingSizeUnitId() {
        return servingSizeUnitId;
    }

    public void setServingSizeUnitId(Integer servingSizeUnitId) {
        this.servingSizeUnitId = servingSizeUnitId;
    }

    public Float getWeightPerServingAmount() {
        return weightPerServingAmount;
    }

    public void setWeightPerServingAmount(Float weightPerServingAmount) {
        this.weightPerServingAmount = weightPerServingAmount;
    }

    public Integer getWeightPerServingUnitId() {
        return weightPerServingUnitId;
    }

    public void setWeightPerServingUnitId(Integer weightPerServingUnitId) {
        this.weightPerServingUnitId = weightPerServingUnitId;
    }

    public Float getVolumePerServingAmount() {
        return volumePerServingAmount;
    }

    public void setVolumePerServingAmount(Float volumePerServingAmount) {
        this.volumePerServingAmount = volumePerServingAmount;
    }

    public Integer getVolumePerServingUnitId() {
        return volumePerServingUnitId;
    }

    public void setVolumePerServingUnitId(Integer volumePerServingUnitId) {
        this.volumePerServingUnitId = volumePerServingUnitId;
    }

    public Boolean getSafeToEdit() {
        return safeToEdit;
    }

    public void setSafeToEdit(Boolean safeToEdit) {
        this.safeToEdit = safeToEdit;
    }

    public Boolean getNeedsAttention() {
        return needsAttention;
    }

    public void setNeedsAttention(Boolean needsAttention) {
        this.needsAttention = needsAttention;
    }

    public List<Integer> getCompatibleUnitsIds() {
        return compatibleUnitsIds;
    }

    public void setCompatibleUnitsIds(List<Integer> compatibleUnitsIds) {
        this.compatibleUnitsIds = compatibleUnitsIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getServingSizeAmount() {
        return servingSizeAmount;
    }

    public void setServingSizeAmount(Float servingSizeAmount) {
        this.servingSizeAmount = servingSizeAmount;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
