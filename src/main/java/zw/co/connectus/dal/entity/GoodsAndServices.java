package zw.co.connectus.dal.entity;

import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Product or Service
 */
@Entity
@Table(name = "goods_and_services")
@Where(clause = "deleted is null")
public class GoodsAndServices extends JpaBaseEntity {

	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	private int    rating;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public int getRating() {

		return rating;
	}

	public void setRating(int rating) {

		this.rating = rating;
	}
}
