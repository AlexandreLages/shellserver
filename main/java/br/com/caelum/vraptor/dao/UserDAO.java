package br.com.caelum.vraptor.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.model.User;

public class UserDAO {
	
	private static String path = "arquivo.txt";

	public static boolean insert(User user) throws IOException {
		System.out.println("# DAO: Insert User");
		
		if(existsUser(user.getUserName()) == false){
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));
	        
	        buffWrite.write(user.getUserName() + " " + user.getPassword() + "\n");
	        buffWrite.close();
	        
	        return true;
		}
		return false;
	}
	
	public static List<User> list() throws IOException {
		System.out.println("# DAO: List Users");
		
		List<User> users = new ArrayList<User>();
		User user;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		String line = bufferedReader.readLine();
		
		while(line != null) {
			String u[] = line.split(" ");
			
			user = new User();
			user.setUserName(u[0]);
			user.setPassword(u[1]);
			
			users.add(user); 
			
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		
		return users;
	}
	
	public static boolean existsUser(String userName) throws IOException{
		System.out.println("# DAO: Exists User");
		
		List<User> users = list();
		
		for(User u : users) {
			if(u.getUserName().equals(userName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean updateUser(User user, String newPassword) throws IOException {
		System.out.println("# DAO: Update User");
		
		boolean updated = false;
		
		List<User> users = list();
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
		
		for(User u : users) {
			if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
				bufferedWriter.write(u.getUserName() + " " + newPassword);
				bufferedWriter.newLine();
				updated = true;
			} else {
				bufferedWriter.write(u.getUserName() + " " + u.getPassword());
				bufferedWriter.newLine();						
			}
		}
		bufferedWriter.close();
		
		return updated;
	}
	
	public static boolean deleteUser(User user) throws IOException {
		System.out.println("# DAO: Delete User");
		
		boolean deleted = false;
		
		List<User> users = list();
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
		
		for(User u : users) {
			if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
				deleted = true;
			} else {
				bufferedWriter.write(u.getUserName() + " " + u.getPassword());
				bufferedWriter.newLine();						
			}
		}
		bufferedWriter.close();
		
		return deleted;
	}
	
	public static boolean removeUser(User user) throws IOException {
		System.out.println("# DAO: Remove User");
		
		List<User> users = list();
		
		for(User u : users) {
			if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
				users.remove(u);
			}
		}
		
		File arquivo = new File(path);
		
		arquivo.delete();
		arquivo.createNewFile();
		
		
		
		for(User u : users) {
			FileWriter fileWriter = new FileWriter(arquivo, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(u.getUserName() + " " + u.getPassword() + "\n");
	        bufferedWriter.close();
		}
		
		return false;
	}
	
	public static boolean verify(User user) throws IOException{
		System.out.println("# DAO: Verify User");
		
		List<User> users = list();
		
		for(User u : users) {
			if(user.getUserName().equals(u.getUserName()) && user.getPassword().equals(u.getPassword())) {
				return true;
			}
		}
		return false;
	}
}