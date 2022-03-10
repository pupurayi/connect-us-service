package zw.co.connectus.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.Product;
import zw.co.connectus.dal.repository.ProductRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.CreateProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

	static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	DtoMapper mapper;

	@PostMapping
	public Product createProduct(@RequestBody CreateProductDto createProductDto, HttpServletRequest request) {

		String token = request.getHeader("Authorization").replace("Bearer ", "");

		Base64.Decoder decoder = Base64.getUrlDecoder();
		String json = new String(decoder.decode(token.split("\\.")[1]));

		JsonObject jwt = new Gson().fromJson(json, JsonObject.class);
		final Product product = mapper.map(createProductDto);

		return productRepository.save(product);
	}

	@GetMapping
	public List<Product> get() {

		return productRepository.findAll();
	}
}
