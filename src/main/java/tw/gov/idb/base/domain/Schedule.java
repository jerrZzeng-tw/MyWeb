package tw.gov.idb.base.domain;

import java.io.Serial;
import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("Schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule implements Serializable {
    @Serial
    private static final long serialVersionUID = -7605245821985967839L;
    private String jobId;
    private String jobName;//job名稱
    private String description;//job說明
    private String param;//job參數
    private String scheduleTime;//排定時間
    private String exeStart;//job啟動時間
    private String exeEnd;//job結束時間
    private String status;//執行狀態, 1-等待中、2-執行中、3-執行失敗、4-執行成功、5-已取消
    private String result;//執行結果
    private String addId;//排程人員
    private String addTime;//新增排程時間
    private String cancelId;//取消人員
    private String cancelTime;//取消排程時間

}
