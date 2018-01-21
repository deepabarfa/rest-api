package filesaver.api.controllers.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import static filesaver.api.enums.v1.ApiStatus.success;
import filesaver.api.exceptions.v1.NotFoundException;
import filesaver.api.models.v1.ApiResponse;
import filesaver.api.resources.v1.FileResource;
import filesaver.api.services.v1.FileService;
import filesaver.api.services.v1.FolderService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 05 Jan 2018
 * 
 */
@Controller
@RequestMapping("/v1/folders/{uniqueId}/files")
public class FileController {
  
  private final FolderService folderService;
  private final FileService fileService;
  
  @Autowired
  public FileController(FolderService folderService, FileService fileService) {
    this.folderService = folderService;
    this.fileService = fileService;
  }
  
  @RequestMapping(method = POST)
  public ResponseEntity<ApiResponse> uploadFile(@PathVariable(value = "uniqueId") String uniqueId, 
    @RequestParam("datafile") MultipartFile uploadedFile, HttpServletRequest request) throws NotFoundException {
    User user = (User) request.getAttribute("principal");
    Folder folder = folderService.getFolderByUniqueIdAndUser(uniqueId, user);
    FileResource fileResource = fileService.uploadFile(folder, uploadedFile, user);
    return new ResponseEntity<>(new ApiResponse(fileResource, success), HttpStatus.CREATED);
  }
  
}
