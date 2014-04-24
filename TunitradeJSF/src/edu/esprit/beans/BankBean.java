package edu.esprit.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import edu.esprit.domain.entities.Bank;
import edu.esprit.service.Bank.BankServiceEJBLocal;


@ManagedBean(name = "bankBean")
@SessionScoped
public class BankBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BankServiceEJBLocal bankManagement;

	private Bank bank;
	private List<Bank> banks;
	private Bank selectedBank = new Bank();
	private Bank selected = new Bank();

	//Save methode
	public void saveBank() {
		// Persist project
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getMaximumSeverity() != null)

		{
			if (selectedBank != null) {
				bankManagement.add(getSelectedBank());
				loadBanks();
				clear();
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Paramétres manquantes",
						"Veuillez remplir les champs");
				FacesContext.getCurrentInstance().addMessage(null, message);

				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
			}
		}

	}

	public void save(ActionEvent actionEvent) {
		// Persist bank
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Bank " + selectedBank.getName()
						+ " ajouter avec succes"));

	}
	
	
	
	//update methode
	public void updateBank() {
		// Persist project

		if (getSelectedBank() != null) {
			bankManagement.update(getSelectedBank());
			clear();
			loadBanks();
		}
	}

	public void update(ActionEvent actionEvent) {
		// Persist bank
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Bank " + selectedBank.getName()
						+ " modifier avec succes"));

	}
	
	
	//delete methode

	public void deleteBank() {
		if (getSelectedBank().getId() != 0) {
			bankManagement.delete(getSelectedBank());
			clear();
			loadBanks();
		}

	}

	public void delete(ActionEvent actionEvent) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Bank " + selected.getName()
						+ " supprimer avec succes"));

	}

	
	//load list methode
	public void loadBanks() {
		banks.clear();
		getBank();
	}

	//clear list methode
	public void clear() {
		selectedBank = new Bank();
		selected = new Bank();
	}

	//Getters&Setters
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Bank> getBanks() {
		banks = bankManagement.findall();
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public Bank getSelectedBank() {
		return selectedBank;
	}

	public void setSelectedBank(Bank selectedBank) {
		this.selectedBank = selectedBank;
	}

	public Bank getSelected() {
		return selected;
	}

	public void setSelected(Bank selected) {
		this.selected = selected;
	}
}
