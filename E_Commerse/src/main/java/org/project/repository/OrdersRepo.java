package org.project.repository;

import java.util.List;

public interface OrdersRepo {

	boolean isTransaction(String uName, String pStatus, String pType);
	 List<String> showTransaction(String uName);
	 List<String> showFinalBill(String username);
}
