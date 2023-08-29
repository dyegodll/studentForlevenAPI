package com.forleven.student.controllers.exceptions;

import java.io.Serializable;

//classe para salvar os erros de validação dos campos específicos
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName; //campo com erro
	private String message; // mensagem de erro do campo

	public FieldMessage() {
	}

	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
