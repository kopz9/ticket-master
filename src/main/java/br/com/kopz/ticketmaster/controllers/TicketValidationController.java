package br.com.kopz.ticketmaster.controllers;

import br.com.kopz.ticketmaster.domain.dtos.TicketValidationRequestDto;
import br.com.kopz.ticketmaster.domain.dtos.TicketValidationResponseDto;
import br.com.kopz.ticketmaster.domain.entities.TicketValidation;
import br.com.kopz.ticketmaster.domain.entities.TicketValidationMethod;
import br.com.kopz.ticketmaster.mappers.TicketValidationMapper;
import br.com.kopz.ticketmaster.services.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ticket-validations")
@RequiredArgsConstructor
public class TicketValidationController {

  private final TicketValidationService ticketValidationService;
  private final TicketValidationMapper ticketValidationMapper;

  @PostMapping
  public ResponseEntity<TicketValidationResponseDto> validateTicket(
      @RequestBody TicketValidationRequestDto ticketValidationRequestDto
  ) {
    TicketValidationMethod method = ticketValidationRequestDto.getMethod();
    TicketValidation ticketValidation;

    if (TicketValidationMethod.MANUAL.equals(method)) {
      ticketValidation = ticketValidationService.validateTicketByTicketId(
          ticketValidationRequestDto.getId());
    } else {
      ticketValidation = ticketValidationService.validateTicketByQrCode(
          ticketValidationRequestDto.getId()
      );
    }

    return ResponseEntity.ok(ticketValidationMapper.toTicketValidationResponseDto(ticketValidation));
  }


}
