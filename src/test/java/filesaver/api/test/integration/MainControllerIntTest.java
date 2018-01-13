package filesaver.api.test.integration;

import filesaver.api.controllers.v1.MainController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Jan 2017
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
  {
    "classpath:configuration/mvc-dispatcher.xml"
  }
)
@Transactional
public class MainControllerIntTest {

  @Autowired
  private MainController controller;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void shouldWelcomeUser() throws Exception {

    String API = "/v1/";
    mockMvc.perform(get(API))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body").value("Welcome to file saver!"))
      .andReturn();

  }

}
