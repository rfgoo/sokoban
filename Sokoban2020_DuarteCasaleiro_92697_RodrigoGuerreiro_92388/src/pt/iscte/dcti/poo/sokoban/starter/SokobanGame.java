package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

	private Player player;
	private int level;
	private ArrayList<AbstractObject> Objects = new ArrayList<AbstractObject>();
	private String userName;
	private User user;
	private int playerscore;
	private static SokobanGame INSTANCE;  
	private Direction Dir;

	private SokobanGame(){

		userName = JOptionPane.showInputDialog("Insira o seu nome: ");
		user = new User(userName, 0);

		buildSampleLevel(level);
	}

	public static SokobanGame getInstance(){
		if(INSTANCE == null)
			INSTANCE = new SokobanGame();
		return INSTANCE;
	}

	public Player getPlayer() {
		return player;
	}

	public int getLevel() {
		return level;
	}

	public ArrayList<AbstractObject> getObjects() {
		return Objects;
	}

	public Direction getDirection() {

		return Dir;

	}


	private void buildSampleLevel(int level){

		for (int i = 0; i != 10; i++) {
			for (int j = 0; j != 10; j++) {
				ImageMatrixGUI.getInstance().addImage(new Chao(new Point2D(i, j), "Chao", 0));
			}
		}

		int y = 0;

		Scanner scan;
		try {
			scan = new Scanner(new File("Levels/level" + level + ".txt"));
			while (scan.hasNextLine()) {

				char[] objs = scan.nextLine().toCharArray();

				System.out.println(objs);

				for (int x = 0; x != objs.length; x++) {

					switch (objs[x]) {
					case '#':
						Objects.add(new Parede(new Point2D(x, y), "Parede", 1));
						break;
					case 'E':
						this.player = new Player(new Point2D(x, y),"Empilhadora_D",2);
						Objects.add(player);
						break;
					case 'C':
						Objects.add(new Caixote(new Point2D(x, y)));
						break;
					case 'X':
						Objects.add(new Alvo(new Point2D(x, y)));
						break;
					case 'O':
						Objects.add(new Buraco(new Point2D(x, y)));
						break;
					case 'b':
						Objects.add(new Bateria(new Point2D(x, y), "Bateria", 1));
						break;
					case 'p':
						Objects.add(new SmallStone(new Point2D(x, y)));
						break;
					case 'P':
						Objects.add(new BigStone(new Point2D(x, y)));
						break;
					case 'm':
						Objects.add(new Martelo(new Point2D(x, y), "Martelo", 1));
						break;
					case 'g':
						Objects.add(new Gelo(new Point2D(x, y)));
						break;
					case '%':
						Objects.add(new ParedeQuebradica(new Point2D(x, y), "Parede_Partida",1));
						break;
					case 't':
						Objects.add(new Portal(new Point2D(x, y)));
						break;
					default:
						break;
					}
				}
				y++;
			}
			System.out.println("------------  SOKOBAN GAME  ------------");
			System.out.println(userName);
			System.out.println("Level: " + level);
			System.out.println("Score: " + playerscore);

			scan.close();
			ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + "  Moves: " + player.getMoves() + "  Energy: " + player.getEnergy() + "  Score: " + playerscore + "  Press R to RESTART   Press Q to QUIT");


		} catch (FileNotFoundException e) {
			System.out.println("\n--------------  WINNER!!  --------------");
			System.out.println("\nCongratulations you've completed the game!");
			System.out.println("\nYou can now see your total score in 'ScoreBoard.txt' :)");
			user.leaderBoard(playerscore);
			System.exit(0);
		}

		for (AbstractObject obj : Objects) {
			ImageMatrixGUI.getInstance().addImage(obj);
		}

	}

	private void close(){

		for (AbstractObject obj : Objects) {
			ImageMatrixGUI.getInstance().removeImage(obj);
		}
		Objects.clear();
	}

	private void rebuild(){

		for(AbstractObject alvo: Objects){
			if(alvo instanceof Alvo && !((Alvo)alvo).check(alvo)){
				return;
			}
		}
		System.out.println("Rebuilding");
		user.singleLevelScore(level,player.getMoves());
		playerscore += player.getMoves();
		close();
		buildSampleLevel(++level);
	}

	@Override
	public void update(Observed arg0) {

		int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();

		if (player != null && (lastKeyPressed == KeyEvent.VK_LEFT||lastKeyPressed == KeyEvent.VK_RIGHT||lastKeyPressed == KeyEvent.VK_UP||lastKeyPressed == KeyEvent.VK_DOWN)) {
			Dir = Direction.directionFor(lastKeyPressed);
			player.chooseImage(Dir);
			player.move();
			ImageMatrixGUI.getInstance().setStatusMessage("Level: " + level + "  Moves: " + player.getMoves() + "  Energy: " + player.getEnergy() + "  Score: " + playerscore + "  Press R to RESTART   Press Q to QUIT");
			if(player.getRestart()){
				close();
				buildSampleLevel(level);
			}
		}
		else if(lastKeyPressed == KeyEvent.VK_R){
			close();
			buildSampleLevel(level);
		}
		else if(lastKeyPressed == KeyEvent.VK_Q){
			System.exit(0);
		}

		rebuild();
		ImageMatrixGUI.getInstance().update();
	}



}
