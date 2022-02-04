package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractObject implements Interactable{

	public Buraco(Point2D point2D) {
		super(point2D,"Buraco", 3, false, true);
	}

	@Override
	public void interact(Point2D playerNextPosition, AbstractObject object) {
		if(object instanceof Movivel){
			object.setPosition(this.getPosition());
			((Movivel)object).falling(playerNextPosition, this);
		}
	}
}



