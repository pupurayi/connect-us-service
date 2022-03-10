package zw.co.connectus.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.GoodsAndServices;
import zw.co.connectus.dal.repository.GoodsAndServicesRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.NewGoodsAndServicesDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/goods-and-services")
@CrossOrigin(origins = "*")
public class GoodsAndServicesController {

	static Logger logger = LoggerFactory.getLogger(GoodsAndServicesController.class);

	@Autowired
	private GoodsAndServicesRepository goodsAndServicesRepository;

	@Autowired
	DtoMapper mapper;

	@PostMapping
	public GoodsAndServices addOffering(@RequestBody NewGoodsAndServicesDto newGoodsAndServicesDto, HttpServletRequest request) {

		String token = request.getHeader("Authorization").replace("Bearer ", "");

		Base64.Decoder decoder = Base64.getUrlDecoder();
		String json = new String(decoder.decode(token.split("\\.")[1]));

		JsonObject jwt = new Gson().fromJson(json, JsonObject.class);
		final GoodsAndServices goodsAndServices = mapper.map(newGoodsAndServicesDto);

		return goodsAndServicesRepository.save(goodsAndServices);
	}

	@GetMapping
	public List<GoodsAndServices> get() {

		return goodsAndServicesRepository.findAll();
	}
}
