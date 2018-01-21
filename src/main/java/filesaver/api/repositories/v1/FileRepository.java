package filesaver.api.repositories.v1;

import filesaver.api.dao.models.v1.File;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 19 Jan 2017
 * 
 */
@Repository
public interface FileRepository extends PagingAndSortingRepository<File, Long> {
  
}
