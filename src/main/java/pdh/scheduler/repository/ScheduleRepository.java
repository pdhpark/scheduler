package pdh.scheduler.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pdh.scheduler.dto.ScheduleRequestDto;
import pdh.scheduler.dto.ScheduleResponseDto;
import pdh.scheduler.entity.Schedule;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //1단계
    public Schedule save(Schedule schedule) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (passwords, personname, todo, createtime, updatetime) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getPasswords());
                    preparedStatement.setString(2, schedule.getPersonname());
                    preparedStatement.setString(3, schedule.getTodo());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(schedule.getCreatetime()));
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getUpdatetime()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    //3단계
    public List<ScheduleResponseDto> findAll(ScheduleRequestDto requestDto) {
        // DB 조회
        String sql = "SELECT * FROM schedule where DATE_FORMAT(updatetime,'%Y-%m-%d')  = ? or personname = ? order by updatetime desc";

        return jdbcTemplate.query(sql, new RowMapper() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 schedule 데이터들을 ScheduleResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong(1);
                String personname = rs.getString(3);
                String todo = rs.getString(4);
                LocalDateTime createtime = rs.getTimestamp(5).toLocalDateTime();
                LocalDateTime updatetime = rs.getTimestamp(6).toLocalDateTime();

                return new ScheduleResponseDto(id, personname, todo, createtime, updatetime);
            }
        }, requestDto.getUpdatetime(), requestDto.getPersonname());
    }

    //4단계
    public void updateSchedule(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET personname = ?, todo = ?, updatetime = ? WHERE id = ? and passwords = ?";
        jdbcTemplate.update(sql, requestDto.getPersonname(), requestDto.getTodo(), LocalDateTime.now(), id, requestDto.getPasswords());
    }

    //5단계
    public void delete(Long id, ScheduleRequestDto requestDto) {
        String sql = "DELETE FROM schedule WHERE id = ? and passwords = ?";
        jdbcTemplate.update(sql, id, requestDto.getPasswords());
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setPasswords(resultSet.getString(2));
                schedule.setPersonname(resultSet.getString(3));
                schedule.setTodo(resultSet.getString(4));
                schedule.setCreatetime(resultSet.getTimestamp(5).toLocalDateTime());
                schedule.setUpdatetime(resultSet.getTimestamp(6).toLocalDateTime());

                return schedule;
            } else {
                return null;
            }
        }, id);
    }


}