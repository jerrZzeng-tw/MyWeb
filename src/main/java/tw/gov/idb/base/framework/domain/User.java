package tw.gov.idb.base.framework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 7245620384307255819L;
    private String id;//使用者ID
    private String name;//使用者名稱
    private String pw;//使用者密碼(SHA加密)
    private String mail;//使用者信箱

    private String empNo;//員工編號
    private String memo;//帳號說明
    private String updateTime;//更新時間
}
