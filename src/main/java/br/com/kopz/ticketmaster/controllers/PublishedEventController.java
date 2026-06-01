package br.com.kopz.ticketmaster.controllers;

import br.com.kopz.ticketmaster.domain.dtos.GetPublishedEventDetailsResponseDto;
import br.com.kopz.ticketmaster.domain.dtos.ListPublishedEventResponseDto;
import br.com.kopz.ticketmaster.domain.entities.Event;
import br.com.kopz.ticketmaster.mappers.EventMapper;
import br.com.kopz.ticketmaster.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventController {

  private final EventService eventService;
  private final EventMapper eventMapper;

  @GetMapping
  public ResponseEntity<Page<ListPublishedEventResponseDto>> listPublishedEvents(
      @RequestParam(required = false) String q,
      Pageable pageable) {

    Page<Event> events;
    if (q != null && !q.trim().isEmpty()) {
      events = eventService.searchPublishedEvents(q, pageable);
    } else {
      events = eventService.listPublishedEvents(pageable);
    }

    return ResponseEntity.ok(
        events.map(eventMapper::toListPublishedEventResponseDto)
    );
  }

  @GetMapping("/{eventId}")
  public ResponseEntity<GetPublishedEventDetailsResponseDto> getPublishedEventDetails(
      @PathVariable UUID eventId
  ) {
    return eventService.getPublishedEvents(eventId)
        .map(eventMapper::toGetPublishedEventDetailsResponseDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }


}
