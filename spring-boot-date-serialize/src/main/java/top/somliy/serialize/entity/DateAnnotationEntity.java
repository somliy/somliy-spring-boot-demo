package top.somliy.serialize.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 类名： @ClassName DateAnnotationEntity 注解
 * 创建人：@author zhao dong
 * 类描述：@Description: 注解
 * 创建时间: 2023/6/20 11:50
 */
@Data
@ToString
public class DateAnnotationEntity {

    private String dateStr;

    private Date date1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date2;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date3;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date4;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date5;
}
