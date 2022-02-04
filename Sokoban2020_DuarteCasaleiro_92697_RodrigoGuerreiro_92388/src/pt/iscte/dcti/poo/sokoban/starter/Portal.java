package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Portal extends AbstractObject implements Interactable{

	public Portal(Point2D point) {
		super(point, "Portal_Verde", 0, false, true);
	}


	private AbstractObject object(Point2D playerNextPosition, AbstractObject object){

		for(AbstractObject portal2: super.getList()){

			if(!portal2.getPosition().equals(this.getPosition()) && portal2 instanceof Portal && portal2.isTransposable()){
				return portal2;
			}
		}
		return null;
	}


	private boolean canTeleport(Point2D playerNextPosition, AbstractObject portal2, AbstractObject object){

		for(AbstractObject obj: super.getList()){

			if(obj.getPosition().equals(portal2.getPosition()) && obj instanceof Movivel){
				return false;
			}
		}
		return true;
	}


	@Override
	public void interact(Point2D playerNextPosition, AbstractObject object) {

		AbstractObject portal2 = object(playerNextPosition, object);

		if(canTeleport(playerNextPosition, portal2, object)){

			object.setPosition(portal2.getPosition());
			return;
		}
		object.setPosition(this.getPosition());
	}

}
