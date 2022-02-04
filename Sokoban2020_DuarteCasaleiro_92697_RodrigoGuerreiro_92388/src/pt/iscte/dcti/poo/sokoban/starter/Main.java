package pt.iscte.dcti.poo.sokoban.starter;

import java.io.FileNotFoundException;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		ImageMatrixGUI.setSize(10, 10);
		SokobanGame s = SokobanGame.getInstance();
		ImageMatrixGUI.getInstance().registerObserver(s);
		ImageMatrixGUI.getInstance().go();

	}

}
