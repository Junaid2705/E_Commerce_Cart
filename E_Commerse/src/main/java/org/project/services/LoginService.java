package org.project.services;

import org.project.models.LoginModel;

public interface LoginService {

	public boolean isUser(LoginModel login);
	 public boolean registerUser(LoginModel login);
}