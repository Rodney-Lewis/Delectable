package com.delectable.recipe;

import java.util.Optional;
import com.delectable.EntityUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
@WithMockUser(authorities = "USER")
public class RecipeAPI {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EntityUtil entityUtil;

	@Autowired
	private RecipeUtil recipeUtil;

	String[] responseStringArray;
	MvcResult response;

	@Test
	public void createValidRecipe() throws Exception {
		Recipe recipe = recipeUtil.createValidTestRecipes(1, false).get(0);
		response = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/recipe")
						.content(entityUtil.toJson(recipe)).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(recipe.getName()))
				.andReturn();
	}


	@Test
	public void createInvalidRecipe() throws Exception {
		Recipe recipe = recipeUtil.createValidTestRecipes(1, false).get(0);
		recipe.setPrepTimeHour((short) -1);
		recipe.setPrepTimeSecond((byte) -1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe")
				.content(entityUtil.toJson(recipe)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	@Test
	public void getRecipeById() throws Exception {
		Long id = recipeUtil.insertValidTestRecipe(false).getId();
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)).andReturn();
	}

	@Test
	public void getPagedRecipes() throws Exception {
		int numberToInsert = 20;
		int numberOfPagesItemInRequest = 5;
		int page = 1;
		JSONObject jsonObj;

		recipeUtil.insertValidTestRecipes(numberToInsert, false);
		response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/recipe/")
						.contentType(MediaType.APPLICATION_JSON).param("page", "1")
						.param("size", String.valueOf(numberOfPagesItemInRequest)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.page").value(page))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.size").value(numberOfPagesItemInRequest))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		jsonObj = new JSONObject(response.getResponse().getContentAsString());
		assert (jsonObj.optJSONArray("content").length() == numberOfPagesItemInRequest);
	}

	@Test
	@Transactional
	public void updateRecipe() throws Exception {
		String nameUpdate = "NoName";
		Long id = recipeUtil.insertValidTestRecipe(false).getId();
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
		Long id = recipeUtil.insertValidTestRecipe(false).getId();
		response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/recipe/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assert (response.getResponse().getContentAsString() != null);
	}

}
