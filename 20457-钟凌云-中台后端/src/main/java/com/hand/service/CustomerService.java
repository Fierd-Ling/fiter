package com.hand.service;

import com.github.pagehelper.PageInfo;
import com.hand.pojo.Customer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/23 09:01
 * @description: usersService接口
 */
public interface CustomerService {

    /**
     　　* @description: 用户登录验证接口
     　　* @param [customer]
     　　* @return java.lang.String 返回success表示验证通过 failure表示没有通过验证
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 9:55
     　　*/
    String customerLong(Customer customer, HttpServletRequest httpServletRequest);

    /**
     　　* @description: 分页查询可用的customer信息
     　　* @param [page, pageSize] page表示想要查询那一页，pagSize表示当前页想要多少条数据
     　　* @return java.util.List<com.hand.pojo.Customer>
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 11:08
     　　*/
    PageInfo<Customer> pageCustomer(int page, int pageSize);

    /**
     　　* @description: 修改前的信息查找
     　　* @param [customerId]
     　　* @return java.util.Map
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 18:08
     　　*/
    Map selectCustomerById(int customerId );

    /**
     　　* @description: 更新用数据只能更新邮箱和地址
     　　* @param [customer]
     　　* @return java.lang.String 更新结果
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 19:31
     　　*/
    String updateCustomer(Customer customer);

    /**
     　　* @description: 删除customer信息
     　　* @param [customerId]
     　　* @return java.lang.String
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/24 9:03
     　　*/
    String deleteCustomer(Integer customerId );

    /**
     　　* @description: 打开新增模态框之前查找地址以及storeId
     　　* @param []
     　　* @return java.util.Map 包含List<Addresss> list<storeId>
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/25 18:27
     　　*/
    Map openAddModal();

    /**
     　　* @description: 新增一条Customer
     　　* @param [customer]
     　　* @return java.lang.String
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/25 22:21
     　　*/
    String addCustomer(Customer customer);

}
