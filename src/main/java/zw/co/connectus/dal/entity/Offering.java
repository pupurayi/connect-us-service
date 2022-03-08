package zw.co.connectus.dal.entity;

import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Product or Service
 */
@Entity
@Table
@Where(clause = "DELETED IS NULL")
public class Offering extends JpaBaseEntity {

}
