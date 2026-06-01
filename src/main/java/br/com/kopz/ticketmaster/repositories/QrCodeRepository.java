package br.com.kopz.ticketmaster.repositories;

import br.com.kopz.ticketmaster.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {
  Optional<QrCode> findByTicketIdAndTicketPurchaseId(UUID ticketId, UUID ticketPurchaseId);

  Optional<QrCode> findByIdAndStatus(UUID id, QrCodeStatusEnum status);

  TicketValidation validateTicket(Ticket ticket, TicketValidationMethod ticketValidationMethod);
}
