package br.com.kopz.ticketmaster.services;

import br.com.kopz.ticketmaster.domain.entities.QrCode;
import br.com.kopz.ticketmaster.domain.entities.Ticket;

public interface QrCodeService {

  QrCode generateQrCode(Ticket ticket);

}
