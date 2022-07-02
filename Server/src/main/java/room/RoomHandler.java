package room;

import java.util.ArrayList;
import java.util.List;

import util.Action;
import util.Message;
import util.Room;

public class RoomHandler extends Room{
	private List<ClientHandler> players = new ArrayList<>();

	public RoomHandler(String name, int max, String owner) {
		super(name, max, owner);
	}

	public RoomHandler(String name, String owner) {
		super(name, owner);
	}

	public void close() {
		players.forEach(ClientHandler::sendToLobby);
		players.clear();
	}

	public boolean add(ClientHandler client) {
		if (this.actual < this.max) {
			players.add(client);
			this.actual++;
			return true;
		}

		return false;
	}

	public void remove(ClientHandler client) {
		if (players.remove(client)) {
			this.actual--;
			client.sendToLobby();
		}
	}

	public Message join() {
		Message join = new Message(Action.JOIN_ROOM);
		join.add(this.name);
		join.add(this.max);
		join.add(this.owner);
		join.add(this.actual);
		join.add(this.players.size());
		for (ClientHandler player : players) {
			join.add(player.getUsername());
		}
		return join;
	}

	public void sendToAll(Message msg) {
		for (ClientHandler player : players) {
			player.send(msg);
		}
	}
}
