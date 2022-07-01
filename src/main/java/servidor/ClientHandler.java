package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private String usuario;

	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.entrada = new DataInputStream(socket.getInputStream());
			this.salida = new DataOutputStream(socket.getOutputStream());
			this.usuario = entrada.readUTF();
		} catch (IOException e) {
			close();
		}
	}

	@Override
	public void run() {}

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

		System.out.println("[Server] : Cliente desconectado.");
	}

}
