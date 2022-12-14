package com.ssafy.live.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.Board;
import com.ssafy.live.model.dto.FileInfo;
import com.ssafy.live.model.dto.User;
import com.ssafy.live.model.service.BoardService;
import com.ssafy.live.model.service.FileService;
import com.ssafy.live.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardController {

	private final BoardService boardService;
	private final FileService fileService;

	@Autowired
	public BoardController(BoardService boardService,FileService fileService) {
		log.info("BoardController 생성자 호출!!!");
		this.boardService = boardService;
		this.fileService = fileService;
	}

	// 글 목록 조회
	@GetMapping("")
	public ResponseEntity<?> list(@RequestParam("type") String type) throws Exception {
		log.debug(type);
		List<Board> list = boardService.getArticleList(type);

		for (Board b : list) {
			log.debug(b.toString());
		}

		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	// 페이지네이션 ver
//	@GetMapping("")
//	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
//		log.debug("list parameter : {}", map);
//
//		List<Board> list = boardService.getArticleList(map);
//		for (Board b : list) {
//			log.debug(b.toString());
//		}
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);
//
//		Map<String, Object> responeMap = new HashMap<String, Object>();
//		responeMap.put("articles", list);
//		responeMap.put("navigation", pageNavigation);
//		responeMap.put("pgno", map.get("pgno"));
//		responeMap.put("key", map.get("key"));
//		responeMap.put("word", map.get("word"));
//
//		return new ResponseEntity<Map<String, Object>>(responeMap, HttpStatus.OK);
//	}

	// 글 쓰기
	@PostMapping("")
	public ResponseEntity<?> write(@RequestParam(required = false) MultipartFile[] files, Board board)
			throws Exception {
		Map<String,String> resultMap=new HashMap<>();
		log.debug("글 쓰기 : {}", board.toString());
		// log.debug("글 입력 전 dto : {}", board.toString());

		// 파일 정보
		if (files!=null&&!files[0].isEmpty()) {
			List<FileInfo> fileInfos = fileService.saveFileInServer(files);
			log.debug(board.toString());
			board.setFileInfos(fileInfos);
		}

		String articleNo= String.valueOf(boardService.writeArticle(board));
		resultMap.put("articleNo",articleNo);
		return new ResponseEntity<Map<String,String>>(resultMap,HttpStatus.CREATED);

	}
	// 게시글 수정
	@PutMapping("")
	public ResponseEntity<Void> modify(@RequestParam(required = false) MultipartFile[] files, Board board) throws Exception {
		log.debug("수정 board dto : {}", board.toString());
		boardService.modifyArticle(board);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 게시글 상세 조회
	@GetMapping("/{articleNo}")
	public ResponseEntity<Board> detail(@PathVariable int articleNo) throws Exception {
		boardService.updateHit(articleNo);
		Board board = boardService.getArticle(articleNo);
		log.debug(board.toString());
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	// 게시글 삭제
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<Void> delete(@PathVariable int articleNo) throws Exception {
		fileService.deleteFileInServer(articleNo);
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
