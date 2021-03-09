package co.com.masiv.delegate.impl;

import co.com.masiv.delegate.IBetDelegate;
import co.com.masiv.delegate.IRouletteDelegate;
import co.com.masiv.dto.BetDto;
import co.com.masiv.model.Bet;
import co.com.masiv.model.Roulette;
import co.com.masiv.service.IBetService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BetDelegate implements IBetDelegate {

  private IBetService betService;
  private IRouletteDelegate rouletteDelegate;

  @Value("${winner.color.value}")
  private double colorValue;

  @Value("${winner.number.value}")
  private double numberValue;

  public BetDelegate(IBetService betService, IRouletteDelegate rouletteDelegate) {
    this.betService = betService;
    this.rouletteDelegate = rouletteDelegate;
  }

  @Override
  public ResponseEntity<String> createBet(@NonNull BetDto betDto) {
    if (validateBet(betDto) && rouletteDelegate.getStatusRoulette(betDto.getCodeRoulette())) {
      return new ResponseEntity<>(betService.createBet(betDto), HttpStatus.OK);
    }

    return new ResponseEntity<>("Verifique la información !!", HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<Object> closeBets(String idRoulette) {
    try {
      Optional<Roulette> roulette = rouletteDelegate.closeRoulette(idRoulette);
      if (roulette.isPresent()) {
        List<Bet> betsOnRouletteWhenActive = getBetsOnRouletteWhenActive(roulette.get());
        getWinner(betsOnRouletteWhenActive);
        return new ResponseEntity<>(betsOnRouletteWhenActive, HttpStatus.OK);
      }

      return new ResponseEntity<>("No se logro cerrar la ruleta !!", HttpStatus.BAD_REQUEST);
    } catch (NoSuchAlgorithmException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private void getWinner(List<Bet> betsOnRouletteWhenActive) throws NoSuchAlgorithmException {
    Random rand = SecureRandom.getInstanceStrong();
    Bet bet = betsOnRouletteWhenActive.get(rand.nextInt(betsOnRouletteWhenActive.size()));
    bet.setWinner(true);
    if (bet.getNumber() > 0) {
      bet.setAmountPaid(bet.getAmount() * numberValue);
    } else if (!bet.getColor().isEmpty()) {
      bet.setAmountPaid(bet.getAmount() * colorValue);
    }
  }

  private List<Bet> getBetsOnRouletteWhenActive(Roulette roulette) {
    List<Bet> betByRoulette = betService.getBetByRoulette(roulette.getId());

    return betByRoulette.stream()
        .filter(
            bet ->
                bet.getCreationDate().after(roulette.getOpeningDate())
                    && bet.getCreationDate().before(roulette.getClosingDate()))
        .collect(Collectors.toList());
  }

  private boolean validateBet(@NonNull BetDto betDto) {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    return validator.validate(betDto).stream().findAny().map(t -> false).orElse(true);
  }
}
