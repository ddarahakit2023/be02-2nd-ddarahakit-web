package com.ddarahakit.web.order.service;

import com.ddarahakit.web.course.model.Course;
import com.ddarahakit.web.order.model.Orders;
import com.ddarahakit.web.order.model.OrderCourse;
import com.ddarahakit.web.order.model.request.PostOrderReq;
import com.ddarahakit.web.order.model.response.PostOrderRes;
import com.ddarahakit.web.order.repository.OrderCourseRepository;
import com.ddarahakit.web.order.repository.OrderRepository;
import com.ddarahakit.web.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCourseRepository orderCourseRepository;

    public PostOrderRes creatOrder(User user, PostOrderReq request) {
        Orders order = Orders.builder()
                .user(user)
                .build();
        order = orderRepository.save(order);
        for (Long courseId : request.getCourseIdList()) {
            OrderCourse orderCourse = OrderCourse.builder()
                    .orders(order)
                    .course(
                            Course.builder()
                                    .id(courseId)
                                    .build()
                    )
                    .build();
            orderCourseRepository.save(orderCourse);
        }


        return PostOrderRes.builder().build();
    }
}
