package zw.co.connectus.dal.entity;

import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Where(clause = "DELETED IS NULL")
public class User extends JpaBaseEntity {

	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
}
