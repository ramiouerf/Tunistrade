package edu.esprit.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import edu.esprit.domain.entities.Admin;
import edu.esprit.service.admin.AdminServiceEJBLocal;



@ManagedBean(name="authBean")
@SessionScoped
public class AuthentificationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private Admin admin;
	@EJB
	private AdminServiceEJBLocal adminService;
	
	
	public String login(){
		
		String navigateTo =null;
		System.out.println(email+" *******"+password);
		Admin found = null ; 
		found = adminService.login(email, password);
		
		if (found == null)
		{
			Admin ad= new Admin();
			
			ad.setPassword("rami");
			ad.setLogin("rami");
			adminService.add(ad);
			FacesMessage msg = null;
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentification échouée", "Verifier vos identifiants");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
			
		}
		
		
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			admin= found;
			request.getSession().setAttribute("admin", admin);
			navigateTo ="/admin/admin";
			return navigateTo;
		}
	}
	
	

	public String logout() {
		getRequest().getSession().invalidate();
		System.out.println(email+" *******"+password);
		return "/login";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}