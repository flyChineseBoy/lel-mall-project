package org.lele.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lele.product.dto.request.CreateProductRequest;
import org.lele.product.dto.request.QueryProductRequest;
import org.lele.common.entity.product.ESProduct;
import org.lele.common.entity.product.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    boolean saveProduct( CreateProductRequest request );
    List<ESProduct> search(QueryProductRequest request);
}