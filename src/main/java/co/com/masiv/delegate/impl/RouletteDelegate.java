package co.com.masiv.delegate.impl;

import co.com.masiv.delegate.IRouletteDelegate;
import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import co.com.masiv.service.IRouletteService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class RouletteDelegate implements IRouletteDelegate {

  private IRouletteService rouletteService;

  @Override
  public ResponseEntity<String> createRoulette(RouletteDto rouletteDto) {
    return new ResponseEntity<>(rouletteService.createRoulette(rouletteDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Boolean> activateRoulette(String id) {
    return new ResponseEntity<>(rouletteService.activateRoulette(id), HttpStatus.OK);
  }

  @Override
  public boolean getStatusRoulette(String codeRoulette) {
    return rouletteService.getStatusRoulette(codeRoulette);
  }

  @Override
  public ResponseEntity<List<Roulette>> getRouletteList() {
    return new ResponseEntity<>(rouletteService.getRouletteList(), HttpStatus.OK);
  }

  @Override
  public Optional<Roulette> closeRoulette(String idRoulette) {
    return rouletteService.closeRoulette(idRoulette);
  }

}
