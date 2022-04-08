package com.example.ylxjb.ylxjb.entity;

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
 * @since 2022-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Nhxn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应名称
     */
    private String gysmc;

    /**
     * 料种
     */
    private String lx;

    /**
     * 可磨性
     */
    private String kmx;

    /**
     * 粘结性
     */
    private String zjx;

    /**
     * 燃烧性
     */
    private String rsx;

    /**
     * 反应性
     */
    private String fyx;

    /**
     * 流动性
     */
    private String ldx;

    /**
     * 喷流性
     */
    private String plx;

    /**
     * 灰熔点
     */
    private String hrd;

    /**
     * 着火点
     */
    private String zhd;


}
