package co.com.masiv.dto;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

@Data
public class BetDto {

  @NonNull private String codeRoulette;

  @Min(value = 0, message = "Number minimum 0")
  @Max(value = 36, message = "Number maximum 36")
  private int number;

  private String color;

  @NonNull
  @Min(value = 1, message = "Minimum stake 1")
  @Max(value = 10000, message = "Maximum stake 100000")
  private double value;

  private Date creationDate;

  private double amountPaid;
  private boolean winner;
}
