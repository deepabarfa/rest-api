package filesaver.api.services.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import filesaver.api.repositories.v1.FileRepository;
import filesaver.api.resources.v1.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
  
  public FileResource uploadFile(Folder folder, MultipartFile uploadedFile, User user) {
    System.out.println(uploadedFile.getContentType());
    System.out.println(uploadedFile.getSize());
    return new FileResource();
  }
  
}
