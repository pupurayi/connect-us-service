package zw.co.connectus.util;

import java.time.ZonedDateTime;
import java.util.UUID;

public class BaseDto {


	private UUID          id;
	private ZonedDateTime created;
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