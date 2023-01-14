package portfolio2022.portfolio2022.dailyshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 상품 등록
     */
    @Transactional
    public Long productRegister(Product product, MultipartFile file) throws IOException {
        transferFileSetting(file, product);
        Product saveProduct = productRepository.save(product);

        return saveProduct.getId();
    }
    /**
     * 상품 전체 조회
     */
    public List<Product> findProducts(){return productRepository.findAll();}
    public Page<Product> findProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }
    /**
     * 상품 한건 조회
     */
    public Product findProduct(Long id){
        return productRepository.findById(id).get();
    }
    /**
     * 상품 삭제
     */
    @Transactional
    public void productDelete(Long id){
        productRepository.deleteById(id);
    }
    /**
     * 상품 수정
     */
    @Transactional
    public void productModify(Long id, String name, MultipartFile file, int price, String category, int stockQuantity) throws IOException {
        Product findProduct = productRepository.findById(id).get();
        transferFileSetting(file, findProduct);
        findProduct.setName(name);
        findProduct.setPrice(price);
        findProduct.setCategory(category);
        findProduct.setStockQuantity(stockQuantity);
    }

    private void transferFileSetting(MultipartFile file, Product findProduct) throws IOException {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
//        String projectPath = System.getProperty("file.dir");

        String originalFilename = file.getOriginalFilename();
        if (file.isEmpty()){
            originalFilename = "noImage";
        }
        int pos = originalFilename.lastIndexOf("_");
        String ext = originalFilename.substring(pos + 1);
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + ext;

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        findProduct.setFileName(originalFilename);
        findProduct.setFilePath("/files/"+fileName);
    }
    /**
     * 카테고리별 상품 조회
     */
    public Page<Product> findByCategory(String category,Pageable pageable){
        return productRepository.findByCategory(category,pageable);
    }
}
