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
@Table(name = "products")
@Where(clause = "deleted is null")
public class Product extends JpaBaseEntity {

    // add Product Tags, add Recommended Products endpoint, add rate random products' endpoint, hotlink whatsapp & location

    private String userId;
    private String category;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    @Column(columnDefinition = "TEXT")
    private String imageFirst;
    private Double lat;
    private Double lng;
    private Float rating = 2.5f;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageFirst() {
        return imageFirst;
    }

    public void setImageFirst(String imageFirst) {
        this.imageFirst = imageFirst;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
