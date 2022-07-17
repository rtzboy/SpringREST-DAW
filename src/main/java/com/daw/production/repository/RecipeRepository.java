package com.daw.production.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.daw.production.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	List<Recipe> findByProductId(Long postId);

	@Transactional
	void deleteByProductId(long productId);

}
