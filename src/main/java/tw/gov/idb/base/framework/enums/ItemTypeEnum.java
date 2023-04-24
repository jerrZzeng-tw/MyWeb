package tw.gov.idb.base.framework.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum ItemTypeEnum {
    FUN("1","功能列"),
    TITLE("0","標題列");
    private final String code;
    private final String info;
}