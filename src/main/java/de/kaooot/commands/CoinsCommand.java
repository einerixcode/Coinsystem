package de.kaooot.commands;

import de.kaooot.Coinsystem;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.PlayerCommandSender;
import io.gomint.command.annotation.Description;
import io.gomint.command.annotation.Name;
import io.gomint.entity.EntityPlayer;

import java.util.Map;

@Name( "coins" )
@Description( "Show your coins amount." )

public class CoinsCommand extends Command {

    @Override
    public CommandOutput execute( CommandSender commandSender, String s, Map<String, Object> map ) {
        CommandOutput output = new CommandOutput();

        if( commandSender instanceof PlayerCommandSender ) {
            EntityPlayer player = (EntityPlayer) commandSender;

            int coins = Coinsystem.getInstance().getCoinAPI().getCoins( player );

            if( coins != 1 ) {
                output.success( Coinsystem.getPrefix() + "You've actually §e" + coins + " §7coins." );
            } else {
                output.success( Coinsystem.getPrefix() + "You've actually §e" + coins + " §7coin." );
            }
        } else {
            output.fail( Coinsystem.getPrefix() + "§cYou must be a player!" );
        }
        return output;
    }
}
