package br.com.kopz.ticketmaster.mappers;

import br.com.kopz.ticketmaster.domain.CreateEventRequest;
import br.com.kopz.ticketmaster.domain.CreateTicketTypeRequest;
import br.com.kopz.ticketmaster.domain.UpdateEventRequest;
import br.com.kopz.ticketmaster.domain.dtos.*;
import br.com.kopz.ticketmaster.domain.entities.Event;
import br.com.kopz.ticketmaster.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

  CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

  CreateEventRequest fromDto(CreateEventRequestDto dto);

  CreateEventResponseDto toDto(Event event);

  ListEventTicketTypeResponseDto toDto(TicketType ticketType);

  ListEventResponseDto toListEventResponseDto(Event event);

  GetEventDetailsResponseDto toGetEventDetailsTicketTypeResponseDto(TicketType ticketType);

  GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

  UpdateTicketTypeRequestDto fromDto(UpdateTicketTypeRequestDto dto);

  UpdateEventRequest fromDto(UpdateEventRequestDto dto);

  UpdateTicketTypeResponseDto toUpdateTicketTypeResponse(TicketType ticketType);

  UpdateEventResponseDto toUpdateEventResponseDto(Event event);

  ListPublishedEventResponseDto toListPublishedEventResponseDto(Event event);

  GetPublishedEventDetailsTicketTypesResponseDto toGetPublishedEventDetailsTicketTypesResponseDto(TicketType ticketType);

  GetPublishedEventDetailsResponseDto toGetPublishedEventDetailsResponseDto(Event event);

}
