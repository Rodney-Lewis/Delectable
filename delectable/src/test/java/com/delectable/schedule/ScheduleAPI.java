package com.delectable.schedule;

import java.time.LocalDate;
import java.util.List;
import com.delectable.EntityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@WithMockUser(roles = "USER")
public class ScheduleAPI {

  @Autowired
  private ScheduleUtil scheduleUtil;

  @Autowired
  private EntityUtil entityUtil;

  @Autowired
  private MockMvc mockMvc;

  String[] responseStringArray;
  MvcResult response;

  private final String endpoint = "/api/schedule/";

  @Test
  public void createSchedule() throws Exception {
    List<Schedule> scheduledItems = scheduleUtil.createListOfScheduledEntities(false);
    response = mockMvc
        .perform(MockMvcRequestBuilders.post(endpoint).content(entityUtil.toJson(scheduledItems))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
  }

  @Test
  public void getScheduled() throws Exception {
    LocalDate beginDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();
    endDate.plusDays(7);
    
    response = mockMvc
        .perform(MockMvcRequestBuilders.get(endpoint)
            .param("begin", String.valueOf(beginDate.toString()))
            .param("end", String.valueOf(endDate.toString())))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();;
  }
}
