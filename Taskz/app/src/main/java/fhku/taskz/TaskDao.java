package fhku.taskz;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    public void insertTask(Task task);

    @Query("SELECT * FROM Task")
    public List<Task> queryAllTasks();

}
