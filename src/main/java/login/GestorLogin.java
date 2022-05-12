package login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import exception.Exception_RoadFighter;
import usuario.Usuario;

public class GestorLogin {

	static public Usuario registrarUsuario(String ruta, String nombre, String contrasenia) throws Exception {
		
		if (!usuarioExistente(ruta, nombre)) {
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(ruta, true);
				
				bw = new BufferedWriter(fw);
			    bw.write(nombre + "-" + contrasenia + "\n");
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(fw != null)
					fw.close();
			}
		} else
			throw new Exception_RoadFighter("Usuario ya registrado!");
		
		return new Usuario(nombre, contrasenia);

	}

	static public boolean login(String path, String nombre, String contra) {
		Scanner file = null;
		boolean coincidenDatos = false;
		try {
			file = new Scanner(new File(path));

			while (file.hasNext() && !coincidenDatos) {
				String[] user = file.next().split("-");
				if (user[0].equals(nombre) && user[1].equals(contra)) {
					coincidenDatos = true;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(file != null)
				file.close();
		}
		
		return coincidenDatos;

	}

	static public boolean usuarioExistente(String ruta, String nombre) {
		Scanner file = null;
		boolean coincidenDatos = false;
		
		try {
			file = new Scanner(new File(ruta));
			while (file.hasNext() && !coincidenDatos) {
				String[] user = file.next().split("-");
				if (user[0].equals(nombre)) {
					coincidenDatos = true;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(file != null)
				file.close();
		}

		return coincidenDatos;
	}

}
