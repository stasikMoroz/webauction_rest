package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.ErrorResponseDto;
import com.vironit.businessauction.exception.*;
import com.vironit.businessauction.logger.LoggerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);


    @ExceptionHandler({AdminNotFoundException.class, BidNotFoundException.class, FeedbackNotFoundException.class,
            LotNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BidNotHaveNewStatusException.class})
    public ResponseEntity<ErrorResponseDto> handleBidNotHaveNewStatusException(BidNotHaveNewStatusException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LoginAlredyExistException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginAlredyExistException(LoginAlredyExistException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LoginOrPasswordIncorrectException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginOrPasswordIncorrectException(LoginOrPasswordIncorrectException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LotCanNotBeActivatedException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginOrPasswordIncorrectException(LotCanNotBeActivatedException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({LotNotHavePauseOrNewStatusException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginOrPasswordIncorrectException(LotNotHavePauseOrNewStatusException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({TradeException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginOrPasswordIncorrectException(TradeException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotHaveEnoughMoneyPayForLotException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginOrPasswordIncorrectException(UserNotHaveEnoughMoneyPayForLotException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({WalletException.class})
    public ResponseEntity<ErrorResponseDto> handleLoginOrPasswordIncorrectException(WalletException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> defaultExceptionHandler(Exception e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
