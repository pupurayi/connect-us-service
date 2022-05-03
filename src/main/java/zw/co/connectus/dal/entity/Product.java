package zw.co.connectus.dal.entity;

import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Product or Service
 */
@Entity
@Table(name = "products")
@Where(clause = "deleted is null")
public class Product extends JpaBaseEntity {

	// add Product Tags, add Recommended Products endpoint, add rate random products' endpoint, hotlink whatsapp & location

	private String     userId;
	private String     category;
	private String     name;
	@Column(columnDefinition = "TEXT")
	private String     description;
	private BigDecimal price;
	@Column(columnDefinition = "TEXT")
	private String     imageFirst;
	@Column(columnDefinition = "TEXT")
	private String     imageSecond;
	private BigDecimal lat    = BigDecimal.valueOf(-17.779172389675505);
	private BigDecimal lng    = BigDecimal.valueOf(31.014082820096977);
	private int        rating = 3;

	public String getCategory() {

		return category;
	}

	public void setCategory(String category) {

		this.category = category;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

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

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
	}

	public String getImageFirst() {

		return imageFirst;
	}

	public void setImageFirst(String imageFirst) {

		this.imageFirst = imageFirst;
	}

	public String getImageSecond() {

		return imageSecond;
	}

	public void setImageSecond(String imageSecond) {

		this.imageSecond = imageSecond;
	}

	public BigDecimal getLat() {

		return lat;
	}

	public void setLat(BigDecimal lat) {

		this.lat = lat;
	}

	public BigDecimal getLng() {

		return lng;
	}

	public void setLng(BigDecimal lng) {

		this.lng = lng;
	}

	public int getRating() {

		return rating;
	}

	public void setRating(int rating) {

		this.rating = rating;
	}
}
