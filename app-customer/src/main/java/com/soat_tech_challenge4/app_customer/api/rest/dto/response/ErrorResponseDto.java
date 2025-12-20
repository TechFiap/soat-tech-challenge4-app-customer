package com.soat_tech_challenge4.app_customer.api.rest.dto.response;

public record ErrorResponseDto(
        Integer status,
        String error
) {}
