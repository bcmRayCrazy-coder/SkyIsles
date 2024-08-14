package site.hjfunny.skyisles.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player)){
            commandSender.sendMessage("只有玩家可执行");
            return true;
        }
        if (Objects.equals(player.getWorld().getName(), "world")) {
            player.teleport(Objects.requireNonNull(Bukkit.getWorld("game")).getSpawnLocation());
        }else {
            player.sendMessage(Component.text("无法加入游戏").color(TextColor.color(0xff0000)));
        }
        return true;
    }
}
