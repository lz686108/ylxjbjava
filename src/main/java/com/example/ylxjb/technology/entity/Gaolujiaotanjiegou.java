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
public class Gaolujiaotanjiegou implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String process;

    private String jiaotanCompany;

    private String proportion;

    private String riqi;

    private String plan;

    private String state;


}
