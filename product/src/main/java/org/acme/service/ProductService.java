package org.acme.service;

import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;


    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(item ->{
            products.add(mapProductEntityToDTO(item));
        });

        return products;
    }



    public void createNewProduct(ProductDTO productDTO){
        productRepository.persist(mapProductDTOToEntity(productDTO));
    }


    public void changeProduct(Long id, ProductDTO productDTO){

        //procura no banco
        ProductEntity productEntity = productRepository.findById(id);

        //popula as informações
        productEntity.setName(productEntity.getName());
        productEntity.setCategory(productEntity.getCategory());
        productEntity.setModel(productEntity.getModel());
        productEntity.setDescription(productEntity.getDescription());
        productEntity.setPrice(productEntity.getPrice());

        // salva no banco
        productRepository.persist(productEntity);

    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity){
            ProductDTO productDTO = new ProductDTO();

            productDTO.setName(productEntity.getName());
            productDTO.setDescription(productEntity.getDescription());
            productDTO.setCategory(productEntity.getCategory());
            productDTO.setModel(productEntity.getModel());
            productDTO.setPrice(productEntity.getPrice());

            return productDTO;
    }

    private ProductEntity mapProductDTOToEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setModel(productDTO.getModel());
        productEntity.setPrice(productDTO.getPrice());

        return productEntity;
    }


}
