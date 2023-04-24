package tw.gov.idb.base.commonAjax.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.gov.idb.base.commonAjax.service.CommonAjaxService;
import tw.gov.idb.base.domain.Cityarea;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.framework.domain.UserBean;
import tw.gov.idb.base.job.service.JobService;

import java.util.List;
import java.util.Map;

/**
 * 系統通用AJAX,例如:縣市下拉選單
 */
@Slf4j
@Controller
public class CommonAjaxController extends BaseController {

    @Autowired
    private CommonAjaxService commonAjaxService;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserBean userData; // 登入系統的使用者資訊


    //取得縣市與鄉鎮市
    @ApLog
    @ResponseBody
    @PostMapping("/CommonAjax_cityArea.action")
    public Map<String, List<Cityarea>> getCityAreaData() {
        return commonAjaxService.selectCityAreaData();
    }

    @Override
    public BaseCase getCaseData() {
        return new BaseCase();
    }
}
