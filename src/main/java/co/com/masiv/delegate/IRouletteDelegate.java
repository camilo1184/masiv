package co.com.masiv.delegate;

import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface IRouletteDelegate {

  ResponseEntity<String> createRoulette(RouletteDto rouletteDto);

  ResponseEntity<Boolean> activateRoulette(String id);

  boolean getStatusRoulette(String codeRoulette);

  ResponseEntity<List<Roulette>> getRouletteList();

  Optional<Roulette> closeRoulette(String idRoulette);
}
