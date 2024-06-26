package com.elay.user.authority.service.impl;

import com.elay.user.authority.service.ILogsExceptionService;
import com.elay.user.authority.entity.LogsException;
import com.elay.user.authority.mapper.LogsExceptionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 - 异常操作日志 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class LogsExceptionService extends ServiceImpl<LogsExceptionMapper, LogsException> implements ILogsExceptionService {

}
