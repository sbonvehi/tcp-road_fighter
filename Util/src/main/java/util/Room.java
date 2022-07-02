package util;

import java.util.Objects;

public class Room {
	protected String name;
	protected String owner;
	protected int max;
	protected int actual;

	public Room(String name, int max, String owner) {
		this.name = name.toUpperCase();
		this.max = max;
		this.owner = owner;
	}

	public Room(String name, String owner) {
		this.name = name;
		this.owner = owner;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}

	public String getOwner() {
		return owner;
	}

	public int getMax() {
		return max;
	}

	public int getActual() {
		return actual;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return name.equals(other.name) && owner.equals(other.owner);
	}

	@Override
	public String toString() {
		return name + " - [" + actual + "/" + max + "] " ;
	}
}
