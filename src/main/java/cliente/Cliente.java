package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import road_fighter.GameSceneHandler;

public class Cliente {
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private String usuario;

	private GameSceneHandler juego;

	public Cliente(String ip, int port, String usuario, GameSceneHandler juego) throws IOException {
		this.socket = new Socket(ip, port);
		this.usuario = usuario;
		this.juego = juego;
		entrada = new DataInputStream(socket.getInputStream());
		salida = new DataOutputStream(socket.getOutputStream());
		salida.writeUTF(usuario);
	}

	public void listen() {
		Thread listener = new Thread(() -> {
			String msg;
			while( socket.isConnected()) {
				try {
					msg = entrada.readUTF();
					System.out.println("[ " + this.usuario + " ] :" + msg);
				} catch(IOException e) {
					close();
				}
			}
		});
		listener.start();
	}
	
	public void close() {
		try {
			if (this.entrada != null)
				this.entrada.close();

			if (this.salida != null)
				this.salida.close();

			if (this.socket != null)
				this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
