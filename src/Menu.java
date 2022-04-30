import java.sql.SQLException;
import java.util.*;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> options = Arrays.asList("Display Teams", "Create a Team", "Delete Team", "Update Team", "Exit");

    public void start() {
        DBConnection.initialize();
        String selection;

        do{
            printMenu();
            selection = scanner.nextLine();

            try {
                if (selection.equals("1")){
                    displayTeams();
                } else if (selection.equals("2")) {
                    createTeam();
                } else if (selection.equals("3")){
                    deleteTeam();
                } else if (selection.equals("4")){
                    updateTeam();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Press enter to continue. . .");
            scanner.nextLine();

        } while (!selection.equals("6"));
    }

    private void printMenu() {
        System.out.println("Select an option: \n-------------------------");
        for (int i = 0; i < options.size(); i++){
            System.out.println(i + 1 + ") " + options.get(i));
        }
    }
    private void displayTeams() throws SQLException {
        List<Team> teams = DBConnection.getTeams();
        for(Team team : teams) {
            System.out.println(team.getName());
        }
    }

    private void createTeam() throws SQLException {
        System.out.println("Enter new team name: ");
        String teamName = scanner.nextLine();
        Team team = new Team();
        team.setName(teamName);
        DBConnection.createNewTeam(team);
    }
    private void deleteTeam() throws SQLException {
        System.out.println("Enter team name to delete: ");
        String teamName = scanner.nextLine();
        Team team = new Team();
        team.setName(teamName);
        DBConnection.deleteTeam(team);
    }
    private void updateTeam() throws SQLException {
        System.out.println("Enter current team name to update: ");
        String currentName = scanner.nextLine();
        System.out.println("Enter new team name: ");
        String newName = scanner.nextLine();
        DBConnection.updateTeam(currentName, newName);
    }

}
