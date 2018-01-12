package filesaver.api.utils.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 11 Jan 2017
 * 
 */
@Component
public class CustomObjectMapper extends ObjectMapper {
  
  public CustomObjectMapper() {
    registerModule(new JodaModule());
  }
}
