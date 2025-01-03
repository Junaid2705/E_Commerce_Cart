package org.project.services;

import java.util.List;

import org.project.repository.OrdersRepo;
import org.project.repository.OrdersRepoImpl;

public class OrdersServiceImpl implements OrdersService {

	OrdersRepo ordRepo = new OrdersRepoImpl();
	@Override
	public boolean isTransaction(String uName, String pStatus, String pType) {
		// TODO Auto-generated method stub
		return ordRepo.isTransaction( uName,  pStatus,  pType);
	}
	
	@Override
	public List<String> showTransaction(String uName) {
		// TODO Auto-generated method stub
		return ordRepo.showTransaction(uName);
	}
	
	@Override
	public List<String> showFinalBill(String uName) {
		// TODO Auto-generated method stub
		return ordRepo.showTransaction(uName);
	}
	

}
