package pdh.scheduler.Controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pdh.scheduler.dto.ScheduleRequestDto;
import pdh.scheduler.dto.ScheduleResponseDto;
import pdh.scheduler.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //1단계
    @PostMapping("/scheduler")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);
    }

    //3단계
    @GetMapping("/scheduler")
    public List<ScheduleResponseDto> getSchedules(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getSchedules(requestDto);
    }

    //2단계
    @GetMapping("/scheduler/{scheduleid}")
    public ScheduleResponseDto getOneSchedule(@PathVariable Long scheduleid) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getOneSchedule(scheduleid);
    }

    //4단계
    @PutMapping("/scheduler/{scheduleid}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleid, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.updateSchedule(scheduleid, requestDto);
    }

    //5단계
    @DeleteMapping("/scheduler/{scheduleid}")
    public Long deleteSchedule(@PathVariable Long scheduleid, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.deleteSchedule(scheduleid, requestDto);
    }

}
