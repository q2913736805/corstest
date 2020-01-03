package cn.wzvtc.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://127.0.0.1:5500",allowCredentials = "true")
@RestController("/")
public class TestController {

    @Autowired
    LvliRepository lvliRepository;
    @RequestMapping(value = "/addlvli")
    public void addlvli(String lvli){
       Lvli lvli1=new Lvli("180020900",lvli);
       this.lvliRepository.save(lvli1);
    }
    @RequestMapping(value = "/lvlilist")
    public List<Lvli> addlvli() {

        return this.lvliRepository.findAll();
    }
    @RequestMapping(value="/userinfo")
    public Map<String,String> userinfo(HttpSession httpSession){
        Map<String,String> resultMap=new HashMap<>();
        if(httpSession.getAttribute("loginnumber")!=null) {
            resultMap.put("myname", "潘某");
            resultMap.put("mynumber", "180020900");
        }
        return resultMap;
    }

    @RequestMapping(value="/login")
    public Map<String,String> login(@RequestBody Map<String,String> map, HttpSession httpSession) {
        String password=map.get("password");
        String number=map.get("number");
        Map<String,String> resultMap=new HashMap<>();
        if ("180020900".equals(number) && "180020900".equals(password)) {
            httpSession.setAttribute("loginnumber",number);
            resultMap.put("result", "success");
        }
        return resultMap;
    }

}