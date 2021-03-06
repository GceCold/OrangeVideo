package ltd.icecold.orange.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import ltd.icecold.orange.OrangeVideo;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public class PacketHandle {
    private static final int IDX = 6666;

    /**
     * Compatible with high version forge to send information
     *
     * @param player Player
     * @param msg    Message
     */
    public static void send(Player player, String msg) {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = Unpooled.buffer(bytes.length + 1);
        buf.writeByte(IDX);
        buf.writeBytes(bytes);
        player.sendPluginMessage(OrangeVideo.getInstance(), OrangeVideo.CHANNEL, buf.array());
    }

    /**
     * Compatible with high version forge to read information
     *
     * @param array Data
     */
    public static String read(byte[] array) {
        ByteBuf buf = Unpooled.wrappedBuffer(array);
        if (buf.readUnsignedByte() == IDX) {
            return buf.toString(StandardCharsets.UTF_8);
        } else {
            return new String(array, StandardCharsets.UTF_8);
        }
    }
}
