package com.green.book_shop.book.mapper;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.dto.BookDTO;
import com.green.book_shop.book.dto.ImgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.awt.print.Book;
import java.util.List;

@Mapper
public interface BookMapper {
  // 카테고리 목록 조회
  public List<BookCategoryDTO> selectCategoryList();

  // 도서 등록
  public void insertBook(BookDTO bookDTO);

  // 3-1 카테고리명 중복 확인 쿼리
  public String isUsableCateName(String cateName);

  // 3-2 카테고리 등록 쿼리 (BookService로 안가고 Impl의 if문으로 ㄱㄱ !)
  public int insertCategory(String cateName);

  //도서 이미지 등록
//  public void insertImgs(List<ImgDTO> imgList);
  public void insertImgs(BookDTO bookDTO);

  //다음에 들어갈 BOOK_CODE 조회
  public int getNextBookCode();

}
