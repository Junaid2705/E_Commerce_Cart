package org.project.services;

import java.util.List;

public interface OrdersService {

	boolean isTransaction(String uName, String pStatus, String pType);
	List<String> showTransaction(String uName);
	List<String> showFinalBill(String uName);
}
