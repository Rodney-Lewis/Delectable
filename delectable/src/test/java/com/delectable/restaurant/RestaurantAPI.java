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
@WithMockUser(roles = "USER")
public class RestaurantAPI {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EntityUtil entityUtil;

  @Autowired
  private RestaurantUtil restaurantUtil;

  private final String endpoint = "/api/restaurant/";

  String[] responseStringArray;
  MvcResult response;

  @Test
  public void createValidRestaurant() throws Exception {
    Restaurant restaurant = restaurantUtil.createValidRestaurants(1, false).get(0);
    response = mockMvc
        .perform(MockMvcRequestBuilders.post(endpoint).content(entityUtil.toJson(restaurant))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(restaurant.getName()))
        .andReturn();
  }

  @Test
  @Transactional
  public void updateRestaurant() throws Exception {
    String stringUpdate = "NoName";
    Long id = restaurantUtil.insertValidRestaurant(false).getId();
    Restaurant restaurant = restaurantUtil.getRestaurant(id);
    restaurant.setName(stringUpdate);
    response = mockMvc
        .perform(MockMvcRequestBuilders.put(endpoint + id).content(entityUtil.toJson(restaurant))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(stringUpdate)).andReturn();
  }
}
