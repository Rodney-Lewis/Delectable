package com.delectable.schedule;

import java.util.Calendar;
import java.util.Date;
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
@WithMockUser(authorities = "USER")
public class ScheduleAPI {

    @Autowired
    private ScheduleUtil scheduleUtil;

    @Autowired
    private EntityUtil entityUtil;

    @Autowired
    private MockMvc mockMvc;

    String[] responseStringArray;
    MvcResult response;

    @Test
    public void createRecipeSchedule() throws Exception {
        Schedule schedule = scheduleUtil.createScheduleForRecipe(false);
        response = mockMvc.perform(MockMvcRequestBuilders.post("/api/schedule")
                .content(entityUtil.toJson(schedule)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void createRestaurantSchedule() throws Exception {
        Schedule schedule = scheduleUtil.createScheduleForRestaurant(false);
        response = mockMvc.perform(MockMvcRequestBuilders.post("/api/schedule")
                .content(entityUtil.toJson(schedule)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void createMealGroupSchedule() throws Exception {
        Schedule schedule = scheduleUtil.createScheduleForMealGroup(false);
        response = mockMvc.perform(MockMvcRequestBuilders.post("/api/schedule")
                .content(entityUtil.toJson(schedule)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void getScheduled() throws Exception {
        Calendar begin = Calendar.getInstance();
        begin.add(Calendar.HOUR, -1);
        Calendar end = Calendar.getInstance();
        end.add(Calendar.HOUR, 1);

        response = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/schedule")
                        .param("begin", String.valueOf(begin.getTimeInMillis()))
                        .param("end", String.valueOf(end.getTimeInMillis())))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();;
    }


}
