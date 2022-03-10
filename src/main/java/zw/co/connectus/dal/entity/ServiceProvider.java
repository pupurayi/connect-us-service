package zw.co.connectus.dal.entity;

import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "service_providers")
@Where(clause = "deleted is null")
public class ServiceProvider extends JpaBaseEntity {

	private String name;
	private String address;
	private double lat;
	private double lng;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public double getLat() {

		return lat;
	}

	public void setLat(double lat) {

		this.lat = lat;
	}

	public double getLng() {

		return lng;
	}

	public void setLng(double lng) {

		this.lng = lng;
	}
}