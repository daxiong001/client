package client;

import utils.JDBCUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: daixiongkun
 * @time: 2019-08-26 16:42
 */
public class Example_2 {
    public static void main(String[] args) throws Exception {
        JDBCUtils utils = new JDBCUtils();
        Map<String, Integer> map = new HashMap<>();
        map.put("13265477888", 168);
        map.put("15241698745", 11);
        map.put("13699989898", 20);
        map.put("18986886666", 120);
        String sql = "select * from numinfo where iphonenum=?";
        map.forEach((k, v) -> {
            Map<String, Object> result = utils.findSimpleResult(sql,k);
            result.forEach((s,z) ->{
                if (k.equals(z)){
                    System.out.println("已登录");
                }else {
                    String sql1 = "insert into numinfo(iphonenum,tomoney)values (?,?)";

                    utils.updateByPreparedStatement(sql1, k, v);

                }
            }
        );
        });

    }
}