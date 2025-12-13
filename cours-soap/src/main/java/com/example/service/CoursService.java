package com.example.service;

import com.example.model.Cours;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CoursService {

	@WebMethod
	String ajouterCours(Cours c);

	@WebMethod
	List<Cours> listerCours();

	@WebMethod
	Cours chercherCours(int id);

	@WebMethod
	String supprimerCours(int id);
}
