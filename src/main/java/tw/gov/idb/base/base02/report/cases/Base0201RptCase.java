package tw.gov.idb.base.base02.report.cases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gov.idb.base.framework.cases.BaseCase;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Base0201RptCase extends BaseCase implements Serializable {

    private static final long serialVersionUID = 8313789224442392322L;

    private List<rptCase> rptCases;

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class rptCase {
        private String id;//
        private String companyName;// 公司名稱
        private String owner;// 負責人姓名
        private String ownerIdn;// 負責人IDN
        private String email;// 公司EMAIL
        private String website;// 公司網址
        private String uniformNumbers;// 統一編號
        private String persons;// 員工人數
        private String capital;// 資本額
        private String city;// 縣市
        private String area;// 鄉鎮市
        private String addr;// 地址
        private String issueDate;// 公司成立日期
        private String updateTime;// 資料異動時間
    }
}
