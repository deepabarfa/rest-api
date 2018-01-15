package filesaver.api.test.integration;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import filesaver.api.controllers.v1.FolderController;
import filesaver.api.controllers.v1.SignupController;
import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.dao.models.v1.UserFileUploadSetting;
import filesaver.api.model.maker.FolderMaker;
import filesaver.api.model.maker.UserFileUploadSettingMaker;
import filesaver.api.model.maker.UserMaker;
import filesaver.api.repositories.v1.FolderRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
public class FolderControllerIntTest {

  @Autowired
  private FolderController controller;

  @Autowired
  private FolderRepository folderRepository;

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
    folderRepository.deleteAll();
    userFileUploadSettingRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Test
  public void shouldCreateFolder() throws Exception {
    User user = make(a(UserMaker.User));
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    Folder folderToCreate = make(a(FolderMaker.Folder));
    String API = "/v1/folders";
    mockMvc.perform(post(API).contentType(MediaType.APPLICATION_JSON)
      .requestAttr("principal", user)
      .content(JsonUtils.toJson(folderToCreate)))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.name").value(folderToCreate.getName()))
      .andReturn();

  }

}
