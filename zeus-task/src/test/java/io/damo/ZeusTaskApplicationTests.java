package io.damo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

public class ZeusTaskApplicationTests {

	@Test
	public void getUUID(){
		UUID uuid=UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr=str.replace("-", "");
		System.out.println(uuidStr);
	}

}
