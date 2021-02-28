package com.delectable.mealgroup;

import java.util.Optional;
import com.delectable.EntityUtil;
import com.delectable.combo.Combo;
import com.delectable.combo.ComboRepository;
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
public class MealGroupAPI {

        @Autowired
        private MealGroupUtil mealGroupUtil;

        @Autowired
        private ComboRepository mealGroupService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private EntityUtil entityUtil;

        String[] responseStringArray;
        MvcResult response;

        @Test
        public void createValidMealGroup() throws Exception {
                Combo mealGroup = mealGroupUtil.createValidMealGroups(1, 2, 2, false).get(0);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.post("/api/mealgroup")
                                                .content(entityUtil.toJson(mealGroup))
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                                .value(mealGroup.getName()))
                                .andReturn();
        }

        @Test
        public void getMealGroupById() throws Exception {
                Long id = mealGroupUtil.insertValidMealGroup(2, 2, false).getId();
                response = mockMvc
                                .perform(MockMvcRequestBuilders.get("/api/mealgroup/" + id)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                                .andReturn();
        }

        @Test
        public void getPagedMealGroups() throws Exception {
                int numberToInsert = 20;
                int numberOfPagesItemInRequest = 5;
                int page = 1;
                JSONObject jsonObj;

                mealGroupUtil.insetValidMealGroups(numberToInsert, 2, 2, false);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.get("/api/mealgroup/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .param("page", "1")
                                                .param("size", String.valueOf(
                                                                numberOfPagesItemInRequest)))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.page").value(page))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.size")
                                                .value(numberOfPagesItemInRequest))
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                jsonObj = new JSONObject(response.getResponse().getContentAsString());
                assert (jsonObj.optJSONArray("content").length() == numberOfPagesItemInRequest);
        }

        @Test
        @Transactional
        public void updateMealGroup() throws Exception {
                String nameUpdate = "NoName";
                Long id = mealGroupUtil.insertValidMealGroup(2, 2, false).getId();
                Optional<Combo> mealGroupOpt = mealGroupService.findById(id);
                Combo mealGroup = mealGroupOpt.get();
                mealGroup.setName(nameUpdate);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.put("/api/mealgroup/" + id)
                                                .content(entityUtil.toJson(mealGroup))
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                                .value(nameUpdate))
                                .andReturn();
        }

        @Test
        public void deleteMealGroup() throws Exception {
                Long id = mealGroupUtil.insertValidMealGroup(2, 2, false).getId();
                response = mockMvc
                                .perform(MockMvcRequestBuilders.delete("/api/mealgroup/" + id)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                assert (response.getResponse().getContentAsString() != null);
        }


}
