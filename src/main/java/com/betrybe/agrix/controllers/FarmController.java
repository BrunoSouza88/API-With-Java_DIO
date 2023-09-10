package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.util.DtoConverter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private FarmService farmService;
  private CropService cropService;

  @Autowired
  public FarmController(FarmService farmSer, CropService cropService) {
    this.farmService = farmSer;
    this.cropService = cropService;
  }

  /**
   * PostMapping.
   */
  @PostMapping()
  public ResponseEntity<Farm> registerFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.registerFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body((newFarm));
  }

  @GetMapping()
  public ResponseEntity<List<Farm>> getFarms() {
    return ResponseEntity.ok(farmService.getFarms());
  }

  /**
   * GetMapping by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarm(@PathVariable Long id) {
    Optional<Farm> optionalFarm = farmService.getFarm(id);

    if (optionalFarm.isPresent()) {
      return ResponseEntity.ok(DtoConverter.modelToDto(optionalFarm.get()));

    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");

  }

  /**
   * PostMapping for crops.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<?> registerCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarm(farmId);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cropService.registerCrop(cropDto, optionalFarm.get()));
  }

  /**
   * List all crops by farmId.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCropsById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarm(farmId);

    if (optionalFarm.isPresent()) {
      return ResponseEntity.ok(cropService.getCropsById(farmId));

    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");

  }
}
