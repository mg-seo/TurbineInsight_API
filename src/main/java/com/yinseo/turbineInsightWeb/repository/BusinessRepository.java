//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.Business;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByUser_UserId(String userId);

    List<Business> findAllByOrderByBusinessIdDesc();
}
