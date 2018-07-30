package de.kaooot.config;

import io.gomint.config.Comment;
import io.gomint.config.InvalidConfigurationException;
import io.gomint.config.YamlConfig;

import java.io.File;

public class MySQLConfig extends YamlConfig {

    @Comment( "Please put in here your MySQL host" )
    private String host = "localhost";

    @Comment( "Please put in here your MySQL port" )
    private String port = "3306";

    @Comment( "Please put in here your MySQL database name" )
    private String database = "coinsystem";

    @Comment( "Please put in here your MySQL username" )
    private String username = "root";

    @Comment( "Please put in here your MySQL password" )
    private String password = "";

    public MySQLConfig() {
        try {
            this.init( new File( "plugins/Coinsystem", "mysql.yml" ) );
            saveConfig();
        } catch( InvalidConfigurationException e ) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public String getDatabase() {
        return this.database;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    private void saveConfig() {
        try {
            this.save( new File( "plugins/Coinsystem", "mysql.yml" ) );
        } catch( InvalidConfigurationException e ) {
            e.printStackTrace();
        }
    }
}
