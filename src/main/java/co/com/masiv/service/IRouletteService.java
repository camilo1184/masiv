package co.com.masiv.service;

import co.com.masiv.dto.RouletteDto;
import java.util.List;

public interface IRouletteService {

  String createRoulette(RouletteDto rouletteDto);

  boolean activateRoulette(String id);

  boolean getStatusRoulette(String codeRoulette);

  List getRouletteList();
}
