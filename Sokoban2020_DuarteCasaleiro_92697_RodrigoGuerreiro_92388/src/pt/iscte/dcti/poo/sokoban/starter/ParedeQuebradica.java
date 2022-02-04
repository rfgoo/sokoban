package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class ParedeQuebradica extends AbstractObject implements Interactable {

	public ParedeQuebradica(Point2D point2d, String nome, int layer) {
		super(point2d,nome, layer, false, false);
	}

	@Override
	public void interact(Point2D playerNextPosition, AbstractObject object) {
		
		System.out.println(SokobanGame.getInstance().getPlayer().hasMartelo());
		
		if((SokobanGame.getInstance().getPlayer().hasMartelo())){
			ImageMatrixGUI.getInstance().removeImage(this);
			super.getList().remove(this);
			object.setPosition(playerNextPosition);
		}

	}

}
