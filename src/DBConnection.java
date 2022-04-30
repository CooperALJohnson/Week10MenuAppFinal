import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/crud";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "password";
    private static Connection connection;

    public static void initialize() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Team> getTeams() throws SQLException {
        String GET_TEAMS_QUERY = "SELECT * FROM teams";
        ResultSet rs = connection.prepareStatement(GET_TEAMS_QUERY).executeQuery();
        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team team = new Team();
            team.setId(rs.getInt("team_id"));
            team.setName(rs.getString("team_name"));
            teams.add(team);
        }
        return teams;
    }

    public static void createNewTeam(Team team) throws SQLException {
        String CREATE_NEW_TEAM_QUERY = "INSERT INTO teams(team_name) VALUES('?')";
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TEAM_QUERY.replace("?", team.getName()));
        ps.executeUpdate();
    }

    public static void deleteTeam(Team team) throws SQLException {
        String DELETE_TEAM_QUERY = "DELETE FROM teams WHERE team_name = '?'";
        PreparedStatement ps = connection.prepareStatement(DELETE_TEAM_QUERY.replace("?", team.getName()));
        ps.executeUpdate();
    }

    public static void updateTeam(String currentName, String newName) throws SQLException {
        String UPDATE_TEAM_QUERY = "UPDATE teams SET team_name = '!' WHERE team_name = '?'";
        PreparedStatement ps = connection.prepareStatement(UPDATE_TEAM_QUERY.replace("?", currentName).replace("!", newName));
        ps.executeUpdate();
    }
}
