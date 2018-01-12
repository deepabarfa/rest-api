package filesaver.api.test.integration;

import filesaver.api.controllers.v1.SignupController;
import filesaver.api.dao.models.v1.User;
import filesaver.api.model.maker.UserMaker;
import filesaver.api.resources.v1.UserResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static com.natpryce.makeiteasy.MakeItEasy.*;
import filesaver.api.repositories.v1.UserRepository;
import filesaver.api.utils.v1.JsonUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 11 Jan 2017
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
public class SignupControllerIntTest {

  @Autowired
  private SignupController controller;

  @Autowired
  private UserRepository userRepository;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Before
  public void init() {
    userRepository.deleteAll();
  }

  @Test
  public void shouldSignupUser() throws Exception {
    User user = make(a(UserMaker.User));
    UserResource userToSignup = new UserResource(user);
    String API = "/v1/signup";
    mockMvc.perform(post(API).contentType(MediaType.APPLICATION_JSON)
      .content(JsonUtils.toJson(userToSignup)))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.emailId").value(user.getEmailId()))
      .andExpect(jsonPath("$.body.name").value(user.getName()))
      .andReturn();

  }
}
