package com.tnt.fund.analyse.crawler;

import com.tnt.fund.analyse.crawler.util.HttpUtil;
import com.tnt.fund.analyse.service.entity.FundCode;
import com.tnt.fund.analyse.service.entity.FundValue;
import com.tnt.fund.analyse.service.entity.enums.FundValueEnums;
import com.tnt.fund.analyse.service.service.FundCodeService;
import com.tnt.fund.analyse.service.service.FundValueService;
import com.tnt.fund.analyse.util.BeanManager;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;

public class HistoryDataCrawler {

    protected final Log log = LogFactory.getLog(getClass().getName());

    private FundValueService fundValueService;

    public static void main(String[] args) throws Exception {
        HistoryDataCrawler historyDataCrawler = new HistoryDataCrawler();
        historyDataCrawler.fundValueService = (FundValueService) BeanManager.getBean("fundValueService");

//        List<Fundcode> fundcodeList = historyDataCrawler.getAllFundCodes(); // String fundcode = "001694";
//        if (CollectionUtils.isNotEmpty(fundcodeList)) {
//            for (Fundcode fundcode : fundcodeList) {
//                if (fundcode != null) {
//                    String fcode = fundcode.getFundcode();
//                    historyDataCrawler.importFundValue(fcode);
//
//                } else {
//                    historyDataCrawler.log.warn("fundcode 实体为空，跳过。。");
//                    continue;
//                }
//            }
//        }


        String fcode = "340007"; //"000961";
        historyDataCrawler.importFundValue(fcode);
    }

    private List<FundCode> getAllFundCodes() {
        FundCodeService fundcodeManager = (FundCodeService) BeanManager.getBean("fundcodeManager");
        List<FundCode> fundcodeList = fundcodeManager.findListByMap(new HashMap());
        return fundcodeList;
    }

    private void importFundValue(String fundcode) {
        long count = 0;
        String data1 = "";
        String hisdatadata1 = "";
        String hisdata = "";
        try {
            String historyUrl = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=" + fundcode + "&page=1&per=10&sdate=&edate=&rt=0.26855431487057246";
            String data = HttpUtil.history(historyUrl);
            data1 = "{" + data.substring(14, data.length());
            JSONObject jo = JSONObject.fromObject(data1.substring(0, data1.length() - 1));
            int pageSize = Integer.valueOf(jo.getString("records"));
            count += pageSize;
            //第二次获取
            String hisUrl = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=" + fundcode + "&page=1&per=" + pageSize + "&sdate=&edate=&rt=0.26855431487057246";
            hisdata = HttpUtil.history(hisUrl);
            hisdatadata1 = "{" + hisdata.substring(14, hisdata.length());
            JSONObject hisdatajo = JSONObject.fromObject(hisdatadata1.substring(0, hisdatadata1.length() - 1));
            Document doc = Jsoup.parseBodyFragment(hisdatajo.get("content").toString());
            Elements links = doc.getElementsByTag("tr");
            for (int i = 1; i < links.size(); i++) {
                Document doctr = Jsoup.parseBodyFragment("<table>" + links.get(i).toString() + "</table>");
                Elements linkstr = doctr.getElementsByTag("td");
                if (linkstr.size() > 6) {
                    String FSRQ = linkstr.get(0).text();
                    String DWJZ = linkstr.get(1).text();
                    String LJJZ = linkstr.get(2).text();
                    String RZZL = linkstr.get(3).text();
                    String SGZT = linkstr.get(4).text();
                    String SHZT = linkstr.get(5).text();
                    String FHSP = linkstr.get(6).text();

                    if (SGZT != null) {
                        if (SGZT.equals(FundValueEnums.ShengouStatus_Fengbi.getName())) {
                            SGZT = FundValueEnums.ShengouStatus_Fengbi.getStatus();
                        } else if (SGZT.equals(FundValueEnums.ShengouStatus_Normal.getName())) {
                            SGZT = FundValueEnums.ShengouStatus_Normal.getStatus();
                        } else if (SGZT.equals(FundValueEnums.ShengouStatus_Stop.getName())) {
                            SGZT = FundValueEnums.ShengouStatus_Stop.getStatus();
                        }
                    }
                    if (SHZT != null) {
                        if (SHZT.equals(FundValueEnums.ShuhuiStatus_Fengbi.getName())) {
                            SHZT = FundValueEnums.ShuhuiStatus_Fengbi.getStatus();
                        } else if (SHZT.equals(FundValueEnums.ShuhuiStatus_Normal.getName())) {
                            SHZT = FundValueEnums.ShuhuiStatus_Normal.getStatus();
                        } else if (SHZT.equals(FundValueEnums.ShuhuiStatus_Stop.getName())) {
                            SHZT = FundValueEnums.ShuhuiStatus_Stop.getStatus();
                        }
                    }
                    FundValue fundValue = new FundValue();
                    fundValue.setFsrq(FSRQ);
                    fundValue.setDwjz(DWJZ);
                    fundValue.setLjjz(LJJZ);
                    fundValue.setRzzl(RZZL);
                    fundValue.setSgzt(SGZT);
                    fundValue.setShzt(SHZT);
                    fundValue.setFhsp(FHSP);
                    fundValue.setFundCode(fundcode);
                    this.fundValueService.save(fundValue);
                } else {
                    FundValue fundValue = new FundValue();
                    fundValue.setFsrq("");
                    fundValue.setDwjz("");
                    fundValue.setLjjz("");
                    fundValue.setRzzl("");
                    fundValue.setSgzt("");
                    fundValue.setShzt("");
                    fundValue.setFhsp("");
                    fundValue.setFundCode(fundcode);
                    this.fundValueService.save(fundValue);
                }
            }

            this.log.info("fundcode " + fundcode + "导入完毕，共" + count + "条净值记录");
        } catch (Exception e) {
            System.out.println(data1);
            System.out.println(hisdata);
            System.out.println(hisdatadata1);
            e.printStackTrace();
        }
    }

}
