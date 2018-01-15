package filesaver.api.repositories.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 14 Jan 2017
 * 
 */
@Repository
public interface FolderRepository extends CrudRepository<Folder, Long> {

  public Optional<Folder> findByUniqueIdAndUser(String uniqueId, User user);

  public Set<Folder> findByUserAndParentFolderIsNull(User user);

}
