package model;

/** The model class is where the current state of the game
 * as well as the winning logic resides. The model class calls 
 * the view to update the gui according to the current state of 
 * the game.
 * */

import view.*;

public class Model {
	private View v;
	private int playerId;
	private int movesCount;
	private char[][] board;
	private String message;
	public static int N = 13;
	// default constructor
	public Model() {
		this.board = new char[N][N];
		this.movesCount = 99;
		this.playerId = 1;
	}
	
	// initializing the reference of view class
	public void registerView(View v) {
		this.v = v;
	}
    
	//setters and getters
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getMovesCount() {
		return movesCount;
	}

	public void setMovesCount(int movesCount) {
		this.movesCount = movesCount;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// function to update the board model
	public void PlayMove(int x, int y) {
		
		if(getMovesCount() > 0){
			// mark the board with x or o depending on playerId
			if(playerId%2 != 0) 
				board[x][y] = 'X';
			else 
				board[x][y] = 'O';
			
			// reduce the count of moves left
			setMovesCount(--movesCount);
			
			// check if playerId has won or if game is tied,
			// and send message accordingly to view, also update the view
			if(isWinner(x, y)) {
				setMessage("Player " + playerId + " is Winner!");
				v.isWinner(x, y, board[x][y], getMessage());
			}
			else if(getMovesCount()==0) {
				setMessage("No Winner! Game ended in a Tie");
				v.isWinner(x, y, board[x][y], getMessage());
			}
			else {
				if(playerId%2 != 0) {
					// toggle the playerId
					setPlayerId(2);
					setMessage("'O':  Player " +getPlayerId());
				}
				else {
					setPlayerId(1);
					setMessage("'X':  Player " +getPlayerId());

				}
				// update the board with message for next player
				v.update(x, y, board[x][y], getMessage());
			}
			
		}
		
	}
	
	// function to check if there is a winner
	public boolean isWinner(int x, int y) {
		int k, j;
        int d = 0;
		char symbol;
		if(getPlayerId() %2 !=0)
			symbol = 'X';
		else
			symbol = 'O';
		
		 for (k = -4; k <= 4; k++) {
	            if (x + k >= 0 && x + k < N) {
	                if (board[x + k][y] == symbol) {
	                    d++;
	                } else if (d < 5) {
	                    d = 0;
	                }
	            }
	        }
	        if (d >= 5) {
	            return true;
	        } else {
	            d = 0;
	        }
	        //xet ngang
	        for (k = -5; k <= 5; k++) {
	            if (y + k >= 0 && y + k < N) {
	                if (board[x][y + k] == symbol) {
	                    d++;
	                } else if (d < 5) {
	                    d = 0;
	                }
	            }
	        }
	        if (d >= 5) {
	            return true;
	        } else {
	            d = 0;
	        }
	        //cheo
	        for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
	            if (y + k >= 0 && y + k < N && x + j >= 0 && x + j < N) {
	                if (board[x + j][y + k] == symbol) {
	                    d++;
	                } else if (d < 5) {
	                    d = 0;
	                }
	            }
	        }
	        if (d >= 5) {
	            return true;
	        } else {
	            d = 0;
	        }
	        for (k = -4; k <= 4; k++) {
	            if (y + k >= 0 && y + k < N && x + k >= 0 && x + k < N) {
	                if (board[x + k][y + k] == symbol) {
	                    d++;
	                } else if (d < 5) {
	                    d = 0;
	                }
	            }
	        }
	        if (d >= 5) {
	            return true;
	        }
	        return false;
	}
	
	// function to clear the model and reset it to initial state
	public void ResetModel() {
		movesCount = 99;
		setPlayerId(1);
		setMessage("");
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board.length;j++) {
				board[i][j] = '\0';
			}
		}
		v.resetGame();
		
	}
		
}
