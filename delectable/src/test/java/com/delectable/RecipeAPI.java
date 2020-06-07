package com.delectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.delectable.ingredient.Ingredient;
import com.delectable.meal.Instruction;
import com.delectable.meal.recipe.Recipe;
import com.delectable.meal.recipe.RecipeService;
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
        private RecipeService recipseService;

        @Autowired
        private MockMvc mockMvc;

        String[] responseStringArray;
        MvcResult response;

		@Before
        @After
        public void cleanup() {
            recipseService.deleteAll();
        }

        @Test
        public void postValidRecipe() throws Exception {
            Recipe recipe = createValidTestRecipe();
            response = mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe")
                			.content(JsonUtil.toJson(recipe))
                            .contentType(MediaType.APPLICATION_JSON))
                            .andDo(MockMvcResultHandlers.print())
                            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            assert (response.getResponse().getContentAsString() != null);
        }

        @Test
        public void getRecipeById() throws Exception {
        int id = insertValidTestRecipe();
                response = mockMvc.perform(MockMvcRequestBuilders.get("/api/recipe/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                assert (response.getResponse().getContentAsString() != null);
        }

        @Test
        public void getAllRecipes() throws Exception {
                insertValidTestRecipe();
                insertValidTestRecipe();
                response = mockMvc.perform(MockMvcRequestBuilders.get("/api/recipe")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
								.andReturn();
				assert (response.getResponse().getContentAsString() != null);
		}
		
		@Test
		@Transactional
		public void updateRecipe() throws Exception {
			int id = insertValidTestRecipe();
			Optional<Recipe> recipeOpt = recipseService.findById(id);
			Recipe recipe = recipeOpt.get();
			recipe.setCookTimeHour(10);
			recipe.setCookTimeMinute(10);
			recipe.setCookTimeSecond(10);

			response = mockMvc.perform(MockMvcRequestBuilders.put("/api/recipe/" + id)
                			.content(JsonUtil.toJson(recipe))
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andReturn();
            assert (response.getResponse().getContentAsString() != null);
		}

		@Test
		public void deleteRecipe() throws Exception {
			int id = insertValidTestRecipe();
            response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/recipe/" + id)
                        	.contentType(MediaType.APPLICATION_JSON))
                            .andDo(MockMvcResultHandlers.print())
                            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            assert (response.getResponse().getContentAsString() != null);
		}

        @Test
        public void postInvalidRecipe() throws Exception {
                Recipe recipe = new Recipe();
                recipe.setPrepTimeHour(-1);
                recipe.setPrepTimeMinute(-1);
                recipe.setPrepTimeSecond(-1);
                recipe.setCookTimeHour(-1);
                recipe.setCookTimeMinute(-1);
                recipe.setCookTimeSecond(-1);

                mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe")
                                                .content(JsonUtil.toJson(recipe))
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print()).andReturn();
        }

        private Recipe createValidTestRecipe() {
                List<Instruction> instructions = new ArrayList<>();
                instructions.add(new Instruction(1, "1"));
                instructions.add(new Instruction(2, "2"));
                instructions.add(new Instruction(3, "3"));

                List<Ingredient> ingredients = new ArrayList<>();
                ingredients.add(new Ingredient("A", "1", "Tablespoon"));
                ingredients.add(new Ingredient("B", "2", "Tablespoon"));
                ingredients.add(new Ingredient("C", "3", "Tablespoon"));

                Recipe recipe = new Recipe("Chicken", 0, 0, 0, 0, 0, 0, "", "Description",
                                "Home kitchen", ingredients, instructions);

                return recipe;
        }

        private int insertValidTestRecipe() {
                Recipe recipe = createValidTestRecipe();
                recipe = recipseService.save(recipe);
                return recipe.getId();
        }

}
