package Sprite;

import java.util.ArrayList;

import application.Images;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class TigerSprite extends Sprite{

	private boolean isMove;
	protected boolean attackable;
	protected int timesBasicAttack;
	protected boolean canMovePosition = true;


    public TigerSprite(Image image, Image[] imageList, Image[] imageL, Image[] imageR)
    {
       super(image, imageL, imageR);
       this.setImageList(imageList);
       this.setVelocityX(0);
       this.setPositionX(0);
       this.setVelocityY(0);
       this.setPositionY(0);
    }
    
    public void setMove(boolean tf) {
    	this.isMove = tf;
    }
    
    public boolean getMove() {
    	return this.isMove;
    }

    // add more type of setPosition 
    public void setPosition(double x, double y)
    {
        this.setPositionX(x-170);
        this.setPositionY(y-150);
    }
    
    public void setVelocity(double x, double y)
    {
        this.setVelocityX(x);
        this.setVelocityY(y);
    }

    public void addVelocity(double x, double y)
    {
        this.setVelocity(x+ this.getVelocityX(), y + this.getVelocityY());
        this.setMove(true);
    }

    public void update(double time)
    {
        this.setPositionX(this.getPositionX() + (this.getVelocityX()) * time);
        this.setPositionY(this.getPositionY() + (this.getVelocityY()) * time);
    }
    

    public void render(GraphicsContext gc)
    {
        gc.drawImage( this.getImage(), this.getPositionX(), this.getPositionY() );
    }

    
    public String toString()
    {
        return " Position: [" + this.getPositionX() + "," + this.getPositionY() + "]" 
        + " Velocity: [" + this.getVelocityX() + "," + this.getVelocityY() + "]";
    }


	public boolean isAttackable() {
		return attackable;
	}
	


	public int getTimesBasicAttack() {
		return timesBasicAttack;
	}

	public void setTimesBasicAttack(int timesBasicAttack) {
		this.timesBasicAttack = timesBasicAttack;
	}
	

	public boolean isCanMovePosition() {
		return canMovePosition;
	}

	public void setCanMovePosition(boolean canMovePosition) {
		this.canMovePosition = canMovePosition;
	}

	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
		this.setTimesBasicAttack((this.getTimesBasicAttack()+1)%3);
		if(attackable == true) {
			this.setImageL(Images.blackTigerBasicAttackL);
			this.setImageR(Images.blackTigerBasicAttackR);
		} else {
			this.setImageL(Images.blackTigerMotionL);
			this.setImageR(Images.blackTigerMotionR);
		}
	} 
	
	@Override
	public void setFace(String face) {
		if (!(face.equals(this.getFace()))) {
			this.face = face;
			this.setPositionR(0);
			this.setPositionL(0);
			return;
		}
		if(face == "LEFT") {
			if(this.isAttackable()) {
				this.setSkillPositionL((timesBasicAttack+1)%3);
			} else {
				this.setPositionL((this.getPositionL()+1)%3);
			}
		} else {
			if(this.isAttackable()) {
				this.setSkillPositionR((timesBasicAttack+1)%3);
			} else {
				this.setPositionR((this.getPositionR()+1)%3);
			}
		}
	}
	
	
	public void switchToWalk() {
		this.setAttackable(false);
		this.setImageL(Images.blackTigerMotionL);
		this.setImageR(Images.blackTigerMotionR);
		if(this.getFace() == "LEFT") {
			this.setImage((this.getImageL())[(this.getPositionL()+1)%3]);
		} else {
			this.setImage((this.getImageR())[(this.getPositionR()+1)%3]);
		}
	}
	
	public void setFace(String face, int duration) {

		if (!(face.equals(this.getFace()))) {
			this.face = face;
			this.setPositionR(0);
			this.setPositionL(0);
			return;
		}
		if(face == "LEFT") {
			if(this.isAttackable()) {
				this.setSkillPositionL((timesBasicAttack+1)%3);
			} else {
				this.setPositionL((this.getPositionL()+1)%3);
			}
		} else {
			if(this.isAttackable()) {
				this.setSkillPositionR((timesBasicAttack+1)%3);
			} else {
				this.setPositionR((this.getPositionR()+1)%3);
			}
		}
	}
	
	

}
