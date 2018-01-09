package filesaver.api.models.v1;

import filesaver.api.enums.v1.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 * 
 */
@Getter
@AllArgsConstructor
public class ApiResponse {
  
  private Object body;
  private ApiStatus status;
}
