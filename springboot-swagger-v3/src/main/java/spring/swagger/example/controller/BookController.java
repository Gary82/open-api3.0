package spring.swagger.example.controller;

import io.swagger.annotations.*;
import spring.swagger.example.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Gary
 */
@Api(tags = "書本")
@RestController
@RequestMapping("book")
public class BookController {

    @ApiOperation(value = "查詢", notes = "所有資料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "書籍名稱", required = true),
            @ApiImplicitParam(name = "price", value = "金額", required = false)
    })
    @GetMapping(value = "/{name}")
    public ResponseEntity<Book> findUser(@PathVariable(value = "name", required = true) String name,
                                         @RequestParam(value = "price", required = false) Integer price) {
        return ResponseEntity.ok(new Book(name,price, LocalDateTime.now()));
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "新增", notes = "新增資料")
    public ResponseEntity<String> addUser(@RequestBody Book book) {
        return ResponseEntity.ok(book.getName() + " created");
    }

    @PutMapping(value = "/")
    @ApiOperation(value = "更新", notes = "更新資料")
    public ResponseEntity<Book> modifyUser(@RequestBody Book book) {
        return ResponseEntity.ok(book);
    }

    @DeleteMapping(value = "/{name}")
    @ApiOperation(value = "刪除", notes = "刪除資料")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(name + " deleted");
    }

}
