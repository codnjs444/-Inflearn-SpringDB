package hello.itemservice.repository.v2;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemSearchCond;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static hello.itemservice.domain.QItem.*;

@Repository
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory queryFactory;

    public ItemQueryRepositoryV2(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Item> findALl(ItemSearchCond cond) {
        return queryFactory.select(item)
                .from(item)
                .where(
                        likeItemName(cond.getItemName()),
                        maxPrice(cond.getMaxPrice())
                )
                .fetch();
    }

    private Predicate maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }

    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }
}
