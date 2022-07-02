package util;

public class Message {
	private String msg;

	public Message(Action msg) {
		this.msg = msg.name();
	}

	public <T> void add(T valor) {
		msg += ":" + String.valueOf(valor);
	}

	public static String[] decode(String msg) {
		return msg.split(":");
	}

	public static Room prepareRoom(String name, String max, String owner, String actual) {
		Room room = new Room(name, Integer.parseInt(max), owner);
		room.setActual(Integer.parseInt(actual));
		
		return room;
	}

	@Override
	public String toString() {
		return msg;
	}

}
