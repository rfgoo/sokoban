package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractObject implements ImageTile{

	private String name;
	private int layer;
	private Point2D position;
	private boolean isMovable;
	private boolean isTransposable;
	
	
	public AbstractObject(Point2D point, String name, int layer, boolean isMovable, boolean isTransposable) {
		this.position=point;
		this.name = name;
		this.layer = layer;
		this.isMovable = isMovable;
		this.isTransposable = isTransposable;
	}

	
	
	

	public boolean isMovable() {
		return isMovable;
	}

	public boolean isTransposable() {
		return isTransposable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public void setTransposable(boolean isTransposable) {
		this.isTransposable = isTransposable;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName(){
		return this.name;
	}

	@Override
	public int getLayer(){
		return this.layer;
	}
	
	public void setLayer(int layer){
		this.layer = layer;
	}
	@Override
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D num){
		position=num;
	}
	
	public ArrayList<AbstractObject> getList() {
		return SokobanGame.getInstance().getObjects();
	}

	public Direction getDir() {
		return SokobanGame.getInstance().getDirection();
	}
	
	
}
