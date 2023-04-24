package tw.gov.idb.base.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public enum JobStatus {
    WAITING("1", "等待中"),
    EXECUTING("2", "執行中"),
    FAILURE("3", "執行失敗"),
    SUCCESS("4", "執行成功"),
    CANCELED("5", "已取消");

    private final String code;
    private final String desc;

    JobStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static JobStatus of(String code) {
        switch (code) {
            case "1":
                return WAITING;
            case "2":
                return EXECUTING;
            case "3":
                return FAILURE;
            case "4":
                return SUCCESS;
            case "5":
                return CANCELED;
            default:
                throw new IllegalStateException("Unexpected value: " + code);
        }
    }

    public static String getName(String code) {
        switch (StringUtils.defaultString(code)) {
            case "1":
                return String.format("%s-%s", code, WAITING.getDesc());
            case "2":
                return String.format("%s-%s", code, EXECUTING.getDesc());
            case "3":
                return String.format("%s-%s", code, FAILURE.getDesc());
            case "4":
                return String.format("%s-%s", code, SUCCESS.getDesc());
            case "5":
                return String.format("%s-%s", code, CANCELED.getDesc());
            default:
                return code;
        }
    }
    
    public static List<JobStatus> getList() {
        return Stream.of(JobStatus.values()).collect(Collectors.toList());
    }
    
    public String getCodeName() {
        return this.code + "-" + this.desc;
    }
}
