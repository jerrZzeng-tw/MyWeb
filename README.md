# Web管理系統範例

## 系統使用framework列表

| 名稱                | 說明               |
|:------------------|:-----------------|
| JAVA              | 1.8 以上           |
| Spring Framework  | 6版               |
| Spring Boot       | Spring 快速開發元件    |
| Spring MVC        | Spring MVC       |
| Spring Validation | Spring 驗證        |
| Mybatis           | 資料庫ORM套件         |
| Lombok            | 自動生成代碼套件         |
| Jackson           | json套件           |
| iText             | EXCEL報表          |
| POI               | PDF報表            |
| Caffeine cache    | 快取元件             |
| Quartz            | 排程元件             |
| JSP/JSTL          | 前端頁面             |
| Jquery            | javascript套件     |
| DataTables        | 頁面TABLE套件        |
| Maven             | PROJECT LIB 管理套件 |
| Git               | 原始碼版控            |

## 系統特性

- 完整權限管控,使用者只能存取有授權的功能跟頁面
- 全系統統一錯誤頁面跟無權限存取頁面,以防系統資訊外洩
- 使用最新版**Springboots**支援**JAR**檔或**WAR**檔運行
- 前端整合JSP,Jquery,DataTables
- 後端整合Spring MVC,Mybatis,hibernate validator,Caffeine cache,Quartz
- 使用 **@APLOG** 記錄使用者每次輸入資訊
- 使用 **@BACKUPDATA** 備份每次使用者修改或刪除的資料
- 內建Quartz排程監控功能
- 頁面有防止使用者**Double Submit**功能


## 說明文件

本系統說明文件放在 **doc** 資料夾下.

| 名稱            | 說明               |
|:--------------|:-----------------|
| 系統開發          | 系統開發說明           |
| APLOG & 資料備份  | 系統自動記錄APLOG跟資料備份 |
| 系統權限          | 系統權限設定           |
| 排程開發          | 系統排程開發說明         |

## DB 
使用 **doc/SQL** 資料夾下建立DB所需TABLE.

## web登入
登入帳號:sys <br>
登入密碼:sys <br>