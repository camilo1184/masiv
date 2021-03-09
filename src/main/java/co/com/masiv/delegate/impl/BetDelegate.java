package co.com.masiv.delegate.impl;

import co.com.masiv.delegate.IBetDelegate;
import co.com.masiv.delegate.IRouletteDelegate;
import co.com.masiv.dto.BetDto;
import co.com.masiv.service.IBetService;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class BetDelegate implements IBetDelegate {

  private IBetService betService;
  private IRouletteDelegate rouletteDelegate;

  @Override
  public ResponseEntity<String> createBet(@NonNull BetDto betDto) {
    if (validateBet(betDto) && rouletteDelegate.getStatusRoulette(betDto.getCodeRoulette())) {
      return new ResponseEntity<>(betService.createBet(betDto), HttpStatus.OK);
    }

    return new ResponseEntity<>("Check the bet information !!", HttpStatus.BAD_REQUEST);
  }

  private boolean validateBet(@NonNull BetDto betDto) {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    return validator.validate(betDto).stream().findAny().map(t -> false).orElse(true);
  }
}
