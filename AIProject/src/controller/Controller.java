package controller;

/** The Controller class is responsible for requesting the model
 * to update its state whenever there is an event on a button on 
 * the game board.
 * */

import java.util.ArrayList;

import model.*;
import view.View;
public class Controller {
	private Model m;
	
	// initializing the reference of model class
	public void setModel(Model m) {
		this.m = m;
	}
	
	// function to request the model to update the board
	public void setRequest(ArrayList<Integer> position) {
		m.PlayMove(position.get(0), position.get(1));
	}
	
	// overloaded function to request model to reset
	public void setRequest() {
		m.ResetModel();
	}
	public static void main(String[] args) {
		Controller c = new Controller();  
		Model m = new Model();  
		View v = new View();
		m.registerView(v);
		c.setModel(m);
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		pos.add(1);
		pos.add(1);
		
		// ask the model to update its board depending on the request
		c.setRequest(pos);
		
		// check if the requested operation updated the model successfully
	}
	
}
