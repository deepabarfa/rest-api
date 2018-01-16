package filesaver.api.models.v1;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @param <T>
 * @since 16 Jan 2017
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesaverPage<T> {
  private int page;
  private int count;
  private int totalPages;
  private long totalItems;
  private List<T> results;
}
