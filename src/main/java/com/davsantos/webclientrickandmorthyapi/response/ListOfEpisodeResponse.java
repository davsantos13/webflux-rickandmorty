package com.davsantos.webclientrickandmorthyapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ListOfEpisodeResponse {

	private List<EpisodeResponse> results;
}
