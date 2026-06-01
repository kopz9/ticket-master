package br.com.kopz.ticketmaster.controllers;

import br.com.kopz.ticketmaster.domain.dtos.ErrorDto;
import br.com.kopz.ticketmaster.exceptions.*;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(TicketNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTicketNotFoundException(TicketNotFoundException ex) {
    log.error("Caught NotFoundException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("Ticket Not Found");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TicketSoldOutException.class)
  public ResponseEntity<ErrorDto> handleTicketSoldOutException(TicketSoldOutException ex) {
    log.error("Caught TicketSoldOutException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("Tickets are sold out for this ticket type");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(QrCodeNotFoundException.class)
  public ResponseEntity<ErrorDto> handleQrCodeNotFoundException(QrCodeNotFoundException ex) {
    log.error("Caught QrCodeNotFoundException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("QrCode not found");

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(TicketTypeNotFoundException.class)
  public ResponseEntity<ErrorDto> handleQrCodeGenerationException(QrCodeGenerationException ex) {
    log.error("Caught QrCodeGenerationException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("Unable to generate QR Code");

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(TicketTypeNotFoundException.class)
  public ResponseEntity<ErrorDto> handleEventUpdateException(EventUpdateException ex) {
    log.error("Caught EventUpdateException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("Unable to update event");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(TicketTypeNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTicketTypeNotFoundException(TicketTypeNotFoundException ex) {
    log.error("Caught TicketTypeNotFoundException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("Ticket type not found");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(EventNotFoundException.class)
  public ResponseEntity<ErrorDto> handleEventNotFoundException(EventNotFoundException ex) {
    log.error("Caught EventNotFoundException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("Event not found");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
    log.error("Caught UserNotFoundException", ex);
    var errorDto = new ErrorDto();
    errorDto.setError("An error has occurred");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

    log.error("Caught MethodArgumentNotValidException", exception);
    var errorDto = new ErrorDto();

    BindingResult bindingResult = exception.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    String errorMessage = fieldErrors.stream()
        .findFirst()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .orElse("Validation error occurred");

    errorDto.setError(errorMessage);

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintDeclarationException.class)
  public ResponseEntity<ErrorDto> handleConstraintValidation(
      ConstraintViolationException exception
  ) {

    log.error("ConstraintViolationException", exception);
    var errorDto = new ErrorDto();

    String errorMessage = exception.getConstraintViolations()
        .stream().findFirst()
        .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
        .orElse("ConstraintViolation occurred");

    errorDto.setError(errorMessage);

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception exception) {
    log.error("Caught exception", exception);
    var errorDto = new ErrorDto();
    errorDto.setError("An unknown error occurred");

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
