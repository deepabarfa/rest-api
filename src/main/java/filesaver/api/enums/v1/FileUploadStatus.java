package filesaver.api.enums.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 31 Jan 2018
 * 
 */
@Getter
@AllArgsConstructor
public enum FileUploadStatus {
  processing, completed, failed;
}
