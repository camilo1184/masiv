package co.com.masiv.service.impl;

import co.com.masiv.dto.RouletteDto;
import co.com.masiv.model.Roulette;
import co.com.masiv.repository.RouletteRepository;
import co.com.masiv.service.IRouletteService;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
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
            .status(rouletteDto.isMoment())
            .build();
    roulette = rouletteRepository.save(roulette);

    return roulette.getId();
  }

  @Override
  public boolean activateRoulette(String id) {
    Function<Roulette, Boolean> activateRoulette =
        t -> {
          t.setStatus(true);
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
            x -> {
              return RouletteDto.builder()
                  .code(x.getId())
                  .description(x.getDescription())
                  .moment(x.isStatus())
                  .tittle(x.getName())
                  .build();
            })
        .collect(Collectors.toList());
  }
}
