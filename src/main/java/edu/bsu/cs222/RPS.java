package edu.bsu.cs222;

import java.util.Scanner;

public class RPS {
        public static void main(String[] args) {
                System.out.println("Enter your move: rock, paper, or scissors: ");
                Scanner scanner = new Scanner(System.in);

                String playerMove = scanner.nextLine().toLowerCase();

                int rand = (int) (Math.random() * 3);

                String gameMove = "";
                if(rand ==0) {
                        gameMove = "rock";
                }
                if (rand ==1){
                        gameMove = "paper";
                }
                if (rand == 2){
                        gameMove = "scissors";
                }
                System.out.println("Game Move: " + gameMove);

                if(playerMove.equals(gameMove)){
                        System.out.println(playerMove + " vs " + gameMove);
                        System.out.println("Tie!");
                }else if(playerMove.equals("rock")&& gameMove.equals("scissors")){
                        System.out.println(playerMove + " vs " + gameMove);
                        System.out.println("You Win");
                }else if(playerMove.equals("paper")&& gameMove.equals("rock")) {
                        System.out.println(playerMove + " vs " + gameMove);
                        System.out.println("You Win");
                }else if(playerMove.equals("scissors")&& gameMove.equals("paper")) {
                        System.out.println(playerMove + " vs " + gameMove);
                        System.out.println("You Win");
                }else{
                        System.out.println(playerMove + " vs " + gameMove);
                        System.out.println("You Lost!");
                }
        }
}
