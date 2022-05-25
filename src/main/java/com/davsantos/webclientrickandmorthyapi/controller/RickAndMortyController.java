package com.davsantos.webclientrickandmorthyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davsantos.webclientrickandmorthyapi.client.RickAndMorthyClient;
import com.davsantos.webclientrickandmorthyapi.response.CharacterResponse;
import com.davsantos.webclientrickandmorthyapi.response.ListOfEpisodeResponse;
import com.davsantos.webclientrickandmorthyapi.response.LocationResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rickandmorty")
@AllArgsConstructor
public class RickAndMortyController {

	private RickAndMorthyClient rickMortyClient;

	@GetMapping("/characters/{id}")
	public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
		return rickMortyClient.findACharacterById(id);
	}

	@GetMapping("/locations/{id}")
	public Mono<LocationResponse> getLocationById(@PathVariable String id) {
		return rickMortyClient.findALocationById(id);
	}

	@GetMapping("/episodes")
	public Flux<ListOfEpisodeResponse> getAllEpisodes() {
		return rickMortyClient.findAllEpisodes();
	}
}
