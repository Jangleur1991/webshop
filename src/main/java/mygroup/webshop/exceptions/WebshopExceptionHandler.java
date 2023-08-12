package mygroup.webshop.exceptions;

import lombok.extern.log4j.Log4j2;
import mygroup.webshop.model.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j2
public class WebshopExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest request, Exception exception) {
        String message = exception.getMessage();
        log.error(message);
        ErrorInfo errorInfo = new ErrorInfo(message, request.getRequestURI());
        return ResponseEntity.internalServerError().body(errorInfo);
    }
}
