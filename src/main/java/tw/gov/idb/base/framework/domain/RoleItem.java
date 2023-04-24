package tw.gov.idb.base.framework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("RoleItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleItem implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 2691128287477205621L;

    private Integer id;
    private String roleId;// 角色名稱
    private String itemId;// 系統功能ID

}
