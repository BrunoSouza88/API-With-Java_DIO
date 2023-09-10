package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.util.DtoConverter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * CropController class.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * List all crops.
   */
  @GetMapping()
  public ResponseEntity<?> getCropsById() {
    return ResponseEntity.ok(cropService.getCrops());

  }

  /**
   * Get Crop.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getCrop(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCrop(id);

    if (optionalCrop.isPresent()) {
      CropDto cropDto = DtoConverter.cropModelDto(optionalCrop.get());
      return ResponseEntity.ok(cropDto);

    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");

  }
}
