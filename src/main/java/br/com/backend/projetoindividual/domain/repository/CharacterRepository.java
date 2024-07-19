package br.com.backend.projetoindividual.domain.repository;

import br.com.backend.projetoindividual.domain.entities.Character;
import br.com.backend.projetoindividual.domain.repository.projection.CharacterProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("select c from Character c")
    Page<CharacterProjection> findAllPageable(Pageable pageable);

    public Optional<Character> findByName(String name);
}
