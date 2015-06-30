package ua.kiev.shuriken.game;

import java.util.ArrayList;
import java.util.List;

public class Collisions {
	private static class Tile{
		List<SolidGameObject> objects = new ArrayList<SolidGameObject>();
		
		public void addObject(SolidGameObject object){
			objects.add(object);
		}
		
		public void deleteObject(SolidGameObject object){
			for(int i = 0; i<objects.size(); i++){
				if(objects.get(i) == object){
					objects.remove(i);
					return;
				}
			}
		}
	}
	
	public static void addGroup(String groupname){
		groups.add(new Group(groupname));
	}
	
	public static void addObject(SolidGameObject object){
		for(int i = 0; i < groups.size(); i++){
			if(groups.get(i).name == object.groupname){
				groups.get(i).objects.add(object);
				object.isInCollisionGroup = true;
				return;
			}
		}
		System.out.println("[ERROR] Can't find group '" + object.groupname + "' for adding object!");
	}
	
	public static void refreshObjects(){
		Group group = null;
		SolidGameObject object;
		for(int i = 0; i < groups.size(); i++){
			group = groups.get(i);
			for(int j = 0; j < groups.get(i).objects.size(); j++){
				object = group.objects.get(j);
				if(object.isDeleting){
					for(int h = 0; h<4; h++){
						if(object.collisionChunks[h] != -1){
							group.tiles[object.collisionChunks[h]].deleteObject(object);
						}
					}
					group.objects.remove(j--);
				} else {
					if(object.collisionChunks[0] != -1){
						group.tiles[object.collisionChunks[0]].deleteObject(object);
					}
					if(object.collisionChunks[1] != -1){
						group.tiles[object.collisionChunks[1]].deleteObject(object);
					}
					if(object.collisionChunks[2] != -1){
						group.tiles[object.collisionChunks[2]].deleteObject(object);
					}
					if(object.collisionChunks[3] != -1){
						group.tiles[object.collisionChunks[3]].deleteObject(object);
					}

					object.collisionChunks[0] = -1;
					object.collisionChunks[1] = -1;
					object.collisionChunks[2] = -1;
					object.collisionChunks[3] = -1;
					
					int posTop = (int)(object.getPosY()+object.hitBoxTop)/(int)group.tileSize;
					int posBottom = (int)(object.getPosY()+object.hitBoxBottom)/(int)group.tileSize;
					int posLeft = (int)(object.getPosX()+object.hitBoxLeft)/(int)group.tileSize;
					int posRight = (int)(object.getPosX()+object.hitBoxRight)/(int)group.tileSize;
					
					boolean doubleX = (posLeft == posRight) ? false : true;
					boolean doubleY = (posTop == posBottom) ? false : true;
					
					if(!doubleX){
						if(!doubleY){
							object.collisionChunks[0] = group.maxX*posTop+posLeft;
							group.tiles[group.maxX*posTop+posLeft].addObject(object);
						} else {
							object.collisionChunks[0] = group.maxX*posTop+posLeft;
							group.tiles[group.maxX*posTop+posLeft].addObject(object);
							object.collisionChunks[1] = group.maxX*posBottom+posLeft;
							group.tiles[group.maxX*posBottom+posLeft].addObject(object);
						}
					} else {
						if(!doubleY){
							object.collisionChunks[0] = group.maxX*posTop+posLeft;
							group.tiles[group.maxX*posTop+posLeft].addObject(object);
							object.collisionChunks[2] = group.maxX*posTop+posRight;
							group.tiles[group.maxX*posTop+posRight].addObject(object);
						} else {
							object.collisionChunks[0] = group.maxX*posTop+posLeft;
							group.tiles[group.maxX*posTop+posLeft].addObject(object);
							object.collisionChunks[1] = group.maxX*posBottom+posLeft;
							group.tiles[group.maxX*posTop+posRight].addObject(object);
							object.collisionChunks[2] = group.maxX*posTop+posRight;
							group.tiles[group.maxX*posBottom+posLeft].addObject(object);
							object.collisionChunks[3] = group.maxX*posBottom+posRight;
							group.tiles[group.maxX*posBottom+posRight].addObject(object);
						}
					}
				}
			}
		}
	}
	
