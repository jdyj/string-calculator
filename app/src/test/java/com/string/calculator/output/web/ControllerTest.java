package com.string.calculator.output.web;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.string.calculator.web.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest({Controller.class})
class ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void controllerTest() throws Exception {
    ResultActions result = mockMvc.perform(get("/result"));
    result.andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().string(equalTo("123412")));
  }

}