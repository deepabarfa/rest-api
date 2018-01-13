package filesaver.api.repositories.v1;

import filesaver.api.dao.models.v1.UserFileUploadSetting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Jan 2017
 * 
 */
@Repository
public interface UserFileUploadSettingRepository extends CrudRepository<UserFileUploadSetting, Long> {
  
}
