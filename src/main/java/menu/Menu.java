package menu;

import java.util.ArrayList;
import java.util.List;

import login.GestorLogin;
import sala.Sala;
import usuario.Usuario;

public class Menu {

	private List<Sala> listaSalas;
	private String fileLogin = "./archivoLogin/usuarios.txt";

	public Menu() {
		this.listaSalas = new ArrayList<Sala>();
	}

	public List<Sala> getListaSalas(){ 
		return this.listaSalas;
	}

	public Usuario registrarUsuario(String nombre, String contrasenia) throws Exception {
		return GestorLogin.registrarUsuario(this.fileLogin, nombre, contrasenia);
	}

	public Usuario loginUsuario(String nombre, String contrasenia) throws Exception {
		if(!GestorLogin.login(this.fileLogin, nombre, contrasenia))
			throw new Exception("Usuario no encontrado!");
		
			return new Usuario(nombre, contrasenia);
	}

	public void agregarSala(Sala sala){ 
		listaSalas.add(sala);
	}

}
