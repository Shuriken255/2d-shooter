package ua.kiev.shuriken.game;

import java.util.List;
import java.util.ArrayList;

public class Layers {
	public static List<Layer> layers = new ArrayList<Layer>();
	
	static public class Layer{
		public boolean isActive = true;
		public boolean isVisible = true;
		public String layerName;
		public boolean isRemove = false;
		public Layer(String name){
			this.layerName = name;
		}
		public List<GameObject> layer = new ArrayList<GameObject>(); 
	}
	
	static public void setRemove(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				layers.get(i).isRemove = true;
			}
		}
	}
	
	static public void logic(){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).isActive){
				for(int j = 0; j < layers.get(i).layer.size(); j++){
					layers.get(i).layer.get(j).logic();
					if(j < layers.get(i).layer.size()){
						if(layers.get(i).layer.get(j).isDeleting){
							layers.get(i).layer.remove(j--);
						}
					}
				}
			}
		}
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).isRemove == true){
				layers.remove(i);
			}
		}
	}
	
	static public void draw(){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).isVisible){
				for(int j = 0; j < layers.get(i).layer.size(); j++){
					layers.get(i).layer.get(j).draw();
				}
			}
		}
	}
	
	static public void addLayer(String name){
		layers.add(new Layer(name));
	}
	
	static public void clearLayer(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				while(layers.get(i).layer.size() > 0){
					layers.get(i).layer.get(layers.get(i).layer.size()-1).destroy();
					layers.get(i).layer.remove(layers.get(i).layer.size()-1);
				}
				return;
			}
		}
		System.out.println("ERROR: Can't clear '" + name + "' layer. Layer doesn't exist!");
	}
	
	static public void setActive(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				layers.get(i).isActive = true;
				return;
			}
		}
		System.out.println("ERROR: Can't set active state to '" + name + "'!");
	}
	
	static public void setInactive(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				layers.get(i).isActive = false;
				return;
			}
		}
		System.out.println("ERROR: Can't set inactive state to '" + name + "'!");
	}
	
	static public void setVisible(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				layers.get(i).isVisible = true;
				return;
			}
		}
		System.out.println("ERROR: Can't set visible state to '" + name + "'!");
	}
	
	static public void setInvisible(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				layers.get(i).isVisible = false;
				return;
			}
		}
		System.out.println("ERROR: Can't set invisible state to '" + name + "'!");
	}
	
	static public void deleteLayer(String name){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).layerName == name){
				while(layers.get(i).layer.size() > 0){
					layers.get(i).layer.get(layers.get(i).layer.size()-1).destroy();
					layers.get(i).layer.remove(layers.get(i).layer.size()-1);
				}
				layers.remove(i);
				return;
			}
		}
		System.out.println("ERROR: Can't delete '" + name + "' layer! Layer doesn't exist!");
	}
	
	static public void addObject(GameObject object){
		for(Layer l:layers){
			if(l.layerName == object.layer){
				l.layer.add(object);
				return;
			}
		}
		addLayer(object.layer);
		layers.get(layers.size()-1).layer.add(object);
	}
	
	static public void deleteAllLayers(){
		layers.clear();
	}
}