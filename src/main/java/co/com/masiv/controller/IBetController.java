package co.com.masiv.controller;

import co.com.masiv.dto.BetDto;
import javax.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Validated
public interface IBetController {

  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  ResponseEntity<String> createBet(
      @RequestHeader("user-id") String userId, @RequestBody BetDto betDto);

  @GetMapping(path="/{idRoulette}")
  ResponseEntity<Object> closeBets(@PathVariable("idRoulette") @NotNull String idRoulette);
}
