package zw.co.connectus.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.connectus.dal.entity.Offering;

import java.util.UUID;

@Repository
public interface OfferingRepository extends JpaRepository<Offering, UUID> {

}

