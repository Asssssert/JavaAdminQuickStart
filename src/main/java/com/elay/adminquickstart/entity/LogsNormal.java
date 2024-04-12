package com.elay.adminquickstart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表 - 正常操作日志
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_logs_normal")
public class LogsNormal implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "logs_id", type = IdType.AUTO)
    private Integer logsId;

    /**
     * 操作用户ID
     */
    private Integer userId;

    /**
     * 接口
     */
    private String action;

    private String details;

    /**
     * 操作时间
     */
    private Date logTime;


}
