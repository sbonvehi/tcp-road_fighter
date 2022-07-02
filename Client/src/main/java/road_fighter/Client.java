package road_fighter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import util.Action;
import util.Comunicator;
import util.Message;
import util.Room;

public class Client implements Comunicator {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String username;
	private List<Room> rooms;
	private Room currentRoom;
	private List<String> playersInRoom;
	public Action actionPending;

	public Client(String ip, int port, String username) throws IOException {
		this.socket = new Socket(ip, port);
		this.username = username;
		this.in = new DataInputStream(socket.getInputStream());
		this.out = new DataOutputStream(socket.getOutputStream());
		this.out.writeUTF(username);
		
		rooms = new ArrayList<Room>();
		rooms.add(new Room("Sala 1", 8, "SERVER"));
	}

	public void listen() {
		Thread listener = new Thread(() -> {
			String msg;
			while (socket.isConnected()) {
				try {
					msg = in.readUTF();
					action(Message.decode(msg));
				} catch (IOException e) {
					close();
				}
			}
		});

		listener.start();
	}

	

	public synchronized List<String> getSalasNames() {
		Message msg = new Message(Action.GET_ROOMS);
		send(msg);

		try {
			wait();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

		List<String> salasNames = new ArrayList<>();
		for (Room room : rooms) {
			salasNames.add(room.toString());
		}

		return salasNames;
	}

	public boolean isOwnerOf(String salaName) {
		for (Room room : rooms) {
			if (room.getName().equalsIgnoreCase(salaName) && room.getOwner().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void close() {
		try {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		case GET_ROOMS:
			int cantSalas = Integer.parseInt(actions[1]);
			this.rooms = new ArrayList<>(cantSalas);

			for (int i = 0, j = 0; j < cantSalas; i += 5, j++) {
				Room room = Message.prepareRoom(actions[2 + i], actions[3 + i], actions[4 + i], actions[5 + i]);
				this.rooms.add(room);
			}
			break;

		case LOBBY:
			break;

		case JOIN_ROOM:
			this.currentRoom = Message.prepareRoom(actions[1], actions[2], actions[3], actions[4]);

			int size = Integer.parseInt(actions[5]);
			this.playersInRoom = new ArrayList<>(size);
			for (int i = 0; i < size; i++) {
				this.playersInRoom.add(actions[6 + i]);
			}

			break;

		default:
			break;
		}

		actionPending = action;
		notifyAll();
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public List<String> getPlayersInSala() {
		return playersInRoom;
	}

	public Room getRoom(int index) {
//		if (index < 0 || index >= rooms.size()) {
//			return null;
//		}

		return this.rooms.get(index);
	}

}
