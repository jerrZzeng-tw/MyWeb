# 系統 Log & 資料異動備份

## 系統 Log
本系統可以自動記錄使用者的操作紀錄.開發者可以視情況需求設定是否需要紀錄LOG以及記錄使用者輸入的資訊.<br>
在Controller中使用 **@ApLog** 標註要LOG的方法即可.<br>
### BD 紀錄資訊
DB table:aplog<br>
DB 欄位:使用者,web頁面名稱,執行功能名稱,執行時間,使用者輸入資訊(optional)<br>
![mvc](img/APLOG.png "markdown")

### 程式範例 - 紀錄基本log
```java
public class Base0102Controller extends BaseController {
    // 此功能需要紀錄ApLog
    @ApLog
    @RequestMapping("/base0102_enter.action")
    public String enter(@ModelAttribute(CASE_KEY) Base0102Case caseData) {
        resetCaseData(caseData);
        return QUERY_PAGE;
    }
}
```

### 程式範例 - 紀錄基本log + 使用者輸入資訊
```java
public class Base0102Controller extends BaseController {
    // 此功能需要紀錄ApLog且需把Model中addAndUpdCase屬性資料記錄下來
    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0102_add.action")
    public String add(@Validated(Base0102Case.addAndUpdValid.class) @ModelAttribute(CASE_KEY) Base0102Case caseData, BindingResult result) {
        try {
            // 略...
            return QUERY_PAGE;
        } catch (Exception e) {
            // 略... 
        }
        return ADD_UPD_PAGE;
    }
}
```
>  &lt; 開發注意事項 &gt;<br>
> **@ApLog** 只能用在 ***Controller*** 中<br>
> log存放在DB中**aplog** table<br>
> fields可輸入多個屬性<br>
> 

## 資料異動備份
DB中重要table資料的異動需要先備份起來才能更新.<br>
需要再Dao寫好備份的方法.系統會執行 **@BackupData** 指定的備份方法<br>
並且把目前所有參數都傳給備份方法<br>
建議insert,update,delete都使用Domain物件當參數.<br>

```java
public interface CompanyDao {
    // 執行此SQL時需備份資料,預設方法名稱為backupData
    @BackupData
    void updateData(Company company);
    // 執行備份的SQL,需要與被@BackupData標註方法有相同的輸入參數
    void backupData(Company company);

    // 執行此SQL時需備份資料,並且使用指定XXX方法備份
    @BackupData(value = "XXX")
    void deleteData(String id);
    // 輸入參數與deleteData相同
    void XXX(String id);
}
```
>  &lt; 開發注意事項 &gt;<br>
> **@BackupData**預設執行同一個**Dao**下的**backupData**方法<br>
> 若有需要不同的備份方法可以用**value**參數指定<br>
> 建議insert,update,delete都使用Domain物件當參數.<br>
> 備份table統一命名為:原table名稱_backup.
