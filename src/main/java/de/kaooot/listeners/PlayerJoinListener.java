package de.kaooot.listeners;

import de.kaooot.Coinsystem;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements EventListener {

    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent event ) {
        EntityPlayer player = event.getPlayer();

        if( ! Coinsystem.getInstance().getCoinAPI().isUserExists( player.getUUID() ) ) {
            Coinsystem.getInstance().getCoinAPI().setPlayer( player.getUUID(), player.getName(), 0 );
        }
    }
}
