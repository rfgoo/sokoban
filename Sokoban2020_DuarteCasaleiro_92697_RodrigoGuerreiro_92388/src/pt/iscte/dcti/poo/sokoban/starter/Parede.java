package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractObject implements Interactable{


	public Parede(Point2D point2D, String name, int layer) {
		super(point2D,name, 1, false, false);
	}

	@Override
	public void interact(Point2D playerNextPosition,
			AbstractObject object) {}
	

	


}
