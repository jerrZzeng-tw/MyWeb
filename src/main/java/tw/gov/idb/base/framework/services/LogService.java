package tw.gov.idb.base.framework.services;

import tw.gov.idb.base.framework.dao.AplogDao;
import tw.gov.idb.base.framework.domain.Aplog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class LogService {

    @Autowired
    private AplogDao aplogDao;

    public void doAplog(Aplog aplog) {
        aplogDao.insertAplog(aplog);
    }
}
