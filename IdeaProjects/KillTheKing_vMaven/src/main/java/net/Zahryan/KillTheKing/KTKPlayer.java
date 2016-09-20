package net.Zahryan.KillTheKing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * Created by USER on 17/09/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class KTKPlayer {

    /*---Variables---*/
    private final ScoreboardManager sManager = Bukkit.getScoreboardManager();
    private final Scoreboard board = sManager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("The ShootCraft", "dummy");
    private int kills = 0;
    private Score sKills = objective.getScore(ChatColor.DARK_AQUA + "Kills: " + kills);
    private Score sTimeLeft = objective.getScore("Temps : Attente");
    private boolean king = false;
	/*---------------*/


    /*---Constructeur---*/
    public KTKPlayer(Player p){
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Kill The King");
        objective.getScore(ChatColor.DARK_AQUA+"").setScore(5);
        sTimeLeft.setScore(4);
        objective.getScore(ChatColor.BLACK+"").setScore(3);
        sKills.setScore(2);
        objective.getScore("§f").setScore(1);
        p.setScoreboard(board);
    }
	/*------------------*/

    /*---Méthodes---*/
    public void kill(){
        this.kills++;
        this.board.resetScores(this.sKills.getEntry());
        this.sKills= this.objective.getScore(ChatColor.DARK_AQUA + "Kills: " + kills);
        this.sKills.setScore(2);
    }

    public void setKing(){
        this.king= true;
    }

    public boolean isKing(){
        return this.king;
    }

    public void updateTimer(int timer) {
        board.resetScores(sTimeLeft.getEntry());
        int minutes = timer/60;
        int dizainesSecondes = (timer - minutes * 60)/10;
        int uniteSeconde = (timer - minutes * 60)%10;
        sTimeLeft = objective.getScore("Temps : " + minutes+":"+dizainesSecondes+uniteSeconde);
        sTimeLeft.setScore(4);
    }
	/*--------------*/

}
