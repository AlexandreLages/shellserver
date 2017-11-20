package br.com.caelum.vraptor.dao;

import java.util.ArrayList;

import br.com.caelum.vraptor.model.Comand;

public class ComandDAO {

	public static ArrayList<Comand> list(){
		ArrayList<Comand> comands = new ArrayList<Comand>();
		
		Comand add = new Comand();
		Comand help = new Comand();
		Comand delete = new Comand();
		Comand pass = new Comand();
		Comand verify = new Comand();
		Comand list = new Comand();
		
		add.setName("Adicionar");
		add.setDescription("Adicione um novo usuário na base de dados.");
		
		list.setName("Listar");
		list.setDescription("Liste os usuários cadastrados no arquivo.");
		
		help.setName("Ajuda");
		help.setDescription("Liste os comandos suportados pela aplicação.");
		
		delete.setName("Deletar");
		delete.setDescription("Remova um usuário da base de dados.");
		
		pass.setName("Alterar");
		pass.setDescription("Altere a senha de um usuário na base de dados.");
		
		verify.setName("Verificar");
		verify.setDescription("Verifica se existe um usuário no arquivo.");
		
		comands.add(add);
		comands.add(list);
		comands.add(help);
		comands.add(delete);
		comands.add(pass);
		comands.add(verify);
		
		return comands;
	}
}
