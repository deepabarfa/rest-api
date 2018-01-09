package filesaver.api.dao.models.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "user_file_upload_settings")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFileUploadSetting extends BaseModel implements Serializable {
  
  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  @NotNull
  @Column(name = "upload_limit")
  private Long uploadLimit;
  
  @NotNull
  @Column(name = "uploaded_bytes")
  private Long uploadedBytes;
  
  public UserFileUploadSetting(User user) {
    this.user = user;
    this.uploadLimit = 1073741824l;
    this.uploadedBytes = 0l;
  }
}
