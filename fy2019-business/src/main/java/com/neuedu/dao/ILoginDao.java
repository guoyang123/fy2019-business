package com.neuedu.dao;

import com.neuedu.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginDao {

	public Integer isusernameexists(@Param("username") String username);
	public Admin findAdminByUsernameAndPassword(@Param("username") String username,
                                                @Param("password") String password);
}
