package zw.co.connectus.util;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;


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

	public UUID getId() {

		return id;
	}

	public void setId(UUID id) {

		this.id = id;
	}

	public ZonedDateTime getCreated() {

		return created;
	}

	public void setCreated(ZonedDateTime created) {

		this.created = created;
	}

	public ZonedDateTime getUpdated() {

		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {

		this.updated = updated;
	}

	public ZonedDateTime getDeleted() {

		return deleted;
	}

	public void setDeleted(ZonedDateTime deleted) {

		this.deleted = deleted;
	}
}
