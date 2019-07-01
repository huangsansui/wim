package common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.HashMap;
import java.util.Map;


/**
 * Created on 2019/6/28
 *
 * @author qing.huang
 */
public class SessionUtil {

    private static Map<String, Channel> map = new HashMap<>();

    private static Map<String, ChannelGroup> groupMap = new HashMap<>();

    public static void bindSession(Session session, Channel channel) {
        if (!map.containsKey(session.getUserId())) {
            map.put(session.getUserId(), channel);
            channel.attr(Attribute.SESSION).set(session);
        }
    }

    public static void unbindSession(Channel channel) {
        if (!hashLogin(channel)) {
            map.remove(getSession(channel));
        }
    }

    public static boolean hashLogin(Channel channel) {
       return channel.hasAttr(Attribute.SESSION);
    }

    public static void markLogin(Channel channel, Session session) {
        channel.attr(Attribute.SESSION).set(session);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attribute.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return map.get(userId);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupMap.get(groupId);
    }

    public static void addChanelGroup(String groupId, ChannelGroup channelGroup) {
        groupMap.put(groupId, channelGroup);
    }

}
