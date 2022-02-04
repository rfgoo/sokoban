package pt.iscte.dcti.poo.sokoban.starter;


import java.util.ArrayList;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends Movivel implements Movable {

	private int moves = 0;
	private int energy = 100;
	private boolean restart = false;
	private boolean martelo=false;


	public Player(Point2D initialPosition, String Name, int layer) {
		super(initialPosition, Name, layer);
	}

	public void setMoves() {
		this.moves++ ;
	}

	public void setEnergy() {
		this.energy--;
	}


	public int getMoves() {
		return moves;
	}

	public int getEnergy() {
		return energy;
	}

	public boolean getRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public void chooseImage(Direction Dir){
		switch (Dir) {

		case LEFT:
			super.setName("Empilhadora_L");
			break;

		case RIGHT:
			super.setName("Empilhadora_R");
			break;

		case UP:
			super.setName("Empilhadora_U");
			break;

		case DOWN:
			super.setName("Empilhadora_D");
			break;
		default:
			break;
		}
	}

	@Override
	public void move() {

		Point2D newPosition = super.getPosition().plus(super.getDir().asVector());

		if (newPosition.getX() >= 0 && newPosition.getX() < 10 && newPosition.getY() >= 0 && newPosition.getY() < 10
				&& energy > 0){

			for (AbstractObject obj : super.getList()) {

				if (obj.getPosition().equals(newPosition) && obj.isMovable()) {
					move2movivel(obj,newPosition);

					return;
				}
				if (obj.getPosition().equals(newPosition) && obj instanceof Interactable) {

					move2interactable(obj,newPosition);
					return;
				}
			}
			super.move();
		}
	}
	private void move2movivel(AbstractObject obj, Point2D newPosition){
		((Movable) obj).move();

		if(!obj.getPosition().equals(newPosition)) {
			check4Interactable(super.getList(), newPosition, super.getDir());
			this.setPosition(newPosition);
		}
	}

	private void move2interactable(AbstractObject obj, Point2D newPosition){

		((Interactable) obj).interact(newPosition, this);

		if(obj.getPosition().equals(newPosition)) {
			check4Movable(super.getList(), newPosition, super.getDir());
		}

	}


	private void check4Interactable(ArrayList<AbstractObject> list, Point2D newPosition, Direction Dir){
		for(AbstractObject obj: list){
			if(obj.getPosition().equals(newPosition) && obj instanceof Interactable){
				((Interactable) obj).interact(newPosition, this);
				return;
			}
		}
	}

	private void check4Movable(ArrayList<AbstractObject> list, Point2D newPosition, Direction Dir){
		for(AbstractObject obj: list){
			if(obj.getPosition().equals(newPosition) && obj instanceof Movable && !(obj instanceof Player)){
				((Movable) obj).move();
				return;
			}
		}
	}



	public void setEnergia(int num) {
		this.energy = num;
	}

	@Override
	public void falling(Point2D playerNextPosition, AbstractObject object) {
		super.falling(playerNextPosition, object);
	}

	public boolean hasMartelo(){
		return martelo;
	}
	public void setMartelo(boolean mart){
		martelo = mart;
	}

}