package br.com.backend.projetoindividual.web.controller;

import br.com.backend.projetoindividual.domain.Dto.CharacterCreateDto;
import br.com.backend.projetoindividual.domain.Dto.CharacterResponseDto;
import br.com.backend.projetoindividual.domain.Dto.mapper.CharacterMapper;
import br.com.backend.projetoindividual.domain.entities.Character;
import br.com.backend.projetoindividual.domain.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping("/save")
    public ResponseEntity<CharacterResponseDto> save(@RequestBody CharacterCreateDto dto){
        Character character = CharacterMapper.toCharacter(dto);
        characterService.save(character);

        return ResponseEntity
                .status(201)
                .body(CharacterMapper.toDto(character));
    }
}
