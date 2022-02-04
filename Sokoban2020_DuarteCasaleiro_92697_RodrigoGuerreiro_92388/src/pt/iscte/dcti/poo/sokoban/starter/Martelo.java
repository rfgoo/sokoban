package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Martelo extends AbstractObject implements Interactable{
	public Martelo(Point2D point, String name, int layer) {
		super(point, name, layer, false, true);
	}
	
	private void pickHammer(AbstractObject obj){
		
		if(obj instanceof Player ){
			System.out.println("Power UP!");
			((Player)obj).setMartelo(true);
			ImageMatrixGUI.getInstance().removeImage(this);
			super.getList().remove(this);
			obj.setPosition(this.getPosition());
		}
		else{
			obj.setPosition(this.getPosition());
		}
		
		
		
		
	}

	@Override
	public void interact(Point2D playerNextPosition, AbstractObject object) {
		pickHammer(object);
	}
}