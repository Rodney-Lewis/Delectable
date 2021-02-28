package com.delectable.mealgroup;

import java.util.ArrayList;
import java.util.List;
import com.delectable.combo.Combo;
import com.delectable.combo.ComboRepository;
import com.delectable.recipe.RecipeUtil;
import com.delectable.restaurant.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MealGroupUtil {
    
    @Autowired
    private ComboRepository mealGroupService;

    @Autowired
    private RecipeUtil recipeUtil;

    @Autowired
    private RestaurantUtil restaurantUtil;

    public List<Combo> createValidMealGroups(int numberOfMealGroupsToCreate,
            int recipesPerGroup, int restaurantsPerGroup, boolean markAsDeleted) {

        List<Combo> mealGroups = new ArrayList<Combo>();

        for (int i = 0; i < numberOfMealGroupsToCreate; i++) {
            mealGroups.add(new Combo("Test" + i,
                    recipeUtil.insertValidTestRecipes(recipesPerGroup, false),
                    restaurantUtil.insertValidRestaurants(restaurantsPerGroup, false)));
            if (markAsDeleted == true) {
                mealGroups.get(i).setDeleted(true);
            }
        }
        return mealGroups;
    }

    public void insetValidMealGroups(int numberOfMealGroupsToCreate, int recipesPerGroup,
            int restaurantsPerGroup, boolean markAsDeleted) {
        List<Combo> mealGroups = new ArrayList<Combo>();
        mealGroups.addAll(createValidMealGroups(numberOfMealGroupsToCreate, recipesPerGroup,
                restaurantsPerGroup, markAsDeleted));
        mealGroupService.saveAll(mealGroups);
    }

    public Combo insertValidMealGroup(int recipesPerGroup, int restaurantsPerGroup,
            boolean markAsDeleted) {
        Combo mealGroup =
                createValidMealGroups(1, recipesPerGroup, restaurantsPerGroup, markAsDeleted)
                        .get(0);
        mealGroup = mealGroupService.save(mealGroup);
        return mealGroup;
    }
}
