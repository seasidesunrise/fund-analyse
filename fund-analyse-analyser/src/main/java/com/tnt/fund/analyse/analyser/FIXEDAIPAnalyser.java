package com.tnt.fund.analyse.analyser;

import com.tnt.fund.analyse.service.entity.FundValue;
import com.tnt.fund.analyse.service.service.FundValueService;
import com.tnt.fund.analyse.util.BeanManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定投收益计算
 */
public class FIXEDAIPAnalyser {
    protected final Log log = LogFactory.getLog(getClass().getName());

    private FundValueService fundValueService;

    public static void main(String[] args) {
        FIXEDAIPAnalyser aipAnalyser = new FIXEDAIPAnalyser();
        aipAnalyser.fundValueService = (FundValueService) BeanManager.getBean("fundValueService");

        String fcode = "000961";
        aipAnalyser.clacFundAvgDwjz(fcode);
//        aipAnalyser.importFundValue(fcode);
    }

    private void importFundValue(String fundcode) {
        double fixedMoney = 100; //  每个月定投金额
        double buyRatio = 0.05 / 100;
        double sellRatio = 0.1 / 100;
        Double xishu = (1 - buyRatio) * (1 - sellRatio) * fixedMoney;

        // 计算从第一期开始定投
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundCode", fundcode);
        List<FundValue> fundValueList = this.fundValueService.findListByMap(paramMap);
        List<FundValue> fundValues = new ArrayList<FundValue>();
        List<Double> dwjzList = new ArrayList<Double>();
        if (CollectionUtils.isNotEmpty(fundValueList)) {
            for (int i = fundValueList.size() - 1; i >= 0; i--) {
                FundValue fundValue = fundValueList.get(i);
                String dwjz = fundValue.getDwjz();
                try {
                    Double dw = Double.parseDouble(dwjz);
                    dwjzList.add(dw);

                    fundValues.add(fundValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        int testBegin = 246; // 191: 01-25
        for (int date = testBegin; date <= testBegin + 4; date++) {
            int begin = date, end = 437;
            if (end > dwjzList.size() - 1) {
                end = dwjzList.size() - 1;
            }

            double result = 0;
            double dwAnjian1 = dwjzList.get(end);
            if (dwjzList != null && end >= 2) {
                for (int i = begin; i <= end; i++) {
                    double dw = dwjzList.get(i);
                    result += 1.0 / dw;
                }
            }
            result = result * dwAnjian1 * xishu;
            double oriMoney = (end - begin + 1) * fixedMoney;
            double profit = result - oriMoney;
            double ratio = profit / oriMoney;

            System.out.println("begin:" + begin + ", oriMoney: " + oriMoney + ", totalMoney: " + result + ", profix:" + profit + ", ratio:" + ratio);

        }

        long count = 0;
    }

    private void clacFundAvgDwjz(String fundcode) {
        // 计算从第一期开始定投
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundCode", fundcode);
        List<FundValue> fundValueList = this.fundValueService.findListByMap(paramMap);
        List<FundValue> fundValues = new ArrayList<FundValue>();
        List<Double> dwjzList = new ArrayList<Double>();

        if (CollectionUtils.isNotEmpty(fundValueList)) {
            for (int i = fundValueList.size() - 1; i >= 0; i--) {
                FundValue fundValue = fundValueList.get(i);
                String dwjz = fundValue.getDwjz();
                try {
                    Double dw = Double.parseDouble(dwjz);
                    dwjzList.add(dw);

                    fundValues.add(fundValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        int testBegin = 286; // 246: 01-25
        double w1 = 0, w2 = 0, w3 = 0, w4 = 0, w5 = 0;
        int w1c = 0, w2c = 0, w3c = 0, w4c = 0, w5c = 0;
        for (int day = testBegin; day <= dwjzList.size() - 1; ) {
            w1 += dwjzList.get(day);
            day += 5;
            w1c++;
        }
        w1 = w1 / w1c;

        for (int day = testBegin + 1; day <= dwjzList.size() - 1; ) {
            w2 += dwjzList.get(day);
            day += 5;
            w2c++;
        }
        w2 = w2 / w2c;

        for (int day = testBegin + 2; day <= dwjzList.size() - 1; ) {
            w3 += dwjzList.get(day);
            day += 5;
            w3c++;
        }
        w3 /= w3c;

        for (int day = testBegin + 3; day <= dwjzList.size() - 1; ) {
            w4 += dwjzList.get(day);
            day += 5;
            w4c++;
        }
        w4 /= w4c;

        for (int day = testBegin + 4; day <= dwjzList.size() - 1; ) {
            w5 += dwjzList.get(day);
            day += 5;
            w5c++;
        }
        w5 /= w5c;

        System.out.println("w1: " + w1 + ", w1c: " + w1c);
        System.out.println("w2: " + w2 + ", w2c: " + w2c);
        System.out.println("w3: " + w3 + ", w3c: " + w3c);
        System.out.println("w4: " + w4 + ", w4c: " + w4c);
        System.out.println("w5: " + w5 + ", w5c: " + w5c);
    }

}
