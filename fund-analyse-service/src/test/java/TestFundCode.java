import com.tnt.fund.analyse.service.dao.FundCodeDAO;
import com.tnt.fund.analyse.service.entity.FundCode;
import com.tnt.fund.analyse.service.service.FundCodeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class TestFundCode {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        FundCodeService fundCodeService = (FundCodeService) context.getBean("fundCodeService");
        FundCodeDAO fundCodeDao = (FundCodeDAO) context.getBean("fundCodeDAO");

        // testGet
//        testGet(fundCodeDao);

        // testUpdate
//        testUpdate(fundCodeDao, fundCode2);
//        testUpdateMyMap(fundCodeDao);

        // testSave
//        testSave(fundCodeDao);


        // testDelete
//        testDelete(fundCodeDao);
    }

    public static void testDelete(FundCodeDAO fundCodeDao) {
        String fundCode = "-100000";
        int affectedRow = fundCodeDao.delete(fundCode);
    }

    public static void testSave(FundCodeDAO fundCodeDao) {
        FundCode fundCode = new FundCode();
        fundCode.setFundName("fundCodeSaved");
        fundCode.setFundCode("-100000");
        fundCode = fundCodeDao.save(fundCode);
        System.out.println("----------save result, fundCode:" + fundCode);
    }

    public static void testUpdate(FundCodeDAO fundCodeDao, FundCode fundCode2) {
        fundCode2.setFundName("fundCode2");
        int rows = fundCodeDao.update(fundCode2);
        System.out.println("----------update result, affected row:" + rows);
    }

    public static void testUpdateMyMap(FundCodeDAO fundCodeDao) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("fundCode", "000023");
        map.put("fundName", "fundCode2c");
        int rows = fundCodeDao.update(map);

        System.out.println("----------updateByMap result, affected row:" + rows);
    }

    public static void testGet(FundCodeDAO fundCodeDao) {
        FundCode fundCode = fundCodeDao.get("000023");
        FundCode fundCode2 = fundCodeDao.findUniqueBy("fundCode", "000023");

        System.out.println(fundCode);
        System.out.println(fundCode2);
    }

}
