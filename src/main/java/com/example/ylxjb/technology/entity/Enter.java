package com.example.ylxjb.technology.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lizhen
 * @since 2021-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Enter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String category;

    private String process;

    private String name;

    private String number;

    private String specification;

    private String item;

    private String price;

    private String mark;

    private String date;

    private String class1;


}
