package com.ddarahakit.web.order.service;

import com.ddarahakit.web.course.model.Course;
import com.ddarahakit.web.course.model.Lecture;
import com.ddarahakit.web.order.model.Orders;
import com.ddarahakit.web.order.model.OrderCourse;
import com.ddarahakit.web.order.model.request.PostOrderReq;
import com.ddarahakit.web.order.model.response.PostOrderRes;
import com.ddarahakit.web.order.repository.OrderCourseRepository;
import com.ddarahakit.web.order.repository.OrderRepository;
import com.ddarahakit.web.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 코스 상품을 주문한 적이 있는지 확인
    // TODO : 결제 기능 추가 후 결제 되었는지 같이 확인
    public Boolean checkOrderCourse(User user, Long courseId) {
        List<Orders> orderList = orderRepository.findByUser(user);
        for (Orders orders : orderList) {
            List<OrderCourse> orderCourseList = orders.getOrderCourseList();
            for (OrderCourse orderCourse: orderCourseList) {
                if(orderCourse.getCourse().getId() == courseId) {
                    return true;
                }
            }
        }
        return false;
    }
}
