package com.atsistemas.ejercicio3.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.ejercicio3.cliente.dto.PersonaDto;

@Controller
@RequestMapping(path = "/Persona")
public class Controlador {

	/*private Servicio servicioPersonas;

	@Autowired
	public Controlador(Servicio servicioPersonas) {
		this.servicioPersonas = servicioPersonas;
	}*/
	
	private RestTemplate restTemplate;
	
	@Autowired
	public Controlador(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@PostMapping
	public String insertar(
			//@ModelAttribute("persona") PersonaDto persona) {
			@RequestBody MultiValueMap<String, String> formParams) throws RestClientException, URISyntaxException {
			//@RequestParam PersonaDto persona) {
		
		PersonaDto personaDto = new PersonaDto(
											Long.parseLong(formParams.getFirst("persona.id")), 
											formParams.getFirst("persona.nombre"), 
											Integer.parseInt(formParams.getFirst("persona.edad")));
		//servicioPersonas.insertar(personaDto);
		
		restTemplate.postForEntity(new URI("http://localhost:8082/Persona"), personaDto, Void.class);
		
		return "redirect:Persona?nombre=" + personaDto.getNombre();
	}

	@GetMapping
	public String buscarPorNombre(@RequestParam String nombre, Model model) throws RestClientException, URISyntaxException {
		//List<PersonaDto> resultado = servicioPersonas.buscarPorNombre(nombre);
		
		ResponseEntity<List> resultado = restTemplate.getForEntity("http://localhost:8082/Persona?nombre={nombre}", List.class, nombre);

		model.addAttribute("resultado", resultado.getBody());
		
		return "persona/detalle";
	}

}
