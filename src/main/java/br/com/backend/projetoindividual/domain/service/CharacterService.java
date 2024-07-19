package br.com.backend.projetoindividual.domain.service;

import br.com.backend.projetoindividual.domain.entities.Character;
import br.com.backend.projetoindividual.domain.exception.CharacterAlreadyExistsException;
import br.com.backend.projetoindividual.domain.exception.InvalidRegistrationInformationException;
import br.com.backend.projetoindividual.domain.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Character save(Character character){

        Optional<Character> usersOptional = this.characterRepository.findByName(character.getName());
        if(usersOptional.isPresent()){
            throw new CharacterAlreadyExistsException("Character already exists");
        }
        if(character.getName().isBlank() || character.getAffiliation().isBlank() || character.getLightsaberColor().isBlank()){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Name, Affiliation or Lightsaber Color)");
        }

        this.characterRepository.save(character);

        log.info("Saving character: {}", character);
        return character;
    }
}
