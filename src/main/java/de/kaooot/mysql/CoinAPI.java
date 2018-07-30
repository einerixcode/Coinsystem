package de.kaooot.mysql;

import de.kaooot.Coinsystem;
import io.gomint.entity.EntityPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinAPI {

    public boolean isUserExists( UUID uuid ) {
        try {
            PreparedStatement ps = Coinsystem.getInstance().getMySQL().connection.prepareStatement( "SELECT coins FROM coinsystem WHERE UUID = ?" );
            ps.setString( 1, uuid.toString() );

            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch( SQLException e ) {
            e.printStackTrace();
            return false;
        }
    }

    public void setPlayer( UUID uuid, String name, Integer coins ) {
        try {
            PreparedStatement ps = Coinsystem.getInstance().getMySQL().connection.prepareStatement( "INSERT INTO coinsystem(UUID, name, coins) VALUES(?,?,?)" );
            ps.setString( 1, uuid.toString() );
            ps.setString( 2, name );
            ps.setInt( 3, coins );
            ps.executeUpdate();
        } catch( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void setCoins( String name, Integer coins ) {
        try {
            PreparedStatement ps = Coinsystem.getInstance().getMySQL().connection.prepareStatement( "UPDATE coinsystem SET coins = ? WHERE name = ?" );
            ps.setInt( 1, coins );
            ps.setString( 2, name );
            ps.executeUpdate();
        } catch( SQLException e ) {
            e.printStackTrace();
        }
    }

    public Integer getCoins( EntityPlayer player ) {
        UUID uuid = player.getUUID();
        Integer coins = 0;

        try {
            PreparedStatement ps = Coinsystem.getInstance().getMySQL().connection.prepareStatement( "SELECT coins FROM coinsystem WHERE UUID = ?" );
            ps.setString( 1, uuid.toString() );

            ResultSet rs = ps.executeQuery();

            if( rs.next() ) {
                return rs.getInt( "coins" );
            }
        } catch( SQLException e ) {
            e.printStackTrace();
        }
        return coins;
    }

    public void addCoins( EntityPlayer player, Integer coins ) {
        UUID uuid = player.getUUID();

        if( isUserExists( uuid ) ) {

            setCoins( player.getName(), getCoins( player ) + coins );
        } else {
            setPlayer( uuid, player.getName(), 0 );
            addCoins( player, coins );
        }
    }

    public void removeCoins( EntityPlayer player, Integer coins ) {
        UUID uuid = player.getUUID();

        if( isUserExists( uuid ) ) {

            setCoins( player.getName(), getCoins( player ) - coins );
        } else {
            setPlayer( uuid, player.getName(), 0 );
            removeCoins( player, coins );
        }
    }
}
