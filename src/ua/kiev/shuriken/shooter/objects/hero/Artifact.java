package ua.kiev.shuriken.shooter.objects.hero;

import ua.kiev.shuriken.game.FileWorker;
import ua.kiev.shuriken.game.GameObject;

public abstract class Artifact extends Item{
	
	public static void init(String name){
		GameObject.addTexture("artifact_"+name+"_icon", "res/objects/Items/Artifacts/"+name+"/"+name+"_icon.png");
		Item.descriptions.put("artifact_"+name, FileWorker.loadStringFromFile("res/objects/Items/Artifacts/"+name+"/"+name+".ini"));
	}
	
	@Override
	public int getType(){
		return Item.ARTIFACT;
	}
	
	public Artifact(String name, String textName){
		super(name, textName);
	}
	
	public abstract void changeStats(Hero hero);
}
