/**
 *
 * @author Gaurav Mahawar
 * @version v1
 * @since 10 Jan 2017
 * 
 */
@TypeDefs(
  {
    @TypeDef(
      name = "localDateTime",
      defaultForType = LocalDateTime.class,
      typeClass = PersistentLocalDateTime.class),

    @TypeDef(
      name = "localDate",
      defaultForType = LocalDate.class,
      typeClass = PersistentLocalDate.class),
  }
)

package filesaver.api.handlers.v1;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
