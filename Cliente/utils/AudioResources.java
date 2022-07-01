package road_fighter.utils;

import javafx.scene.media.AudioClip;

public final class AudioResources {

	private static AudioClip create(String name) {
		return new AudioClip(ClassLoader.getSystemResource(name).toString());
	}

	public static AudioClip getStartAudio() {
		return create("snd/start.wav");
	}

	public static AudioClip getCountdownAudio() {
		return create("snd/countdown.wav");
	}

	public static AudioClip getDriveAudio() {
		return create("snd/drive.wav");
	}

	public static AudioClip getSkidAudio() {
		return create("snd/skid.wav");
	}
	
	public static AudioClip getPowerUpAudio() {
		return create("snd/powerUp.wav");
	}

	public static AudioClip getExplosionAudio() {
		return create("snd/explosion.wav");
	}

	public static AudioClip getFinishAudio() {
		return create("snd/finish.wav");
	}

	public static AudioClip getGameOverAudio() {
		return create("snd/gameOver.wav");
	}

}
