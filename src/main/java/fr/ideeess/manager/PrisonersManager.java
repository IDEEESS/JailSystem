package fr.ideeess.manager;

import fr.ideeess.main.JailSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PrisonersManager extends JailSystem {

    public void createPrisoner(String playerName , int punition){
        Player player = Bukkit.getPlayer(playerName);
        PlayerInventory inventory = player.getInventory();

        List<Map<String, Object>> itemsList = new ArrayList<>();
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                Map<String, Object> itemData = new HashMap<>();

                // Durabilité
                short durability = item.getDurability();
                itemData.put("durability", durability);

                // Enchantements
                Map<String, Integer> enchantmentsMap = new HashMap<>();
                Map<Enchantment, Integer> enchantments = item.getEnchantments();
                for (Enchantment enchantment : enchantments.keySet()) {
                    int level = enchantments.get(enchantment);
                    String enchantmentName = enchantment.getKey().getKey();
                    enchantmentsMap.put(enchantmentName, level);
                }
                itemData.put("enchantments", enchantmentsMap);

                // Nom spécifique
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.hasDisplayName()) {
                    String displayName = meta.getDisplayName();
                    itemData.put("display_name", displayName);
                }

                itemsList.add(itemData);
            }
        }

        getConfig().set("prisonners."+playerName+".punition",punition);
        getConfig().set("prisonners."+playerName+".inventory",itemsList);
        saveConfig();

    }

    public void sendInJail(String playerName , int punition){
        Player player = Bukkit.getPlayer(playerName);

        assert player != null;
        player.getInventory().clear();
        player.teleport(JAILSPAWNLOCATION);
        player.sendMessage(ChatColor.RED + "Vous êtes maintenant en prison .Vous devez casser " + punition + " blocs");

        ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE); // Crée une pioche en pierre

        // Ajoute l'enchantement "Unbreaking" de niveau maximal pour rendre la pioche incassable
        pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        // Définit l'attribut "generic.max_durability" de la pioche à une valeur élevée pour prolonger la durabilité
        ItemMeta meta = pickaxe.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.max_durability", 100000, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        pickaxe.setItemMeta(meta);

    }

    public void endOfJail(String playerName){
        Player player = Bukkit.getPlayer(playerName);

        player.teleport(JAILEXITLOCATION);
        player.getInventory().clear();

        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) getConfig().get("prisonners."+playerName+".punition");
        for (Map<String, Object> itemData : itemsList) {
            ItemStack item = new ItemStack(Material.STONE); // Remplace le type d'objet par défaut par exemple

            // Durabilité
            if (itemData.containsKey("durability")) {
                short durability = (short) itemData.get("durability");
                item.setDurability(durability);
            }

            // Enchantements
            if (itemData.containsKey("enchantments")) {
                Map<String, Integer> enchantmentsMap = (Map<String, Integer>) itemData.get("enchantments");
                for (Map.Entry<String, Integer> entry : enchantmentsMap.entrySet()) {
                    String enchantmentName = entry.getKey();
                    int level = entry.getValue();
                    Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantmentName));
                    if (enchantment != null) {
                        item.addUnsafeEnchantment(enchantment, level);
                    }
                }
            }

            // Nom spécifique
            if (itemData.containsKey("display_name")) {
                String displayName = (String) itemData.get("display_name");
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(displayName);
                    item.setItemMeta(meta);
                }
            }

            // Ajouter l'objet à l'inventaire du joueur
            player.getInventory().addItem(item);
        }

        player.sendMessage(ChatColor.GREEN + "Vous venez d'être libéré , merci de ne pas recommencer.");

        getConfig().set("prisonners."+playerName+".punition",null);
        getConfig().set("prisonners."+playerName+".inventory",null);
        saveConfig();
    }

}
