package spring.swagger.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author Gary
 */
@Data
@ApiModel("書籍Bean")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @ApiModelProperty(value = "名稱", required = true,example = "小紅帽")
    private String name;

    @ApiModelProperty(value = "價格", required = true,example = "100")
    private Integer price;

    @ApiModelProperty(value = "上架日")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime date;
}
