package com.coremei.socketdemo.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.coremei.socketdemo.domain.Mensaje;

@Controller
public class ChatController {

	private String colores[] = { "red", "blue", "magenta", "green", "orange", "purple" };

	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibirMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if (mensaje.getTipo().equals("nuevo_usuario")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
		}
		// mensaje.setTexto("Recibido por el broker: " + mensaje.getTexto());
		return mensaje;
	}

	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String notificarUsuarioEscribiendo(String usuario) {
		return usuario.concat(" está escribiendo...");
	}
}
