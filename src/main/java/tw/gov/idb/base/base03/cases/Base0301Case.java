package tw.gov.idb.base.base03.cases;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gov.idb.base.enums.JobStatus;
import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.validation.NotEmpty;

@Data
@NoArgsConstructor
public class Base0301Case extends BaseCase implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -3775401822016054977L;

	private String jobName;//排程名稱
	private String addId;//新增人員
	private String status;//執行狀態, 1-等待中、2-執行中、3-執行失敗、4-執行成功、5-已取消
	private List<JobStatus> statusOptions;
	
	private List<Base0301qCase> base0301qCases;
	@Valid
	private AddAndQueryCase addAndQueryCase;
	private Integer listIndex;
	private String condition;// 新增add 查詢query

	@Data
	@NoArgsConstructor
	@Builder
	@AllArgsConstructor
	public static class AddAndQueryCase {
		private String jobClassName;// 排程類別
		@NotEmpty(label = "排程名稱")
		private String jobName;// 排程名稱
		private String description;// 排程描述
		@NotEmpty(label = "排定時間")
		private String scheduleTime;// 排定時間 //格式-YYYYMMDDHHmmSS 
		private String param;// 排程參數
		private String jobId;
		private String exeStart;//job啟動時間
		private String exeEnd;//job結束時間
		private String status;//執行狀態, 1-等待中、2-執行中、3-執行失敗、4-執行成功、5-已取消
		private String result;//執行結果
		private String addId;//排程人員
		private String addTime;//新增排程時間
		private String cancelId;//取消人員
		private String cancelTime;//取消排程時間
		
		public String getStatusName() {
            return JobStatus.getName(this.status);
        }
	}
	
	@Data
	@NoArgsConstructor
	@Builder
	@AllArgsConstructor
	public static class Base0301qCase {
		private String jobId;
		private String jobName;// job名稱
		private String scheduleTime;// 排定時間
		private String exeStart;//job啟動時間
		private String exeEnd;//job結束時間
		private String status;//執行狀態, 1-等待中、2-執行中、3-執行失敗、4-執行成功、5-已取消
		private String result;//執行結果
		
		public String getStatusName() {
            return JobStatus.getName(this.status);
        }
	}
}
