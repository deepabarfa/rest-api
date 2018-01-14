package filesaver.api.interceptors.v1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 14 Jan 2017
 * 
 */
public class NonAuthKeyInterceptor extends HandlerInterceptorAdapter {
  
  private static final Logger LOGGER = LogManager.getLogger(NonAuthKeyInterceptor.class);
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    LOGGER.debug(request.getMethod() + ": " + request.getRequestURI() + " Started at: " + new LocalDateTime());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response,
    Object handler, ModelAndView modelAndView) throws Exception {
    LOGGER.debug(request.getRequestURI() + " Ended at: " + new LocalDateTime());
  }
  
}
