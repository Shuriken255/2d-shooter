package ua.kiev.shuriken.game;

public abstract class SolidGameObject extends GameObject{
	public float hitBoxTop = 0f,
			hitBoxBottom = this.sizeY,
			hitBoxLeft = 0,
			hitBoxRight = this.sizeX;
	public String groupname;
	public int[] collisionChunks = {-1, -1, -1, -1};
	public boolean isInCollisionGroup = false;
	
	public SolidGameObject(String groupname, String layer){
		super(layer);
		this.groupname = groupname;
		Collisions.addObject(this);
	}
	
	public SolidGameObject(String layer){
		super(layer);
	}
	
	public SolidGameObject checkCollision(String groupName, float x, float y){
		return Collisions.checkCollision(this, x, y, groupName);
	}
	
	@Override
	abstract public void logic();
	
	@Override
	public void destroy(){
		this.isDeleting = true;
	}
	
	abstract public void touch(String objective);
}