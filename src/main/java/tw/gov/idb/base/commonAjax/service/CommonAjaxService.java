package tw.gov.idb.base.commonAjax.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tw.gov.idb.base.dao.CityareaDao;
import tw.gov.idb.base.domain.Cityarea;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static tw.gov.idb.base.framework.config.CacheConfig.CACHE_NAME;

@Slf4j
@Service
public class CommonAjaxService {
    @Autowired
    private CityareaDao cityareaDao;

    @Cacheable(value = CACHE_NAME, keyGenerator = "cacheKeyGenerator")
    public Map<String, List<Cityarea>> selectCityAreaData() {
        List<Cityarea> cityareas = cityareaDao.selectCityArea();
        Map<String, List<Cityarea>> map = cityareas.stream()
                .collect(Collectors.groupingBy(t -> t.getCity(), LinkedHashMap::new, Collectors.toList()));
        return map;
    }
}
