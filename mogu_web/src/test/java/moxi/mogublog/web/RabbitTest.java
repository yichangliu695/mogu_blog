package moxi.mogublog.web;

import com.moxi.mogublog.xo.utils.RabbitMqUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiuYiChang
 * @date 2023/8/12 14:30
 */

@SpringBootTest
public class RabbitTest {
    @Autowired
    private RabbitMqUtil rabbitMqUtil;

    @Test
    public void testSendMail(){
        String email="848144092@qq.com";
        rabbitMqUtil.sendSimpleEmail(email,"测试邮件接收者");

    }

}
