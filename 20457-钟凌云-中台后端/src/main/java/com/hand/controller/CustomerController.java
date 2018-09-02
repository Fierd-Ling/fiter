package com.hand.controller;

import com.github.pagehelper.PageInfo;
import com.hand.pojo.Customer;
import com.hand.service.CustomerService;
import com.hand.util.AbstractConstant;
import com.hand.util.Login;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/23 09:03
 * @description:
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    private Log log= LogFactory.getLog(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 　　* @description: 用户登录控制层
     * 　　* @param [customer]
     * 　　* @return java.lang.String 验证结果 success表示通过 failure表示验证没有通过
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/23 10:08
     *
     */
    @RequestMapping(value = "/long",method = RequestMethod.POST)
    public
    @ResponseBody
    String customerLong(Customer customer, HttpServletRequest httpServletRequest) {
        String resultString = AbstractConstant.FAILURE;
        // 判断是否为空对象 以及必要参数是否存在
        if (customer == null) {
            return resultString;
        }
        if ("".equals(customer.getLastName()) || customer.getLastName() == null) {
            return resultString;
        }
        if ("".equals(customer.getFirstName()) || customer.getFirstName() == null) {
            return resultString;
        }
        resultString = customerService.customerLong(customer,httpServletRequest);
        return resultString;
    }

    /**
     　　* @description: 分页查询可用的customer信息
     　　* @param [page, pageSize] page表示需要查看的页数，pageSize表示当前页有多少条记录
     　　* @return com.github.pagehelper.PageInfo
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 11:18
     　　*/
    @RequestMapping(value = "/pageCustomer" ,method = RequestMethod.POST)
    public @ResponseBody PageInfo pageCustomer(Integer page,Integer pageSize){
        if(page==null||pageSize==null){
            return null;
        }
        PageInfo pageInfo=customerService.pageCustomer(page,pageSize);
        return pageInfo;
    }

     /**
      　　* @description: 修改前的信息浏览以及信息确认
      　　* @param [customerId]
      　　* @return java.util.Map 包含List<Address> 以及customer实体
      　　* @throws
      　　* @author ZhongLingYun
      　　* @date 2018/8/23 18:07
      　　*/
    @RequestMapping(value = "/selectCustomer" ,method = RequestMethod.POST)
    public @ResponseBody Map selectCustomerById(Integer customerId){
        if(customerId==null){
            return null;
        }
       return customerService.selectCustomerById(customerId);
    }

    /**
     　　* @description: 更新用户信息（只更新邮箱和地址）
     　　* @param [customer] 更新对象
     　　* @return java.lang.String 返回结果提示
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/24 8:59
     　　*/
    @RequestMapping(value="updateMassage" ,method = RequestMethod.POST)
    public @ResponseBody String updateCustomerMassage(Customer customer){
        String resultString =AbstractConstant.FAILURE;
        if (customer==null){
            return resultString;
        }
        log.info(customer.getEmail());
        resultString=customerService.updateCustomer(customer);
        log.info(resultString);
        return resultString;
    }

/**
 　　* @description: 删除当前customer信息
 　　* @param [customerId]
 　　* @return java.lang.String
 　　* @throws
 　　* @author ZhongLingYun
 　　* @date 2018/8/24 10:09
 　　*/
    @RequestMapping(value = "/delete",method = RequestMethod.POST )
    public @ResponseBody String deleteCustomer(Integer customerId){
        String resultString =AbstractConstant.FAILURE;
        if(customerId==null){
            return resultString;
        }
        // 用于捕获事物回退的异常
        try {
           resultString= customerService.deleteCustomer(customerId);
        }catch (Exception e){
            log.info("删除出错");
        }finally {
            // 返回提示结果
            return resultString;
        }
    }

    /**
     　　* @description: 新增之前的数据查找
     　　* @param []
     　　* @return java.util.Map 包含所有的地址以及所有的storeId
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/25 18:33
     　　*/
    @RequestMapping(value="/openAddModel" ,method = RequestMethod.POST)
    public @ResponseBody Map openAddModal(){
        return customerService.openAddModal();
    }

    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    public @ResponseBody String addCustomer(Customer customer){
        String resultString =AbstractConstant.FAILURE;
        if(customer==null){
            return resultString;
        }
        // 页面数据已经选择了storeId
        if(customer.getStoreId()==0){
            return resultString;
        }
        resultString=customerService.addCustomer(customer);
        return resultString;
    }

    /**
     　　* @description: 查找当前已经登录验证通过的用户名
     　　* @param [httpServletRequest]
     　　* @return java.lang.String
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/26 13:02
     　　*/
    @RequestMapping(value = "/getUserName",method = RequestMethod.POST)
    public @ResponseBody String getUserName(HttpServletRequest httpServletRequest){
        return Login.getUserName(httpServletRequest);
    }

    /**
     　　* @description: 退出
     　　* @param [httpServletRequest]
     　　* @return void
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/26 14:27
     　　*/
    @RequestMapping(value = "/longOut",method = RequestMethod.POST)
    public @ResponseBody String longOut(HttpServletRequest httpServletRequest){
        Login.longOut(httpServletRequest);
        return AbstractConstant.SUCCESS;
    }

    /**
     　　* @description: 去到customer页面
     　　* @param []
     　　* @return org.springframework.web.servlet.ModelAndView
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/26 18:36
     　　*/
    @RequestMapping(value = "/goToCustomerPage",method = RequestMethod.GET)
    public ModelAndView goToCustomerPage(){
        log.info("1");
        ModelAndView modelAndView=new ModelAndView("/html/index");
        return modelAndView;
    }

    @RequestMapping(value ="goToFilmPage",method = RequestMethod.GET)
    public ModelAndView goToFilmPage(){
        log.info("3");
        ModelAndView modelAndView=new ModelAndView("/html/film");
        return modelAndView;
    }

    /**
    　　* @description: 测试未登录状态下的ajax请求
    　　* @param
    　　* @return
    　　* @throws
    　　* @author ZhongLingYun
    　　* @date 2018/8/27 11:24
    　　*/
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public@ResponseBody String testAjax(){
        return "1234";
    }
}
