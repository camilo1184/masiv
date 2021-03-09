package co.com.masiv.controller.impl;

import co.com.masiv.constants.ResourceEndpoint;
import co.com.masiv.controller.IBetController;
import co.com.masiv.delegate.IBetDelegate;
import co.com.masiv.dto.BetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceEndpoint.BET)
@Slf4j
public class BetController implements IBetController {

  private IBetDelegate betDelegate;

  public BetController(IBetDelegate betDelegate) {
    this.betDelegate = betDelegate;
  }

  @Override
  public ResponseEntity<String> createBet(String userId, BetDto betDto) {
    log.info("Header[user-id] :: {}", userId);
    return betDelegate.createBet(betDto);
  }

  @Override
  public ResponseEntity<Object> closeBets(String idRoulette) {
    return betDelegate.closeBets(idRoulette);
  }

}
