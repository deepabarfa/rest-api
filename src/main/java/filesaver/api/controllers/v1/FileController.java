package filesaver.api.controllers.v1;

import filesaver.api.dao.models.v1.Folder;
import filesaver.api.dao.models.v1.User;
import static filesaver.api.enums.v1.ApiStatus.success;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.NotFoundException;
import filesaver.api.models.v1.ApiResponse;
import filesaver.api.models.v1.FilesaverPage;
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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
@RequestMapping("/v1/folders/{folderUniqueId}/files")
public class FileController {

  private final FolderService folderService;
  private final FileService fileService;

  @Autowired
  public FileController(FolderService folderService, FileService fileService) {
    this.folderService = folderService;
    this.fileService = fileService;
  }

  @RequestMapping(method = POST)
  public ResponseEntity<ApiResponse> uploadFile(@PathVariable(value = "folderUniqueId") String folderUniqueId,
    @RequestParam("file") MultipartFile file, HttpServletRequest request) throws NotFoundException, InvalidRequestException {
    User user = (User) request.getAttribute("principal");
    Folder folder = folderService.getFolderByUniqueIdAndUser(folderUniqueId, user);
    FileResource fileResource = fileService.uploadFile(folder, file, user);
    return new ResponseEntity<>(new ApiResponse(fileResource, success), HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/{fileUniqueId}")
  public ResponseEntity<ApiResponse> downloadFile(@PathVariable(value = "folderUniqueId") String folderUniqueId,
    @PathVariable(value = "fileUniqueId") String fileUniqueId, HttpServletRequest request) throws NotFoundException {
    User user = (User) request.getAttribute("principal");
    Folder folder = folderService.getFolderByUniqueIdAndUser(folderUniqueId, user);
    FileResource fileResource = fileService.downloadFile(folder, fileUniqueId, user);
    return new ResponseEntity<>(new ApiResponse(fileResource, success), HttpStatus.OK);
  }
  
  @RequestMapping(method = GET)
  public ResponseEntity<ApiResponse> getFiles(@PathVariable(value = "folderUniqueId") String folderUniqueId, 
    @RequestParam(name = "page", defaultValue = "1", required = false) int page, 
    @RequestParam(name = "count", defaultValue = "5", required = false) int count,
    HttpServletRequest request) throws NotFoundException {
    User user = (User) request.getAttribute("principal");
    Folder folder = folderService.getFolderByUniqueIdAndUser(folderUniqueId, user);
    FilesaverPage<FileResource> paginatedFiles = fileService.getPaginatedFiles(folder, user, page, count);
    return new ResponseEntity<>(new ApiResponse(paginatedFiles, success), HttpStatus.OK);
  }

}
