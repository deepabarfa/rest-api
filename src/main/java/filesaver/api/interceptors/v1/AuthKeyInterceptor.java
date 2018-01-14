package filesaver.api.interceptors.v1;

import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.InvalidHeaderException;
import filesaver.api.exceptions.v1.UnAuthorizeException;
import filesaver.api.services.v1.UserService;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 07 Jan 2017
 * 
 */
public class AuthKeyInterceptor extends HandlerInterceptorAdapter {

  private static final Logger LOGGER = LogManager.getLogger(AuthKeyInterceptor.class);
  
  public static final String AUTH_KEY = "Auth-Key";

  @Autowired
  private UserService userService;
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvalidHeaderException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnAuthorizeException {
    LOGGER.debug(request.getMethod() + ": " + request.getRequestURI() + " Started at: " + new LocalDateTime());
    
    String authKey = request.getHeader(AUTH_KEY);
    if(StringUtils.isBlank(authKey)) {
      throw new InvalidHeaderException("Auth-Key header is missing or invalid.");
    }
    User user = userService.findUserByAuthKey(authKey);
    request.setAttribute("principal", user);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response,
    Object handler, ModelAndView modelAndView) throws Exception {
    LOGGER.debug(request.getRequestURI() + " Ended at: " + new LocalDateTime());
  }
}
