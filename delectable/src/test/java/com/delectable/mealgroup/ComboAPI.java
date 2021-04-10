package com.delectable.mealgroup;

import com.delectable.EntityUtil;
import com.delectable.combo.Combo;
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
@WithMockUser(roles = "USER")
public class ComboAPI {

  @Autowired
  private ComboUtil comboUtil;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EntityUtil entityUtil;

  final String endpoint = "/api/combo/";

  String[] responseStringArray;
  MvcResult response;

  @Test
  public void createValidCombo() throws Exception {
    Combo combo = comboUtil.createValidCombo(1, 2, false).get(0);
    response = mockMvc
        .perform(MockMvcRequestBuilders.post(endpoint).content(entityUtil.toJson(combo))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(combo.getName())).andReturn();
  }

  @Test
  @Transactional
  public void updateCombo() throws Exception {
    String nameUpdate = "NoName";
    Long id = comboUtil.insertValidCombo(2, false).getId();
    Combo combo = comboUtil.getCombo(id);
    combo.setName(nameUpdate);
    response = mockMvc
        .perform(MockMvcRequestBuilders.put(endpoint + id).content(entityUtil.toJson(combo))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(nameUpdate)).andReturn();
  }

}
