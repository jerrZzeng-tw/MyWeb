package tw.gov.idb.base.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TestEnum {
    TYPE1("1", "商品種類A"),
    TYPE2("2", "商品種類B"),
    TYPE3("3", "商品種類C"),
    TYPE4("4", "商品種類D"),
    TYPE5("5", "商品種類E");

    private final String code;
    private final String desc;

    TestEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
    
    public static List<TestEnum> getList() {
        return Stream.of(TestEnum.values()).collect(Collectors.toList());
    }
    
    public String getCodeName() {
        return this.code + "-" + this.desc;
    }
}
