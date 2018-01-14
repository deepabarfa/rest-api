package filesaver.api.test.service;

import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.AlreadyExistException;
import filesaver.api.model.maker.UserMaker;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.services.v1.SignupService;
import filesaver.api.services.v1.UserService;
import filesaver.api.utils.v1.MessageUtils;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static com.natpryce.makeiteasy.MakeItEasy.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 11 Jan 2017
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupServiceTest extends TestCase {
  
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  
  @InjectMocks
  private SignupService service;
  
  @Mock
  private UserService userService;
  
  @Mock
  private MessageUtils messageUtils;
  
  @Test
  public void shouldThrowAlreadyExistException() throws Exception {
    User user = make(a(UserMaker.User));
    when(userService.doesEmailIdExist(user.getEmailId())).thenReturn(true);
    when(messageUtils.t("error.user.alreadyExist")).thenReturn(String.format("User with email id %s already exist.", user.getEmailId()));
    expectedException.expect(AlreadyExistException.class);
    service.signup(new UserResource(user));
    verify(userService, never()).saveUser(any(User.class));
  }
  
}
