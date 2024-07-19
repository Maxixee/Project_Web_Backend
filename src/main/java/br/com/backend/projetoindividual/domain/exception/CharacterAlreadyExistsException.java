package br.com.backend.projetoindividual.domain.exception;

public class CharacterAlreadyExistsException extends RuntimeException{
    public CharacterAlreadyExistsException(String message){
        super(message);
    }
}
