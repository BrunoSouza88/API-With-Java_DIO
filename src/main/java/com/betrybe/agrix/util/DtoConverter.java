package com.betrybe.agrix.util;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.util.List;


/**
 * DtoConverter class.
 */
public class DtoConverter {

  public static FarmDto modelToDto(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  public static CropDto cropModelDto(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId());
  }

  public static List<CropDto> listModelDto(List<Crop> crops) {
    return crops.stream().map(crop -> new CropDto(crop.getId(), crop.getName(),
        crop.getPlantedArea(), crop.getFarm().getId())).toList();
  }
}
