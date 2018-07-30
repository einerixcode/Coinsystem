package de.kaooot.commands;

import de.kaooot.Coinsystem;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.annotation.*;
import io.gomint.command.validator.IntegerValidator;
import io.gomint.command.validator.TargetValidator;
import io.gomint.entity.EntityPlayer;

import java.util.Map;

@Name( "addcoins" )
@Description( "Add coins to given player" )
@Permission( "coinsystem.manage" )
@Overload( {
    @Parameter( name = "player", validator = TargetValidator.class ),
    @Parameter( name = "coins", validator = IntegerValidator.class, arguments = ".*" )
} )

public class AddCoinsCommand extends Command {

    @Override
    public CommandOutput execute( CommandSender commandSender, String s, Map<String, Object> map ) {
        CommandOutput output = new CommandOutput();

        EntityPlayer target = (EntityPlayer) map.get( "player" );
        Integer coins = (Integer) map.get( "coins" );

        if( ! Integer.toString( coins ).startsWith( "-" ) ) {
            Coinsystem.getInstance().getCoinAPI().addCoins( target, coins );
            output.success( Coinsystem.getPrefix() + "Du hast dem Spieler §e" + target.getName() + " " + coins + " Coins §7hinzugefügt." );
        } else {
            output.fail( Coinsystem.getPrefix() + "Du kannst keinen negativen amount setzen!" );
        }

        return output;
    }
}
