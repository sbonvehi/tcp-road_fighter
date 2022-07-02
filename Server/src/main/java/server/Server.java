package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import room.ClientHandler;
import room.Rooms;

public class Server {

	private final ServerSocket serverSocket;

	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		Rooms.createRoom("Sala 1", 8, "SERVER");
	}

	public void start() {
		try {
			while (!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado.");
				
				ClientHandler clientHandler = new ClientHandler(socket);
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		} catch (IOException e) {
			this.close();
		}
	}

	public void close() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Server server = new Server(30000);
			System.out.println("Empezo");
			
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
