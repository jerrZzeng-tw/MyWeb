package tw.gov.idb.base.base04.cases;

import java.io.Serializable;
import java.util.List;

import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.validation.NotEmpty;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Base0401Case extends BaseCase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -952222987191779948L;

	 /**
     * 儲存的時候校驗分組
     */
    
    public interface addCase {
    }
    
    public interface updCase {
    }
    
	private String id;// 使用者ID
	private String name;// 使用者名稱

	private List<Base0401qCase> base0401qCases;
	@Valid
	private AddAndUpdCase addAndUpdCase;
	private Integer listIndex;
	private String status;// 新增add 修改upd

	@Data
	@NoArgsConstructor
	@Builder
	@AllArgsConstructor
	public static class Base0401qCase {
		private String id;// 使用者ID
		private String name;// 使用者名稱
		private String pw;// 使用者密碼(SHA加密)
		private String mail;// 使用者信箱
		private String empNo;// 員工編號
		private String memo;// 帳號說明
		private String updateTime;// 更新時間
	}

	@Data
	@NoArgsConstructor
	@Builder
	@AllArgsConstructor
	public static class AddAndUpdCase {
		@NotEmpty(label = "使用者ID",groups = addCase.class)
		private String id;// 使用者ID
		@NotEmpty(label = "使用者名稱",groups = {addCase.class,updCase.class})
		private String name;// 使用者名稱
		@NotEmpty(label = "使用者密碼",groups = addCase.class)
		private String pw;// 使用者密碼(SHA加密)
		private String mail;// 使用者信箱
		private String empNo;// 員工編號
		private String memo;// 帳號說明
		private String updateTime;// 更新時間
	}
}
