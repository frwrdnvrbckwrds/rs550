package com.runescape.game.net.update;

public final class UpdateEncryptionMessage {

	private final int key;

	public UpdateEncryptionMessage(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

}
