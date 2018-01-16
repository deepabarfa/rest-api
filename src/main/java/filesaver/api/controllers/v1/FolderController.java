package filesaver.api.controllers.v1;

import filesaver.api.dao.models.v1.User;
import static filesaver.api.enums.v1.ApiStatus.success;
import filesaver.api.exceptions.v1.InvalidParameterException;
import filesaver.api.exceptions.v1.InvalidRequestException;
import filesaver.api.exceptions.v1.NotFoundException;
import filesaver.api.exceptions.v1.UnAuthorizeException;
import filesaver.api.models.v1.ApiResponse;
import filesaver.api.models.v1.FilesaverPage;
import filesaver.api.resources.v1.MinimalFolderResource;
import filesaver.api.services.v1.FolderService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 05 Jan 2018
 * 
 */
@Controller
@RequestMapping("/v1/folders")
public class FolderController {
  
  private final FolderService folderService;
  
  @Autowired
  public FolderController(FolderService folderService) {
    this.folderService = folderService;
  }
  
  @RequestMapping(method = POST)
  public ResponseEntity<ApiResponse> createFolder(@RequestBody MinimalFolderResource folderResource, HttpServletRequest request) throws InvalidRequestException, InvalidParameterException, UnAuthorizeException {
    User user = (User) request.getAttribute("principal");
    MinimalFolderResource createdFolder = folderService.createFolder(folderResource, user);
    return new ResponseEntity<>(new ApiResponse(createdFolder, success), HttpStatus.CREATED);
  }
  
  @RequestMapping(method = POST, value = "/{uniqueId}")
  public ResponseEntity<ApiResponse> createSubFolder(@PathVariable(value = "uniqueId") String uniqueId, @RequestBody MinimalFolderResource folderResource, 
    HttpServletRequest request) throws InvalidRequestException, InvalidParameterException, UnAuthorizeException, NotFoundException {
    User user = (User) request.getAttribute("principal");
    MinimalFolderResource createdFolder = folderService.createSubFolder(uniqueId, folderResource, user);
    return new ResponseEntity<>(new ApiResponse(createdFolder, success), HttpStatus.CREATED);
  }
  
  @RequestMapping(method = GET, value = "/{uniqueId}")
  public ResponseEntity<ApiResponse> getFolder(@PathVariable(value = "uniqueId") String uniqueId, HttpServletRequest request) throws InvalidRequestException, InvalidParameterException, UnAuthorizeException, NotFoundException {
    User user = (User) request.getAttribute("principal");
    MinimalFolderResource createdFolder = folderService.getFolder(uniqueId, user);
    return new ResponseEntity<>(new ApiResponse(createdFolder, success), HttpStatus.OK);
  }
  
  @RequestMapping(method = GET)
  public ResponseEntity<ApiResponse> getTopFolders(
    @RequestParam(name = "page", defaultValue = "1", required = false) int page, 
    @RequestParam(name = "count", defaultValue = "5", required = false) int count, HttpServletRequest request) throws InvalidRequestException, InvalidParameterException, UnAuthorizeException {
    User user = (User) request.getAttribute("principal");
    FilesaverPage<MinimalFolderResource> paginatedTopFolders = folderService.getPaginatedTopFoldersForUser(user, page, count);
    return new ResponseEntity<>(new ApiResponse(paginatedTopFolders, success), HttpStatus.OK);
  }
  
}
