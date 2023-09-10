package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Farm service.
 */
@Service
public class FarmService {
  private FarmRepository farmRepositorio;

  @Autowired
  public FarmService(FarmRepository farmRep) {
    this.farmRepositorio = farmRep;
  }

  public Farm registerFarm(Farm farm) {
    return farmRepositorio.save(farm);
  }

  public List<Farm> getFarms() {
    return farmRepositorio.findAll();
  }

  public Optional<Farm> getFarm(Long id) {
    return farmRepositorio.findById(id);
  }
}
