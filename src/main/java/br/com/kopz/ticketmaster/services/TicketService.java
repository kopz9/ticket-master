package br.com.kopz.ticketmaster.services;

import br.com.kopz.ticketmaster.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TicketService {

  Page<Ticket> listTicketsForUser(UUID purchaserId, Pageable pageable);

  Optional<Ticket> getTicketForUser(UUID userId, UUID ticketId);
}
