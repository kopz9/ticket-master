package br.com.kopz.ticketmaster.domain.dtos;

import br.com.kopz.ticketmaster.domain.entities.TicketValidationMethod;
import br.com.kopz.ticketmaster.domain.entities.TicketValidationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationResponseDto {

  private UUID ticketId;
  private TicketValidationStatusEnum status;

}
