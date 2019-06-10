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
recipes.addShaped(<Railcraft:machine.alpha:7>, [[fireBrick, mortar, fireBrick], [mortar, null, mortar],[fireBrick, mortar, fireBrick]]);


var steel = <terrafirmacraft:item.Steel Ingot>;
var blacksteel = <terrafirmacraft:item.Black Steel Ingot>;
# Anvil recipes
#standard rail
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.rail:0> * 2, steel, "rail", 4);
game.setLocalization("en_US", "gui.plans.rail", "Rail");

# advanced rail
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.rail:4>, blacksteel, "rail", 4);

# Gears
recipes.remove(<Railcraft:part.gear>);
recipes.remove(<Railcraft:part.gear:1>);
recipes.remove(<Railcraft:part.gear:2>);
recipes.remove(<Railcraft:part.gear:3>);
game.setLocalization("en_US", "gui.plans.gear", "Gear");

# Gold
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.gear:0>, <terrafirmacraft:item.Gold Double Sheet>, "gear", 1);

# Iron
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.gear:1>, <terrafirmacraft:item.Wrought Iron Double Sheet>, "gear", 3);

# Steel
mods.Terrafirmacraft.Anvil.addAnvilRecipe(<Railcraft:part.gear:2>, <terrafirmacraft:item.Steel Double Sheet>, "gear", 4);

# Boiler tanks

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