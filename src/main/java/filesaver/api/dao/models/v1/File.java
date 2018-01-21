package filesaver.api.dao.models.v1;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 06 Jan 2017
 *
 */
@Table(name = "files")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseModel implements Serializable {

  @NotNull
  @Column(name = "unique_id")
  private String uniqueId;

  @NotNull
  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "folder_id")
  private Folder folder;

  @JoinColumn(name = "user_id")
  @ManyToOne(cascade = CascadeType.ALL)
  private User user;

  @NotNull
  @Column(name = "file_size")
  private Long size;

  @NotNull
  @Column(name = "file_type")
  private String type;
  
  @NotNull
  @Column(name = "upload_status")
  private String status;
}
