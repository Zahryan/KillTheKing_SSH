package net.Zahryan.KillTheKing.listeners;

import net.Zahryan.KillTheKing.KillTheKing;
import net.Zahryan.KillTheKing.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by USER on 17/09/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class PvPListeners implements Listener{

    /*---Variables---*/
    private final KillTheKing ktk;
	/*---------------*/

    /*---Constructeur---*/
    public PvPListeners(KillTheKing killTheKing) {
        this.ktk = killTheKing;
    }
	/*------------------*/

    /*---Méthodes---*/
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.getDrops().clear();
        Player p = e.getEntity();
        if(this.ktk.playerData.get(p.getUniqueId()).isKing()){
            GameManager.endGame(this.ktk, p);
        }else{
            Player killer = e.getEntity().getKiller();
            this.ktk.playerData.get(killer.getUniqueId()).kill();
        }
    }

    @EventHandler
    public void noFriendlyFire(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player p1 = (Player)e.getDamager();
            Player p2 = (Player)e.getEntity();
            if(this.ktk.equipeBleue.contains(p1.getUniqueId())&&this.ktk.equipeBleue.contains(p2.getUniqueId())||this.ktk.equipeRouge.contains(p1.getUniqueId())&&this.ktk.equipeRouge.contains(p2.getUniqueId())){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if(ktk.equipeBleue.contains(p.getUniqueId())){
            event.setRespawnLocation(new Location(Bukkit.getWorld("world"),ktk.getConfig().getInt("Spawn Bleu X"),ktk.getConfig().getInt("Spawn Bleu Y"),ktk.getConfig().getInt("Spawn Bleu Z")));
        }else{
            event.setRespawnLocation(new Location(Bukkit.getWorld("world"),ktk.getConfig().getInt("Spawn Rouge X"),ktk.getConfig().getInt("Spawn Rouge Y"),ktk.getConfig().getInt("Spawn Rouge Z")));
        }
    }
	/*--------------*/

}
