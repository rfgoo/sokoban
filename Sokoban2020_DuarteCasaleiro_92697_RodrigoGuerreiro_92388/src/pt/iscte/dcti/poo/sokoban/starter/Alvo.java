package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractObject{

	public Alvo(Point2D point2D) {
		super(point2D,"Alvo", 1, false, true);
	}

	
	public boolean check(AbstractObject object){
		
		for(AbstractObject obj: super.getList()){
			if(object.getPosition().equals(obj.getPosition()) && obj instanceof Caixote){
				return true;
			}
		}
		return false;
	}

}
