package co.com.masiv.controller;

import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface IRouletteController {

  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  ResponseEntity<String> createRoulette(@RequestBody RouletteDto rouletteDto);

  @PutMapping(path="/{id}")
  ResponseEntity<Boolean> activateRoulette(@PathVariable("id") String id);

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  ResponseEntity<List<Roulette>> getRouletteList();

}
