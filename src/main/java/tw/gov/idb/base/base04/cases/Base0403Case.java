package tw.gov.idb.base.base04.cases;

import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.validation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Base0403Case extends BaseCase implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -39912191846008485L;

    @NotEmpty(label = "角色ID")
    private String roleId;// 角色ID
    @NotEmpty(label = "角色名稱")
    private String roleName;// 角色名稱
    private String roleDesc;// 角色說明

    private Integer listIndex;
    private String status;// 新增add 修改upd

    private List<Base0403Case> base0403Cases;

    private List<AuthCase> authCases;
    private Integer authListIndex;

    @Data
    @NoArgsConstructor
    public static class AuthCase {
        private boolean status = false;// 狀態:啟用 停用 tag
        private String itemName;
        private String url;
        private String itemId;//key 去對應role_item
        private String showStatus = "停用";//啟用 停用
    }

}
