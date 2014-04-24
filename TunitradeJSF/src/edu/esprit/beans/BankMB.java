package edu.esprit.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.esprit.domain.entities.Bank;
import edu.esprit.service.Bank.BankServiceEJBLocal;




@ManagedBean
@ViewScoped
public class BankMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Bank> banks;
	private Bank bank;
	
	@EJB
	private BankServiceEJBLocal bankServiceEJBLocal;
	
	
	public BankMB() {
		bank = new Bank();
	  banks = new ArrayList<Bank>();			
	}
	
	
	
	public List<Bank> getBanks() {
		banks=bankServiceEJBLocal.findall();
		return banks;
	}
	public void setBanks(List<Bank> banks) {
		
	
		this.banks = banks;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	

	public String doAddBank() {
		String navigateTo = "";
		try {
			bankServiceEJBLocal.add(bank);
			navigateTo = " Ajoutersucces";
		} catch (Exception e) {
			navigateTo = "Erruer";
		}
		return navigateTo;
	}
	public String doSuppBank() {
		String navigateTo = "";
		try {
			bankServiceEJBLocal.delete(bank);
			navigateTo = " supprimsucces";
		} catch (Exception e) {
			navigateTo = "Erruer";
		}
		return navigateTo;
	}
	
}
