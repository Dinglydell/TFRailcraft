var steel = <terrafirmacraft:item.Steel Ingot>;
var blacksteel = <terrafirmacraft:item.Black Steel Ingot>;
var ironDoubleSheet = <terrafirmacraft:item.Wrought Iron Double Sheet>;
var steelDoubleSheet = <terrafirmacraft:item.Steel Double Sheet>;
var gearGold = <Railcraft:part.gear>;
var gearIron = <Railcraft:part.gear:1>;
var gearSteel = <Railcraft:part.gear:2>;

# Wooden ties

recipes.remove(<Railcraft:part.tie:0>);

for lumber in <ore:woodLumber>.items {
	mods.Terrafirmacraft.Barrel.addItemConversion(<Railcraft:part.tie>, lumber, <liquid:creosote> * 250, 0, true, 4, true);
}


#Stone ties

recipes.remove(<Railcraft:part.tie:1>);
# rocks seem a little odd but what else could it be?
recipes.addShaped(<Railcraft:part.tie:1>, [[null, <Railcraft:part.rebar>, null], [<ore:itemRock>, <ore:itemRock>, <ore:itemRock>]]);


# logs -> charcoal
# mods.Railcraft.CokeOven.addRecipe(<terrafirmacraft:item.coal:1>, <liquid:creosote> * 250, <ore:logWood>, 1800); 
# above threw an error, not sure why but I'm doing this in code instead

var fireBrick = <terrafirmacraft:item.Fire Brick:1>;
var mortar = <terrafirmacraft:item.Mortar>;

# coke oven
recipes.remove(<Railcraft:machine.alpha:7>);
recipes.addShaped(<Railcraft:machine.alpha:7>, [[fireBrick, mortar, fireBrick], [mortar, null, mortar],[fireBrick, mortar, fireBrick]]);

# solid firebox
#recipes.remove(<Railcraft:machine.beta:5>);
recipes.addShaped(<Railcraft:machine.beta:5>, [[fireBrick, fireBrick, fireBrick], [fireBrick, null, fireBrick], [fireBrick, fireBrick, fireBrick]]);

#iron tank gauge
recipes.removeShaped(<Railcraft:machine.beta:1>, [[<*>, <Railcraft:part.plate>, <*>], [<*>, <*>, <*>], [<*>, <*>, <*>]]);
recipes.addShapeless(<Railcraft:machine.beta:1>, [<ore:plateIron>, <ore:blockGlass>]);

#iron tank wall
recipes.removeShaped(<Railcraft:machine.beta:0>, [[<*>, <*>], [<*>, <*>]]);
recipes.addShapeless(<Railcraft:machine.beta:0> * 4, [<ore:plateIron>, <ore:plateIron>, <ore:plateIron>, <ore:plateIron>]);

#iron tank valve
recipes.removeShaped(<Railcraft:machine.beta:2>, [[<*>, <Railcraft:part.plate>, <*>], [<*>, <*>, <*>], [<*>, <*>, <*>]]);
recipes.addShaped(<Railcraft:machine.beta:2>, [[<minecraft:iron_bars>, <ore:plateIron>, <minecraft:iron_bars>], [<ore:plateIron>, null, <ore:plateIron>], [<minecraft:iron_bars>, <ore:plateIron>, <minecraft:iron_bars>]]);

#steel tank gauge
recipes.removeShaped(<Railcraft:machine.beta:14>, [[<*>, <Railcraft:part.plate:1>, <*>], [<*>, <*>, <*>], [<*>, <*>, <*>]]);
recipes.addShapeless(<Railcraft:machine.beta:14>, [<ore:plateSteel>, <ore:blockGlass>]);

#steel tank wall
recipes.removeShaped(<Railcraft:machine.beta:13>, [[<*>, <*>], [<*>, <*>]]);
recipes.addShapeless(<Railcraft:machine.beta:13> * 4, [<ore:plateSteel>, <ore:plateSteel>, <ore:plateSteel>, <ore:plateSteel>]);

#steel tank valve
recipes.removeShaped(<Railcraft:machine.beta:15>, [[<*>, <Railcraft:part.plate:1>, <*>], [<*>, <*>, <*>], [<*>, <*>, <*>]]);
recipes.addShaped(<Railcraft:machine.beta:15>, [[<minecraft:iron_bars>, <ore:plateSteel>, <minecraft:iron_bars>], [<ore:plateSteel>, null, <ore:plateSteel>], [<minecraft:iron_bars>, <ore:plateSteel>, <minecraft:iron_bars>]]);


# steam engines
# hobbyist
recipes.remove(<Railcraft:machine.beta:7>);
recipes.addShaped(<Railcraft:machine.beta:7>, [[null, <ore:plateGold>, null], [gearGold, <minecraft:piston>, gearGold]]);

