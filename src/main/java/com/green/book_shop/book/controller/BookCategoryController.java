package com.green.book_shop.book.controller;

import com.green.book_shop.book.dto.BookCategoryDTO;
import com.green.book_shop.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class BookCategoryController {
  private final BookService bookService;

  // 카테고리 목록 조회 api
  // ~/categories
  @GetMapping("")
  public ResponseEntity<?> getCateList(){
    try {
      //조회가 안되면 list는 null 아님!!, list.size() == 0 데이터 하나도 없이 나옴
      List<BookCategoryDTO> list = bookService.selectCategoryList();

      return ResponseEntity
              .status(HttpStatus.OK)
              .body(list);
    }catch(Exception e){
      e.printStackTrace(); //오류가 발생한 위치 및 이유를 알려줌
//      return ResponseEntity
//              .status(HttpStatus.INTERNAL_SERVER_ERROR)
//              .body("카테고리 목록 조회 중 서버 오류 발생");

      //상태는 주되 데이터는 안가져갈때
      //전달한 데이터가 없으면 .build() 메서드 호출로 마무리!
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


  }

  // 카테고리 등록 api
  // post ~/categories
  @PostMapping("")
  public ResponseEntity<?> insertCategory(@RequestBody BookCategoryDTO bookCategoryDTO){
    //@RequestBody 넘어오는 객체의 키와 매개변수에 작성한 dto 클래스의 변수가 일치
    //등록 성공 ➔ 1 리턴
    //등록 안했으면 ➔ 0 리턴
    int result = bookService.insertCategory(bookCategoryDTO.getCateName());

    return ResponseEntity
            .status(result == 1 ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR)
            .body(result == 1 ? result : "알수없는 이유로 등록이 되지 않았습니다");
  }
  @GetMapping("/test1")
  public ResponseEntity<?> test1(){
    return ResponseEntity.status(HttpStatus.OK).body("java");
  }

  @GetMapping("/test2")
  public ResponseEntity<?> test2(){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(10);
  }

  @GetMapping("/test3")
  public ResponseEntity<List<BookCategoryDTO>> test3(){
    List<BookCategoryDTO> list = bookService.selectCategoryList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(list);
  }

  @GetMapping("/test4")
  public ResponseEntity<String> test4(){
    HttpHeaders headers = new HttpHeaders();
    headers.add("myName", "hong");
    headers.add("myAge", "20");

    return ResponseEntity
            .status(HttpStatus.OK)
            .headers(headers)
            .body("jsva");
  }

  @GetMapping("/test5/{cateCode}")
  public ResponseEntity<?> test5(@PathVariable("cateCode") int cateCode){
    try {
      //예외가 날 수 있는 코드 작성
      BookCategoryDTO resultDTO = bookService.getCategoryByCateCode(cateCode);

      return ResponseEntity
              .status(resultDTO == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
              .body(resultDTO == null ? "정보가 없습니다" : resultDTO);
    }catch (Exception e){
      //예외가 발생했을 때 실행할 코드
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("서버에서 오류 발생");
    }



//    //조회가 되지 않았을 때
//    if(resultDTO == null){
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("정보가 없습니다");
//    }
//    //조회가 잘 됐을 때
//    else {
//      return ResponseEntity.status(HttpStatus.OK).body(resultDTO);
//    }
  }

}
