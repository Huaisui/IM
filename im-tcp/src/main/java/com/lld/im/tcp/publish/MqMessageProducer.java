package com.lld.im.tcp.publish;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lld.im.codec.proto.Message;
import com.lld.im.codec.proto.MessageHeader;
import com.lld.im.common.constant.Constants;
import com.lld.im.common.enums.command.CommandType;
import com.lld.im.tcp.utils.MqFactory;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 投递消息到逻辑层
 * @author: teo
 * @version: 1.0
 */
@Slf4j
public class MqMessageProducer {

    public static void sendMessage(Message message, Integer command) {
        Channel channel = null;
        String channelName = chooseMQChannelName(command);
        try {
            channel = MqFactory.getChannel(channelName);

            JSONObject o = (JSONObject) JSON.toJSON(message.getMessagePack());
            o.put("command", command);
            o.put("clientType", message.getMessageHeader().getClientType());
            o.put("imei", message.getMessageHeader().getImei());
            o.put("appId", message.getMessageHeader().getAppId());
            channel.basicPublish(channelName, "", null, o.toJSONString().getBytes());
        } catch (Exception e) {
            log.error("发送消息出现异常：{}", e.getMessage());
        }
    }

    public static void sendMessage(Object message, MessageHeader header, Integer command) {
        Channel channel = null;
        String channelName = chooseMQChannelName(command);
        try {
            channel = MqFactory.getChannel(channelName);

            JSONObject o = (JSONObject) JSON.toJSON(message);
            o.put("command", command);
            o.put("clientType", header.getClientType());
            o.put("imei", header.getImei());
            o.put("appId", header.getAppId());
            channel.basicPublish(channelName, "", null, o.toJSONString().getBytes());
        } catch (Exception e) {
            log.error("发送消息出现异常：{}", e.getMessage());
        }
    }

    private static String chooseMQChannelName(Integer command) {
        String com = command.toString();
        String commandSub = com.substring(0, 1);
        CommandType commandType = CommandType.getCommandType(commandSub);
        if (commandType == CommandType.MESSAGE) {
            return Constants.RabbitConstants.IM_TO_MESSAGE_SERVICE;
        } else if (commandType == CommandType.GROUP) {
            return Constants.RabbitConstants.IM_TO_GRPUP_SERVICE;
        } else if (commandType == CommandType.FRIEND) {
            return Constants.RabbitConstants.IM_TO_FRIENDSHIP_SERVICE;
        } else if (commandType == CommandType.USER) {
            return Constants.RabbitConstants.IM_TO_USER_SERVICE;
        }
        return "";
    }

}
