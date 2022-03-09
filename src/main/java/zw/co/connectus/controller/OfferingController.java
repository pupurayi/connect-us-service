package zw.co.connectus.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.connectus.dal.entity.Offering;
import zw.co.connectus.dal.repository.OfferingRepository;
import zw.co.connectus.service.mapper.DtoMapper;
import zw.co.connectus.service.model.NewOfferingDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/offering")
@CrossOrigin(origins = "*")
public class OfferingController {

	static Logger logger = LoggerFactory.getLogger(OfferingController.class);

	@Autowired
	private OfferingRepository offeringRepository;

	@Autowired
	DtoMapper mapper;

	@PostMapping
	public Offering addOffering(@RequestBody NewOfferingDto newOfferingDto, HttpServletRequest request) {

		String token = request.getHeader("Authorization").replace("Bearer ", "");

		Base64.Decoder decoder = Base64.getUrlDecoder();
		String json = new String(decoder.decode(token.split("\\.")[1]));

		JsonObject jwt = new Gson().fromJson(json, JsonObject.class);
		final Offering offering = mapper.map(newOfferingDto);

		return offeringRepository.save(offering);
	}

	@GetMapping
	public List<Offering> get() {

		return offeringRepository.findAll();
	}
}
