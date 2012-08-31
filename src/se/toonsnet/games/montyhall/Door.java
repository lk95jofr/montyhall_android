package se.toonsnet.games.montyhall;

public class Door {
	private int number;
	private boolean price = false;
	private boolean chosen = false;
	private boolean open = false;
	
	public Door(int number) {
		this.number = number;
	}

	public boolean hasPrice() {
		return price;
	}

	public void setPrice(boolean price) {
		this.price = price;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getNumber() {
		return number;
	}
}
