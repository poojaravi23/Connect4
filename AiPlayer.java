import java.util.*;

/**@author James Spargo
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing.
 *
 *
 *
 */

public class AiPlayer
{

	/**
	 * The constructor essentially does nothing except instantiate an
	 * AiPlayer object.4
	 *
	 *
	 */
	public AiPlayer(int depthLevel)
	{
		//this.depthLevel_level = depthLevel;
	}
	/**
	 * @param currentGame The GameBoard object that is currently being used to
	 * play the game.
	 * @return an integer indicating which column the AiPlayer would like
	 * to play in.
	 */
	public int findBestPlay(GameBoard currentGame, int depthLevel ) //alphabeta pruning logic
	{
		int alpha=Integer.MIN_VALUE; //initialise the value alpha= -infinity
		int beta=Integer.MAX_VALUE;  //initialise the value beta= +infinity
		int large_value = Integer.MIN_VALUE;
		int largest = -1;
		int v=alpha;

		for (int i=0; i<7;i++) {
			if(currentGame.isValidPlay(i)) {
				GameBoard temp = new GameBoard(currentGame.getGameBoard());
				temp.playPiece(i);
				v =minvalue(temp,alpha,beta,depthLevel-1);
				if( v > large_value){
					large_value =v;
					largest=i;
				}
			}

		}
		return largest;


	}

	int maxvalue(GameBoard state,int alpha,int beta, int depthLevel) //max player move
	{
		if(state.getPieceCount()==42)
			return utility(state);
		if( depthLevel == 0 )
			return evaluate(state);
		else {
			ArrayList<GameBoard> childNodes = new ArrayList<GameBoard>(); //create an array list for a tree
			childNodes=	getPossibleBoards(state);

			int v = Integer.MIN_VALUE;
			for (int i = 0; i < childNodes.size(); i++) {
				v = Math.max(v, minvalue( childNodes.get(i), alpha, beta, depthLevel-1));
				if(v>=beta) {
					return v;
				}
				alpha = Math.max(alpha, v);
			}
			return v;

		}


	}
	int minvalue(GameBoard state,int alpha,int beta, int depthLevel) //min player move
	{
		if(state.getPieceCount()==42) //if the game reaches terminal node return utility value
			return utility(state);
		if( depthLevel == 0 )
			return evaluate(state);
		else {
			ArrayList<GameBoard> childNodes = new ArrayList<GameBoard>(); //array list for the child nodes
			childNodes=	getPossibleBoards(state);

			int v = Integer.MAX_VALUE;
			for (int i = 0; i < childNodes.size(); i++) {
				v = Math.min(v, maxvalue(childNodes.get(i), alpha, beta,depthLevel-1));
				if(v<=alpha) {
					return v;
				}
				beta = Math.min(beta, v);
			}
			return v;

		}
	}
	//to get the successor function
	ArrayList<GameBoard> getPossibleBoards(GameBoard state) {
		ArrayList<GameBoard> childNodes = new ArrayList<GameBoard>();
		for (int i=0; i<7; i++) {

			if(state.isValidPlay(i)) {
				GameBoard tempChild = new GameBoard(state.getGameBoard());
				tempChild.playPiece(i);
				childNodes.add(tempChild);
			}
		}
		return childNodes;

	}
	//calculate the utility function
	public int utility(GameBoard currentGame){
		if(currentGame.getScore(1) > currentGame.getScore(2)) {
			int utility_value=Integer.MAX_VALUE;
			return utility_value;
		}
		else if(currentGame.getScore(2) > currentGame.getScore(1)) {
			int utility_value=Integer.MIN_VALUE;
			return utility_value;
		}
		else {
			int utility_value=0;
			return utility_value;
		}
	}
	//calculation of evaluation function
	public int evaluate(GameBoard currentGame) {
		int et = (currentGame.getScore(1) - currentGame.getScore(2));
		return et;


		// TODO Auto-generated method stub

	}

	/* start random play code
	Random randy = new Random();
	int playChoice = 99;

	playChoice = randy.nextInt( 7 );

	while(!currentGame.isValidPlay( playChoice ))
	    playChoice = randy.nextInt( 7 );

	 //end random play code*/

	//return playChoice;
}
