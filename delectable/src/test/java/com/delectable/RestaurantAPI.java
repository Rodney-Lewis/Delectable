package com.delectable;

import java.util.Optional;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantService;
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
import org.json.JSONArray;
import org.junit.Test;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestaurantAPI {

        @Autowired
        private RestaurantService restaurantService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private EntityUtil entityUtil;

        private static final String apiEndpoint = "/api/restaurant/";

        String[] responseStringArray;
        MvcResult response;

        @Test
        public void postValidRestaurant() throws Exception {
                Restaurant restaurant = entityUtil.createValidRestaurants(1, false).get(0);
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
        @Transactional
        public void putRestaurant() throws Exception {
                String stringUpdate = "NoName";
                Long id = entityUtil.insertValidRestaurant(false);
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
                Long id = entityUtil.insertValidRestaurant(false);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.delete(apiEndpoint + id)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        }

        @Test
        public void getRestaurant() throws Exception {
                Long id = entityUtil.insertValidRestaurant(false);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.get(apiEndpoint + id)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                                .andReturn();
        }

        @Test
        public void getAllRestaurants() throws Exception {
                JSONArray jsonArr;
                entityUtil.insertValidRestaurants(10, false);
                response = mockMvc
                                .perform(MockMvcRequestBuilders.get(apiEndpoint)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
                jsonArr = new JSONArray(response.getResponse().getContentAsString());
                assert (jsonArr.length() >= 10);
        }


}
