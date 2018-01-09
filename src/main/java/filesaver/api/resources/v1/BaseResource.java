package filesaver.api.resources.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @param <T>
 * @since 13 Dec 2017
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResource<T> {
  
  @Getter(onMethod = @__(@JsonIgnore))
  @Setter(value = AccessLevel.PUBLIC)
  private T model;
  
}
