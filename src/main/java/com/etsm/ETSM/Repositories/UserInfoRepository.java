package com.etsm.ETSM.Repositories;

import com.etsm.ETSM.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
