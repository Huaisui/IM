package com.lld.im.tcp.utils;

import com.alibaba.fastjson.JSONObject;
import com.lld.im.codec.pack.user.UserStatusChangeNotifyPack;
import com.lld.im.codec.proto.MessageHeader;
import com.lld.im.common.constant.Constants;
import com.lld.im.common.enums.ImConnectStatusEnum;
import com.lld.im.common.enums.command.UserEventCommand;
import com.lld.im.common.model.UserClientDto;
import com.lld.im.common.model.UserSession;
import com.lld.im.tcp.publish.MqMessageProducer;
import com.lld.im.tcp.redis.RedisManager;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 以 Dto 为 key 存放 NioSocketChannel
 * @author: teo
 *  
 */
public class SessionSocketHolder {

    private static final Map<UserClientDto, NioSocketChannel> CHANNELS = new ConcurrentHashMap<>();

    public static void put(Integer appId, String userId, Integer clientType, String imei, NioSocketChannel channel) {
        UserClientDto dto = new UserClientDto();
        dto.setImei(imei);
        dto.setAppId(appId);
        dto.setClientType(clientType);
        dto.setUserId(userId);
        CHANNELS.put(dto, channel);
    }

    public static NioSocketChannel get(Integer appId, String userId, Integer clientType, String imei) {
        UserClientDto dto = new UserClientDto();
        dto.setImei(imei);
        dto.setAppId(appId);
        dto.setClientType(clientType);
        dto.setUserId(userId);
        return CHANNELS.get(dto);
    }

    public static List<NioSocketChannel> get(Integer appId, String id) {

        Set<UserClientDto> channelInfos = CHANNELS.keySet();
        List<NioSocketChannel> channels = new ArrayList<>();

        channelInfos.forEach(channel -> {
            if (channel.getAppId().equals(appId) && id.equals(channel.getUserId())) {
                channels.add(CHANNELS.get(channel));
            }
        });

        return channels;
    }

    public static void remove(Integer appId, String userId, Integer clientType, String imei) {
        UserClientDto dto = new UserClientDto();
        dto.setAppId(appId);
        dto.setImei(imei);
        dto.setClientType(clientType);
        dto.setUserId(userId);
        CHANNELS.remove(dto);
    }

    public static void remove(NioSocketChannel channel) {
        CHANNELS.entrySet().stream().filter(entity -> entity.getValue() == channel)
                .forEach(entry -> CHANNELS.remove(entry.getKey()));
    }

    /**
     * 登出
     * */
    public static void removeUserSession(NioSocketChannel nioSocketChannel) {
        String userId = (String) nioSocketChannel.attr(AttributeKey.valueOf(Constants.USER_ID)).get();
        Integer appId = (Integer) nioSocketChannel.attr(AttributeKey.valueOf(Constants.APP_ID)).get();
        Integer clientType = (Integer) nioSocketChannel.attr(AttributeKey.valueOf(Constants.CLIENT_TYPE)).get();
        String imei = (String) nioSocketChannel.attr(AttributeKey.valueOf(Constants.IMEI)).get();

        SessionSocketHolder.remove(appId, userId, clientType, imei);
        RedissonClient redissonClient = RedisManager.getRedissonClient();
        RMap<Object, Object> map = redissonClient.getMap(appId + Constants.RedisConstants.USER_SESSION_CONSTANTS + userId);
        map.remove(clientType + ":" + imei);

        MessageHeader messageHeader = createMessageHeader(appId, imei, clientType);
        UserStatusChangeNotifyPack userStatusChangeNotifyPack = createUserStatusChangeNotifyPack(appId, userId, ImConnectStatusEnum.OFFLINE_STATUS.getCode());
        MqMessageProducer.sendMessage(userStatusChangeNotifyPack, messageHeader, UserEventCommand.USER_ONLINE_STATUS_CHANGE.getCommand());

        nioSocketChannel.close();
    }

    /**
     * 离线
     * */
    public static void offlineUserSession(NioSocketChannel nioSocketChannel) {
        String userId = (String) nioSocketChannel.attr(AttributeKey.valueOf(Constants.USER_ID)).get();
        Integer appId = (Integer) nioSocketChannel.attr(AttributeKey.valueOf(Constants.APP_ID)).get();
        Integer clientType = (Integer) nioSocketChannel.attr(AttributeKey.valueOf(Constants.CLIENT_TYPE)).get();
        String imei = (String) nioSocketChannel.attr(AttributeKey.valueOf(Constants.IMEI)).get();

        SessionSocketHolder.remove(appId, userId, clientType, imei);

        RedissonClient redissonClient = RedisManager.getRedissonClient();
        RMap<String, String> map = redissonClient.getMap(appId + Constants.RedisConstants.USER_SESSION_CONSTANTS + userId);
        String sessionStr = map.get(clientType.toString() + ":" + imei);
        if (!StringUtils.isBlank(sessionStr)) {
            UserSession userSession = JSONObject.parseObject(sessionStr, UserSession.class);
            userSession.setConnectState(ImConnectStatusEnum.OFFLINE_STATUS.getCode());
            map.put(clientType.toString() + ":" + imei, JSONObject.toJSONString(userSession));
        }

        MessageHeader messageHeader = createMessageHeader(appId, imei, clientType);
        UserStatusChangeNotifyPack userStatusChangeNotifyPack = createUserStatusChangeNotifyPack(appId, userId, ImConnectStatusEnum.OFFLINE_STATUS.getCode());
        MqMessageProducer.sendMessage(userStatusChangeNotifyPack, messageHeader, UserEventCommand.USER_ONLINE_STATUS_CHANGE.getCommand());

        nioSocketChannel.close();
    }

    private static UserStatusChangeNotifyPack createUserStatusChangeNotifyPack(Integer appId, String userId, Integer code) {
        UserStatusChangeNotifyPack userStatusChangeNotifyPack = new UserStatusChangeNotifyPack();
        userStatusChangeNotifyPack.setAppId(appId);
        userStatusChangeNotifyPack.setUserId(userId);
        userStatusChangeNotifyPack.setStatus(ImConnectStatusEnum.OFFLINE_STATUS.getCode());
        return userStatusChangeNotifyPack;
    }

    private static MessageHeader createMessageHeader(Integer appId, String imei, Integer clientType) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setAppId(appId);
        messageHeader.setImei(imei);
        messageHeader.setClientType(clientType);
        return messageHeader;
    }


}
