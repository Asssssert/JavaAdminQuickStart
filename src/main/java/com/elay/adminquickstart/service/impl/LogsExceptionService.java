package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.LogsException;
import com.elay.adminquickstart.mapper.LogsExceptionMapper;
import com.elay.adminquickstart.service.ILogsExceptionService;
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
