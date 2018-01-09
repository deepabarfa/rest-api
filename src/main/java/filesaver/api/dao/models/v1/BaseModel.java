package filesaver.api.dao.models.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 13 Dec 2017
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @Type(type = "localDateTime")
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @JsonIgnore
  @Type(type = "localDateTime")
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
  
  @PreUpdate
  public void onUpdate() {
    updatedAt = new LocalDateTime();
  }

  @PrePersist
  public void onCreate() {
    updatedAt = createdAt = new LocalDateTime();
  }

}
