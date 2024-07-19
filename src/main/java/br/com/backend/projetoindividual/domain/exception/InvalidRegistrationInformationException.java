package br.com.backend.projetoindividual.domain.exception;

public class InvalidRegistrationInformationException extends RuntimeException{
    public InvalidRegistrationInformationException(String message){
        super(message);
    }
}
