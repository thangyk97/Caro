package model;

import java.awt.Point;

import view.BoardView;

public class Bot extends Player{

	private Player player;
	public static final int MAX = 1000;
	public static final int MIN = -1000;

  public Bot(String name, Player player) {
		super(name);
		
		this.player = player;
	}
  
	/**
	 * test game without AI module
	 * @param board
	 * @return square
	 */
  
	public Point calculate(Board board) {
		for(int i = 0; i < board.getHeight(); i++)
			for(int j = 0; j < board.getWidth(); j++) {
				
				if( ! board.getArraySquare()[i][j].isChosen()) {
					return board.getArraySquare()[i][j].getPoint();
				}
			}
		return null;

	}
	
	/**
	 * check does board has empty square
	 * @param arraySquare
	 * @return boolean
	 */
	
  private boolean isNotHaveSquareEmpty(Square[][] arraySquare) {
    for(int i = 0; i < BoardView.size; i++) {
      for(int j = 0; j < BoardView.size; j++) {
        if(! arraySquare[i][j].isChosen())
          return false;
      }
    }
    return true;
  }
  
	/**
	 * evaluate score of board state 
	 * @param arraySquare
	 * @param point
	 * @return 10 with win for AI_bot and -10 with win for player
	 */
	
	public int evaluate(Square[][] arraySquare, Point point) {
	  if(this.checkEndGame(point, arraySquare)) {
	    return 10;
	  }
	  if(player.checkEndGame(point, arraySquare)) {
	    return -10;
	  }
	  return 0;
	}
	
	/**
	 * return score of move 
	 * @param arrSquare
	 * @param point
	 * @param depth
	 * @param isMax
	 * @return 10 if maximizer win
	 *        -10 if minimizer win
	 *          0 if no winner
	 */
	
	public int minimax(Square[][] arrSquare, Point point,
	                   int depth, boolean isMax) {
	  int score = 0;
	  
	  score = evaluate(arrSquare, point);
	  
	  if(score == 10 ||
	     score == -10 ||
	     this.isNotHaveSquareEmpty(arrSquare) ||
	     depth > 1) {
	    return score-depth;
	  }
	  
	  if(isMax) {
	    int best = -1000;
	    
	    for(int i = 0; i < BoardView.size; i++) {
	      for(int j = 0; j < BoardView.size; j++) {

	        if(! arrSquare[i][j].isChosen()) {
	          
	          arrSquare[i][j].setChosen(true);
	          arrSquare[i][j].setMark(this.getName());
	          best = Math.max(best, minimax(arrSquare, new Point(i,j), 
          	                              depth + 1, !isMax));
	          
	          arrSquare[i][j].setChosen(false);
	          arrSquare[i][j].setMark(null);
	          
	        }
	        
	      }
	    }
	    return best;
	    
	  }else {
	    int best = 1000;
	    
	    for(int i = 0; i < BoardView.size; i++) {
	      for(int j = 0; j < BoardView.size; j++) {
	        
	        if(! arrSquare[i][j].isChosen()) {
	          
	          arrSquare[i][j].setChosen(true);
	          arrSquare[i][j].setMark(player.getName());
	          best = Math.min(best, minimax(arrSquare, new Point(i,j),
	                                        depth + 1, !isMax));
            arrSquare[i][j].setChosen(false);
            arrSquare[i][j].setMark(null);	          
	        }
	      }
	    }
	    return best;
	  }
	}

  /**
	 * return the move then AI_bot win
	 * @param arraySquare
	 * @return point
	 */

	public Point findBestMove(Square[][] arraySquare) {
	  
	  Point bestMove = new Point(1,1);
	  int bestVal = -1000;
	  int moveVal = 0;
	  
	  for(int i = 0; i < BoardView.size; i++) {
	    for(int j = 0; j < BoardView.size; j++) {
	      
        if(! arraySquare[i][j].isChosen()) {
          
          System.out.println(i + "," + j + "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
          
          this.markSquare(new Point(i,j), arraySquare);
          
          moveVal = minimax(arraySquare, new Point(i,j), 0, false);
          
          System.out.println(moveVal);
          arraySquare[i][j].setChosen(false);
          arraySquare[i][j].setMark(null);  
          
          if(moveVal > bestVal) {
            bestVal = moveVal;
            bestMove.setLocation(i, j);
          }
          
        }	      
	    }
	  }
	  return bestMove;
	}
	/*
	 * 
	 * 
	 * 
	 */
	public Point findBestMoveAlphaBeta(Square [][] arraySquare) {
	  Point bestMove = new Point(0,0);
	  int alpha = MIN;
	  int beta = MAX;
	  int best = MIN;
	  
    for(int i = 0; i < BoardView.size; i++) {
      for(int j = 0; j < BoardView.size; j++) {
        
        
        
        if(! arraySquare[i][j].isChosen()) {
          this.markSquare(new Point(i,j), arraySquare);
          int val = minimaxAlphaBeta(0, arraySquare, new Point(i,j), false, alpha, beta);
          
          if(val > best) {
            bestMove.setLocation(i, j);
          }
          best = Math.max(best, val);
          
          alpha = Math.max(alpha, best);
          
          arraySquare[i][j].setChosen(false);
          arraySquare[i][j].setMark(null);
          
          if(beta <= alpha) {
            
            break;
          }
        }
        
        
      }
    }
	  return bestMove;
	}
	/*
	 * 
	 */
	public int minimaxAlphaBeta(int depth, Square[][] arraySquare, Point point,
	                            boolean isMax, int alpha, int beta) {
	  
    int score = 0;
    
    score = evaluate(arraySquare, point);
    if(score == 10 ||
       score == -10 ||
       this.isNotHaveSquareEmpty(arraySquare) ||
       depth > 3) {
      return score;
    }

    if(isMax) {
      int best = MIN;
      
      for(int i = 0; i < BoardView.size; i++) {
        for(int j = 0; j < BoardView.size; j++) {
          
          if(! arraySquare[i][j].isChosen()) {
            this.markSquare(new Point(i,j), arraySquare);
            int val = minimaxAlphaBeta(depth + 1, arraySquare, new Point(i,j), false, alpha, beta);
            
            best = Math.max(best, val);
            alpha = Math.max(alpha, best);
            
            arraySquare[i][j].setChosen(false);
            arraySquare[i][j].setMark(null);
            
            if(beta <= alpha) {
              System.out.println("break");
              break;
            }
          }
        }
      }
      
      return best;
    }else {
      int best = MAX;
      
      for(int i = 0; i < BoardView.size; i++) {
        for(int j = 0; j < BoardView.size; j++) {
          
          if(! arraySquare[i][j].isChosen()) {
            player.markSquare(new Point(i,j), arraySquare);
            
            int val = minimaxAlphaBeta(depth + 1, arraySquare, new Point(i,j), true, alpha, beta);
            
            best = Math.min(best, val);
            beta = Math.min(beta, best);
            
            arraySquare[i][j].setChosen(false);
            arraySquare[i][j].setMark(null);
            
            if(beta <= alpha) {
              break;
            }
          }
        }
      }
      
      return best;
    }
	}
}




















