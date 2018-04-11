package top.lmoon.dao;

import java.util.List;

import top.lmoon.model.TodoEntry;

/**
 *
 */
public interface TodoListDAO {

    void save(TodoEntry entry);

    List<TodoEntry> list();
}