# commercial
recipes.remove(<Railcraft:machine.beta:8>);
recipes.addShaped(<Railcraft:machine.beta:8>, [[null, <ore:plateIron>, null], [null, <terrafirmacraft:item.Wrought Iron Tuyere>, null], [gearIron, <minecraft:piston>, gearIron]]);

# industrial
recipes.remove(<Railcraft:machine.beta:9>);
recipes.addShaped(<Railcraft:machine.beta:9>, [[null, <ore:plateSteel>, null], [null, <terrafirmacraft:item.Steel Tuyere>, null], [gearSteel, <minecraft:piston>, gearSteel]]);

# Anvil recipes
#standard rail
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.rail:0> * 2, steel, "rail", 4);
game.setLocalization("en_US", "gui.plans.rail", "Rail");

# advanced rail
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.rail:4>, blacksteel, "rail", 5);

# Gears
recipes.remove(gearGold);
recipes.remove(gearIron);
recipes.remove(gearSteel);
recipes.remove(<Railcraft:part.gear:3>);
game.setLocalization("en_US", "gui.plans.gear", "Gear");

# Gold
mods.Terrafirmacraft.Anvil.addAnvilRecipe(gearGold, <terrafirmacraft:item.Gold Double Sheet>, "gear", 1);

# Iron
mods.Terrafirmacraft.Anvil.addAnvilRecipe(gearIron, ironDoubleSheet, "gear", 3);

# Steel
mods.Terrafirmacraft.Anvil.addAnvilRecipe(gearSteel, steelDoubleSheet, "gear", 4);

# Boiler tanks
recipes.remove(<Railcraft:machine.beta:3>);
recipes.remove(<Railcraft:machine.beta:4>);
game.setLocalization("en_US", "gui.plans.boilerTank", "Boiler Tank");
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:machine.beta:3>, ironDoubleSheet, "boilerTank", 3);
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:machine.beta:4>, steelDoubleSheet, "boilerTank", 4);

#Ore dict RC plates with TFC sheets
<ore:plateIron>.add(<Railcraft:part.plate:0>);
<ore:plateSteel>.add(<Railcraft:part.plate:1>);
<ore:plateTin>.add(<Railcraft:part.plate:2>);

# rolling machine - evil recipe
<ore:plateTierSix>.add(<terrafirmacraft:item.Red Steel Sheet>);
<ore:plateTierSix>.add(<terrafirmacraft:item.Blue Steel Sheet>);
var rollingMachine = <Railcraft:machine.alpha:8>;
recipes.remove(rollingMachine);
recipes.addShaped(rollingMachine, [[<ore:plateTierSix>, <minecraft:piston>, <ore:plateTierSix>], [<minecraft:piston>, <ore:craftingTableWood>, <minecraft:piston>], [<ore:plateTierSix>, <minecraft:piston>, <ore:plateTierSix>]]);


# remove steel minecart recipe
recipes.remove(<minecraft:minecart>);
recipes.addShaped(<minecraft:minecart>, [[<ore:plateIron>, null, <ore:plateIron>], [<ore:plateIron>, <ore:plateIron>, <ore:plateIron>]]);

# cargo cart
recipes.remove(<Railcraft:cart.cargo>);
recipes.addShapeless(<Railcraft:cart.cargo>, [<minecraft:minecart>]);
recipes.addShapeless(<minecraft:minecart>, [<Railcraft:cart.cargo>]);


# item loader
recipes.remove(<Railcraft:machine.gamma>);
recipes.addShaped(<Railcraft:machine.gamma>, [[<ore:cobblestone>, <ore:cobblestone>, <ore:cobblestone>], [<ore:cobblestone>, <terrafirmacraft:Hopper>, <ore:cobblestone>], [<ore:cobblestone>, <Railcraft:detector:0>, <ore:cobblestone>]]);
# item unloader
recipes.remove(<Railcraft:machine.gamma:1>);
recipes.addShaped(<Railcraft:machine.gamma:1>, [[<ore:cobblestone>, <ore:cobblestone>, <ore:cobblestone>], [<ore:cobblestone>, <Railcraft:detector:0>, <ore:cobblestone>], [<ore:cobblestone>, <terrafirmacraft:Hopper>, <ore:cobblestone>]]);

#adv item loader
recipes.remove(<Railcraft:machine.gamma:2>);
recipes.addShaped(<Railcraft:machine.gamma:2>, [[<ore:ingotSteel>, <ore:dustRedstone>, <ore:ingotSteel>], [<ore:dustRedstone>, <Railcraft:machine.gamma>, <ore:dustRedstone>], [<ore:ingotSteel>, <ore:dustRedstone>, <ore:ingotSteel>]]);

