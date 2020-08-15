package com.delectable;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import com.delectable.pantry.PantryItem;
import com.delectable.pantry.PantryService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.json.JSONArray;
import org.junit.Test;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class PantryItemAPI {

    @Autowired
    private PantryService pantryItemService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityUtil entityUtil;

    String[] responseStringArray;
    MvcResult response;

    @Test
    public void postValidPantryItem() throws Exception {
        PantryItem pantryItem = entityUtil.createValidPantryItems(1, false, false).get(0);
        response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/pantry")
                        .content(entityUtil.toJson(pantryItem))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(pantryItem.getName()))
                .andReturn();
        assert (response.getResponse().getContentAsString() != null);
    }

    @Test
    @Transactional
    public void putPantryItem() throws Exception {
        String stringUpdate = "NoName";
        int id = entityUtil.insertValidPantryItem(false, false);
        Optional<PantryItem> pantryItemOpt = pantryItemService.findById(id);
        PantryItem pantryItem = pantryItemOpt.get();
        pantryItem.setName(stringUpdate);
        response = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/pantry/" + id)
                        .content(entityUtil.toJson(pantryItem))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(stringUpdate))
                .andReturn();
    }

    @Test
    public void deletePantryItem() throws Exception {
        int id = entityUtil.insertValidPantryItem(false, false);
        response = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/pantry/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void getPantryItem() throws Exception {
        int id = entityUtil.insertValidPantryItem(false, false);
        response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/pantry/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)).andReturn();
    }

    @Test
    public void getAllPantryItems() throws Exception {
        JSONArray jsonArr;
        entityUtil.insertValidPantryItems(10, false, false);
        response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/pantry")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        jsonArr = new JSONArray(response.getResponse().getContentAsString());
        assert (jsonArr.length() == 10);

        entityUtil.insertValidPantryItems(10, true, false);
        response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/pantry")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        jsonArr = new JSONArray(response.getResponse().getContentAsString());
        assert (jsonArr.length() == 10);
    }

    @Test
    public void getAllPantryScheduledItems() throws Exception {
        JSONArray jsonArr;
        entityUtil.insertValidPantryItems(10, false, true);
        response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/pantry").param("schedulable", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        jsonArr = new JSONArray(response.getResponse().getContentAsString());
        assert (jsonArr.length() == 10);

        entityUtil.insertValidPantryItems(10, true, true);
        response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/pantry").param("schedulable", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        jsonArr = new JSONArray(response.getResponse().getContentAsString());
        assert (jsonArr.length() == 10);
    }

}
