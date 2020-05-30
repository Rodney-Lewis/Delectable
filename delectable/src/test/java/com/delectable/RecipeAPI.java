package com.delectable;

import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeController;
import com.delectable.recipe.RecipeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.minidev.json.JSONObject;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeAPI {

    @MockBean
    private RecipeService recipseService;

    @Autowired
    private RecipeController recipeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createValidRecipe() throws Exception {
        JSONObject obj = new JSONObject();
        obj.appendField("name", "Chicken");
        obj.appendField("source", "Home kitchen");
        obj.appendField("prepTimeHour", 0);
        obj.appendField("prepTimeMinute", 0);
        obj.appendField("prepTimeSecond", 0);
        obj.appendField("cookTimeHour", 0);
        obj.appendField("cookTimeMinute", 0);
        obj.appendField("cookTimeSecond", 0);
        obj.appendField("imageSource", "");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe")
        .content(obj.toJSONString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createInvalidRecipeNoName() throws Exception {
        JSONObject obj = new JSONObject();
        obj.appendField("source", "Home kitchen");
        obj.appendField("prepTimeHour", 0);
        obj.appendField("prepTimeMinute", 0);
        obj.appendField("prepTimeSecond", 0);
        obj.appendField("cookTimeHour", 0);
        obj.appendField("cookTimeMinute", 0);
        obj.appendField("cookTimeSecond", 0);
        obj.appendField("imageSource", "");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/recipe")
        .content(obj.toJSONString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
