package room;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import util.Action;
import util.Comunicator;
import util.Message;

public class ClientHandler implements Runnable, Comunicator {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String username;
	private RoomHandler currentRoom;

	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.username = in.readUTF();
		} catch (IOException e) {
			close();
		}
	}

	@Override
	public void run() {
		String msg;

		while (socket.isConnected()) {
			try {
				msg = in.readUTF();
				action(Message.decode(msg));
			} catch (IOException e) {
				close();
				break;
			}
		}
	}

	



	public void sendToLobby() {
		Message msg = new Message(Action.LOBBY);
		send(msg);
	}

	public String getUsername() {
		return username;
	}

	public void close() {
		currentRoom.remove(this);
		try {
			if (this.in != null) {
				this.in.close();
			}
			if (this.out != null) {
				this.out.close();
			}
			if (this.socket != null) {
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Cliente desconectado.");
	}
	
	@Override
	public void send(Message msg) {
		try {
			this.out.writeUTF(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void action(String[] actions) {
		Action action = Action.valueOf(actions[0]);
		switch (action) {
		case CREATE_ROOM:
			Rooms.createRoom(actions[1], Integer.valueOf(actions[2]), this.username);
			break;

		case GET_ROOMS:
			Message msg = new Message(Action.GET_ROOMS);
			List<String> rooms = Rooms.salasToString();

			for (String room : rooms) {
				msg.add(room);
			}

			send(msg);
			break;

		case DELETE_ROOM:
			Rooms.removeRoom(actions[1], username);
			break;

		case JOIN_ROOM:
			this.currentRoom = Rooms.addClient(actions[1], actions[2], this);
			if (currentRoom != null) {
				currentRoom.sendToAll(currentRoom.join());
			}
			break;

		case LOBBY:
			currentRoom.remove(this);
			currentRoom.sendToAll(currentRoom.join());
			break;

		default:
			break;
		}
	}
}
