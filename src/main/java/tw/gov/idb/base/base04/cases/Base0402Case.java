package tw.gov.idb.base.base04.cases;


import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.validation.NotEmpty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Base0402Case extends BaseCase implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7089332620015692759L;

    private List<String> options;
    private String itemIdParent;// 父功能項ID

    private List<Base0402qCase> base0402qCases;
    @Valid
    private AddAndUpdCase addAndUpdCase;
    private Integer listIndex;
    private String status;//新增add 修改upd

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Base0402qCase {
        private String itemId;// 功能項ID
        private String itemIdParent;// 父功能項ID
        private String levelNo;// 功能項層數
        private String itemName;// 功能項名稱
        private String url;// 功能項 URL
        private Integer sortOrder;// 功能項 排序
        private String type;// 功能項種類1:功能列 0:標題列
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class AddAndUpdCase {
        @NotEmpty(label = "功能項ID")
        private String itemId;// 功能項ID
        @NotEmpty(label = "父功能項ID")
        private String itemIdParent;// 父功能項ID
        private String levelNo;// 功能項層數
        @NotEmpty(label = "功能項名稱")
        private String itemName;// 功能項名稱
        private String url;// 功能項 URL
        @NotNull(message = "功能項排序不可為空")
        private Integer sortOrder;// 功能項 排序
        @NotEmpty(label = "功能項種類")
        private String type;// 功能項種類1:功能列 0:標題列
    }
}
