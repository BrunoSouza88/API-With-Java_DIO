package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;


/**
 * Crop dto.
 */
public record CropDto(Long id, String name, Double plantedArea, Long farmId) {
  public Crop toCrop(Farm farm) {
    return new Crop(id, name, plantedArea, farm);
  }
}
