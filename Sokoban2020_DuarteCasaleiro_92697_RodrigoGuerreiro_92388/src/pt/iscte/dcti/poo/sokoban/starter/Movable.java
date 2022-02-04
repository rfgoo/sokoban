package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public interface Movable {

	abstract void move();
	abstract void falling(Point2D playerNextPosition, AbstractObject object);
}
