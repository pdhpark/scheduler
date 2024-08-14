package pdh.scheduler.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pdh.scheduler.dto.ScheduleRequestDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String passwords;
    private String personname;
    private String todo;
    private LocalDateTime createtime = LocalDateTime.now();
    private LocalDateTime updatetime = LocalDateTime.now();

    public Schedule(ScheduleRequestDto requestDto) {
        this.passwords = requestDto.getPasswords();
        this.personname = requestDto.getPersonname();
        this.todo = requestDto.getTodo();
    }
}
