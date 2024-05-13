package com.HRManager.g01.entities;
import com.HRManager.g01.enums.TaskPriority;
import com.HRManager.g01.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Getter

public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    private String description;
    private String Appreciation;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dueDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date completedAt;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date approuvedDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date assignedDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private TaskPriority Priority;

    //Each person has many tasks
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Person> taskCompleters= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idManager",nullable = false)
    private Manager manager;

   @OneToMany(fetch = FetchType.EAGER,mappedBy = "task")
   private List<Bonus> bonuses;
}
