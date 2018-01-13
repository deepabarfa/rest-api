package filesaver.api.controllers.v1;

import com.fasterxml.jackson.annotation.JsonView;
import static filesaver.api.enums.v1.ApiStatus.success;
import filesaver.api.exceptions.v1.AlreadyExistException;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.models.v1.ApiResponse;
import filesaver.api.resources.v1.UserResource;
import filesaver.api.services.v1.SignupService;
import filesaver.api.utils.v1.JsonViews;
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
@RequestMapping("/v1/signup")
public class SignupController {
  
  private final SignupService signupService;
  
  @Autowired
  public SignupController(SignupService signupService) {
    this.signupService = signupService;
  }
  
  @JsonView(JsonViews.userWithoutPassword.class)
  @RequestMapping(method = POST)
  public ResponseEntity<ApiResponse> signup(@RequestBody UserResource userResource) throws InvalidRequestException, InvalidParameterException, AlreadyExistException {
    UserResource createdUser = signupService.signup(userResource);
    return new ResponseEntity<>(new ApiResponse(createdUser, success), HttpStatus.CREATED);
  }
  
}
