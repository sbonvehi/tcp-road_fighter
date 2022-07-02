package login;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;

import usuario.Usuario;

import java.io.FileWriter;

public class GestorLogin {

	private String _filePath;
	
	
	
	public GestorLogin()
	{
		_filePath = "./archivoLogin/usuarios.txt";
	}
	
	public boolean registrarUsuario(String nombre, String contrasenia) {
		if (usuarioExistente(nombre)) {
			System.out.println("El nombre ingresado ya se encuentra Registrado");
			return false;
		}

		FileWriter archivo = null;
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter(_filePath, true);
			
			bw = new BufferedWriter(fw);
		    bw.write(nombre + "-" + contrasenia + "\n");
			bw.flush();
			
		    archivo = fw;
		    
		    if (null != archivo) {
				archivo.close();
				System.out.println("Usuario registrado correctamente!");					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	public boolean login(String nombre, String contra) {
		try {
			Scanner file = new Scanner(new File(_filePath));

			while (file.hasNext()) {
				String[] user = file.next().split("-");
				if (user[0].equals(nombre) && user[1].equals(contra)) {
					file.close();
					return true;
				}
			}

			file.close();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean usuarioExistente(String nombre) {
		Scanner fileAux = null;
		try {
			Scanner file = new Scanner(new File(_filePath));

			fileAux = file;
			while (file.hasNext()) {
				String[] user = file.next().split("-");
				if (user[0].equals(nombre)) {
					return true;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			fileAux.close();
		}

		return false;
	}

}