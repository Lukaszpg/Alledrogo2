package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.lukasgorny.model.Category;
import pro.lukasgorny.util.QueryBody;

import java.util.List;

/**
 * Created by Łukasz on 26.10.2017.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIsNull();
    List<Category> findByParentId(Long parentId);

    @Query(QueryBody.CategoryQuery.FIND_ALL_IDS)
    List<Long> findAllIds();

    @Query(QueryBody.CategoryQuery.FIND_CATEGORY_COUNT)
    Long countAll();

}
