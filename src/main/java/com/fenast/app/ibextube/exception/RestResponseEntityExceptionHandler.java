package com.fenast.app.ibextube.exception;

import com.fenast.app.ibextube.constants.MessageType;
import com.fenast.app.ibextube.http.ResponseMessageBase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>("Access Denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ UserNotFoundException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseMessageBase> userNotFound(HttpServletRequest request, UserNotFoundException e) {
        ResponseMessageBase respMsgBase = new ResponseMessageBase();
        respMsgBase.setMessage(e.getErrorMessage());
        respMsgBase.setMessage_type(MessageType.Message_ERROR.getType());
        respMsgBase.setSuccess(false);
        return getErrorResponse(request, e.getErrorMessage(), HttpStatus.PRECONDITION_FAILED, MessageType.Message_ERROR.getType());
        //return new ResponseEntity<Object>("User Not Found Exception", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ UserExistException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> userExist(HttpServletRequest request, UserExistException e) {
        return new ResponseEntity<Object>("User alreay exist with this email or phone!", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ InvalidVerificationTokenException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseMessageBase> invalidVerificationToken(HttpServletRequest request, InvalidVerificationTokenException e) {
        ResponseMessageBase respMsgBase = new ResponseMessageBase();
        respMsgBase.setMessage(e.getErrorMessage());
        respMsgBase.setMessage_type(MessageType.Message_ERROR.getType());
        respMsgBase.setSuccess(false);
        return getErrorResponse(request, e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR, MessageType.Message_ERROR.getType());
    }

    ResponseEntity<ResponseMessageBase> getErrorResponse(HttpServletRequest request, String msg, HttpStatus status, String msgType) {
        return new ResponseEntity<ResponseMessageBase>(getErrorResponseMessage(request, msg, status, msgType), HttpStatus.OK);
    }

    ResponseMessageBase getErrorResponseMessage(HttpServletRequest request, String msg, HttpStatus status, String messageType) {
        ResponseMessageBase respMsgBase = new ResponseMessageBase();
        respMsgBase.setError(msg);
        respMsgBase.setStatusCode(status.value());
        respMsgBase.setSuccess(false);
        respMsgBase.setMessage_type(messageType);
        return respMsgBase;
    }
}
