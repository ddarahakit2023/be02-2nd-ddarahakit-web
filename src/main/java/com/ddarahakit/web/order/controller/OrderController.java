package com.ddarahakit.web.order.controller;

import com.ddarahakit.web.order.model.request.PostOrderReq;
import com.ddarahakit.web.order.model.response.PostOrderRes;
import com.ddarahakit.web.order.service.OrderService;
import com.ddarahakit.web.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createOrder(@AuthenticationPrincipal User user, @RequestBody PostOrderReq request){
        PostOrderRes postOrderRes = orderService.creatOrder(user, request);
        return ResponseEntity.ok().body(postOrderRes);
    }
}
