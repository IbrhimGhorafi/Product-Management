package ighorafi.tpproduct.service;

import ighorafi.tpproduct.entity.Category;

public interface CategoryService {
    Category getCategoryByCode(Long codeCategory);
}
