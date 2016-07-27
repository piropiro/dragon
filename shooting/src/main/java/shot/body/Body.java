package shot.body;

import lombok.Data;

@Data
public class Body {

	private int x;
	private int y;
	private int w;
	private int h;
	private int life;

	public Body() {
	}
	
	public Body(int x, int y, int w, int h, int life) {
		init(x, y, w, h, life);
	}

	public void init(int x_, int y_, int w_, int h_, int life_) {
		this.x = x_;
		this.y = y_;
		this.w = w_;
		this.h = h_;
		this.life = life_;
	}

	public static boolean hit(Body a, Body b) {
		if (a.x + a.w < b.x)
			return false;
		if (a.x > b.x + b.w)
			return false;
		if (a.y + a.h < b.y)
			return false;
		if (a.y > b.y + b.h)
			return false;
		return true;
	}
	
	public boolean isAlive(){
		return life > 0;
	}

	public int incLife(int i){
		return life += i;
	}
	
	public int decLife(int i){
		return life -= i;
	}
	
	public void die() {
		life = 0;
	}
}
