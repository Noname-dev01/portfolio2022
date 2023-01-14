package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Category;
import portfolio2022.portfolio2022.dailyshop.repository.CategoryRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long categorySave(Category category){
        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getId();
    }
}
