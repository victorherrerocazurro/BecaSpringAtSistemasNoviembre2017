package com.atsistemas.jms.core.servicios;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MensajesJmsListener {

	@JmsListener(destination="mensajes")
	public void procesarMensaje(String mensaje) {
		System.out.println("Procesando el mensaje: " + mensaje);
	}
	
}
