package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.NotFoundException;
import filesaver.api.models.v1.FilesaverPage;
import org.springframework.data.domain.Page;
import filesaver.api.repositories.v1.FolderRepository;
import filesaver.api.resources.v1.MinimalFolderResource;
import filesaver.api.utils.v1.ExceptionUtils;
import filesaver.api.utils.v1.ValidationUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  public MinimalFolderResource createFolder(MinimalFolderResource folderResource, User user) throws InvalidRequestException, InvalidParameterException {
    ValidationUtils.validateCreateFolderRequest(folderResource);
    Folder folder = folderResource.getModel();
    folder.initFolder(user);
    folder = saveFolder(folder);
    return new MinimalFolderResource(folder);
  }

  @Transactional(rollbackFor = {Throwable.class})
  public Folder saveFolder(Folder folder) {
    return folderRepository.save(folder);
  }
  
  public Folder getFolderByUniqueIdAndUser(String uniqueId, User user) throws NotFoundException {
    Optional<Folder> optionalFolder = folderRepository.findByUniqueIdAndUser(uniqueId, user);
    return optionalFolder.orElseThrow(() -> ExceptionUtils.returnNotFoundExceptionForFolder());
  }

  @Transactional(rollbackFor = {Throwable.class})
  public MinimalFolderResource createSubFolder(String parentUniqueId, MinimalFolderResource folderResource, User user) throws NotFoundException, InvalidRequestException, InvalidParameterException {
    Folder parentFolder = getFolderByUniqueIdAndUser(parentUniqueId, user);
    ValidationUtils.validateCreateFolderRequest(folderResource);
    Folder folder = folderResource.getModel();
    folder.setParentFolder(parentFolder);
    folder.initFolder(user);
    folder = saveFolder(folder);
    return new MinimalFolderResource(folder);
  }

  public MinimalFolderResource getFolder(String uniqueId, User user) throws NotFoundException {
    Folder folder = getFolderByUniqueIdAndUser(uniqueId, user);
    return new MinimalFolderResource(folder);
  }
  
  public FilesaverPage<MinimalFolderResource> getPaginatedTopFoldersForUser(User user, int page, int count) {
    Pageable pageable = PageRequest.of(page - 1, count, new Sort(Sort.Direction.DESC, "updatedAt"));
    Page<Folder> topFoldersPage = folderRepository.findByUserAndParentFolderIsNull(user, pageable);
    List<MinimalFolderResource> folderResources = topFoldersPage.getContent()
      .stream()
      .map(tf -> new MinimalFolderResource(tf))
      .collect(Collectors.toList());
    FilesaverPage filesaverPage = new FilesaverPage(
      page, count, topFoldersPage.getTotalPages(), topFoldersPage.getTotalElements(), folderResources);
    return filesaverPage;
  }

}
