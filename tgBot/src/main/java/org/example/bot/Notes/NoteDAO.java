package org.example.bot.Notes;

import org.example.bot.Logic.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private final String url;
    private final String user;
    private final String password;

    public NoteDAO(Config config) {
        this.url = config.getDbUrl();
        this.user = config.getDbUser();
        this.password = config.getDbPassword();
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS notes (" +
                    "id SERIAL PRIMARY KEY, " +
                    "text TEXT NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        String query = "SELECT id, text FROM notes";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                notes.add(new Note(rs.getInt("id"), rs.getString("text")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public void addNote(String text) {
        String query = "INSERT INTO notes (text) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, text);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editNote(int id, String newText) {
        String query = "UPDATE notes SET text = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newText);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(int id) {
        String query = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existNote(int id) {
        String query = "SELECT id FROM notes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
