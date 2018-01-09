package filesaver.api.utils.v1;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 06 Jan 2017
 * 
 */
@Component
public class MessageUtils implements MessageSourceAware {
  
  public static final String DEFAULT_LANG = "en";
  private MessageSource messageSource;

  public String t(String messageCode, Object... replacements) {
    return t(messageCode, replacements, DEFAULT_LANG);
  }
  
  private String t(String messageCode, Object[] replacements, String locale) {
    return messageSource.getMessage(messageCode, replacements, new Locale(locale));
  }

  @Override
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }
}
