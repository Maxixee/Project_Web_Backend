package br.com.backend.projetoindividual.domain.service;

import br.com.backend.projetoindividual.domain.entities.Character;
import br.com.backend.projetoindividual.domain.exception.CharacterAlreadyExistsException;
import br.com.backend.projetoindividual.domain.exception.EntityNotFoundException;
import br.com.backend.projetoindividual.domain.exception.InvalidRegistrationInformationException;
import br.com.backend.projetoindividual.domain.repository.CharacterRepository;
import br.com.backend.projetoindividual.domain.repository.projection.CharacterProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public Character update(Long id, Character updatedCharacter) {
        Character existingCharacter = this.characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Character id=%s not found", id))
        );

        if (updatedCharacter.getName().isBlank() || updatedCharacter.getAffiliation().isBlank() || updatedCharacter.getLightsaberColor().isBlank()) {
            throw new InvalidRegistrationInformationException("Invalid update credentials (Name, Affiliation or Lightsaber Color)");
        }

        existingCharacter.setName(updatedCharacter.getName());
        existingCharacter.setAffiliation(updatedCharacter.getAffiliation());
        existingCharacter.setLightsaberColor(updatedCharacter.getLightsaberColor());

        this.characterRepository.save(existingCharacter);
        log.info("Updating character id={}: {}", id, existingCharacter);
        return existingCharacter;
    }

    @Transactional(readOnly = true)
    public Character findById(Long id) {
        return characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Character id=%s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<CharacterProjection> findAll(Pageable pageable) {
        return characterRepository.findAllPageable(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Character character = this.findById(id); // Reutiliza o método findById para verificar se o personagem existe
        this.characterRepository.delete(character);
        log.info("Deleting character id={}: {}", id, character);
    }
}
