package com.kamelchukov.common.api;

import com.kamelchukov.common.model.dto.carDto.response.FullDataOfCarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${autocatalogapi.feign.name}", url = "${autocatalogapi.feign.url}")
public interface AutoCatalogApi {

    @GetMapping("/cars/fullData")
    List<FullDataOfCarResponse> findFullDataAllOfCars();
}
