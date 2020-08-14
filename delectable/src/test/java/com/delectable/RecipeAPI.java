package com.delectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.delectable.recipe.Direction;
import com.delectable.pantry.PantryItem;
import com.delectable.pantry.PantryService;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.unit.Unit;
import com.delectable.unit.UnitService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RecipeAPI {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UnitService unitService;

	@Autowired
	private PantryService pantryService;

	@Autowired
	private MockMvc mockMvc;

	String[] responseStringArray;
	MvcResult response;

	@Before
	@After
	public void cleanup() {
		recipeService.deleteAll();
		unitService.deleteAll();
		pantryService.deleteAll();
	}

	@Test
	public void postValidRecipe() throws Exception {
		Recipe recipe = createValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/recipe").content(JsonUtil.toJson(recipe))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void getRecipeById() throws Exception {
		int id = insertValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void getAllRecipes() throws Exception {
		insertValidTestRecipes(5);
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	@Transactional
	public void updateRecipe() throws Exception {
		int id = insertValidTestRecipe();
		Optional<Recipe> recipeOpt = recipeService.findById(id);
		Recipe recipe = recipeOpt.get();
		recipe.setCookTime(1000);
		response = mockMvc
				.perform(MockMvcRequestBuilders.put("/api/recipe/" + id)
						.content(JsonUtil.toJson(recipe)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void deleteRecipe() throws Exception {
		int id = insertValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void postInvalidRecipe() throws Exception {
		Recipe recipe = createValidTestRecipe();
		recipe.setPrepTime(-1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe").content(JsonUtil.toJson(recipe))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	private Recipe createValidTestRecipe() {
		Unit unit1 = new Unit("Pound(s)");
		Unit unit2 = new Unit("Gram(s)");
		Unit unit3 = new Unit("Pinch");
		unit1 = unitService.save(unit1);
		unit2 = unitService.save(unit2);
		unit3 = unitService.save(unit3);

		PantryItem pantryItem1 = new PantryItem("Carrot");
		PantryItem pantryItem2 = new PantryItem("Onion");
		PantryItem pantryItem3 = new PantryItem("Apples");
		pantryItem1 = pantryService.save(pantryItem1);
		pantryItem2 = pantryService.save(pantryItem2);
		pantryItem3 = pantryService.save(pantryItem3);

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

	private int insertValidTestRecipe() {
		Recipe recipe = createValidTestRecipe();
		recipe = recipeService.save(recipe);
		return recipe.getId();
	}

	private void insertValidTestRecipes(int numberOfRecipes) {
		Recipe recipe;
		if (numberOfRecipes > 0) {
			for (int i = 0; i < numberOfRecipes; i++) {
				recipe = createValidTestRecipe();
				recipeService.save(recipe);
			}
		}
	}

}
