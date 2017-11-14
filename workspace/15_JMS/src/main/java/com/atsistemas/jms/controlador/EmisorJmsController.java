package com.atsistemas.jms.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmisorJmsController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	//El String que se retorna no es una clave, sino es un dato de tipo String
	@RequestMapping(path="/Enviar")
	public String enviarMensaje(@RequestParam String mensaje) {
		
		//componente que me permite comunicar con el JMS
		jmsTemplate.convertAndSend("mensajes", mensaje);
		
		return "Mensaje enviado: " + mensaje;
	}
	
}
