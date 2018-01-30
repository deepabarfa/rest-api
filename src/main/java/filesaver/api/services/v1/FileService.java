package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.File;
import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.models.v1.FilesaverPage;
import filesaver.api.repositories.v1.FileRepository;
import filesaver.api.resources.v1.FileResource;
import filesaver.api.utils.v1.AwsUtils;
import filesaver.api.utils.v1.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 19 Jan 2017
 *
 */
@Service
public class FileService {
  
  private final AsyncService asyncService;
  private final FileRepository fileRepository;
  
  @Autowired
  public FileService(AsyncService asyncService, FileRepository fileRepository) {
    this.asyncService = asyncService;
    this.fileRepository = fileRepository;
  }
  
  @Transactional(rollbackFor = {Throwable.class})
  public FileResource uploadFile(Folder folder, MultipartFile file, User user) throws InvalidRequestException {
    ValidationUtils.validateUploadFileRequest(file);
    return new FileResource();
  }

  public FileResource downloadFile(Folder folder, String fileUniqueId, User user) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public FilesaverPage<FileResource> getPaginatedFiles(Folder folder, User user, int page, int count) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
