package br.com.backend.projetoindividual.web.controller;

import br.com.backend.projetoindividual.domain.Dto.CharacterCreateDto;
import br.com.backend.projetoindividual.domain.Dto.CharacterResponseDto;
import br.com.backend.projetoindividual.domain.Dto.PageableDto;
import br.com.backend.projetoindividual.domain.Dto.mapper.CharacterMapper;
import br.com.backend.projetoindividual.domain.Dto.mapper.PageableMapper;
import br.com.backend.projetoindividual.domain.entities.Character;
import br.com.backend.projetoindividual.domain.repository.projection.CharacterProjection;
import br.com.backend.projetoindividual.domain.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
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

    @PutMapping("/update/{id}")
    public ResponseEntity<CharacterResponseDto> update(@PathVariable Long id, @RequestBody CharacterCreateDto dto) {
        Character updatedCharacter = CharacterMapper.toCharacter(dto);
        Character character = characterService.update(id, updatedCharacter);
        return ResponseEntity.ok(CharacterMapper.toDto(character));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDto> getById(@PathVariable Long id) {
        Character character = characterService.findById(id);

        return ResponseEntity.ok(CharacterMapper.toDto(character));
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAll(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable) {
        Page<CharacterProjection> characters = characterService.findAll(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(characters));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.ok("Character deleted");
    }
}
