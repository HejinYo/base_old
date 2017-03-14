package com.hejinyo.controller.other;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hejinyo.model.other.Account;
import com.hejinyo.service.other.impl.AccountServiceImpl;
import com.hejinyo.utils.JsonRetrun;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Account Controller
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {
    @Resource
    private AccountServiceImpl accountsService;

    @Resource
    private Account account;

    @RequestMapping(value = "/getJson")
    @ResponseBody
    public Object getAllAccounts() {
        List<Account> list = accountsService.getAllAccounts(account);
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> listData = new ArrayList<String>();

        JSONObject type = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject sets = new JSONObject();
        JSONObject sets1 = new JSONObject();
        JSONArray datasets = new JSONArray();
        JSONObject json = new JSONObject();
        JSONObject JSChart = new JSONObject();

        for (Account a : list) {
            String tmp = "{unit:'" + a.getAccount() + "',value:" + a.getBalance() + "}";
            listData.add(tmp);
        }

        JSONArray jsonArray = JSONArray.fromObject(listData);

        // sets.put("type","pie");
        sets.put("data", jsonArray);
        datasets.add(sets);


        json.put("datasets", datasets);
        JSChart.put("JSChart", json);
        map.put("json", listData);
        return jsonArray;
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "/test";
    }

    @RequestMapping(value = "/getJson1")
    @ResponseBody
    public Map<String, Object> getJson1() {
        Map<String, Object> map1 = new HashMap<String, Object>();
        List<Account> list = accountsService.getAllAccounts(account);
        map1.put("status", 200);
        map1.put("data", "product");
        map1.put("message", "success!");
        map1.put("data", list);
        return map1;
    }

    @RequestMapping(value = "/getjson")
    @ResponseBody
    public Object getjson() {
        JSONObject data = new JSONObject();
        JSONObject data1 = new JSONObject();
        JSONArray json = new JSONArray();
        // String tmp[] = {"{'text':'已使用','value':30}","{'text':'未使用','value':70}"};
        // JSONArray jsonArray = JSONArray.fromObject(tmp);
        JSONObject data2 = new JSONObject();
        JSONObject data3 = new JSONObject();
        //data3.put("json",jsonArray);


        JSONObject type1 = new JSONObject();
        JSONObject type2 = new JSONObject();
        JSONObject jsondata = new JSONObject();
        type1.put("used", "30");
        type1.put("unused", "30");
        type2.put("used", "40");
        type2.put("unused", "60");

        data2.put("hejinyo", "hejinyo1");
        data2.put("data", type1);
        data3.put("hejinyo", "hejinyo2");
        data3.put("data", type2);

        jsondata.put("1", data2);
        jsondata.put("2", data3);
        System.out.print(jsondata.getJSONObject("1").getString("hejinyo"));
        data.put("text", "已使用");
        data.put("value", 30);
        data1.put("text", "未使用");
        data1.put("value", 70);

        json.add(data);
        json.add(data1);

        return jsondata;


    }

    @RequestMapping(value = "/getdate")
    @ResponseBody
    public Object getdate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());

        List<String> listData = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            rightNow.add(Calendar.DAY_OF_YEAR, -1);//日期加10天
            String date = sdf.format(rightNow.getTime());
            String tmp = "{test:'" + date + "',value:'" + i + "'}";
            listData.add(tmp);
        }
        JSONObject date = new JSONObject();
        date.put("ticket", listData);
        // JSONArray array = JSONArray.fromObject(json);
        return date;
    }

    @RequestMapping("/testJson")
    @ResponseBody
    public Map<String, Object> testJson() {
        JsonRetrun jsonRetrun = new JsonRetrun();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Account account = new Account();
        account.setCustid("s0000001");
        account.setAccount("banace");
        List list = new ArrayList();
        list.add("wo");
        list.add("shi");
        list.add(123);
        jsonMap.put("name", "hss");
        jsonMap.put("number", 20122281);
        jsonMap.put("list", list);
        jsonMap.put("account", account);
        /*jsonRetrun.setData(jsonMap);
        jsonRetrun.setStatus(1);
        jsonRetrun.setMessage("登录成功");
        return jsonRetrun.result();*/
        return null;
    }

    @RequestMapping("/testJson2")
    @ResponseBody
    public Object testJson2() {
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        Map<String, String> map3 = new HashMap<String, String>();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        map1.put("name", "小明");
        map1.put("age", "23");
        map1.put("sex", "男");
        list.add(map1);

        map2.put("name", "小王");
        map2.put("age", "24");
        map2.put("sex", "女");
        list.add(map2);

        map3.put("name", "小张");
        map3.put("age", "22");
        map3.put("sex", "男");
        list.add(map3);
        String jsonStr = JSONArray.fromObject(list).toString();
        System.out.println(jsonStr);
        JSONArray ja = JSONArray.fromObject(jsonStr);

        System.out.println(ja.toString());
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        int msg = 1;
        String status = "SUCCESS";

        jsonMap.put("msg", msg);
        jsonMap.put("status", status);
        jsonMap.put("data", map3);
        String s = jsonMap.toString();
        System.out.println(s);

        return jsonMap;
    }

    @RequestMapping(value = "/testJson2", method = RequestMethod.GET)
    @ResponseBody
    public String gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        int msg = 1;
        String status = "SUCCESS";

        jsonMap.put("msg", msg);
        jsonMap.put("status", status);
        jsonMap.put("data", 123);
        System.out.println(gson.toJson(jsonMap));
        return gson.toJson(jsonMap);
    }

    @RequestMapping(value = "/testExcrption")
    @ResponseBody
    public String testExcrption(HttpServletRequest request) {
        String s = "";
        Enumeration enu = request.getHeaderNames();//取得全部头信息
        while (enu.hasMoreElements()) {//以此取出头信息
            String headerName = (String) enu.nextElement();
            String headerValue = request.getHeader(headerName);//取出头信息内容
            s += headerName + ":" + headerValue + "<br>";
            //System.out.println(headerName + ":" + headerValue);
        }
        String s1 = null;
        //s1.length();
        int x = 1 / 0;
        //throw new RuntimeException("xxx");
        return s;
    }

    @RequestMapping(value = "/excptionView")
    public String excptionView() {
        return "test/exception";
    }

    @RequestMapping(value = "/html1")
    public String html1() {
        return "test/Modal";
    }

    @RequestMapping(value = "/html2")
       public String html() {
           return "test/Modal2";
       }


    @RequestMapping(value = "/testresponse")
    public void testresponse(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpStatus.OK.value());
        try {
            response.getWriter().print("test");
            response.getWriter().print("test2");
            response.getWriter().print("test3");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
