# Wooden ties

recipes.remove(<Railcraft:part.tie:0>);

for lumber in <ore:woodLumber>.items {
	mods.Terrafirmacraft.Barrel.addItemConversion(<Railcraft:part.tie>, lumber, <liquid:creosote> * 250, 0, true, 4, true);
}


#Stone ties

recipes.remove(<Railcraft:part.tie:1>);
# rocks seem a little odd but what else could it be?
recipes.addShaped(<Railcraft:part.tie:1>, [[null, <Railcraft:part.rebar>, null], [<ore:itemRock>, <ore:itemRock>, <ore:itemRock>]]);