package com.ddarahakit.web.order.model.request;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOrderReq {
    List<Long> courseIdList;
}
