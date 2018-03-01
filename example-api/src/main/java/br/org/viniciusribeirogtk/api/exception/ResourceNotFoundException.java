package br.org.viniciusribeirogtk.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4078858365284320370L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
