package com.hand.mapper;

import com.hand.pojo.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/23 17:32
 * @description:
 */
@Repository
public interface AddressMapper {

    /**
     　　* @description: 查询所有的地址
     　　* @param []
     　　* @return java.util.List<com.hand.pojo.Address>
     　　* @throws
     　　* @author ZhongLingYun
     　　* @date 2018/8/24 9:14
     　　*/
    List<Address> listAddress();
}
