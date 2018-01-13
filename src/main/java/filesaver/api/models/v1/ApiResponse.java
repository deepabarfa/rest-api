package filesaver.api.models.v1;

import com.fasterxml.jackson.annotation.JsonView;
import filesaver.api.enums.v1.ApiStatus;
import filesaver.api.utils.v1.JsonViews;
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
  
  @JsonView(JsonViews.baseResponse.class)
  private Object body;
  
  @JsonView(JsonViews.baseResponse.class)
  private ApiStatus status;
}
