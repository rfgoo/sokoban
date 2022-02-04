package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends Movivel implements Movable{


	public SmallStone(Point2D point2D) {
		super(point2D, "SmallStone", 2);
	}

	@Override
	public void move() {
		super.move();
	}

	@Override
	public void falling(Point2D playerNextPosition, AbstractObject object) {
		System.out.println("Falling");
		ImageMatrixGUI.getInstance().removeImage(this);
		super.getList().remove(this);

	}

}
