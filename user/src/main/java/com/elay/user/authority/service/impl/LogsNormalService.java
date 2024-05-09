package com.elay.user.authority.service.impl;

import com.elay.user.authority.service.ILogsNormalService;
import com.elay.user.authority.entity.LogsNormal;
import com.elay.user.authority.mapper.LogsNormalMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 - 正常操作日志 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class LogsNormalService extends ServiceImpl<LogsNormalMapper, LogsNormal> implements ILogsNormalService {

}
