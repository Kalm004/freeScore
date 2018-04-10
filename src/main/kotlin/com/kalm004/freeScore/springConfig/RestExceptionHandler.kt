package com.kalm004.freeScore.springConfig

import com.kalm004.freeScore.exceptions.EntityNotFoundException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    protected fun handleEntityNotFound(ex: EntityNotFoundException) = ResponseEntity(ex.entityName, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(BadCredentialsException::class)
    protected fun handleBadCredential(ex: BadCredentialsException) = ResponseEntity(ex.message, HttpStatus.FORBIDDEN)
}