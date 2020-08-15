package com.delectable;

import java.util.List;
import java.util.ArrayList;
import com.delectable.pantry.PantryItem;
import com.delectable.pantry.PantryService;
import com.delectable.recipe.Direction;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.unit.Unit;
import com.delectable.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityUtil {

    @Autowired
    private static PantryService pantryItemService;

    @Autowired
    private static RecipeService recipeService;

    @Autowired
    private static UnitService unitService;


    static void insertValidPantryItems(int numberOfItemsToInsert, boolean markAsDeleted,
            boolean markAsSchedulable) {
        List<PantryItem> pantryItems = new ArrayList<PantryItem>();
        pantryItems.addAll(
                createValidPantryItems(numberOfItemsToInsert, markAsDeleted, markAsSchedulable));
        pantryItemService.saveAll(pantryItems);
    }

    static int insertValidPantryItem(boolean markAsDeleted, boolean markAsSchedulable) {
        PantryItem pantryItem =
                (createValidPantryItems(1, markAsDeleted, markAsSchedulable).get(0));
        pantryItem = pantryItemService.save(pantryItem);
        return pantryItem.getId();
    }

    static List<PantryItem> createValidPantryItems(int numberOfItemsToCreate,
            boolean markAsDeleted, boolean markAsSchedulable) {
        List<PantryItem> pantryItems = new ArrayList<PantryItem>();
        for (int i = 0; i < numberOfItemsToCreate; i++) {
            pantryItems.add(new PantryItem("Name" + i, "Brand" + i));

            if (markAsDeleted == true) {
                pantryItems.get(i).setDeleted(true);
            }

            if (markAsSchedulable == true) {
                pantryItems.get(i).setSchedulable(true);
            }
        }
        return pantryItems;
    }

    static Recipe createValidTestRecipe() {
		Unit unit1 = new Unit("Pound(s)");
		Unit unit2 = new Unit("Gram(s)");
		Unit unit3 = new Unit("Pinch");
		unit1 = unitService.save(unit1);
		unit2 = unitService.save(unit2);
		unit3 = unitService.save(unit3);

		PantryItem pantryItem1 = new PantryItem("Carrot");
		PantryItem pantryItem2 = new PantryItem("Onion");
		PantryItem pantryItem3 = new PantryItem("Apples");
		pantryItem1 = pantryItemService.save(pantryItem1);
		pantryItem2 = pantryItemService.save(pantryItem2);
		pantryItem3 = pantryItemService.save(pantryItem3);

		List<Direction> instructions = new ArrayList<>();
		instructions.add(new Direction(1, "Step 1"));
		instructions.add(new Direction(2, "Step 2"));
		instructions.add(new Direction(3, "Step 3"));
		instructions.add(new Direction(4, "Step 4"));
		instructions.add(new Direction(5, "Step 5"));

		Recipe recipe =
				new Recipe("Recipe1", "Kitchen", "Description", 3600000, 3600000, instructions, "");
		recipe.addIngredientFromPantry(pantryItem1, "0", unit1);
		recipe.addIngredientFromPantry(pantryItem2, "1", unit2);
		recipe.addIngredientFromPantry(pantryItem3, "2", unit3);
		return recipe;
	}

	static int insertValidTestRecipe() {
		Recipe recipe = createValidTestRecipe();
		recipe = recipeService.save(recipe);
		return recipe.getId();
	}

	static void insertValidTestRecipes(int numberOfRecipes) {
		Recipe recipe;
		if (numberOfRecipes > 0) {
			for (int i = 0; i < numberOfRecipes; i++) {
				recipe = createValidTestRecipe();
				recipeService.save(recipe);
			}
		}
	}
    
}