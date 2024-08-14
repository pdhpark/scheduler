package pdh.scheduler.service;

import org.springframework.stereotype.Service;
import pdh.scheduler.dto.ScheduleRequestDto;
import pdh.scheduler.dto.ScheduleResponseDto;
import pdh.scheduler.entity.Schedule;
import pdh.scheduler.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //1단계
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //RequestDto => Entity
        Schedule schedule = new Schedule(requestDto);

        //DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        //Entity => ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    //3단계
    public List<ScheduleResponseDto> getSchedules(ScheduleRequestDto requestDto) {
        return scheduleRepository.findAll(requestDto);
    }

    //2단계
    public ScheduleResponseDto getOneSchedule(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            schedule.setId(id);
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
            return scheduleResponseDto;
        } else {
            throw new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.");
        }
    }

    //4단계
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            scheduleRepository.updateSchedule(id, requestDto);
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(scheduleRepository.findById(id));
            scheduleResponseDto.setId(id);
            return scheduleResponseDto;
        } else {
            throw new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.");
        }
    }

    //5단계
    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            scheduleRepository.delete(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.");
        }
    }

}
