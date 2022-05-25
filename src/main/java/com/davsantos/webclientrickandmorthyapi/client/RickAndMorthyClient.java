package com.davsantos.webclientrickandmorthyapi.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.davsantos.webclientrickandmorthyapi.response.CharacterResponse;
import com.davsantos.webclientrickandmorthyapi.response.ListOfEpisodeResponse;
import com.davsantos.webclientrickandmorthyapi.response.LocationResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMorthyClient {

	private final WebClient webClient;

	public RickAndMorthyClient(WebClient.Builder builder) {
		webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
	}

	public Mono<CharacterResponse> findACharacterById(String id) {
		log.info("Buscando personagem com id [{}]", id);

		return webClient
			.get()
			.uri("/character/" + id)
			.accept(APPLICATION_JSON)
			.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						e -> Mono.error(new RuntimeException("Verifique os parametros")))
			.bodyToMono(CharacterResponse.class);
	}
	
	public Mono<LocationResponse> findALocationById(String id) {
		log.info("Buscando localizacao com o id [{}]", id);
		
		return webClient
				.get()
				.uri("/location/" + id)
				.accept(APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, e -> Mono.error(new RuntimeException("Verifique os parametros")))
				.bodyToMono(LocationResponse.class);
	}

	public Flux<ListOfEpisodeResponse> findAllEpisodes() {
		log.info("Buscando todos os episodios");
		
		return webClient
				.get()
				.uri("/episode/")
				.accept(APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, e -> Mono.error(new RuntimeException("Verifique os parametros")))
				.bodyToFlux(ListOfEpisodeResponse.class);
	}

}
