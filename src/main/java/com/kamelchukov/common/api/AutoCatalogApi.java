package com.kamelchukov.common.api;

import com.kamelchukov.common.model.dto.carDto.response.FullDataOfCarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${autocatalogcatalogapi.feign.name}", url = "${autocatalogcatalogapi.feign.url}")
public interface AutoCatalogApi {

    @GetMapping(value = "/cars/fullData")
    List<FullDataOfCarResponse> findFullDataAllOfCars();
}
