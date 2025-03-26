package org.example.bot.Notes;

import org.example.bot.Logic.Config;

import java.util.List;

public class NoteManager {
    private final NoteDAO noteDAO;

    public NoteManager(Config config) {
        this.noteDAO = new NoteDAO(config);
    }

    public List<Note> getNotes() {
        return noteDAO.getNotes();
    }

    public void addNote(String text) {
        noteDAO.addNote(text);
    }

    public void editNote(int id, String newText) {
        noteDAO.editNote(id, newText);
    }

    public void deleteNote(int id) {
        noteDAO.deleteNote(id);
    }

    public boolean existNote(int id) {
        return noteDAO.existNote(id);
    }
}
