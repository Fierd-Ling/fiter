package com.hand.mapper;

import com.hand.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/22 22:35
 * @description: customer表操作接口
 */
@Repository
public interface CustomerMapper {

    /**
     　　* @description:  通过账号和密码检索记录条数
     　　* @param [customer]
     　　* @return int
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 9:10
     　　*/
    int countCustomerByMassage(Customer customer);

    /**
     　　* @description: 查询所有可用的customer数据
     　　* @param []
     　　* @return java.util.List<com.hand.pojo.Customer>
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 10:57
     　　*/
    List<Customer> listCustomer();

    /**
     　　* @description: 通过id查找一条数据
     　　* @param [customerId]
     　　* @return com.hand.pojo.Customer
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 13:20
     　　*/
    Customer selectCustomerById(int customerId);

    /**
     　　* @description: 修改customer信息只能修改邮箱和地址
     　　* @param [customer]
     　　* @return java.lang.Integer
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 22:14
     　　*/
    Integer updateCustomer(Customer customer);

    /**
    　　* @description:  删除外键关联数据依次调用，做事物处理前面没有调用成功后面整体回退
    　　* @param customerId
    　　* @return
    　　* @throws
    　　* @author ZhongLingYun
    　　* @date 2018/8/24 8:50
    　　*/
    void deleteRental(Integer customerId);

    void deletePayment(Integer customerId);

    int deleteCustomer(Integer customerId);
    // delete 删除end

    // 添加customer前需要获取storid
    /**
     　　* @description: 获取所有的stroid
     　　* @param []
     　　* @return java.util.List
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/25 18:24
     　　*/
    List<Integer> listStorid();

    /**
     　　* @description: 新增一条customer
     　　* @param [customer]
     　　* @return int
     　　* @throws 此处插如果storeId不存在会有异常抛出
     　　* @author ZhongLingYun
     　　* @date 2018/8/25 22:24
     　　*/
    int addCustomer(Customer customer);


}
