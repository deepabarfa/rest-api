package filesaver.api.controllers.v1;

import static filesaver.api.enums.v1.ApiStatus.success;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.UnAuthorizeException;
import filesaver.api.models.v1.ApiResponse;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.services.v1.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Controller
@RequestMapping("/v1")
public class LoginController {
  
  private final LoginService loginService;
  
  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }
  
  @RequestMapping(method = POST, value = "/login-with-password")
  public ResponseEntity<ApiResponse> loginWithPassword(@RequestBody UserResource userResource) throws InvalidRequestException, InvalidParameterException, UnAuthorizeException {
    UserResource createdUser = loginService.loginWithPassword(userResource);
    return new ResponseEntity<>(new ApiResponse(createdUser, success), HttpStatus.OK);
  }
  
}
