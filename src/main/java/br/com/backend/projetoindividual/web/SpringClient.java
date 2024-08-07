package br.com.backend.projetoindividual.web;

import br.com.backend.projetoindividual.domain.Dto.CharacterCreateDto;
import br.com.backend.projetoindividual.domain.Dto.CharacterResponseDto;
import br.com.backend.projetoindividual.domain.Dto.PageableDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SpringClient {

    private final WebClient webClient;

    public SpringClient(WebClient.Builder webClientBuilder, @Value("${character.api.url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Mono<CharacterResponseDto> saveCharacter(CharacterCreateDto dto) {
        return webClient.post()
                .uri("/save")
                .body(Mono.just(dto), CharacterCreateDto.class)
                .retrieve()
                .bodyToMono(CharacterResponseDto.class);
    }

    public Mono<CharacterResponseDto> updateCharacter(Long id, CharacterCreateDto dto) {
        return webClient.put()
                .uri("/update/{id}", id)
                .body(Mono.just(dto), CharacterCreateDto.class)
                .retrieve()
                .bodyToMono(CharacterResponseDto.class);
    }

    public Mono<CharacterResponseDto> getCharacterById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CharacterResponseDto.class);
    }

    public Mono<PageableDto> getAllCharacters(int page, int size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve()
                .bodyToMono(PageableDto.class);
    }

    public Mono<String> deleteCharacter(Long id) {
        return webClient.delete()
                .uri("/delete/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }
}


