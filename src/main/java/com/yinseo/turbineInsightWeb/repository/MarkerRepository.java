//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.Marker;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    List<Marker> findByBusiness_BusinessId(Long businessId);
}
