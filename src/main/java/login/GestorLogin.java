package login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import usuario.Usuario;

import java.io.FileWriter;

public class GestorLogin {

	static public Usuario registrarUsuario(String ruta, String nombre, String contrasenia) {
		if (usarioExistente(ruta, nombre)) {
			System.out.println("El nombre ingresado ya se encuentra Registrado");
			return null;
		}

		FileWriter archivo = null;
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter(ruta, true);
			
			bw = new BufferedWriter(fw);
		    bw.write(nombre + "-" + contrasenia + "\n");
			bw.flush();
			
		    archivo = fw;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != archivo) {
					archivo.close();
					System.out.println("Cerro!");					
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return new Usuario(nombre, contrasenia);

	}

	static public boolean login(String path, String nombre, String contra) {
		try {
			Scanner file = new Scanner(new File(path));

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

	static public boolean usarioExistente(String ruta, String nombre) {
		Scanner fileAux = null;
		try {
			Scanner file = new Scanner(new File(ruta));

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
