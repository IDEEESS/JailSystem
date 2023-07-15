package fr.ideeess.main;
import fr.ideeess.commands.SetCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;


public class JailSystem extends JavaPlugin{

    public static final String WORLDJAILNAME = new JailSystem().getConfig().getString("setup.worldJailName");
    public static final String JAILCOMMANDPERMISSION = new JailSystem().getConfig().getString("setup.permissions.jailCommandPermission");
    public static final String UNJAILCOMMANDPERMISSION = new JailSystem().getConfig().getString("setup.permissions.unjailCommandPermission");
    public static final String JAILBYPASSPERMISSION = new JailSystem().getConfig().getString("setup.permissions.bypassJail");
    public static int JAILSPAWNX;
    public static int JAILSPAWNY;
    public static int JAILSPAWNZ;
    public static boolean DEFINEJAILSPAWN = false;
    public static boolean DEFINEJAILZONE = false;
    public static int JAILZONEX;
    public static int JAILZONEY;
    public static int JAILZONEZ;
    public static int JAILZONEX1;
    public static int JAILZONEY1;
    public static int JAILZONEZ1;
    public static Location JAILSPAWNLOCATION;
    public static int JAILEXITSPAWNX;
    public static int JAILEXITSPAWNY;
    public static int JAILEXITSPAWNZ;
    public static boolean DEFINEJAILEXITSPAWN = false;
    public static Location JAILEXITLOCATION;
    public static int BLOCK1X;
    public static int BLOCK1Y;
    public static int BLOCK1Z;
    public static int BLOCK2X;
    public static int BLOCK2Y;
    public static int BLOCK2Z;
    public static int BLOCK3X;
    public static int BLOCK3Y;
    public static int BLOCK3Z;
    public static boolean DEFINEBLOCKBREAKPOINT1 = false;
    public static boolean DEFINEBLOCKBREAKPOINT2 = false;
    public static boolean DEFINEBLOCKBREAKPOINT3 = false;

    @Override
    public void onEnable() {

        super.onEnable();

        System.out.println("This plugin has been enabled");

        //Config
        this.saveConfig();
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        // Vérifications
        if (!Bukkit.getWorlds().contains(WORLDJAILNAME)){
            this.getServer().getPluginManager().disablePlugin(this);
            System.out.println("Le monde \""+WORLDJAILNAME+"\" est introuvable . Veuillez modifier le nom du monde dans le fichier config.yml du plugin ou créer le monde.");
        }

        if (!(getConfig().get("setup.jailSpawnLocation.x") instanceof Integer && getConfig().get("setup.jailSpawnLocation.y") instanceof Integer && getConfig().get("setup.jailSpawnLocation.z") instanceof Integer)){
            Bukkit.getLogger().severe("Merci de définir le point de spawn dans la prison");
            DEFINEJAILSPAWN = true;
        }else {
            JAILSPAWNX = (int) getConfig().get("setup.jailSpawnLocation.x");
            JAILSPAWNY = (int) getConfig().get("setup.jailSpawnLocation.y");
            JAILSPAWNZ = (int) getConfig().get("setup.jailSpawnLocation.z");
        }

        if (!(getConfig().get("setup.jail.x") instanceof Integer && getConfig().get("setup.jail.y") instanceof Integer && getConfig().get("setup.jail.z") instanceof Integer && getConfig().get("setup.jail.x1") instanceof Integer && getConfig().get("setup.jail.y2") instanceof Integer && getConfig().get("setup.jail.z2") instanceof Integer)){
            Bukkit.getLogger().severe("Merci de définir la zone de la prison");
            DEFINEJAILZONE = true;
        }else{
            JAILZONEX = (int) getConfig().get("setup.jail.x");
            JAILZONEY = (int) getConfig().get("setup.jail.y");
            JAILZONEZ = (int) getConfig().get("setup.jail.z");
            JAILZONEX1 = (int) getConfig().get("setup.jail.x1");
            JAILZONEY1 = (int) getConfig().get("setup.jail.y1");
            JAILZONEZ1 = (int) getConfig().get("setup.jail.z1");
        }

        if (!DEFINEJAILSPAWN){
            JAILSPAWNLOCATION = new Location(Bukkit.getWorld(WORLDJAILNAME),JAILSPAWNX , JAILSPAWNY , JAILSPAWNZ);
        }

        if (!(getConfig().get("setup.jailexit.x") instanceof Integer && getConfig().get("setup.jailexit.y") instanceof Integer && getConfig().get("setup.jailexit.z") instanceof Integer)){
            Bukkit.getLogger().severe("Merci de définir le point de spawn de la sortie de prison");
            DEFINEJAILEXITSPAWN = true;
        }else {
            JAILEXITSPAWNX = (int) getConfig().get("setup.jailexit.x");
            JAILEXITSPAWNY = (int) getConfig().get("setup.jailexit.y");
            JAILEXITSPAWNZ = (int) getConfig().get("setup.jailexit.z");
        }

        if(!DEFINEJAILEXITSPAWN){
            JAILEXITLOCATION = new Location(Bukkit.getWorld(WORLDJAILNAME),JAILEXITSPAWNX , JAILEXITSPAWNY , JAILEXITSPAWNZ);
        }

        if (!(getConfig().get("setup.block1.x") instanceof Integer && getConfig().get("setup.block1.y") instanceof Integer && getConfig().get("setup.block1.z") instanceof Integer && getConfig().get("setup.block2.x") instanceof Integer && getConfig().get("setup.block2.y") instanceof Integer && getConfig().get("setup.block2.z") instanceof Integer && getConfig().get("setup.block3.x") instanceof Integer && getConfig().get("setup.block3.y") instanceof Integer && getConfig().get("setup.block3.z") instanceof Integer)){
            Bukkit.getLogger().severe("Merci de définir les points de spawn des blocs");
            DEFINEBLOCKBREAKPOINT1 = true;
            DEFINEBLOCKBREAKPOINT2 = true;
            DEFINEBLOCKBREAKPOINT3 = true;
        }else{
            BLOCK1X = (int) getConfig().get("setup.block1.x");
            BLOCK1Y = (int) getConfig().get("setup.block1.y");
            BLOCK1Z = (int) getConfig().get("setup.block1.z");
            BLOCK2X = (int) getConfig().get("setup.block2.x");
            BLOCK2Y = (int) getConfig().get("setup.block2.y");
            BLOCK2Z = (int) getConfig().get("setup.block2.z");
            BLOCK3X = (int) getConfig().get("setup.block3.x");
            BLOCK3Y = (int) getConfig().get("setup.block3.y");
            BLOCK3Z = (int) getConfig().get("setup.block3.z");

        }

        //Commandes
        getCommand("setjail").setExecutor(new SetCommand(this));

        //Tabcomplete
        getCommand("setjail").setTabCompleter(new SetCommand(this));

    }

    @Override
    public void onDisable() {
        super.onDisable();

        System.out.println("This plugin has been disabled");

        saveConfig();
    }
}
