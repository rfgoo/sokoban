package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public interface Interactable {

	public void interact(Point2D playerNextPosition, AbstractObject object);
	
}
