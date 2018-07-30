package de.kaooot.mysql;

import de.kaooot.Coinsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = Coinsystem.getInstance().getMySQLConfig().getHost();
    private String port = Coinsystem.getInstance().getMySQLConfig().getPort();
    private String database = Coinsystem.getInstance().getMySQLConfig().getDatabase();
    private String username = Coinsystem.getInstance().getMySQLConfig().getUsername();
    private String password = Coinsystem.getInstance().getMySQLConfig().getPassword();
    public Connection connection;

    public MySQL() {
        if( ! isConnected() ) {
            try {
                Class.forName( "com.mysql.jdbc.Driver" );

                connection = DriverManager.getConnection( "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password );
                Coinsystem.getInstance().getLogger().info( "MySQL-Verbindung hergestellt!" );
            } catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if( isConnected() ) {
            try {
                connection.close();
                Coinsystem.getInstance().getLogger().info( "MySQL-Verbindung geschlossen!" );
            } catch( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    private boolean isConnected() {
        return (connection != null);
    }
}
