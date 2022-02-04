package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone extends Movivel implements Movable{

	public BigStone(Point2D point2D) {
		super(point2D,"BigStone", 4);
	}


	@Override
	public void move() {
		if(this.isMovable()){
			super.move();
		}
	}

	@Override
	public void falling(Point2D playerNextPosition, AbstractObject object) {
		
		super.setPosition(playerNextPosition);
		
		ImageMatrixGUI.getInstance().removeImage(object);
		super.getList().remove(object);
		
		this.setMovable(false);

	}

}
