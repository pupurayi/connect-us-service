package zw.co.connectus.controller;

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

		String authorization = request.getHeader("Authorization");
		logger.info(authorization);
		Base64.Decoder decoder = Base64.getUrlDecoder();
		logger.info(String.valueOf(decoder.decode(authorization.split(".")[1])));
		return offeringRepository.save(mapper.map(newOfferingDto));
	}
}
