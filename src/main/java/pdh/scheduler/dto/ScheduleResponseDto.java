package pdh.scheduler.dto;

import lombok.Getter;
import pdh.scheduler.entity.Schedule;

import java.time.format.DateTimeFormatter;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String personname;
    private String todo;
    private String createtime;
    private String updatetime;


    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.personname = schedule.getPersonname();
        this.todo = schedule.getTodo();
        this.createtime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(schedule.getCreatetime());
        this.updatetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(schedule.getUpdatetime());
    }

    public ScheduleResponseDto(Long id, String personname, String todo) {
        this.id = id;
        this.personname = personname;
        this.todo = todo;
    }
}
