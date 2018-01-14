package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.repositories.v1.FolderRepository;
import filesaver.api.resources.v1.FolderResource;
import filesaver.api.utils.v1.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 14 Jan 2017
 *
 */
@Service
public class FolderService {

  private final FolderRepository folderRepository;

  @Autowired
  public FolderService(FolderRepository folderRepository) {
    this.folderRepository = folderRepository;
  }
  
  @Transactional(rollbackFor = {Throwable.class})
  public FolderResource createFolder(FolderResource folderResource, User user) throws InvalidRequestException, InvalidParameterException {
    ValidationUtils.validateCreateFolderRequest(folderResource);
    Folder folder = folderResource.getModel();
    folder.initFolder(user);
    folder = saveFolder(folder);
    return new FolderResource(folder);
  }

  @Transactional(rollbackFor = {Throwable.class})
  public Folder saveFolder(Folder folder) {
    return folderRepository.save(folder);
  }

}
