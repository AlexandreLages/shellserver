package br.com.caelum.vraptor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.UserDAO;
import br.com.caelum.vraptor.model.User;
import br.com.caelum.vraptor.view.Results;

@Controller
public class UserController {
	
	private Result result;
	
	protected UserController() {
		this(null);
	}
	
	@Inject
	public UserController(Result result) {
		this.result = result;
	}
	
	@Consumes({"application/json", "application/xml"})
	@Post("/user/login")
	public void login(User user){
		try {
			if(UserDAO.verify(user)) {
				result.use(Results.json()).withoutRoot().from("Usuário existente no banco de dados");
				result.use(Results.status()).ok();
			} else {
				result.use(Results.json()).withoutRoot().from("Usuário inexistente no banco de dados");
				result.use(Results.status()).conflict();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Consumes({"application/json", "application/xml"})
	@Post({"/user", "/user/"})
	public void insert(User user){
		try {
			if(UserDAO.insert(user)){
				result.use(Results.status()).ok();
			} else {
				result.use(Results.status()).conflict();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Get("/user/list")
	public void list(){
		try {
			ArrayList<User> users = (ArrayList<User>) UserDAO.list();
			result.use(Results.json()).withoutRoot().from(users).serialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Consumes({"application/json", "application/xml"})
	@Post({"/user/editar", "/user/editar/"})
	public void pass(User user, String newPassword){
		try {
			if(UserDAO.updateUser(user, newPassword)) {
				result.use(Results.status()).ok();
			} else {
				result.use(Results.status()).conflict();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Consumes({"application/json", "application/xml"})
	@Post({"/user/delete", "/user/delete/"})
	public void delete(User user){
		try {
			if(UserDAO.deleteUser(user)) {
				result.use(Results.status()).ok();
			} else {
				result.use(Results.status()).conflict();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}