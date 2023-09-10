package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.util.DtoConverter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Crop service.
 */
@Service
public class CropService {

  private CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public CropDto registerCrop(CropDto cropDto, Farm farm) {
    Crop crop = cropRepository.save(cropDto.toCrop(farm));
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId());
  }

  /**
   * List all crops by farmId.
   */
  public List<CropDto> getCropsById(Long farmId) {
    return DtoConverter.listModelDto(cropRepository.getCropsByFarm(farmId));
  }

  /**
   * List all crops.
   */
  public List<CropDto> getCrops() {
    return DtoConverter.listModelDto(cropRepository.findAll());
  }

  /**
   * Get crop.
   */

  public Optional<Crop> getCrop(Long id) {
    return cropRepository.findById(id);
  }
}
