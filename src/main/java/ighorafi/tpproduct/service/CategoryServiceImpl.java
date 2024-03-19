package ighorafi.tpproduct.service;

import ighorafi.tpproduct.entity.Category;
import ighorafi.tpproduct.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Override
    public Category getCategoryByCode(Long codeCategory) {
        Category category = categoryRepository.findByCode(codeCategory);
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        return category;
    }

}
