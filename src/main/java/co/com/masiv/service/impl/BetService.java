package co.com.masiv.service.impl;

import co.com.masiv.dto.BetDto;
import co.com.masiv.model.Bet;
import co.com.masiv.repository.BetRepository;
import co.com.masiv.service.IBetService;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BetService implements IBetService {

  private BetRepository betRepository;

  @Override
  public String createBet(BetDto betDto) {
    Bet bet =
        Bet.builder()
            .idRoulette(betDto.getCodeRoulette())
            .amount(betDto.getValue())
            .color(betDto.getColor())
            .number(betDto.getNumber())
            .winner(false)
            .creationDate(new Date())
            .build();
    bet = betRepository.save(bet);

    return bet.getId();
  }

  @Override
  public List<Bet> getBetByRoulette(String idRoulette) {
    return betRepository.findByIdRoulette(idRoulette);
  }
}
