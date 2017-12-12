package com.catvgd.springbootdemo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.catvgd.springbootdemo.biz.calc.controller.UserController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getUserList() throws Exception {
        long s = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/user/list").accept(MediaType.APPLICATION_JSON)).andReturn();
            int statusCode = result.getResponse().getStatus();
            Assert.assertEquals(statusCode, 200);
            String body = result.getResponse().getContentAsString();
            // System.out.println("--" + i + "--body:" + body);
        }
        long e = System.currentTimeMillis();
        long c = (e - s);
        logger.info("cost : " + c + "ms");
    }

    // @Test
    // public void getGitHubEntityByUsername() throws Exception {
    // MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/github/get/users/Datartisan").accept(MediaType.APPLICATION_JSON)).andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:" + body);
    // }
    //
    // /**
    // * attention:
    // * Details：测试查询
    // *
    // * @author chhliu
    // * 创建时间：2016-12-7 下午3:41:49
    // * @throws Exception
    // * void
    // */
    // @Test
    // public void getGitHubEntityById() throws Exception {
    // MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/github/get/user/721").accept(MediaType.APPLICATION_JSON)).andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:" + body);
    // }

    // /**
    // * attention:
    // * Details：测试分页查询
    // *
    // * @author chhliu
    // * 创建时间：2016-12-7 下午3:42:02
    // * @throws Exception
    // * void
    // */
    // @Test
    // public void getGitHubEntityPage() throws Exception {
    // MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/github/get/users/page").param("pageOffset", "0").param("pageSize", "10")
    // .param("orderColumn", "id").accept(MediaType.APPLICATION_JSON)).andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:" + body);
    // }
    //
    // /**
    // * attention:
    // * Details：测试插入，方式一，此方式需要controller中方法参数前没有@RequestBody
    // *
    // * @author chhliu
    // * 创建时间：2016-12-7 下午3:42:19
    // * @throws Exception
    // * void
    // */
    // @Test
    // public void postGithubEntity() throws Exception {
    // RequestBuilder request = MockMvcRequestBuilders.post("/github/post").param("codeSnippet", "package")
    // .param("codeUrl", "http://localhost:8080/code").param("projectUrl", "http://localhost:8080").param("userName", "chhliu")
    // .param("sensitiveMessage", "love").param("spriderSource", "CSDN").contentType(MediaType.APPLICATION_JSON_UTF8);
    // MvcResult result = mvc.perform(request).andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:" + body);
    // }

    // /**
    // * attention:
    // * Details：测试插入，方式二，此方式需要controller中方法参数前加@RequestBody
    // * @author chhliu
    // * 创建时间：2016-12-7 下午3:42:19
    // * @throws Exception
    // * void
    // */
    // @Test
    // public void postGithubEntity1() throws Exception{
    // GitHubEntity entity = new GitHubEntity();
    // entity.setUserName("xyhg");
    // entity.setSpriderSource("ITeye");
    // entity.setCodeUrl("http://localhost:8080");
    // ObjectMapper mapper = new ObjectMapper();
    // MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/github/post")
    // .contentType(MediaType.APPLICATION_JSON_UTF8)
    // .content(mapper.writeValueAsString(entity)))
    // .andExpect(MockMvcResultMatchers.status().isOk())
    // .andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:"+body);
    // }

    // /**
    // * attention:
    // * Details：测试更新和插入类似
    // * @author chhliu
    // * 创建时间：2016-12-7 下午3:42:32
    // * @throws Exception
    // * void
    // */
    // @Test
    // public void putGithubEntity() throws Exception{
    // RequestBuilder request = MockMvcRequestBuilders.put("/github/put")
    // .param("id", "719")
    // .param("codeSnippet", "import java.lang.exception")
    // .param("codeUrl", "http://localhost:8080/code")
    // .param("projectUrl", "http://localhost:8080")
    // .param("userName", "xyh")
    // .param("sensitiveMessage", "love")
    // .param("spriderSource", "CSDN");
    // MvcResult result = mvc.perform(request)
    // .andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:"+body);
    // }
    //
    // @Test
    // public void deleteGithubEntity() throws Exception{
    // RequestBuilder request = MockMvcRequestBuilders.delete("/github/delete/719");
    // MvcResult result = mvc.perform(request)
    // .andReturn();
    // int statusCode = result.getResponse().getStatus();
    // Assert.assertEquals(statusCode, 200);
    // String body = result.getResponse().getContentAsString();
    // System.out.println("body:"+body);
    // }
}
