package filesaver.api.repositories.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 14 Jan 2017
 * 
 */
@Repository
public interface FolderRepository extends PagingAndSortingRepository<Folder, Long> {

  public Optional<Folder> findByUniqueIdAndUser(String uniqueId, User user);

  public Page<Folder> findByUserAndParentFolderIsNull(User user, Pageable pageable);

  public Page<Folder> findByUserAndParentFolder(User user, Folder parentFolder, Pageable pageable);

}
