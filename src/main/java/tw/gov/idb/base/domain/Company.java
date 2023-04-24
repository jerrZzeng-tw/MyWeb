package tw.gov.idb.base.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("Company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -7605245821985967839L;
    private String id;//
    private String companyName;// 公司名稱
    private String owner;// 負責人姓名
    private String ownerIdn;// 負責人IDN
    private String email;// 公司EMAIL
    private String website;// 公司網址

    private String uniformNumbers;// 統一編號
    private Integer persons;// 員工人數
    private Integer capital;// 資本額
    private String city;// 縣市
    private String area;// 鄉鎮市

    private String addr;// 地址
    private String issueDate;// 公司成立日期
    private String updateTime;// 資料異動時間

}