	public static boolean checkCollision(SolidGameObject a, float x, float y, SolidGameObject b){
		if(a.getPosX()+a.hitBoxLeft+x < b.getPosX()+b.hitBoxRight &&
				a.getPosX()+a.hitBoxRight+x > b.getPosX()+b.hitBoxLeft &&
				a.getPosY()+a.hitBoxTop+y < b.getPosY()+b.hitBoxBottom &&
				a.getPosY()+a.hitBoxBottom+y > b.getPosY()+b.hitBoxTop){
			return true;
		} else {
			return false;
		}
	}
	
	static List<Group> groups = new ArrayList<Group>();
	
	private static class Group{
		List<SolidGameObject> objects = new ArrayList<SolidGameObject>();
		float stageX=2048, stageY=2048, tileSize=128;
		String name;
		public int maxX = ((int)stageX/(int)tileSize+1);
		public int maxY = ((int)stageY/(int)tileSize+1);
		
		public Tile[] tiles = new Tile[maxX*maxY];
		
		public Group(String name){
			this.name = name;
			for(int i = 0; i < tiles.length; i++){
				tiles[i] = new Tile();
			}
		}
	}
	
	public static void deleteAllCollisionGroups(){
		groups.clear();
		System.out.println("[INFO] All collision groups has been deleted");
	}
	
	public static SolidGameObject checkCollision(SolidGameObject object,
			float x,
			float y,
			String groupName){
		Group group = null;
		boolean found = false;
		for(int i = 0; i < groups.size() && !found; i++){
			if(groups.get(i).name == groupName){
				group = groups.get(i);
			}
		}
		if(group == null){
			return null;
		}
		
		if(object.collisionChunks[0] != -1){
			for(SolidGameObject g:group.tiles[object.collisionChunks[0]].objects){
				if(g != object){
					if(checkCollision(object, x, y, g)){
						return g;
					}
				}
			}
		}
		if(object.collisionChunks[1] != -1){
			for(SolidGameObject g:group.tiles[object.collisionChunks[1]].objects){
				if(g != object){
					if(checkCollision(object, x, y, g)){
						return g;
					}
				}
			}
		}
		if(object.collisionChunks[2] != -1){
			for(SolidGameObject g:group.tiles[object.collisionChunks[2]].objects){
				if(g != object){
					if(checkCollision(object, x, y, g)){
						return g;
					}
				}
			}
		}
		if(object.collisionChunks[3] != -1){
			for(SolidGameObject g:group.tiles[object.collisionChunks[3]].objects){
				if(g != object){
					if(checkCollision(object, x, y, g)){
						return g;
					}
				}
			}
		}
		return null;
	}
}



// Old version
// It would work very slow with large amount of objects in one collision groups
/*
package ua.kiev.shuriken.game;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.shuriken.game.objects.*;

public class Collisions {
	
	public static void refreshObject(SolidGameObject object){
		
	}
	
	private static boolean checkCollision(SolidGameObject a, float x, float y, SolidGameObject b){
		if(a.getPosX()+a.hitBoxLeft+x < b.getPosX()+b.hitBoxRight &&
				a.getPosX()+a.hitBoxRight+x > b.getPosX()+b.hitBoxLeft &&
				a.getPosY()+a.hitBoxTop+y < b.getPosY()+b.hitBoxBottom &&
				a.getPosY()+a.hitBoxBottom+y > b.getPosY()+b.hitBoxTop){
			return true;
		} else {
			return false;
		}
	}
	
	static List<Group> groups = new ArrayList<Group>();
	
	static{
		groups.add(new Group("cubes"));
	}
	
	//===============================================
	private static class Group{
		List<SolidGameObject> objects = new ArrayList<SolidGameObject>();
		String name;
		
		public Group(String name){
			this.name = name;
		}
	}
	
	public static void addObject(String groupName, SolidGameObject object){
		for(int i = 0; i<groups.size(); i++){
			if(groupName == groups.get(i).name){
				groups.get(i).objects.add(object);
			}
		}
	}
	
	
	public static SolidGameObject checkCollision(SolidGameObject object, float x, float y, String groupName){
		Group group = null;
		boolean found = false;
		for(int i = 0; i < groups.size() && !found; i++){
			if(groups.get(i).name == groupName){
				group = groups.get(i);
			}
		}
		if(group == null){
			return null;
		}
		
		for(SolidGameObject o:group.objects){
			if(o != object){
				if(checkCollision(object, x, y, o)){
					return o;
				}
			}
		}
		return null;
	}
}
*/