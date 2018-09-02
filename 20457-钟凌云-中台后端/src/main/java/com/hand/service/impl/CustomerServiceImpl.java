package com.hand.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hand.mapper.AddressMapper;
import com.hand.mapper.CustomerMapper;
import com.hand.pojo.Address;
import com.hand.pojo.Customer;
import com.hand.service.CustomerService;
import com.hand.util.AbstractConstant;
import com.hand.util.Login;
import com.hand.util.Time;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/22 22:28
 * @description: CustomerService实现类
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private Log log= LogFactory.getLog(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 　　* @description: 用户登录验证接口
     * 　　* @param [customer]
     * 　　* @return java.lang.String 返回success表示验证通过 failure表示没有通过验证
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/23 9:55
     *
     * @param customer
     */
    @Override
    public String customerLong(Customer customer, HttpServletRequest httpServletRequest) {
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
        int result = customerMapper.countCustomerByMassage(customer);
        // 只有等于1表明当前用户唯一确定
        if (result == 1) {
            resultString = AbstractConstant.SUCCESS;
            Login.addUserName(httpServletRequest,customer.getFirstName());
            return resultString;
        }
        return resultString;
    }

    /**
     * 　　* @description: 分页查询可用的customer信息
     * 　　* @param [page, pageSize] page表示想要查询那一页，pagSize表示当前页想要多少条数据
     * 　　* @return java.util.List<com.hand.pojo.Customer>
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/23 11:08
     *
     * @param page 当前查看的页
     * @param pageSize 当前页有多少条
     */
    @Override
    public PageInfo<Customer> pageCustomer(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Customer> list = customerMapper.listCustomer();
        // 替换空邮箱
        for(int x=0;x<list.size();x++){
            if(list.get(x).getEmail()==null){
                list.get(x).setEmail("无");
            }
        }
        for (int x=0;x<list.size();x++){
            String lastUpdateString = Time.dateToString(list.get(x).getLastUpdate());
            list.get(x).setLastUpdateString(lastUpdateString);
        }
        // pageInfo 将list数据和page数据进行封装
        PageInfo<Customer> pageInfo = new PageInfo<>(list);
        // 包含所有的分页查询信息
        return pageInfo;

    }

    /**
     　　* @description: 将所有的地址信息和当前修改者的信息封装为map
     　　* @param [customerId]
     　　* @return java.util.Map
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/23 18:11
     　　*/
    @Override
    public Map selectCustomerById(int customerId) {
                Customer customer= customerMapper.selectCustomerById(customerId);
                List<Address> addressList=addressMapper.listAddress();
                Map map = new HashMap();
                map.put("customer",customer);
                map.put("addressList",addressList);
                return map;
    }

    /**
     * 　　* @description: 更新用数据只能更新邮箱和地址
     * 　　* @param [customer]
     * 　　* @return java.lang.String 更新结果
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/23 19:31
     *
     *
     * @param customer 内部包含跟数据
     */
    @Override
    public String updateCustomer(Customer customer) {
        String resultString =AbstractConstant.FAILURE;
        if (customer==null){
            // null 对象直接不走数据库
            return resultString;
        }
        customer.setLastUpdateString(Time.getNowDateString());
        int result=customerMapper.updateCustomer(customer);
        // 一个对象只影响一行数据
        if(result==1){
            resultString=AbstractConstant.SUCCESS;
        }
        return resultString;
    }

    /**
     * 　　* @description: 删除customer信息 调用三次删除做事物控制
     *                      出现异常直接回滚保持原数据，上层调用需要手动添加异常捕获
     * 　　* @param [customerId]
     * 　　* @return java.lang.String
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/24 9:03
     *
     *
     * @param customerId
     */
    @Override
    @Transactional(rollbackOn= Exception.class)
    public String deleteCustomer(Integer customerId) {
        String resultString=AbstractConstant.FAILURE;
            customerMapper.deleteRental(customerId);
            customerMapper.deletePayment(customerId);
            int result=customerMapper.deleteCustomer(customerId);
            // 主表只影响一行
            if(result ==1){
                return  AbstractConstant.SUCCESS;
            }
            return resultString;
    }

    /**
     * 　　* @description: 打开新增模态框之前查找地址以及storeId
     * 　　* @param []
     * 　　* @return java.util.Map 包含List<Addresss> list<storeId>
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/25 18:27
     *
     */
    @Override
    public Map openAddModal() {
        Map map = new HashMap();
        List store=  customerMapper.listStorid();
        List<Address> address=addressMapper.listAddress();
        map.put("store",store);
        map.put("address",address);
        return map;
    }

    /**
     * 　　* @description: 新增一条Customer
     * 　　* @param [customer]
     * 　　* @return java.lang.String
     * 　　* @throws
     * 　　* @author ZhongLingYun
     * 　　* @date 2018/8/25 22:21
     *
     *
     * @param customer
     */
    @Override
    public String addCustomer(Customer customer) {
        String resultString =AbstractConstant.FAILURE;
        if(customer==null){
            return resultString;
        }
        customer.setLastUpdateString(Time.getNowDateString());
        customer.setCreateDateString(Time.getNowDateString());
        // 插入出现异常，比如到关联字段不存在或者必要的字段没有
      try{
        int result =customerMapper.addCustomer(customer);
            if (result==1){
                resultString=AbstractConstant.SUCCESS;
            }
      }catch (Exception e){
            log.error("插入数据失败缺少必要的数据");
        }
        finally {
            return resultString;
        }
    }
}
