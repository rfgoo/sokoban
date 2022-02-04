package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class User implements Comparable<User>{

	private String name;
	private int score;

	public User(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return score + " " + name;
	}


	public void leaderBoard(int playerscore){
		this.score = playerscore;

		try {
			Scanner scanner = new Scanner(new File("ScoreBoard.txt"));

			ArrayList<User> scoreList = new ArrayList<>();

			while(scanner.hasNextLine()){
				int Score = scanner.nextInt();
				String name = scanner.nextLine().trim();
				scoreList.add(new User(name, Score));
			}
			scanner.close();
			scoreList.add(this);

			Collections.sort(scoreList);

			PrintWriter pw = new PrintWriter(new File("ScoreBoard.txt"));
			for(User a: scoreList){
				pw.println(a);
			}
			pw.close();
			showScreenScores(scoreList, playerscore);
			
		} catch (FileNotFoundException e) {
			try {
				PrintWriter pw = new PrintWriter(new File("ScoreBoard.txt"));
				leaderBoard(playerscore);
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}	
	}
	
	private void showScreenScores(ArrayList<User> scoreList, int playerscore){
		if (scoreList.size()==1){
			if(scoreList.get(0).getScore()==this.score)
			JOptionPane.showMessageDialog(null,"NEW HIGH SCORE " + playerscore + " MOVES\n\n TOP 3\n\n1º - "+ scoreList.get(0));
			else
			JOptionPane.showMessageDialog(null,"YOU'VE FINISHED WITH " + playerscore + " MOVES\n\n TOP 3\n\n1º - "+ scoreList.get(0));

			}
			else if(scoreList.size()==2){
			if(scoreList.get(0).getScore()==this.score)
			JOptionPane.showMessageDialog(null,"NEW HIGH SCORE " + playerscore + " MOVES\n\n TOP 3\n\n1º - "+ scoreList.get(0)+ "\n2º - " + scoreList.get(1));
			else
			JOptionPane.showMessageDialog(null,"YOU'VE FINISHED WITH " + playerscore + " MOVES\n\n TOP 3\n\n1º - "
			+ scoreList.get(0) + "\n2º - " + scoreList.get(1));}
			else{
			if(scoreList.get(0).getScore()==this.score)
			JOptionPane.showMessageDialog(null,"NEW HIGH SCORE " + playerscore + " MOVES\n\n TOP 3\n\n1º - "+ scoreList.get(0)+ "\n2º - " + scoreList.get(1) + "\n3º - "+ scoreList.get(2));
			else
			JOptionPane.showMessageDialog(null,"YOU'VE FINISHED WITH " + playerscore + " MOVES\n\n TOP 3\n\n1º - "
			+ scoreList.get(0) + "\n2º - " + scoreList.get(1) + "\n3º - "+ scoreList.get(2));}

	}

	@Override
	public int compareTo(User a) {

		if(getScore()<a.getScore())
			return-1;
		if(getScore()>a.getScore())
			return 1;
		if(getScore()==a.getScore()){
			return getName().compareToIgnoreCase(a.getName());
		}
		return 0;
	}

	public void singleLevelScore(int level, int score){

		try {
			Scanner scanner = new Scanner(new File("ScoreBoard"+level+".txt"));

			ArrayList<User> scoreListlevel = new ArrayList<>();

			while(scanner.hasNextLine()){
				int Score = scanner.nextInt();
				String name = scanner.nextLine().trim();


				scoreListlevel.add(new User(name, Score));
			}
			scanner.close();
			scoreListlevel.add(new User(name,score));

			Collections.sort(scoreListlevel);

			PrintWriter pw = new PrintWriter(new File("ScoreBoard"+level+".txt"));
			for(User a: scoreListlevel){
				pw.println(a);
			}
			pw.close();

		} catch (FileNotFoundException e) {
			try {
				PrintWriter pw = new PrintWriter(new File("ScoreBoard"+level+".txt"));
				singleLevelScore(level,score);
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		}	
	}

}




