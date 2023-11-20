package com.ssafy.triends.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.triends.board.model.BoardDto;
import com.ssafy.triends.board.model.BoardListDto;
import com.ssafy.triends.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardMapper.writeArticle(boardDto);
	}

	@Override
	public BoardListDto listArticle() throws Exception {
		List<BoardDto> list = boardMapper.listArticle();
		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		return boardListDto;
	}
	
	@Override
    public void deleteArticle(int articleNo) throws Exception {
        boardMapper.deleteArticle(articleNo);
    }

//	@Override
//	public void modifyArticle(BoardDto boardDto) throws Exception {
//		// TODO : BoardDaoImpl의 modifyArticle 호출
//		boardMapper.modifyArticle(boardDto);
//	}

//	@Override
//	@Transactional
//	public void deleteArticle(int articleNo, String path) throws Exception {
//		// TODO : BoardDaoImpl의 deleteArticle 호출
//		List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
//		boardMapper.deleteFile(articleNo);
//		boardMapper.deleteArticle(articleNo);
//		for(FileInfoDto fileInfoDto : fileList) {
//			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
//			file.delete();
//		}
//	}

//	@Override
//	public void deleteArticle(int articleNo) throws Exception {
//		// TODO : BoardDaoImpl의 deleteArticle 호출
//		boardMapper.deleteArticle(articleNo);
//	}

}
