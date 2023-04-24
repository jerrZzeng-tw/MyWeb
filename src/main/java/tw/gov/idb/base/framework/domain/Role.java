package tw.gov.idb.base.framework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -3433122925602245695L;

    private String roleId;// 角色ID
    private String roleName;// 角色名稱
    private String roleDesc;// 角色說明
}
