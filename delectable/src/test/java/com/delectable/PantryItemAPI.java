package com.delectable;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PantryItemAPI {

    @Autowired
    private PantryService pantryItemService;

    @Autowired
    private MockMvc mockMvc;

    String[] responseStringArray;
    MvcResult response;

    @Before
    @After
    public void cleanup() {
        pantryItemService.deleteAll();
    }

    @Test
    public void postValidPantryItem() {
        try {
            PantryItem pantryItem = createValidPantryItems(1, false, false).get(0);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/pantry")
                            .content(JsonUtil.toJson(pantryItem))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(pantryItem.getName()))
                    .andReturn();
            assert (response.getResponse().getContentAsString() != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void putPantryItem() {
        try {
            String stringUpdate = "NoName";
            int id = insertValidPantryItem(false, false);
            Optional<PantryItem> pantryItemOpt = pantryItemService.findById(id);
            PantryItem pantryItem = pantryItemOpt.get();
            pantryItem.setName(stringUpdate);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.put("/api/pantry/" + id)
                            .content(JsonUtil.toJson(pantryItem))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(stringUpdate))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePantryItem() {
        try {
            int id = insertValidPantryItem(false, false);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.delete("/api/pantry/" + id)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPantryItem() {
        try {
            int id = insertValidPantryItem(false, false);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/pantry/" + id)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllPantryItems() {
        try {
            JSONArray jsonArr;
            insertValidPantryItems(10, false, false);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/pantry")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            jsonArr = new JSONArray(response.getResponse().getContentAsString());
            assert (jsonArr.length() == 10);

            insertValidPantryItems(10, true, false);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/pantry")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            jsonArr = new JSONArray(response.getResponse().getContentAsString());
            assert (jsonArr.length() == 10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllPantryScheduledItems() {
        try {
            JSONArray jsonArr;
            insertValidPantryItems(10, false, true);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/pantry").param("schedulable", "true")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            jsonArr = new JSONArray(response.getResponse().getContentAsString());
            assert (jsonArr.length() == 10);

            insertValidPantryItems(10, true, true);
            response = mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/pantry").param("schedulable", "true")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            jsonArr = new JSONArray(response.getResponse().getContentAsString());
            assert (jsonArr.length() == 10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertValidPantryItems(int numberOfItemsToInsert, boolean markAsDeleted,
            boolean markAsSchedulable) {
        List<PantryItem> pantryItems = new ArrayList<PantryItem>();
        pantryItems.addAll(
                createValidPantryItems(numberOfItemsToInsert, markAsDeleted, markAsSchedulable));
        pantryItemService.saveAll(pantryItems);
    }

    private int insertValidPantryItem(boolean markAsDeleted, boolean markAsSchedulable) {
        PantryItem pantryItem =
                (createValidPantryItems(1, markAsDeleted, markAsSchedulable).get(0));
        pantryItem = pantryItemService.save(pantryItem);
        return pantryItem.getId();
    }

    private List<PantryItem> createValidPantryItems(int numberOfItemsToCreate,
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
}
