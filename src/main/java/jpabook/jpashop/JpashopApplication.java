package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//돈과 관련된 중요한 것이고 데이터가 항상 맞아야되면 외래키를 거는 것에 대해서 고민할 필요가 있다. 
@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
//		Hello hello =new Hello();
//		hello.setData("hello");
//		String data = hello.getData();
//		System.out.println("data = " + data);
		SpringApplication.run(JpashopApplication.class, args);
	}

}
