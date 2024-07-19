package br.com.backend.projetoindividual.domain.Dto.mapper;

import br.com.backend.projetoindividual.domain.Dto.CharacterCreateDto;
import br.com.backend.projetoindividual.domain.Dto.CharacterResponseDto;
import br.com.backend.projetoindividual.domain.entities.Character;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CharacterMapper {

    public static Character toCharacter(CharacterCreateDto dto) {
        return new ModelMapper().map(dto, Character.class);
    }

    public static CharacterResponseDto toDto(Character character) {
        return new ModelMapper().map(character, CharacterResponseDto.class);
    }
}
