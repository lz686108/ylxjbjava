package com.example.ylxjb.technology.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class CEnter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String factory;

    private String abbreviation;

    private String class1;

    private String time;

    @TableField("H2O")
    private String h2o;

    private String username;

    private String date;


}
