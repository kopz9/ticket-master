package br.com.kopz.ticketmaster.services.impl;

import br.com.kopz.ticketmaster.domain.entities.QrCode;
import br.com.kopz.ticketmaster.domain.entities.Ticket;
import br.com.kopz.ticketmaster.repositories.QrCodeRepository;
import br.com.kopz.ticketmaster.services.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {

  private final QrCodeRepository qrCodeRepository;

  @Override
  public QrCode generateQrCode(Ticket ticket) {
    return null;
  }
}
