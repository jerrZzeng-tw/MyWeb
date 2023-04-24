package tw.gov.idb.base.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -13269049567697414L;
    private String year;
    private String dayOfWeek;
    private String month;
    private String dayOfMonth;
    private String hours;
    private String minutes;
    private String seconds;
    private Map<String, String> jobParamMap;
    private String jobName;// job名稱
    private String description;// job說明
    private String addId;// 排程人員ID
}
