package com.lld.im.common.constant;

/**
 * @description:
 * @author: teo
 *  
 */
public class Constants {

    /** channel绑定的userId Key*/
    public static final String USER_ID = "userId";

    /** channel绑定的appId */
    public static final String APP_ID = "appId";

    public static final String CLIENT_TYPE = "clientType";

    public static final String IMEI = "imei";

    /** channel绑定的clientType 和 imel Key*/
    public static final String CLIENT_IMEI = "clientImei";

    public static final String READ_TIME = "readTime";

    public static final String IM_CORE_ZK_ROOT = "/im-coreRoot";

    public static final String IM_CORE_ZK_ROOT_TCP = "/tcp";

    public static final String IM_CORE_ZK_ROOT_WEB = "/web";


    public static class RedisConstants{

        /**
         * userSign，格式：appId:userSign:
         */
        public static final String USER_SIGN = "userSign";

        /**
         * 用户上线通知channel
         */
        public static final String USER_LOGIN_CHANNEL
                = "signal/channel/LOGIN_USER_INNER_QUEUE";


        /**
         * 用户session，appId + UserSessionConstants + 用户id 例如10000：userSession：lld
         */
        public static final String USER_SESSION_CONSTANTS = ":userSession:";

        /**
         * 缓存客户端消息防重，格式： appId + :cacheMessage: + messageId
         */
        public static final String CACHE_MESSAGE = "cacheMessage";

        public static final String OFFLINE_MESSAGE = "offlineMessage";

        /**
         * seq 前缀
         */
        public static final String SEQ_PREFIX = "seq";

        /**
         * 用户订阅列表，格式 ：appId + :subscribe: + userId。Hash结构，filed为订阅自己的人
         */
        public static final String SUBSCRIBE = "subscribe";

        /**
         * 用户自定义在线状态，格式 ：appId + :userCustomerStatus: + userId。set，value为用户id
         */
        public static final String USER_CUSTOMER_STATUS = "userCustomerStatus";

    }

    public static class RabbitConstants{

        public static final String IM_TO_USER_SERVICE = "pipeline2UserService";

        public static final String IM_TO_MESSAGE_SERVICE = "pipeline2MessageService";

        public static final String IM_TO_GRPUP_SERVICE = "pipeline2GroupService";

        public static final String IM_TO_FRIENDSHIP_SERVICE = "pipeline2FriendshipService";

        public static final String MESSAGE_SERVICE_TO_IM = "messageService2Pipeline";

        public static final String GROUP_SERVICE_TO_IM = "GroupService2Pipeline";

        public static final String FRIENDSHIP_TO_IM = "friendShip2Pipeline";

        public static final String STORE_P2P_MESSAGE = "storeP2PMessage";

        public static final String STORE_GROUP_MESSAGE = "storeGroupMessage";


    }

    public static class CallbackCommand{
        public static final String ModifyUserAfter = "user.modify.after";

        public static final String CreateGroupAfter = "group.create.after";

        public static final String UpdateGroupAfter = "group.update.after";

        public static final String DestoryGroupAfter = "group.destory.after";

        public static final String TransferGroupAfter = "group.transfer.after";

        public static final String GroupMemberAddBefore = "group.member.add.before";

        public static final String GroupMemberAddAfter = "group.member.add.after";

        public static final String GroupMemberDeleteAfter = "group.member.delete.after";

        public static final String AddFriendBefore = "friend.add.before";

        public static final String AddFriendAfter = "friend.add.after";

        public static final String UpdateFriendBefore = "friend.update.before";

        public static final String UpdateFriendAfter = "friend.update.after";

        public static final String DeleteFriendAfter = "friend.delete.after";

        public static final String AddBlackAfter = "black.add.after";

        public static final String DeleteBlack = "black.delete";

        public static final String SendMessageAfter = "message.send.after";

        public static final String SendMessageBefore = "message.send.before";

    }

    public static class SeqConstants {
        public static final String Message = "messageSeq";

        public static final String GroupMessage = "groupMessageSeq";


        public static final String Friendship = "friendshipSeq";

//        public static final String FriendshipBlack = "friendshipBlackSeq";

        public static final String FriendshipRequest = "friendshipRequestSeq";

        public static final String FriendshipGroup = "friendshipGrouptSeq";

        public static final String Group = "groupSeq";

        public static final String Conversation = "conversationSeq";

    }

}
