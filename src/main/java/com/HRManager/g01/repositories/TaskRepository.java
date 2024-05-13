package com.HRManager.g01.repositories;

import com.HRManager.g01.entities.Person;
import com.HRManager.g01.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks,Long> {

    @Query(
            value="select * from tasks where id_manager=?1 ",
            nativeQuery = true
    )
    List<Tasks> getTasksByManager(Long id);

    @Query(
            value="SELECT tasks.* FROM tasks JOIN tasks_task_completers ON tasks.id_task = tasks_task_completers.tasks_id_task WHERE tasks_task_completers.task_completers_id_person = ?1",
            nativeQuery = true
    )List<Tasks> getTasksByExecutor(Long id);
}
