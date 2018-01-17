package filesaver.api.test.integration;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import filesaver.api.controllers.v1.FolderController;
import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.dao.models.v1.UserFileUploadSetting;
import filesaver.api.model.maker.FolderMaker;
import filesaver.api.model.maker.UserFileUploadSettingMaker;
import filesaver.api.model.maker.UserMaker;
import filesaver.api.repositories.v1.FolderRepository;
import filesaver.api.repositories.v1.UserFileUploadSettingRepository;
import filesaver.api.repositories.v1.UserRepository;
import filesaver.api.utils.v1.JsonUtils;
import filesaver.api.utils.v1.SecurityUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
  
  @Test
  public void shouldCreateSubFolder() throws Exception {
    User user = make(a(UserMaker.User));
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    Folder parentFolder = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    parentFolder.setUser(user);
    folderRepository.save(parentFolder);
    
    Folder folderToCreate = make(a(FolderMaker.Folder));
    String API = "/v1/folders/" + parentFolder.getUniqueId();

    mockMvc.perform(post(API).contentType(MediaType.APPLICATION_JSON)
      .requestAttr("principal", user)
      .content(JsonUtils.toJson(folderToCreate)))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.name").value(folderToCreate.getName()))
      .andReturn();

  }
  
  @Test
  public void shouldGetFolder() throws Exception {
    User user = make(a(UserMaker.User));
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    Folder folder = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    folder.setUser(user);
    folderRepository.save(folder);
    
    String API = "/v1/folders/" + folder.getUniqueId();

    mockMvc.perform(get(API).contentType(MediaType.APPLICATION_JSON)
      .requestAttr("principal", user))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.name").value(folder.getName()))
      .andExpect(jsonPath("$.body.uniqueId").value(folder.getUniqueId()))
      .andReturn();

  }
  
  @Test
  public void shouldGetSubFolder() throws Exception {
    User user = make(a(UserMaker.User));
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    Folder parentFolder = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    parentFolder.setUser(user);
    folderRepository.save(parentFolder);
    
    Folder subFolder = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    subFolder.setUser(user);
    subFolder.setParentFolder(parentFolder);
    folderRepository.save(subFolder);
    String API = "/v1/folders/" + subFolder.getUniqueId();

    mockMvc.perform(get(API).contentType(MediaType.APPLICATION_JSON)
      .requestAttr("principal", user))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.name").value(subFolder.getName()))
      .andExpect(jsonPath("$.body.uniqueId").value(subFolder.getUniqueId()))
      .andReturn();

  }
  
  @Test
  public void shouldGetTopFolders() throws Exception {
    User user = make(a(UserMaker.User));
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    Folder parentFolder1 = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    Folder parentFolder2 = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    parentFolder1.setUser(user);
    parentFolder2.setUser(user);
    folderRepository.save(parentFolder1);
    folderRepository.save(parentFolder2);
    
    Folder subFolder = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    subFolder.setUser(user);
    subFolder.setParentFolder(parentFolder1);
    folderRepository.save(subFolder);
    
    String API = "/v1/folders";

    mockMvc.perform(get(API).contentType(MediaType.APPLICATION_JSON)
      .requestAttr("principal", user))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.results").isArray())
      .andExpect(jsonPath("$.body.results[*]").value(Matchers.hasSize(2)))
      .andExpect(jsonPath("$.body.results[*].uniqueId").value(containsInAnyOrder(parentFolder1.getUniqueId(), parentFolder2.getUniqueId())))
      .andReturn();
  }
  
  @Test
  public void shouldGetSubFolders() throws Exception {
    User user = make(a(UserMaker.User));
    UserFileUploadSetting fileUploadSetting = make(a(UserFileUploadSettingMaker.FileUploadSetting));
    fileUploadSetting.setUser(user);
    user.setFileUploadSetting(fileUploadSetting);
    user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
    userRepository.save(user);
    
    Folder parentFolder1 = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    Folder parentFolder2 = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    parentFolder1.setUser(user);
    parentFolder2.setUser(user);
    folderRepository.save(parentFolder1);
    folderRepository.save(parentFolder2);
    
    Folder subFolder = make(a(FolderMaker.Folder, with(FolderMaker.uniqueId, 
      RandomStringUtils.randomAlphanumeric(20))));
    subFolder.setUser(user);
    subFolder.setParentFolder(parentFolder1);
    folderRepository.save(subFolder);
    
    String API = "/v1/folders/" + parentFolder1.getUniqueId() + "/subfolders";

    mockMvc.perform(get(API).contentType(MediaType.APPLICATION_JSON)
      .requestAttr("principal", user))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.status").value("success"))
      .andExpect(jsonPath("$.body.results").isArray())
      .andExpect(jsonPath("$.body.results[*]").value(Matchers.hasSize(1)))
      .andExpect(jsonPath("$.body.results[*].uniqueId").value(containsInAnyOrder(subFolder.getUniqueId())))
      .andReturn();
  }

}
