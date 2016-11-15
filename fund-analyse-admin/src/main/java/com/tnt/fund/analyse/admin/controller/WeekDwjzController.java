package com.tnt.fund.analyse.admin.controller;

import com.tnt.fund.analyse.service.entity.FundCode;
import com.tnt.fund.analyse.service.entity.FundValue;
import com.tnt.fund.analyse.service.service.FundCodeService;
import com.tnt.fund.analyse.service.service.FundValueService;
import com.tnt.fund.analyse.util.DateUtil;
import com.tnt.fund.analyse.util.DateWeekUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.Calendar.*;

@Controller
@RequestMapping("/fund")
public class WeekDwjzController {
    private Logger log = org.slf4j.LoggerFactory.getLogger(WeekDwjzController.class);

    @Resource
    private FundValueService fundValueService;

    @Resource
    private FundCodeService fundCodeService;

    @RequestMapping("/week2")
    @ResponseBody
    public ModelAndView week2(HttpServletRequest request) {
        String fcode = request.getParameter("fcode");
        log.info("fcode: " + fcode);
        if (fcode == null) {
            fcode = "000961";
        }

        FundCode fundCode = fundCodeService.get(fcode);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundCode", fcode);
        List<FundValue> fundValueList = this.fundValueService.findListByMap(paramMap);
        List<FundValue> fundValues = new ArrayList<FundValue>();
        Map<String, FundValue> fundValueMap = new HashMap<String, FundValue>();
        List<Double> dwjzList = new ArrayList<Double>();
        if (CollectionUtils.isNotEmpty(fundValueList)) {
            for (int i = fundValueList.size() - 1; i >= 0; i--) {
                FundValue fundValue = fundValueList.get(i);
                String dwjz = fundValue.getDwjz();
                try {
                    Double dw = Double.parseDouble(dwjz);
                    dwjzList.add(dw);

                    fundValues.add(fundValue);
                    fundValueMap.put(fundValue.getFsrq(), fundValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        List dwjzListWithArray = new ArrayList();
        if (CollectionUtils.isNotEmpty(fundValues)) {
            FundValue fundValueStart = fundValues.get(0);
            FundValue fundValueEnd = fundValues.get(fundValues.size() - 1);

            Calendar calendar = getCalendar(fundValueStart);
            Calendar calendarEnd = getCalendar(fundValueEnd);
            while (calendar.compareTo(calendarEnd) <= 0) {
                int weekOfD = calendar.get(DAY_OF_WEEK);

                Double[] dwzjArray = new Double[5];
                for (int num = weekOfD; num <= SATURDAY; num++) {
                    String dateStr = DateUtil.getDateStr(calendar);
                    FundValue fundValue = fundValueMap.get(dateStr);
                    if (fundValue == null) {
                        calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 60 * 60 * 1000);
                        continue;
                    }
                    String dwjz = fundValue.getDwjz();
                    Double dwjzD = Double.parseDouble(dwjz);

                    int weekOfDay = calendar.get(DAY_OF_WEEK);
                    switch (weekOfDay) {
                        case 2:
                            dwzjArray[0] = dwjzD;
                            break;
                        case 3:
                            dwzjArray[1] = dwjzD;
                            break;
                        case 4:
                            dwzjArray[2] = dwjzD;
                            break;
                        case 5:
                            dwzjArray[3] = dwjzD;
                            break;
                        case 6:
                            dwzjArray[4] = dwjzD;
                            break;
                        default:
                            break;
                    }

                    calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 60 * 60 * 1000);
                }
                dwjzListWithArray.add(dwzjArray);

            }

        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("weekstock");
        mv.addObject("dwjzListWithArray", dwjzListWithArray);
        mv.addObject("maxCC", dwjzListWithArray.size());
        mv.addObject("fundCode", fundCode);

        return mv;
    }

    @RequestMapping("/week")
    @ResponseBody
    public ModelAndView create(HttpServletRequest request) {
        String fcode = request.getParameter("fcode");
        String lastPot = request.getParameter("last");
        log.info("fcode: " + fcode  + ", lastPot: " + lastPot);
        if (fcode == null) {
            fcode = "000961";
        }

        FundCode fundCode = fundCodeService.get(fcode);

        int last = 0;
        if (lastPot != null) {
            last = Integer.parseInt(lastPot);
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundCode", fcode);
        List<FundValue> fundValueList = this.fundValueService.findListByMap(paramMap);
        List<FundValue> fundValues = new ArrayList<FundValue>();
        Map<String, FundValue> fundValueMap = new HashMap<String, FundValue>();
        if (CollectionUtils.isNotEmpty(fundValueList)) {
            for (int i = fundValueList.size() - 1; i >= 0; i--) {
                FundValue fundValue = fundValueList.get(i);
                fundValues.add(fundValue);
                fundValueMap.put(fundValue.getFsrq(), fundValue);
            }
        }

        List dwjzListWithArray = new ArrayList();
        if (CollectionUtils.isNotEmpty(fundValues)) {
            FundValue fundValueStart = fundValues.get(0);
            FundValue fundValueEnd = fundValues.get(fundValues.size() - 1);

            Calendar calendar = getCalendar(fundValueStart);
            Calendar calendarEnd = getCalendar(fundValueEnd);
            while (calendar.compareTo(calendarEnd) <= 0) {
                int weekOfD = calendar.get(DAY_OF_WEEK);

                Double[] dwzjArray = new Double[5];
                for (int num = weekOfD; num <= SATURDAY; num++) {
                    String dateStr = DateUtil.getDateStr(calendar);
                    FundValue fundValue = fundValueMap.get(dateStr);
                    if (fundValue == null) {
                        calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 60 * 60 * 1000);
                        continue;
                    }
                    String dwjz = fundValue.getDwjz();
                    Double dwjzD = Double.parseDouble(dwjz);

                    int weekOfDay = calendar.get(DAY_OF_WEEK);
                    switch (weekOfDay) {
                        case 2:
                            dwzjArray[0] = dwjzD;
                            break;
                        case 3:
                            dwzjArray[1] = dwjzD;
                            break;
                        case 4:
                            dwzjArray[2] = dwjzD;
                            break;
                        case 5:
                            dwzjArray[3] = dwjzD;
                            break;
                        case 6:
                            dwzjArray[4] = dwjzD;
                            break;
                        default:
                            break;
                    }

                    calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 60 * 60 * 1000);
                }
                dwjzListWithArray.add(dwzjArray);
            }

        }

        ModelAndView mv = new ModelAndView();
        if (last != 0) {
            List subList = dwjzListWithArray.subList(dwjzListWithArray.size() - last, dwjzListWithArray.size() - 1);
            mv.addObject("dwjzListWithArray", subList);
            mv.addObject("maxCC", subList.size());
        } else {
            mv.addObject("dwjzListWithArray", dwjzListWithArray);
            mv.addObject("maxCC", dwjzListWithArray.size());
        }
        mv.setViewName("week");
        mv.addObject("title", "titleTest"); // just for test for jrebel
        mv.addObject("fundCode", fundCode);
        return mv;
    }

    private Calendar getCalendar(FundValue fundValue) {
        if (fundValue == null) {
            return null;
        }
        String rq = fundValue.getFsrq();
        Date date = DateWeekUtil.getDate(rq);
        return DateUtil.getCalendar(date);
    }

    private ModelAndView forward2Page(String page) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(page);
        return mv;
    }

}
