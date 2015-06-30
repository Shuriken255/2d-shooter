package ua.kiev.shuriken.game;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.WaveData;
import org.lwjgl.openal.AL10;

public class Sound {
	private static Map<String, Integer> soundMap = new HashMap<String, Integer>();
	
	public static int getSound(String name){
		return soundMap.get(name);
	}
	
	public static void loadSound(String name, String pathToFile){
		try {
			WaveData wave = null;
			int buffer = AL10.alGenBuffers();
			wave = WaveData.create(new BufferedInputStream(new FileInputStream(pathToFile)));
			AL10.alBufferData(buffer, wave.format, wave.data, wave.samplerate);
			wave.dispose();
			soundMap.put(name, buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
