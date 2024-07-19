package br.com.backend.projetoindividual.domain.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CHARACTER")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "affiliation", nullable = false )
    private String affiliation;

    @Column( name = "lightsaberColor", nullable = false )
    private String lightsaberColor;

    @Column( name = "description", nullable = false )
    private String description;
}
