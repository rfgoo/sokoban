package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Movivel extends AbstractObject implements Movable{

	private Point2D lastPosition = this.getPosition();


	public Movivel(Point2D point, String name, int layer) {
		super(point, name, layer, true, false);
	}



	public Point2D getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point2D lastPosition) {
		this.lastPosition = lastPosition;
	}
	
	private void updatePosition(){
		Direction Dir = super.getDir();
		
		SokobanGame.getInstance().getPlayer().setEnergy();
		SokobanGame.getInstance().getPlayer().setMoves();
		
		lastPosition = this.getPosition();
		super.setPosition(this.getPosition().plus(Dir.asVector()));
	}



	public void move(){

		ArrayList<AbstractObject> list = super.getList();
		Direction Dir = super.getDir();
		
		for(AbstractObject nextObj : list){

			if(nextObj.getPosition().equals(this.getPosition().plus(Dir.asVector()))){

				if(nextObj instanceof Interactable){
					((Interactable)nextObj).interact(this.getPosition().plus(Dir.asVector()), this);
				}
				else if(nextObj.isTransposable()){
					updatePosition();
					return;
				}
				return;
			}
		}
		updatePosition();
	}

	
	
	public void falling(Point2D playerNextPosition, AbstractObject object) {

		SokobanGame.getInstance().getPlayer().setRestart(true);
		super.setPosition(this.getPosition().plus(super.getDir().asVector()));
	}

}
