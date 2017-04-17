package com.tzl.dao;

import com.tzl.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hztangzhilong on 2016/12/11.
 */
@Repository
public interface UserResposity extends CrudRepository<User,Long> {
}
