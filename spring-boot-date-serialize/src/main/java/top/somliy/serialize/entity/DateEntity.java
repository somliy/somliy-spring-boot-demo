package top.somliy.serialize.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 类名： @ClassName DateEntity 序列化
 * 创建人：@author zhao dong
 * 类描述：@Description: 序列化
 * 创建时间: 2023/6/19 17:40
 */
@Data
@ToString
public class DateEntity {

    private String dateStr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date1;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date2;
}