#adv item unloader
recipes.remove(<Railcraft:machine.gamma:3>);
recipes.addShaped(<Railcraft:machine.gamma:3>, [[<ore:ingotSteel>, <ore:dustRedstone>, <ore:ingotSteel>], [<ore:dustRedstone>, <Railcraft:machine.gamma:1>, <ore:dustRedstone>], [<ore:ingotSteel>, <ore:dustRedstone>, <ore:ingotSteel>]]);

#fluid loader
recipes.remove(<Railcraft:machine.gamma:4>);
recipes.addShaped(<Railcraft:machine.gamma:4>, [[<ore:blockGlass>, <terrafirmacraft:Hopper>, <ore:blockGlass>], [<ore:blockGlass>, null, <ore:blockGlass>], [<ore:blockGlass>, <Railcraft:detector:8>, <ore:blockGlass>]]);

#fluid unloader
recipes.remove(<Railcraft:machine.gamma:5>);
recipes.addShaped(<Railcraft:machine.gamma:5>, [[<ore:blockGlass>, <Railcraft:detector:8>, <ore:blockGlass>], [<ore:blockGlass>, null, <ore:blockGlass>], [<ore:blockGlass>, <terrafirmacraft:Hopper>, <ore:blockGlass>]]);



# Detectors
 
# Empty detector (stone brick)
recipes.addShaped(<Railcraft:detector:2>, [[<ore:stoneBricks>, <ore:stoneBricks>, <ore:stoneBricks>], [<ore:stoneBricks>, <minecraft:stone_pressure_plate>, <ore:stoneBricks>], [<ore:stoneBricks>, <ore:stoneBricks>, <ore:stoneBricks>]]);

# Mob detector (moss stone -> rocks)
recipes.addShaped(<Railcraft:detector:3>, [[<ore:itemRock>, <ore:itemRock>, <ore:itemRock>], [<ore:itemRock>, <minecraft:stone_pressure_plate>, <ore:itemRock>], [<ore:itemRock>, <ore:itemRock>, <ore:itemRock>]]);

# Player detector (stone slabs -> smooth stone)
recipes.addShaped(<Railcraft:detector:5>, [[<ore:stoneSmooth>, <ore:stoneSmooth>, <ore:stoneSmooth>], [<ore:stoneSmooth>, <minecraft:stone_pressure_plate>, <ore:stoneSmooth>], [<ore:stoneSmooth>, <ore:stoneSmooth>, <ore:stoneSmooth>]]);

# Animal detector (log -> lumber)
recipes.addShaped(<Railcraft:detector:7>, [[<ore:woodLumber>, <ore:woodLumber>, <ore:woodLumber>], [<ore:woodLumber>, <minecraft:stone_pressure_plate>, <ore:woodLumber>], [<ore:woodLumber>, <ore:woodLumber>, <ore:woodLumber>]]);

# Tank - Brick -> fire brick (item)
recipes.addShaped(<Railcraft:detector:8>, [[fireBrick, fireBrick, fireBrick], [fireBrick, <minecraft:stone_pressure_plate>, fireBrick], [fireBrick, fireBrick, fireBrick]]);

# Age - spruce log -> TFC log
recipes.addShaped(<Railcraft:detector:11>, [[<ore:logWood>, <ore:logWood>, <ore:logWood>], [<ore:logWood>, <minecraft:stone_pressure_plate>, <ore:logWood>], [<ore:logWood>, <ore:logWood>, <ore:logWood>]]);

# Train - Nether brick -> fire brick block
recipes.addShaped(<Railcraft:detector:12>, [[<terrafirmacraft:FireBrick>, <terrafirmacraft:FireBrick>, <terrafirmacraft:FireBrick>], [<terrafirmacraft:FireBrick>, <minecraft:stone_pressure_plate>, <terrafirmacraft:FireBrick>], [<terrafirmacraft:FireBrick>, <terrafirmacraft:FireBrick>, <terrafirmacraft:FireBrick>]]);

# Sheep - wool -> wool cloth
recipes.addShaped(<Railcraft:detector:13>, [[<ore:materialWool>, <ore:materialWool>, <ore:materialWool>], [<ore:materialWool>, <minecraft:stone_pressure_plate>, <ore:materialWool>], [<ore:materialWool>, <ore:materialWool>, <ore:materialWool>]]);

# Routing - chiselled quartz -> stone brick (item)
recipes.addShaped(<Railcraft:detector:16>, [[<terrafirmacraft:item.ItemStoneBrick:*>, <terrafirmacraft:item.ItemStoneBrick:*>, <terrafirmacraft:item.ItemStoneBrick:*>], [<terrafirmacraft:item.ItemStoneBrick:*>, <minecraft:stone_pressure_plate>, <terrafirmacraft:item.ItemStoneBrick:*>], [<terrafirmacraft:item.ItemStoneBrick:*>, <terrafirmacraft:item.ItemStoneBrick:*>, <terrafirmacraft:item.ItemStoneBrick:*>]]);
