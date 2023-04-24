package tw.gov.idb.base.base02.cases;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gov.idb.base.framework.cases.BaseCase;

@Data
@NoArgsConstructor
public class Base0201Case extends BaseCase implements Serializable {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 5698428832065224953L;
	private String companyName;// 公司名稱
    private String owner;// 負責人姓名

}
