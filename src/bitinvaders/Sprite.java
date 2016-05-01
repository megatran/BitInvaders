package bitinvaders;

import java.awt.Image;

public class Sprite {
	int row;
	int col;
	public Sprite(int row, int col){
		this.row=row;
		this.col=col;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	private boolean visible;
	protected boolean hit;
    private Image image;
    public boolean isHit() {
		return hit;
	}
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	protected int x;
    protected int y;
    protected boolean dying;
    protected int dx;
    
    public Sprite() {
        visible = true;
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }

}
