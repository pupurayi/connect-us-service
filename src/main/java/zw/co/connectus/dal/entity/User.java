package zw.co.connectus.dal.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table
@Where(clause = "DELETED IS NULL")
public class User extends JpaBaseEntity {

	@Column(nullable = false, unique = true)
	private String msisdn;
	@Column(unique = true)
	private String email;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(columnDefinition = "TEXT")
	private String password;
}
