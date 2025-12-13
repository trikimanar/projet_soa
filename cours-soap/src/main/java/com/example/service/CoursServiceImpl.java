package com.example.service;

import com.example.model.Cours;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@HandlerChain(file = "handler-chain.xml")
@WebService(endpointInterface = "com.example.service.CoursService")
public class CoursServiceImpl implements CoursService {

	private static List<Cours> coursList = new ArrayList<>();

	@Override
	public String ajouterCours(Cours c) {
		coursList.add(c);
		return "Cours ajouté avec succès !";
	}

	@Override
	public List<Cours> listerCours() {
		return coursList;
	}

	@Override
	public Cours chercherCours(int id) {
		return coursList.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
	}

	@Override
	public String supprimerCours(int id) {
		coursList.removeIf(c -> c.getId() == id);
		return "Cours supprimé !";
	}
}
