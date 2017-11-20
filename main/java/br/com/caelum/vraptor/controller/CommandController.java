package br.com.caelum.vraptor.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ComandDAO;
import br.com.caelum.vraptor.model.Comand;
import br.com.caelum.vraptor.view.Results;

@Controller
public class CommandController {

	private Result result;
	
	protected CommandController(){
		this(null);
	}
	
	@Inject
	public CommandController(Result result) {
		this.result = result;
	}
	
	@Get("/command/help")
	public void list(){
		ArrayList<Comand> comands = ComandDAO.list();
		result.use(Results.json()).withoutRoot().from(comands).serialize();
	}
}