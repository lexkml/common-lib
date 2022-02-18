package com.common.api;

import com.common.model.dto.carDto.response.FullDataOfCarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${autoCatalogApi.feign.name}", url = "${autoCatalogApi.feign.url}")
public interface AutoCatalogApi {

    @GetMapping("/cars/fullData")
    List<FullDataOfCarResponse> findFullDataAllOfCars();
}
