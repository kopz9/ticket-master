package br.com.kopz.ticketmaster.services.impl;

import br.com.kopz.ticketmaster.domain.entities.Ticket;
import br.com.kopz.ticketmaster.domain.entities.TicketStatusEnum;
import br.com.kopz.ticketmaster.domain.entities.TicketType;
import br.com.kopz.ticketmaster.domain.entities.User;
import br.com.kopz.ticketmaster.exceptions.TicketSoldOutException;
import br.com.kopz.ticketmaster.exceptions.TicketTypeNotFoundException;
import br.com.kopz.ticketmaster.exceptions.UserNotFoundException;
import br.com.kopz.ticketmaster.repositories.TicketRepository;
import br.com.kopz.ticketmaster.repositories.TicketTypeRepository;
import br.com.kopz.ticketmaster.repositories.UserRepository;
import br.com.kopz.ticketmaster.services.QrCodeService;
import br.com.kopz.ticketmaster.services.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

  private final UserRepository userRepository;
  private final TicketRepository ticketRepository;
  private final TicketTypeRepository ticketTypeRepository;
  private final QrCodeService qrCodeService;

  @Override
  @Transactional
  public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
        String.format("User with ID %s not found", userId)
    ));

    TicketType ticketType = ticketTypeRepository.findById(ticketTypeId).orElseThrow(() -> new TicketTypeNotFoundException(
        String.format("Ticket type with ID %s not found", ticketTypeId)
    ));

    int purchasedTickets = ticketRepository.countByTicketTypeId(ticketType.getId());
    Integer totalAvailable = ticketType.getTotalAvailable();

    if (purchasedTickets + 1 > totalAvailable) {
      throw new TicketSoldOutException("");
    }

    var ticket = new Ticket();
    ticket.setStatus(TicketStatusEnum.PURCHASED);
    ticket.setTicketType(ticketType);
    ticket.setPurchaser(user);

    Ticket savedTicket = ticketRepository.save(ticket);

    return ticketRepository.save(savedTicket);
  }
}
