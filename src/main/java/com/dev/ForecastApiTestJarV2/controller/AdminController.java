package com.dev.ForecastApiTestJarV2.controller;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
import com.dev.ForecastApiTestJarV2.dto.QuestDTO;
import com.dev.ForecastApiTestJarV2.model.member.Member;
import com.dev.ForecastApiTestJarV2.model.quest.Quest;
import com.dev.ForecastApiTestJarV2.model.quest.QuestHashTag;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestHashTagRepository;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestRepository;
import com.dev.ForecastApiTestJarV2.service.S3UploadService;
import com.dev.ForecastApiTestJarV2.service.quest.QuestAnswerService;
import com.dev.ForecastApiTestJarV2.service.quest.QuestImagesService;
import com.dev.ForecastApiTestJarV2.service.quest.QuestService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/admin")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {

	private final MemberRepository memberRepository;
	private final QuestService questService;
	private final QuestRepository questRepository;
	private final S3UploadService s3UploadService;
	private final QuestHashTagRepository questHashTagRepository;
	private final QuestImagesService questImagesService;
	private final QuestAnswerService questAnswerService;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@GetMapping("/selectMemberAll")
	public @ResponseBody Object selectMemberDetail(
			@PageableDefault(size = 10) Pageable pageable
			) {
		
		Page<Member> members = memberRepository.findAll(pageable);
		int startPage = Math.max(1, members.getPageable().getPageNumber() - 4);
		int endPage = Math.min(members.getTotalPages(), members.getPageable().getPageNumber() + 4);
		HashMap<String, Object> member = new HashMap<String, Object>();
		member.put("members", members);
		member.put("startPage", startPage);
		member.put("endPage", endPage);
		member.put("totalPage", members.getPageable());
		member.put("totalElements", members.getTotalElements());
		return member;
	}
	
	@GetMapping("/selectMemberDetail/{address}")
	public @ResponseBody Object selectMemberDetail(
			@PathVariable String address
			) {
		
		return memberRepository.findOneByMemberWalletAddress(address);
	} 
	
	@PostMapping("/questHashTagRegistration")
	public @ResponseBody Object questHashTagRegistration(
			@RequestBody Map<String, ArrayList<String>> hashTags
			
			) {
		
		List<String> tags = hashTags.get("hashTags");
		List<String> dupList = new ArrayList<String>();
		Map<String, Object> result = new HashMap<>();
		
		for(String tag : tags) {
		QuestHashTag hashTag = new QuestHashTag();
			try {
				hashTag.setHashtagName(tag);
				questHashTagRepository.save(hashTag);
				result.put("CODE", "SUCCESS");
			}catch(ConstraintViolationException e) {
				result.put("CODE", "ConstraintViolationException");
			}catch(DataIntegrityViolationException e) {
				dupList.add(tag);
				result.put("CODE", "DataIntegrityViolationException");
			}
		}
		result.put("DUP WORDS", dupList);
		return result;
	} 
	
	@PostMapping(
			value = "/questRegistration", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
			)
	public @ResponseBody Object questRegistration(
			@RequestPart QuestDTO quest,
			@RequestParam List<MultipartFile> files,
			HttpServletRequest request
			) throws IOException, IllegalArgumentException, FileNotFoundException, java.io.IOException {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		String token = "";
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			token = bearerToken.split(" ")[1].trim();
		}
		String walletAddress = jwtTokenProvider.getAuthentication(token).getName();
		Date day = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dirName = "uploadFile/" + format.format(day);
		List<String> filePath = new ArrayList<String>();
		
		for (int x=0; x<files.size(); x++) {
			if (!files.get(x).isEmpty()) {
				filePath.add(s3UploadService.upload(files.get(x), dirName));
			}
		}
		
		Quest q = questService.questRegistration(walletAddress, quest, 
				filePath.get(0), questHashTagRepository.findById(quest.getQuestHashTag()).get());
		questImagesService.questImagesRegistration(filePath, q);
		questAnswerService.questAnswerRegistration(q, quest.getQuestAnswer());
		
		Quest registered = questRepository.findById(q.getQuestId()).get();
		return registered;
	}
	
	
}




















