package client;

import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: daixiongkun
 * @time: 2019-08-27 14:06
 */
public class Example_4 {

    public static void main(String[] args) {
        JDBCUtils utils = new JDBCUtils();
        List<Object> list = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE score > ?";
        List<Map<String, Object>> result = utils.findModeResult(sql, 60);
        for (Map<String, Object> s:result
             ) {
                list.add(s);
        }
        list.forEach((k)->{
            System.out.println(k);
        });

        String sql1 = "update  student set score=100 where name like ? and sex=?";
        boolean flag = true;
        flag = utils.updateByPreparedStatement(sql1, "__", "男");
        if (flag){
            System.out.println("执行成功");
        }
    }
}
