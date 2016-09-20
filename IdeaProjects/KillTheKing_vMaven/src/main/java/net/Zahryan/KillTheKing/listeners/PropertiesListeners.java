package net.Zahryan.KillTheKing.listeners;

import net.Zahryan.KillTheKing.KillTheKing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by USER on 17/09/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class PropertiesListeners implements Listener{

    /*---Variables---*/
    private final KillTheKing ktk;
	/*---------------*/

    /*---Constructeur---*/
    public PropertiesListeners(KillTheKing killTheKing) {
        this.ktk=killTheKing;
    }
	/*------------------*/

    /*---Méthodes---*/
    //Pas de perte de nourriture (a voir a optimiser en parametrable)
    @EventHandler
    public void foodLoss(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
    //Pas de detruction de blocs (a voir a optimiser en parametrable)
    @EventHandler
    public void noGrief(BlockBreakEvent e){
        e.setCancelled(true);
    }

    //Interdiction de poser des blocs
    @EventHandler
    public void noBuild(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    //Pas de drop d'items (a voir a optimiser en parametrable)
    @EventHandler
    public void denyDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    //Pas de destruction d'items (a voir a optimiser en parametrable)
    @EventHandler
    public void denyBreak(PlayerItemDamageEvent e){
        e.setCancelled(true);
    }

    //Pas de degats sur les items (a voir a optimiser en parametrable)
    @EventHandler
    public void denyConsume(PlayerItemConsumeEvent e){
        e.setCancelled(true);
    }

    //Le temps ne change pas ?
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
	/*--------------*/

}
