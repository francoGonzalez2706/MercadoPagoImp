package com.example.mp.controlers;


import com.example.mp.Entitis.Product;
import com.example.mp.services.BaseService;
import com.example.mp.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/producto")
public class ProductController extends BaseController<Product,ProductService>{

    protected ProductController(ProductService service) {
        super(service);
    }
}
