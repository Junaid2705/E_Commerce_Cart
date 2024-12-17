package org.project.clientApp;

import org.project.services.*;

//import org.project.repository.*;
public class E_Commerce_Cart_System {
	public static void main(String[] args) {
		ProductServiceI psi = new ProductServiceC();
		if (psi.addProdUct()) {
			System.out.println("Success");
		} else {
			System.out.println("Failed");
		}
	}
}
