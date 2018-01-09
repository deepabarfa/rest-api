package filesaver.api.controllers.v1;

import static filesaver.api.enums.v1.ApiStatus.success;
import filesaver.api.models.v1.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Controller
@RequestMapping("/v1/")
public class MainController {
  
  @RequestMapping(method = GET)
  public ResponseEntity<ApiResponse> welcome() {
    return new ResponseEntity<>(new ApiResponse("Welcome to file saver!", success), HttpStatus.OK);
  }
  
}
