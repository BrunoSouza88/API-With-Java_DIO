package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Crop repository.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {
  @Query(value = "SELECT * FROM crops WHERE crops.farm_id = :farmId", nativeQuery = true)
  List<Crop> getCropsByFarm(Long farmId);
}
