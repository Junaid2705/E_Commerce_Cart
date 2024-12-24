package org.project.repository;

import org.project.models.LoginModel;

public interface LoginRepo {

	 boolean isUser(LoginModel login);
//	 boolean registerUser(LoginModel login);
	  boolean registerUser(LoginModel login);
}