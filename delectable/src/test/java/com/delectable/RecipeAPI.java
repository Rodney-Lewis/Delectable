package com.delectable;


import java.util.Optional;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
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
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RecipeAPI {

	@Autowired
    private static RecipeService recipeService;

	@Autowired
	private MockMvc mockMvc;

	String[] responseStringArray;
	MvcResult response;

	@Test
	public void postValidRecipe() throws Exception {
		Recipe recipe = EntityUtil.createValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/recipe").content(JsonUtil.toJson(recipe))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void getRecipeById() throws Exception {
		int id = EntityUtil.insertValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void getAllRecipes() throws Exception {
		EntityUtil.insertValidTestRecipes(5);
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
		int id = EntityUtil.insertValidTestRecipe();
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
		int id = EntityUtil.insertValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void postInvalidRecipe() throws Exception {
		Recipe recipe = EntityUtil.createValidTestRecipe();
		recipe.setPrepTime(-1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe").content(JsonUtil.toJson(recipe))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
