package com.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.namespace.QName;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class JWTAuthHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String SECRET_KEY = "maCleSecreteSuperSecurisee";

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (!outbound) {
			Map<?, ?> headers = (Map<?, ?>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
			if (headers == null || !headers.containsKey("Authorization")) {
				throw new RuntimeException("JWT manquant");
			}

			String token = ((List<String>) headers.get("Authorization")).get(0);
			if (!token.startsWith("Bearer ")) {
				throw new RuntimeException("JWT invalide");
			}

			token = token.substring(7);

			try {
				Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			} catch (SignatureException e) {
				throw new RuntimeException("JWT invalide ou expiré");
			}
		}

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return new HashSet<>();
	}
}
