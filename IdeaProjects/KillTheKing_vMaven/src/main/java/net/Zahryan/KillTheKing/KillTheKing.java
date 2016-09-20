package net.Zahryan.KillTheKing;

import net.Zahryan.KillTheKing.game.GameManager;
import net.Zahryan.KillTheKing.game.GameState;
import net.Zahryan.KillTheKing.listeners.JoinQuitListeners;
import net.Zahryan.KillTheKing.listeners.PropertiesListeners;
import net.Zahryan.KillTheKing.listeners.PvPListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

// import : alt entree
// switch variable : shift f6
// https://github.com/Sentrance/MiniShootCraft

/**
 * Created by USER on 17/09/2016.
 */

@SuppressWarnings("DefaultFileTemplate")
public class KillTheKing extends JavaPlugin implements Runnable{

    /*---Variables---*/
    public final HashMap<UUID, KTKPlayer> playerData = new HashMap<UUID, KTKPlayer>();
    public final ArrayList<UUID> equipeRouge = new ArrayList<UUID>();
    public final ArrayList<UUID> equipeBleue = new ArrayList<UUID>();
    private int preGameTimer = 30;
    private int gameTimer = 300;
	/*---------------*/

    /*---Constructeur---*/
    public KillTheKing(){}
	/*------------------*/

    /*---Méthodes---*/
    public void onEnable() {
        super.onEnable();

        Bukkit.broadcastMessage("[KillTheKing]Enabling...");

        //Stockage des coordonnées du spawn de l'équipe rouge dans un fichier de config
        this.getConfig().addDefault("Spawn Rouge X", 5.0F);
        this.getConfig().addDefault("Spawn Rouge Y", 5.0F);
        this.getConfig().addDefault("Spawn Rouge Z", 5.0F);

        //Stockage des coordonnées du spawn de l'équipe bleu dans un fichier de config
        this.getConfig().addDefault("Spawn Bleu X", 10.0F);
        this.getConfig().addDefault("Spawn Bleu Y", 10.0F);
        this.getConfig().addDefault("Spawn Bleu Z", 10.0F);

        //Stockage de la taille des équipes dans un fichier de config
        this.getConfig().addDefault("Taille des equipes", 10);

        //Sauvegarde du fichier de config
        this.getConfig().options().copyDefaults(true);
        saveConfig();

        GameState.setState(GameState.EN_ATTENTE);

        Bukkit.getWorld("world").setPVP(false);

        getServer().getPluginManager().registerEvents(new JoinQuitListeners(this), this);
        getServer().getPluginManager().registerEvents(new PropertiesListeners(this), this);
        getServer().getPluginManager().registerEvents(new PvPListeners(this), this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, this, 0, 20);

        Bukkit.broadcastMessage("[KillTheKing]Enabling : done.");
    }

        public void onDisable(){

            Bukkit.broadcastMessage("[KillTheKing]Disabling...");

            super.onDisable();

            Bukkit.broadcastMessage("[KillTheKing]Disabling : done.");

        }

    public void run() {
        int currentTimer = 0;
        if (GameState.isState(GameState.EN_ATTENTE)) {
            preGameTimer--;
            currentTimer = preGameTimer;
            if (preGameTimer <= 0) {
                GameState.setState(GameState.EN_JEU);
                GameManager.startGame(KillTheKing.this);
            }
        }

        if (GameState.isState(GameState.EN_JEU)) {
            gameTimer--;
            currentTimer = gameTimer;
            if (gameTimer <= 0) {
                GameState.setState(GameState.FIN);
                GameManager.endGame(KillTheKing.this);
                // Cancel task Bukkit.getScheduler().;
                Bukkit.getScheduler().cancelTasks(KillTheKing.this);
            }
        }

        for (KTKPlayer p : playerData.values()) {
            p.updateTimer(currentTimer);
        }
    }
    /*--------------*/

}
