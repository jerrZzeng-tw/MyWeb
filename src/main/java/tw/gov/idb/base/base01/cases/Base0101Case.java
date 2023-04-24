package tw.gov.idb.base.base01.cases;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gov.idb.base.enums.TestEnum;
import tw.gov.idb.base.framework.cases.BaseCase;

@Data
@NoArgsConstructor
public class Base0101Case extends BaseCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3518214722027782687L;

	private List<TestEnum> testOptions;
	private String selectVal;
	private List<String> checkboxVal;
	private String radioVal;
	private String inputVal;

	private MultipartFile fileContent;
	private String fileName;
	private boolean haveFile = false;
	
	private Base0101qCase base0101qCase;

	@Data
	@NoArgsConstructor
	@Builder
	@AllArgsConstructor
	public static class Base0101qCase {
		private String selectVal;
		private List<String> checkboxVal;
		private String radioVal;
		private String inputVal;
	}
}
