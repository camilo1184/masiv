package co.com.masiv.delegate;

import co.com.masiv.dto.RouletteDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IRouletteDelegate {

  ResponseEntity<String> createRoulette(RouletteDto rouletteDto);

  ResponseEntity<Boolean> activateRoulette(String id);

  boolean getStatusRoulette(String codeRoulette);

  ResponseEntity<List> getRouletteList();
}
