package filesaver.api.enums.v1;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 19 Jan 2018
 * 
 */
@Getter
@AllArgsConstructor
public enum SupportedFileTypes {
  txt("text/plain");
  
  private final String type;
  
  public static Set<String> getTypes() {
    return Stream.of(values())
      .map(v -> v.getType())
      .collect(Collectors.toSet());
  }
}
