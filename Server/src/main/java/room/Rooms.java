package room;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
	private static final int MAX_LENGHT = 100;
	private static List<RoomHandler> clients = new ArrayList<>();

	private Rooms() {
	}

	public static void createRoom(String roomName, int cantidad, String owner) {
		if (roomName.isEmpty()) {
			return;
		}

		if (roomName.length() > MAX_LENGHT) {
			roomName = roomName.substring(0, MAX_LENGHT);
		}

		RoomHandler sala = new RoomHandler(roomName, cantidad, owner);
		clients.add(sala);
	}

	public static void removeRoom(String salaName, String owner) {
		RoomHandler sala = new RoomHandler(salaName, owner);
		if (clients.contains(sala)) {
			sala = clients.get(clients.indexOf(sala));
			sala.close();
			clients.remove(sala);
		}
	}

	public static RoomHandler addClient(String roomName, String owner, ClientHandler client) {
		RoomHandler room = new RoomHandler(roomName, owner);

		if (clients.contains(room)) {
			room = clients.get(clients.indexOf(room));
			if (room.add(client)) {
				return room;
			}
		}

		return null;
	}

	public static void removeClient(String roomName, String owner, ClientHandler client) {
		RoomHandler room = new RoomHandler(roomName, owner);

		if (clients.contains(room)) {
			room = clients.get(clients.indexOf(room));
			room.remove(client);
		}
	}

	public static List<String> salasToString() {
		List<String> salas = new ArrayList<>();

		salas.add(String.valueOf(clients.size()));
		for (RoomHandler room : clients) {
			salas.add(room.getName());
			salas.add(String.valueOf(room.getMax()));
			salas.add(room.getOwner());
			salas.add(String.valueOf(room.getActual()));
		}

		return salas;
	}
}
