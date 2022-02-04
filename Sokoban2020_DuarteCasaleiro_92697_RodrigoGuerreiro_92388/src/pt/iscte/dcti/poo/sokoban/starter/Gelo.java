package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Gelo extends AbstractObject implements Interactable {


	public Gelo(Point2D point2d) {
		super(point2d, "Gelo", 0, false, true);
	}



	@Override
	public void interact(Point2D playerNextPosition, AbstractObject object) {

		while(isGelo(playerNextPosition) && canMove(playerNextPosition, object)){

			playerNextPosition = playerNextPosition.plus(super.getDir().asVector());
			object.setPosition(playerNextPosition);
		}
		nextInteractable(playerNextPosition, object);
		SokobanGame.getInstance().getPlayer().setEnergy();
		SokobanGame.getInstance().getPlayer().setMoves();
	}

	private boolean canMove(Point2D playerNextPosition, AbstractObject object){

		for(AbstractObject obj: super.getList()){

			if(obj.getPosition().equals(playerNextPosition.plus(super.getDir().asVector())) && !(obj instanceof Gelo) && !(obj.isTransposable())){

				if(obj.getPosition().equals(this.getPosition().plus(super.getDir().asVector())) &&  !obj.isTransposable()){
					object.setPosition(playerNextPosition);
				}

				return false;
			}
		}
		return true;
	}

	private void nextInteractable(Point2D playerNextPosition, AbstractObject object){
		
		for(AbstractObject obj: super.getList()){

			if(obj.getPosition().equals(playerNextPosition) && obj instanceof Interactable && !(obj instanceof Gelo)){

				((Interactable)obj).interact(playerNextPosition, object);
				return;
			}
		}
	}



	private boolean isGelo(Point2D playerNextPosition){

		boolean bol = false;

		for(AbstractObject obj: super.getList()){

			if(obj.getPosition().equals(playerNextPosition) && !(obj.isMovable()) && (obj instanceof Gelo)){
				bol = true;
			}

		}
		return bol;
	}

}




