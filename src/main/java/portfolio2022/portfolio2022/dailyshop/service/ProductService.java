package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.controller.ProductForm;
import portfolio2022.portfolio2022.dailyshop.domain.Product;
import portfolio2022.portfolio2022.dailyshop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void productRegister(Product product, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        product.setFileName(fileName);
        product.setFilePath("/files/"+fileName);

        productRepository.save(product);
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Product findProduct(Long id){
        return productRepository.findById(id).get();
    }

    @Transactional
    public void productDelete(Long id){
        productRepository.deleteById(id);
    }

}
