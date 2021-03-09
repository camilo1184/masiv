package co.com.masiv.controller.impl;

import co.com.masiv.constants.ResourceEndpoint;
import co.com.masiv.controller.IRouletteController;
import co.com.masiv.delegate.IRouletteDelegate;
import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceEndpoint.ROULETTE)
@AllArgsConstructor
public class RouletteController implements IRouletteController {

  private IRouletteDelegate rouletteDelegate;

  @Override
  public ResponseEntity<String> createRoulette(RouletteDto rouletteDto) {
    return rouletteDelegate.createRoulette(rouletteDto);
  }

  @Override
  public ResponseEntity<Boolean> activateRoulette(String id) {
    return rouletteDelegate.activateRoulette(id);
  }

  @Override
  public ResponseEntity<List<Roulette>> getRouletteList() {
    return rouletteDelegate.getRouletteList();
  }
}
