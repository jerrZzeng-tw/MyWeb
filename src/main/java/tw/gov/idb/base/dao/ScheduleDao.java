package tw.gov.idb.base.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tw.gov.idb.base.domain.Schedule;

@Mapper
public interface ScheduleDao {

    /**
     * 新增排程監控資料
     *
     * @param schedule JOB資料
     * @return 筆數
     */
    int scheduleJob(Schedule schedule);

    /**
     * 更新排程狀態
     *
     * @param schedule JOB資料
     * @return 筆數
     */
    int updateJob(Schedule schedule);
    
    List<Schedule> selectSchedules(@Param("jobName") String jobName, @Param("addId") String addId, @Param("status") String status);
    
    Optional<Schedule> seletOneSchedule(@Param("jobId") String jobId);

}
