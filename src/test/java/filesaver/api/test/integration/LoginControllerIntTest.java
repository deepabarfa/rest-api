package filesaver.api.test.integration;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import filesaver.api.controllers.v1.LoginController;
import filesaver.api.dao.models.v1.User;
import filesaver.api.dao.models.v1.UserFileUploadSetting;
import filesaver.api.model.maker.UserFileUploadSettingMaker;
import filesaver.api.model.maker.UserMaker;
import filesaver.api.repositories.v1.UserFileUploadSettingRepository;
import filesaver.api.repositories.v1.UserRepository;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.utils.v1.JsonUtils;
import filesaver.api.utils.v1.SecurityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class LoginControllerIntTest {

  @Autowired
  private LoginController controller;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserFileUploadSettingRepository userFileUploadSettingRepository;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Before
  public void init() {
    userFileUploadSettingRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Test
  public void shouldLoginWithPassword() throws Exception {
    User user = make(a(UserMaker.User));
    UserResource userToLogin = new UserResource(user.getEmailId(), user.getPassword());
    
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    String API = "/v1/login-with-password";
    mockMvc.perform(post(API).contentType(MediaType.APPLICATION_JSON)
      .content(JsonUtils.toJson(userToLogin)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.emailId").value(user.getEmailId()))
      .andExpect(jsonPath("$.body.name").value(user.getName()))
      .andReturn();

  }

}
