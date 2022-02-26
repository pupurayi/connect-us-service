package zw.co.connectus.util;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class JpaBaseEntity {

	@Id
	@Type(type = "uuid-char")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID          id;
	@CreationTimestamp
	@Column(nullable = false)
	private ZonedDateTime created;
	@UpdateTimestamp
	@Column(nullable = false)
	private ZonedDateTime updated;
	private ZonedDateTime deleted;
}
