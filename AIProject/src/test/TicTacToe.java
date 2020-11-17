package test;

/** This is the Driver class for the TicTacToe game.
 * It creates objects of Model, View and Controller 
 * classes and aggregates them.
 * */

import model.*;
import view.*;
import controller.*;

public class TicTacToe {

	public static void main(String[] args) {
		// Create the components

		Controller c = new Controller();
		View v = new View();
		Model m = new Model();

		// Use aggregation to put the components together
		m.registerView(v);
		c.setModel(m);
		v.setActionListener(c);
	}
}