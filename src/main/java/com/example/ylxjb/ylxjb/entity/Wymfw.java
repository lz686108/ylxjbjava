package com.example.ylxjb.ylxjb.entity;

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
 * @since 2022-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Wymfw implements Serializable {

    private static final long serialVersionUID = 1L;

    private String items;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("Ad")
    private String ad;

    @TableField("St_d")
    private String stD;

    @TableField("Qgr_d")
    private String qgrD;

    @TableField("Vdaf")
    private String vdaf;

    @TableField("FCd")
    private String fcd;

    @TableField("Mt")
    private String mt;

    @TableField("Mad")
    private String mad;

    @TableField("KO")
    private String ko;

    @TableField("NaO")
    private String nao;

    @TableField("ZnO")
    private String zno;


}
