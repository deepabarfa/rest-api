package filesaver.api.handlers.v1;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 07 Jan 2017
 * 
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger(AsyncExceptionHandler.class);

  @Override
  public void handleUncaughtException(Throwable thrwbl, Method method, Object... os) {
    LOGGER.error("Async method exception: ", thrwbl);
  }
}
