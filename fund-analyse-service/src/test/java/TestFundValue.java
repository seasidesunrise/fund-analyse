import com.tnt.fund.analyse.service.dao.FundCodeDAO;
import com.tnt.fund.analyse.service.dao.FundValueDAO;
import com.tnt.fund.analyse.service.entity.FundCode;
import com.tnt.fund.analyse.service.entity.FundValue;
import com.tnt.fund.analyse.service.service.FundCodeService;
import com.tnt.fund.analyse.util.page.Page;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFundValue {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FundValueDAO fundValueDAO = (FundValueDAO) context.getBean("fundValueDAO");

        // testFundValueDAO
//        testFindListByMap(fundValueDAO);

        // testGetPage
        testGetPage(fundValueDAO);
    }


    public static void testFindListByMap(FundValueDAO fundValueDAO) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundCode", "000961");
        List<FundValue> fundValueList = fundValueDAO.findListByMap(paramMap);
        System.out.println(fundValueList);
    }

    public static void testGetPage(FundValueDAO fundValueDAO) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fundCode", "000961");

        Page<FundValue> page = new Page<FundValue>();
        page.setPageNo(2);

        Page<FundValue> fundValueList = fundValueDAO.getPageByMap(page, paramMap);
        System.out.println(fundValueList);
    }

}
