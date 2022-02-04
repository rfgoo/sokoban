package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractObject implements Interactable{



	public Bateria(Point2D point2D, String name, int layer) {
		super(point2D,name, layer, false, true);
	}


	public void refill(AbstractObject obj){

		if(obj instanceof Player){
			((Player)obj).setEnergia(100);
			System.out.println("Reffiled!");
			ImageMatrixGUI.getInstance().removeImage(this);
			super.getList().remove(this);
			obj.setPosition(this.getPosition());
			return;
		}

		obj.setPosition(this.getPosition());

	}

	@Override
	public void interact(Point2D playerNextPosition, AbstractObject object) {
		refill(object);
	}

}	

