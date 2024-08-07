package br.com.backend.projetoindividual.domain.Dto;

import lombok.*;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCreateDto {
    private String name;
    private String affiliation;
    private String lightsaberColor;
    private String description;
}
