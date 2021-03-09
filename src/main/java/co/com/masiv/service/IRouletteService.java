package co.com.masiv.service;

import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import java.util.List;
import java.util.Optional;

public interface IRouletteService {

  String createRoulette(RouletteDto rouletteDto);

  boolean activateRoulette(String id);

  boolean getStatusRoulette(String codeRoulette);

  List<Roulette> getRouletteList();

  Optional<Roulette> closeRoulette(String idRoulette);
}
