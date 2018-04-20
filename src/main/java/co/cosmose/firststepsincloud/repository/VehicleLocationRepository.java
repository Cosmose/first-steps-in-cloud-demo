package co.cosmose.firststepsincloud.repository;

import co.cosmose.firststepsincloud.domain.VehicleLocation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(value = Transactional.TxType.MANDATORY)
public interface VehicleLocationRepository extends JpaRepository<VehicleLocation, Long> {

    List<VehicleLocation> findByLineIn(@Param("lines") List<String> lines, Sort sort);

    @Modifying
    @Query("update VehicleLocation set status = 'NEXT_TO_LATEST' where status = 'LATEST'")
    void changeLatestToNextToLatest();

    @Modifying
    @Query("delete from VehicleLocation where status = 'NEXT_TO_LATEST'")
    void removeNextToLatest();

}
