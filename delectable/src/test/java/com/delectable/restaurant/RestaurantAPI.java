package com.delectable.restaurant;

import java.util.Optional;
import com.delectable.EntityUtil;
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.json.JSONObject;
import org.junit.Test;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@WithMockUser(authorities = "USER")
public class RestaurantAPI {

        @Autowired
        private RestaurantRepository restaurantService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private EntityUtil entityUtil;

        @Autowired
        private RestaurantUtil restaurantUtil;

        private static final String apiEndpoint = "/api/restaurant/";

        String[] responseStringArray;
        MvcResult response;

        @Test
        public void createValidRestaurant() throws Exception {
                Restaurant restaurant = restaurantUtil.createValidRestaurants(1, false).get(0);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.post(apiEndpoint)
                                                .content(entityUtil.toJson(restaurant))
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                                .value(restaurant.getName()))
                                .andReturn();
        }

        @Test
        public void getRestaurantById() throws Exception {
                Long id = restaurantUtil.insertValidRestaurant(false).getId();
                response = mockMvc
                                .perform(MockMvcRequestBuilders.get(apiEndpoint + id)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                                .andReturn();
        }

        @Test
        public void getPagedRestaurants() throws Exception {
                int numberToInsert = 20;
                int numberOfPagesItemInRequest = 5;
                int page = 1;
                JSONObject jsonObj;

                restaurantUtil.insertValidRestaurants(numberToInsert, false);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.get(apiEndpoint)
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
        public void updateRestaurant() throws Exception {
                String stringUpdate = "NoName";
                Long id = restaurantUtil.insertValidRestaurant(false).getId();
                Optional<Restaurant> restaurantOpt = restaurantService.findById(id);
                Restaurant restaurant = restaurantOpt.get();
                restaurant.setName(stringUpdate);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.put(apiEndpoint + id)
                                                .content(entityUtil.toJson(restaurant))
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                                .value(stringUpdate))
                                .andReturn();
        }

        @Test
        public void deleteRestaurant() throws Exception {
                Long id = restaurantUtil.insertValidRestaurant(false).getId();
                response = mockMvc
                                .perform(MockMvcRequestBuilders.delete(apiEndpoint + id)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        }
}
