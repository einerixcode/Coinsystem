package de.kaooot;

import de.kaooot.config.MySQLConfig;
import de.kaooot.listeners.PlayerJoinListener;
import de.kaooot.mysql.CoinAPI;
import de.kaooot.mysql.MySQL;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;
import lombok.Getter;

@PluginName( "Coinsystem" )
@Version( major = 1, minor = 0 )

public class Coinsystem extends Plugin {

    @Getter
    private static String prefix = "§7[§eCoinSystem§7] ";
    @Getter
    private static Coinsystem instance;
    @Getter
    private MySQLConfig mySQLConfig;
    @Getter
    private MySQL mySQL;
    @Getter
    private CoinAPI coinAPI;

    @Override
    public void onStartup() {
        instance = this;

        getLogger().info( getPrefix() + "§aThe plugin was enabled." );
    }

    @Override
    public void onInstall() {
        mySQLConfig = new MySQLConfig();
        mySQL = new MySQL();
        coinAPI = new CoinAPI();

        registerListener( new PlayerJoinListener() );
    }

    @Override
    public void onUninstall() {
        getMySQL().disconnect();
        getLogger().info( getPrefix() + "§cThe plugin was disabled." );
    }
}
