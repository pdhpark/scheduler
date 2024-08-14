package pdh.scheduler.Controller;

import org.springframework.web.bind.annotation.*;
import pdh.scheduler.dto.ScheduleRequestDto;
import pdh.scheduler.dto.ScheduleResponseDto;
import pdh.scheduler.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //1단계
    @PostMapping("/scheduler")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    //3단계
    @GetMapping("/scheduler")
    public List<ScheduleResponseDto> getSchedules(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.getSchedules(requestDto);
    }

    //2단계
    @GetMapping("/scheduler/{scheduleid}")
    public ScheduleResponseDto getOneSchedule(@PathVariable Long scheduleid) {
        return scheduleService.getOneSchedule(scheduleid);
    }

    //4단계
    @PutMapping("/scheduler/{scheduleid}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleid, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleid, requestDto);
    }

    //5단계
    @DeleteMapping("/scheduler/{scheduleid}")
    public Long deleteSchedule(@PathVariable Long scheduleid, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(scheduleid, requestDto);
    }

}
