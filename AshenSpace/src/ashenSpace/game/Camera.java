package ashenSpace.game;


public class Camera {

	Handler handler;
	private float x, y;
	
	public Camera(float x, float y, Handler handler){
		this.x = x;
		this.y = y;
		this.handler = handler;
		
		
	}
	
	public void tick(GameObject player){
		x = -player.getX() + Game.width/2;
		
		y = -player.getY() + Game.height/2 + 100.5f;
		
		
	}
	
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
}

