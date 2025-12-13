package com.example.publisher;

import com.example.service.CoursServiceImpl;
import javax.xml.ws.Endpoint;

public class ServerPublisher {

	public static void main(String[] args) {
		System.out.println("Démarrage du service SOAP...");
		Endpoint.publish("http://localhost:8085/ws/cours", new CoursServiceImpl());
		System.out.println("Service disponible sur : http://localhost:8085/ws/cours?wsdl");
	}
}
