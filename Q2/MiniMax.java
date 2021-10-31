package Q2;

import java.util.*;

public class MiniMax {
    private static boolean gameOver(State state) {
        if(state.x == 0 && state.y == 0)
            return true;
        return false;
    }

    private static State minimax_decision(State state) {
        ArrayList<State> possibleStates = new ArrayList<>();
        ArrayList<Integer> utilityScores = new ArrayList<>();
        Player nextPlayer;
        if(state.player == Player.MAX)
            nextPlayer = Player.MIN;
        else    nextPlayer = Player.MAX;
        for(int i = state.x-1; i >= 0; --i) {
            possibleStates.add(new State(i,state.y,nextPlayer));
        }
        for(int i = state.y-1; i >= 0; --i) {
            possibleStates.add(new State(state.x,i,nextPlayer));
        }
        for(State s : possibleStates) {
            utilityScores.add(minimax_value(s));
        }
        return possibleStates.get(utilityScores.indexOf(Collections.max(utilityScores)));
        
    }

    private static int minimax_value(State s) {
        if(gameOver(s) && s.player == Player.MAX)
            return -1;
        if(gameOver(s) && s.player == Player.MIN)
            return 1;
        ArrayList<State> possibleStates = new ArrayList<>();
        ArrayList<Integer> utilityScores = new ArrayList<>();
        Player nextPlayer;
        if(s.player == Player.MAX)
            nextPlayer = Player.MIN;
        else    nextPlayer = Player.MAX;
        for(int i = s.x-1; i >= 0; --i) {
            possibleStates.add(new State(i,s.y,nextPlayer));
        }
        for(int i = s.y-1; i >= 0; --i) {
            possibleStates.add(new State(s.x,i,nextPlayer));
        }
        for(State s1 : possibleStates) {
            int a = minimax_value(s1);
            utilityScores.add(a);
        }
        if(s.player == Player.MAX)
            return Collections.max(utilityScores);
        return Collections.min(utilityScores);
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the stones in the piles(x y): ");
        State currentState = new State(scan.nextInt(), scan.nextInt(), Player.MAX);
        System.out.println("\n--Game--\n");
        System.out.println("Alternatively the computer and user plays the game, starting off by computer");
        System.out.println("The state of the game i.e, the number of stones remained in piles [A,B] would be displayed after the computer's move");
        System.out.println("Upon which user needs to enter his move: Pilenumber numberofstonestoberemovedfrom\n");
        System.out.println("--Let's Play--");
        while(!gameOver(currentState)) {
            System.out.println("\nComputer's move");
            currentState = minimax_decision(currentState);
            System.out.println("State after Computer's move: " + "[" + currentState.x + "," + currentState.y + "]");
            currentState.player = Player.MIN;
            if(gameOver(currentState))
                break;
            System.out.print("\nEnter your next move(pilenum noofstones): ");
            if(scan.nextInt() == 1)
                currentState.x -= scan.nextInt();
            else
                currentState.y -= scan.nextInt();
            currentState.player = Player.MAX;
        }
        if(currentState.player == Player.MAX)
            System.out.println("\nUSER - WON, Computer - Lost!!");
        else if(currentState.player == Player.MIN)
            System.out.println("\nUser - Lost, COMPUTER - WON!!");
        scan.close();
    }
}

class State {
    int x;
    int y;
    Player player;

    State(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
}

enum Player {
    MIN, MAX;
}