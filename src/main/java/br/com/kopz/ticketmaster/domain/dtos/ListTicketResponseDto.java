package br.com.kopz.ticketmaster.domain.dtos;

import br.com.kopz.ticketmaster.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketResponseDto {

  private UUID id;
  private TicketStatusEnum status;
  private ListTicketTypeResponseDto ticketType;

}
