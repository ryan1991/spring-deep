package com.kinbo.boot2deep;

import com.kinbo.boot2deep.antlr4.hello.HelloLexer;
import com.kinbo.boot2deep.antlr4.hello.HelloParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Boot2DeepApplicationTests {

    @Test
    public void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8080/echo/hello", String.class);
        System.out.println(result);



    }

    @Test
    public void testAntlr(){
        HelloLexer lexer = new HelloLexer(CharStreams.fromString("hello world"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.s();
        System.out.println(tree.toStringTree(parser));


    }



}
