package top.lmoon.service;

import top.lmoon.dao.JdbcTodoListDAO;
import top.lmoon.dao.TodoListDAO;
import top.lmoon.vo.TodoEntry;

import java.util.List;

/**
 *
 */
public class TodoListService {

    private TodoListDAO dao = new JdbcTodoListDAO();

    public void addEntry(TodoEntry entry) {
        dao.save(entry);
    }

    public List<TodoEntry> getAllEntries() {
        return dao.list();
    }
}
