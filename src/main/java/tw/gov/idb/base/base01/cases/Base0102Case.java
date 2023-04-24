package tw.gov.idb.base.base01.cases;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gov.idb.base.domain.Cityarea;
import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.validation.EmailAddress;
import tw.gov.idb.base.framework.validation.IdNoFormat;
import tw.gov.idb.base.framework.validation.NotEmpty;
import tw.gov.idb.base.framework.validation.RequiredPositive;
import tw.gov.idb.base.framework.validation.WestDate;
import tw.gov.idb.base.util.DateUtility;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Base0102Case extends BaseCase implements Serializable {
    @Serial
    private static final long serialVersionUID = -952222987191779948L;

    //分組驗證 valid用interface
    public interface queryValid {
    }

    public interface addAndUpdValid {
    }

    private String companyName;// 公司名稱
    private String owner;// 負責人姓名
    @RequiredPositive(label = "資本額", groups = queryValid.class)
    private String capital;// 資本額

    private List<Base0102qCase> base0102qCases;
    @Valid
    private AddAndUpdCase addAndUpdCase;
    private Integer listIndex;
    private String status;// 新增add 修改upd

    private List<Cityarea> selectCitys;//縣市 default
    private List<Cityarea> selectAreas;//鄉鎮 AJAX 選縣市後才撈

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Base0102qCase {
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

        public String getChineseIssueDate() {
            return DateUtility.changeWestDateForCaseGet(issueDate);
        }
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class AddAndUpdCase {
        private String id;// 
        @NotEmpty(label = "公司名稱", groups = addAndUpdValid.class)
        private String companyName;// 公司名稱
        @NotEmpty(label = "負責人姓名", groups = addAndUpdValid.class)
        private String owner;// 負責人姓名
        @NotEmpty(label = "負責人IDN", groups = addAndUpdValid.class)
        @IdNoFormat(label = "負責人IDN", groups = addAndUpdValid.class)
        private String ownerIdn;// 負責人IDN
        @NotEmpty(label = "公司EMAIL", groups = addAndUpdValid.class)
        @EmailAddress(label = "公司EMAIL", groups = addAndUpdValid.class)
        private String email;// 公司EMAIL
        private String website;// 公司網址
        private String uniformNumbers;// 統一編號
        @RequiredPositive(label = "員工人數", groups = addAndUpdValid.class)
        private String persons;// 員工人數
        @RequiredPositive(label = "資本額", groups = addAndUpdValid.class)
        private String capital;// 資本額
        private String city;// 縣市
        private String area;// 鄉鎮市
        private String addr;// 地址
        @NotEmpty(label = "公司成立日期", groups = addAndUpdValid.class)
        @WestDate(label = "公司成立日期", groups = addAndUpdValid.class)
        private String issueDate;// 公司成立日期
        //		@NotEmpty(label = "資料異動時間") 自己upd
        private String updateTime;// 資料異動時間

        //日期部分    
        public void setChineseIssueDate(String issueDate) {
            this.issueDate = DateUtility.changeChineseDateForCaseSet(issueDate);
        }

        public String getChineseIssueDate() {
            return DateUtility.changeWestDateForCaseGet(issueDate);
        }
    }

    // APLOG 紀錄刪除資料
    public Base0102qCase getDeleteData() {
        return base0102qCases.get(listIndex);
    }
}
