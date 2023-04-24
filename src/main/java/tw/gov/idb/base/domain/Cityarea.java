package tw.gov.idb.base.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("Cityarea")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cityarea implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 5709372817360187503L;
    private String city;// 縣市
    private String cityId;// 縣市代碼
    private String area;// 區鄉鎮
    private String areaId;//
}
