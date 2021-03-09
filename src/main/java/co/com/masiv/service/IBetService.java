package co.com.masiv.service;

import co.com.masiv.dto.BetDto;
import co.com.masiv.model.Bet;
import java.util.List;

public interface IBetService {

  String createBet(BetDto betDto);

  List<Bet> getBetByRoulette(String idRoulette);
}
