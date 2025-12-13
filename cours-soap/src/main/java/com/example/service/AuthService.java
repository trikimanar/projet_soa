package com.example.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

@WebService
public class AuthService {

	private static final String SECRET_KEY = "maCleSecreteSuperSecurisee";

	@WebMethod
	public String login(String username, String password) {

		if ("admin".equals(username) && "admin123".equals(password)) {
			return Jwts.builder().setSubject(username).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1h
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		} else {
			return null;
		}
	}
}
