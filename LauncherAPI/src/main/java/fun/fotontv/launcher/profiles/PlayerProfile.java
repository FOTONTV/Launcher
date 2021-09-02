package fun.fotontv.launcher.profiles;

import fun.fotontv.launcher.serialize.HInput;
import fun.fotontv.launcher.serialize.HOutput;
import fun.fotontv.launcher.serialize.stream.StreamObject;
import fun.fotontv.utils.helper.IOHelper;
import fun.fotontv.utils.helper.VerifyHelper;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public final class PlayerProfile extends StreamObject {
    public final UUID uuid;
    public final String username;
    public final String email;
    public final int globalMoney;
    public final int gameMoney;
    public final int xp;
    public final int rep;
    public final int rang;
    public final String refCode;
    public final Texture skin, cloak;

    public PlayerProfile(HInput input) throws IOException {
        uuid = input.readUUID();
        username = VerifyHelper.verifyUsername(input.readString(64));
        email = VerifyHelper.verifyEmail(input.readString(64));
        globalMoney = input.readInt();
        gameMoney = input.readInt();
        xp = input.readInt();
        rep = input.readInt();
        rang = input.readInt();
        refCode = input.readString(64);
        skin = input.readBoolean() ? new Texture(input) : null;
        cloak = input.readBoolean() ? new Texture(input) : null;
    }

    public PlayerProfile(UUID uuid, String username, String email, int globalMoney, int gameMoney,
                         int xp, int rep, int rang, String refCode, Texture skin, Texture cloak) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
        this.username = VerifyHelper.verifyUsername(username);
        this.email = VerifyHelper.verifyEmail(email);
        this.globalMoney = globalMoney;
        this.gameMoney = gameMoney;
        this.xp = xp;
        this.rep = rep;
        this.rang = rang;
        this.refCode = refCode;
        this.skin = skin;
        this.cloak = cloak;
    }

    public static PlayerProfile newOfflineProfile(String username) {
        return new PlayerProfile(offlineUUID(username), username, "test@fotontv.fun", 0, 0, 0, 0, 1, "test", null, null);
    }

    public static UUID offlineUUID(String username) {
        return UUID.nameUUIDFromBytes(IOHelper.encodeASCII("OfflinePlayer:" + username));
    }

    @Override
    public void write(HOutput output) throws IOException {
        output.writeUUID(uuid);
        output.writeString(username, 64);
        output.writeString(email, 64);
        output.writeInt(globalMoney);
        output.writeInt(gameMoney);
        output.writeInt(xp);
        output.writeInt(rep);
        output.writeInt(rang);
        output.writeString(refCode, 64);

        // Write textures
        output.writeBoolean(skin != null);
        if (skin != null)
            skin.write(output);
        output.writeBoolean(cloak != null);
        if (cloak != null)
            cloak.write(output);
    }
}
