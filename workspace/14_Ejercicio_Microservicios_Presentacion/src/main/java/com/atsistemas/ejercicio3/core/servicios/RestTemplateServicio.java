package com.atsistemas.ejercicio3.core.servicios;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.ejercicio3.cliente.dto.PersonaDto;

@Service
public class RestTemplateServicio implements Servicio {

	private RestTemplate restTemplate;

	@Autowired
	public RestTemplateServicio(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Override
	public void insertar(PersonaDto personaDto) {
		try {
			restTemplate.postForEntity(new URI("http://localhost:8082/Persona"), personaDto, Void.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PersonaDto> buscarPorNombre(String nombre) {
		return restTemplate.getForEntity("http://localhost:8082/Persona?nombre={nombre}", List.class, nombre).getBody();
	}

}
