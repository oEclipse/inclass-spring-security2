package ac.su.inclassspringsecurity.service;

import ac.su.inclassspringsecurity.domain.Order;
import ac.su.inclassspringsecurity.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    @Transactional
    public Order getOrderWithUserFetched() {
        Long orderId = 14L;
        String jpqJoinQuery =
                "SELECT o " +
                "FROM Order o " +
                "JOIN FETCH o.user" +  // Lazy 전략 설정된 User 객체를 Eager 전략으로 로드
                "WHERE o.id = :orderId";
        Order orderWithEagerLoadedUser =
                entityManager.createQuery(jpqJoinQuery, Order.class)
                            .setParameter("orderId",orderId)
                            .getSingleResult();
        return orderWithEagerLoadedUser;
    }
}
