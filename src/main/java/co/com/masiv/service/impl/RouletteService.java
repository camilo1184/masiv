package co.com.masiv.service.impl;

import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import co.com.masiv.repository.RouletteRepository;
import co.com.masiv.service.IRouletteService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RouletteService implements IRouletteService {

  private RouletteRepository rouletteRepository;

  @Override
  public String createRoulette(RouletteDto rouletteDto) {
    Roulette roulette =
        Roulette.builder()
            .name(rouletteDto.getTittle())
            .description(rouletteDto.getDescription())
            .status(rouletteDto.isStatus())
            .creationDate(new Date())
            .build();
    roulette = rouletteRepository.save(roulette);

    return roulette.getId();
  }

  @Override
  public boolean activateRoulette(String id) {
    Function<Roulette, Boolean> activateRoulette =
        t -> {
          t.setStatus(true);
          t.setOpeningDate(new Date());
          rouletteRepository.save(t);
          return true;
        };

    return rouletteRepository.findById(id).map(activateRoulette).orElse(false);
  }

  @Override
  public boolean getStatusRoulette(String codeRoulette) {
    return rouletteRepository.findById(codeRoulette).map(Roulette::isStatus).orElse(false);
  }

  @Override
  public List getRouletteList() {
    Iterator<Roulette> iteratorRoulette = rouletteRepository.findAll().iterator();

    return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iteratorRoulette, Spliterator.ORDERED), false)
        .map(
            x -> RouletteDto.builder()
                .code(x.getId())
                .description(x.getDescription())
                .status(x.isStatus())
                .tittle(x.getName())
                .creationDate(x.getCreationDate())
                .closing(x.getClosingDate())
                .opening(x.getOpeningDate())
                .build())
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Roulette> closeRoulette(String idRoulette) {
    UnaryOperator<Roulette> closeRoulette =
        t -> {
          t.setStatus(false);
          t.setClosingDate(new Date());
          return rouletteRepository.save(t);
        };

    return rouletteRepository.findById(idRoulette).map(closeRoulette);
  }

}
