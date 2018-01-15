package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.NotFoundException;
import filesaver.api.repositories.v1.FolderRepository;
import filesaver.api.resources.v1.FolderResource;
import filesaver.api.utils.v1.ExceptionUtils;
import filesaver.api.utils.v1.ValidationUtils;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
  
  public Folder getFolderByUniqueIdAndUser(String uniqueId, User user) throws NotFoundException {
    Optional<Folder> optionalFolder = folderRepository.findByUniqueIdAndUser(uniqueId, user);
    return optionalFolder.orElseThrow(() -> ExceptionUtils.returnNotFoundExceptionForFolder());
  }
  
  public Set<Folder> getTopFolders(User user) {
    return folderRepository.findByUserAndParentFolderIsNull(user);
  }

  @Transactional(rollbackFor = {Throwable.class})
  public FolderResource createSubFolder(String parentUniqueId, FolderResource folderResource, User user) throws NotFoundException, InvalidRequestException, InvalidParameterException {
    Folder parentFolder = getFolderByUniqueIdAndUser(parentUniqueId, user);
    ValidationUtils.validateCreateFolderRequest(folderResource);
    Folder folder = folderResource.getModel();
    folder.setParentFolder(parentFolder);
    folder.initFolder(user);
    folder = saveFolder(folder);
    return new FolderResource(folder);
  }

  public FolderResource getFolder(String uniqueId, User user) throws NotFoundException {
    Folder folder = getFolderByUniqueIdAndUser(uniqueId, user);
    return new FolderResource(folder);
  }

}
