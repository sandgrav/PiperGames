package org.teambravo.pipergames.view;

import org.teambravo.pipergames.controller.TeamClassController;
import org.teambravo.pipergames.entity.Team;

import java.util.Scanner;

public class Menu {
    private TeamClassController teamClassController;

    public Menu() {
        this.teamClassController = new TeamClassController();
    }

    public void showMenu(TeamClassController teamClassController) {
        System.out.println("\n-------------------Gör ett val-----------------\n");
        System.out.println("12. Add team");
        System.out.println("--------------------------");
        System.out.println("13. List teams");
        System.out.println("--------------------------");
        System.out.println("14. Delete team");
        System.out.println("--------------------------");

        handleInput();
    }

    private void space() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    private void handleInput() {
        System.out.print("Ange ditt val: ");
        String userChoice = new Scanner(System.in).nextLine();
        space();
        switch (userChoice) {
            case "12":
                System.out.print("Ange team: ");
                String teamName = new Scanner(System.in).nextLine();
                if (teamClassController.saveTeam(new Team(teamName))) {
                    System.out.println(teamName + " added");
                } else {
                    System.out.println("Failed to add team");
                }
                break;
            case "13":
                teamClassController.getAllTeams(true);
                break;
            case "14":
                teamClassController.getAllTeams(true);
                System.out.print("Choose team: ");
                if (teamClassController.deleteTeam(new Scanner(System.in).nextInt())) {
                    System.out.println("Team deleted");
                } else {
                    System.out.println("Failed to delete team");
                }
                break;
            default:
                break;
        }
        showMenu(teamClassController);
    }
}
