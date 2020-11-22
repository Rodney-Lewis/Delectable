package com.delectable;

import java.util.Optional;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import org.json.JSONArray;
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
	private RecipeService recipeService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EntityUtil entityUtil;

	String[] responseStringArray;
	MvcResult response;

	@Test
	public void postValidRecipe() throws Exception {
		Recipe recipe = entityUtil.createValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/recipe")
						.content(entityUtil.toJson(recipe)).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(recipe.getName()))
				.andReturn();
	}

	@Test
	public void getRecipeById() throws Exception {
		Long id = entityUtil.insertValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)).andReturn();
	}

	@Test
	public void getAllRecipes() throws Exception {
		JSONArray jsonArr;
		entityUtil.insertValidTestRecipes(10, false);
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		jsonArr = new JSONArray(response.getResponse().getContentAsString());
		assert (jsonArr.length() == 10);

		entityUtil.insertValidTestRecipes(10, true);
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		jsonArr = new JSONArray(response.getResponse().getContentAsString());
		assert (jsonArr.length() == 10);
	}

	@Test
	@Transactional
	public void putToUpdateRecipe() throws Exception {
		String nameUpdate = "NoName";
		Long id = entityUtil.insertValidTestRecipe();
		Optional<Recipe> recipeOpt = recipeService.findById(id);
		Recipe recipe = recipeOpt.get();
		recipe.setName(nameUpdate);
		response = mockMvc
				.perform(MockMvcRequestBuilders.put("/api/recipe/" + id)
						.content(entityUtil.toJson(recipe)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(nameUpdate)).andReturn();
	}

	@Test
	public void deleteRecipe() throws Exception {
		Long id = entityUtil.insertValidTestRecipe();
		response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

	@Test
	public void postInvalidRecipe() throws Exception {
		Recipe recipe = entityUtil.createValidTestRecipe();
		recipe.setPrepTimeHour((short) -1);
		recipe.setPrepTimeSecond((byte) -1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe")
				.content(entityUtil.toJson(recipe)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
