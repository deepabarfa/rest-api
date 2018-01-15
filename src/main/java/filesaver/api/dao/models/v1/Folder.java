package filesaver.api.dao.models.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 06 Jan 2017
 *
 */
@Table(name = "folders")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Folder extends BaseModel implements Serializable {

  @NotNull
  @Column(name = "unique_id")
  private String uniqueId;

  @NotNull
  @Column(name = "name")
  private String name;

  @JoinColumn(name = "parent_folder_id")
  @ManyToOne
  private Folder parentFolder;

  @JoinColumn(name = "user_id")
  @ManyToOne
  private User user;

  @NotNull
  @Column(name = "folder_size")
  private Long size;

  @OneToMany(mappedBy = "folder", fetch = FetchType.EAGER)
  private Set<File> files = new HashSet<>(0);

  @OneToMany(mappedBy = "parentFolder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @Fetch(FetchMode.SUBSELECT)
  private Set<Folder> subFolders = new HashSet<>(0);

  public void initFolder(User user) {
    this.user = user;
    this.size = 0l;
    this.uniqueId = RandomStringUtils.randomAlphanumeric(20);
  }
  
  @PrePersist
  private void setUniqueId() {
    this.uniqueId = uniqueId.toLowerCase();
  }

}
