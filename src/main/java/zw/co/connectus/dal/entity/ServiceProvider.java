package zw.co.connectus.dal.entity;

import lombok.Data;
import org.hibernate.annotations.Where;
import zw.co.connectus.util.JpaBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table
@Where(clause = "DELETED IS NULL")
public class ServiceProvider extends JpaBaseEntity {

}