package tw.gov.idb.base.framework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Aplog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aplog implements Serializable {
    private String userid;
    private String page;
    private String url;
    private String accessTime;
    private String data;

}
