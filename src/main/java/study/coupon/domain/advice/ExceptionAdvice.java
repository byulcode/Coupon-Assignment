package study.coupon.domain.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.coupon.domain.advice.exception.NotFoundIdException;
import study.coupon.domain.dto.ErrorResponse;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse defaultException() {
        return new ErrorResponse("defaultError");
    }

    @ExceptionHandler(NotFoundIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse notFoundIdException() {
        return new ErrorResponse("Not found Id");
    }
}
