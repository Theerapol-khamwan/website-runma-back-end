package com.runma.backendspringbootkeng.Model;

import com.runma.backendspringbootkeng.entity.Status;

import java.util.Date;

public record TicketDTOResponse(
        Integer id,
        Date createDate,
        Status status,
        String raceTypeName,
        Double raceTypePrize,
        Integer userId
) {
}
