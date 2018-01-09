package filesaver.api.dao.models.v1;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "notifications")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseModel implements Serializable {
  
  @NotNull
  @Column(name = "unique_id")
  private String uniqueId;
  
  @NotNull
  @Column(name = "message")
  private String message;
  
  @JoinColumn(name = "user_id")
  @ManyToOne(cascade = CascadeType.ALL)
  private User user;
  
  @NotNull
  @Column(name = "is_read")
  private Boolean isRead;
  
  @NotNull
  @Column(name = "is_active")
  private Boolean isActive;
}
