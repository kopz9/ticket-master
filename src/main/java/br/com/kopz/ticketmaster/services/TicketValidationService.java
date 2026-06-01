package br.com.kopz.ticketmaster.services;

import br.com.kopz.ticketmaster.domain.entities.TicketValidation;

import java.util.UUID;

public interface TicketValidationService {

  TicketValidation validateTicketByQrCode(UUID qrCodeId);
  TicketValidation validateTicketByTicketId(UUID ticketId);
}
