package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Caixote extends Movivel implements Movable{

	

	public Caixote(Point2D point2D) {
		super(point2D,"Caixote", 2);
	}

	@Override
	public void move() {
		super.move();
	}


	@Override
	public void falling(Point2D playerNextPosition, AbstractObject object) {
		super.falling(playerNextPosition, object);
	}


}


