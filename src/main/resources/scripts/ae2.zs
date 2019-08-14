import crafttweaker.item.IItemStack;
import mods.jei.JEI.removeAndHide;
import mods.gregtech.recipe.RecipeMap;
import mods.appliedenergistics2.Inscriber;

Inscriber.removeRecipe(<appliedenergistics2:material:13>);
Inscriber.removeRecipe(<appliedenergistics2:material:14>);
Inscriber.removeRecipe(<appliedenergistics2:material:15>);
Inscriber.removeRecipe(<appliedenergistics2:material:19>);
Inscriber.removeRecipe(<appliedenergistics2:material:20>);
Inscriber.removeRecipe(<appliedenergistics2:material:18>);
Inscriber.removeRecipe(<appliedenergistics2:material:17>);
Inscriber.removeRecipe(<appliedenergistics2:material:16>);
Inscriber.removeRecipe(<appliedenergistics2:material:22>);
Inscriber.removeRecipe(<appliedenergistics2:material:23>);
Inscriber.removeRecipe(<appliedenergistics2:material:24>);


mods.jei.JEI.removeAndHide(<appliedenergistics2:quartz_growth_accelerator>);
mods.jei.JEI.removeAndHide(<appliedenergistics2:inscriber>);

//##########processor#################
val logicProcessor = <appliedenergistics2:material:22>;
val calcProcessor = <appliedenergistics2:material:23>;
val engiProcessor = <appliedenergistics2:material:24>;
//################greg circuit###################
val basicCircuit = <ore:circuitBasic>;
val circuitGood = <ore:circuitGood>;
val circuitAdvanced = <ore:circuitAdvanced>;


val printedSilicon = <appliedenergistics2:material:20>;
var machineComputerCassing = null;
val crystalFluix = <ore:crystalFluix>;
val crystalPureFluix = <ore:crystalPureFluix>;
val fluix= crystalFluix.or(crystalPureFluix);
val glassCable = <appliedenergistics2:part:16>;
var fineCopperWire = <ore:wireFineCopper>.or(<ore:wireFineAnnealedCopper>);
val pureCertuz = <appliedenergistics2:material:10>;
val crystal = <appliedenergistics2:material:1>.or(pureCertuz);
val electrumWire = <ore:wireFineElectrum>;
val wireFineRedAlloy = <ore:wireFineRedAlloy>;

val annCore = <appliedenergistics2:material:44>;
val formationCore = <appliedenergistics2:material:43>;
val quartz = <ore:crystalNetherQuartz>.or(<ore:gemQuartz>).or(<ore:gemNetherQuartz>);

val meInteface = <appliedenergistics2:interface>;
val meInteface2 = <appliedenergistics2:part:440>;
val lvMachineCassing = <gregtech:machine:501>;
val mvMachineHull = <gregtech:machine:502>;
val mvCircuit = <ore:circuitGood>;
val plateAluminium = <ore:plateAluminium>;

val meTerminal = <appliedenergistics2:part:380>;
val meCraftingTerminal = <appliedenergistics2:part:360>;
val craftTable = <ore:craftingTableWood>.or(<ore:workbench>);
val meCraftingUnit = <appliedenergistics2:crafting_unit>;
val meStorageHousing = <appliedenergistics2:material:39>;
val storageComponent4k = <appliedenergistics2:material:36>;
val mePatternTerminal  = <appliedenergistics2:part:340>;

//#################crafing table##############################


//--energy acceptor
recipes.remove(<appliedenergistics2:energy_acceptor>);
recipes.addShaped(<appliedenergistics2:energy_acceptor>,
             [[<ore:cableGtSingleTin>,  <ore:circuitBasic>,        <ore:cableGtSingleTin>],
              [fluix,                   <gregtech:machine:501>,     fluix],
              [glassCable,              fluix,                      glassCable]
             ]
);
//-- me interface
recipes.remove(meInteface);
recipes.addShaped (meInteface,
             [[null,        null,         null],
              [null,        meInteface2,  null],
              [null,        null,         null]
             ]
);
recipes.addShaped (meInteface,
             [[<ore:cableGtSingleTin>,   fluix,                      <ore:cableGtSingleTin>],
              [formationCore,            lvMachineCassing,           annCore],
              [glassCable,               fluix,                      glassCable]
             ]
);

// -- me drive
recipes.remove(<appliedenergistics2:drive>);
recipes.addShaped (<appliedenergistics2:drive>,
             [[plateAluminium,   calcProcessor,  plateAluminium],
              [glassCable,       mvMachineHull,  glassCable],
              [plateAluminium,   calcProcessor,  plateAluminium]
             ]
);
//me controller
recipes.remove(<appliedenergistics2:controller>);
recipes.addShaped (<appliedenergistics2:controller>,
             [[plateAluminium,     calcProcessor,     plateAluminium],
              [engiProcessor,      mvMachineHull,     engiProcessor],
              [crystalPureFluix,   calcProcessor,     crystalPureFluix]
             ]
);

//#################autoclave##############################
val autoclave as RecipeMap = RecipeMap.getByName("autoclave");
//pure certuz
autoclave.recipeBuilder()
    .inputs([<appliedenergistics2:crystal_seed>.withTag({progress: 0})])
    .fluidInputs([<liquid:water> * 1000])
    .outputs(pureCertuz)
    .duration(800)
    .EUt(30)
    .buildAndRegister();

//#################circuit assembler##############################
val circuitAssembler as RecipeMap = RecipeMap.getByName("circuit_assembler");

//logic processor
circuitAssembler.recipeBuilder()
    .inputs([<appliedenergistics2:material:18>,printedSilicon, fineCopperWire * 2, basicCircuit])
    .fluidInputs([<liquid:redstone> * 144])
    .outputs(logicProcessor)
    .duration(80)
    .EUt(30)
    .buildAndRegister();

//calculation processor
circuitAssembler.recipeBuilder()
   .inputs([<appliedenergistics2:material:16>,printedSilicon, wireFineRedAlloy * 4, circuitGood])
   .fluidInputs([<liquid:redstone> * 144])
   .outputs(calcProcessor)
   .duration(80)
   .EUt(60)
   .buildAndRegister();

//engiiners processor
circuitAssembler.recipeBuilder()
    .inputs([<appliedenergistics2:material:17>,printedSilicon, electrumWire * 4, circuitAdvanced])
    .fluidInputs([<liquid:redstone> * 144])
    .outputs(engiProcessor)
    .duration(80)
    .EUt(480)
    .buildAndRegister();



val laserEngraver as RecipeMap = RecipeMap.getByName("laser_engraver");

//--------------------------logic

//--- mold
laserEngraver.recipeBuilder()
    .inputs([<ore:blockIron>])
    .notConsumable(<appliedenergistics2:material:15>)
    .outputs(<appliedenergistics2:material:15>)
    .duration(200)
    .EUt(1920)
    .buildAndRegister();

//----logic press
laserEngraver.recipeBuilder()
    .inputs([<ore:plateGold>])
    .notConsumable(<appliedenergistics2:material:15>)
    .outputs(<appliedenergistics2:material:18>)
    .duration(200)
    .EUt(30)
    .buildAndRegister();


//---------------------------calc

laserEngraver.recipeBuilder()
    .inputs([<ore:blockIron>])
    .notConsumable(<appliedenergistics2:material:13>)
    .outputs(<appliedenergistics2:material:13>)
    .duration(200)
    .EUt(1920)
    .buildAndRegister();

laserEngraver.recipeBuilder()
    .inputs([<appliedenergistics2:material:10>*9])
    .notConsumable(<appliedenergistics2:material:13>)
    .outputs(<appliedenergistics2:material:16>)
    .duration(200)
    .EUt(30)
    .buildAndRegister();


//---------------------------------engi
laserEngraver.recipeBuilder()
    .inputs([<ore:blockIron>])
    .notConsumable(<appliedenergistics2:material:14>)
    .outputs(<appliedenergistics2:material:14>)
    .duration(200)
    .EUt(1920)
    .buildAndRegister();

laserEngraver.recipeBuilder()
    .inputs([<ore:blockDiamond>])
    .notConsumable(<appliedenergistics2:material:14>)
    .outputs(<appliedenergistics2:material:17>)
    .duration(200)
    .EUt(30)
    .buildAndRegister();


//---silicon

laserEngraver.recipeBuilder()
    .inputs([<ore:blockIron>])
    .notConsumable(<appliedenergistics2:material:19>)
    .outputs(<appliedenergistics2:material:19>)
    .duration(200)
    .EUt(1920)
    .buildAndRegister();

laserEngraver.recipeBuilder()
    .inputs([<ore:blockSilicon>])
    .notConsumable(<appliedenergistics2:material:19>)
    .outputs(printedSilicon)
    .duration(200)
    .EUt(30)
    .buildAndRegister();


//---------------------------------------------------------------------assembler


val assembler as RecipeMap = RecipeMap.getByName("assembler");


///---glass cable
recipes.remove(glassCable);
assembler.recipeBuilder()
    .inputs([<appliedenergistics2:part:140>*4,fluix*8])
    .outputs(glassCable*4)
    .duration(80)
    .EUt(30)
    .buildAndRegister();


//annihilation core
recipes.remove(annCore);
assembler.recipeBuilder()
    .inputs([<ore:dustFluix>,logicProcessor, quartz])
    .outputs(annCore*2)
    .duration(180)
    .EUt(30)
    .buildAndRegister();

//formation core
recipes.remove(formationCore);
assembler.recipeBuilder()
    .inputs([<ore:dustFluix>, logicProcessor, crystal])
    .outputs(formationCore*2)
    .duration(180)
    .EUt(30)
    .buildAndRegister();
//1k disk drive
recipes.remove(<appliedenergistics2:material:35>);
assembler.recipeBuilder()
    .inputs([calcProcessor, <ore:dustRedstone> * 4, pureCertuz * 4])
    .outputs(<appliedenergistics2:material:35>)
    .duration(180)
    .EUt(30)
    .buildAndRegister();

assembler.recipeBuilder()
    .inputs([<ore:crystalCertusQuartz> * 2, <ore:ingotWroughtIron>])
    .outputs(<appliedenergistics2:quartz_fixture>)
    .duration(180)
    .EUt(30)
    .buildAndRegister();


//-----------------------machines
recipes.remove(meTerminal);
recipes.addShaped(meTerminal,
             [[<ore:plateRedAlloy>,    logicProcessor,                      <ore:plateRedAlloy>],
              [annCore,                <ore:itemIlluminatedPanel>,           formationCore],
              [glassCable,             logicProcessor,                       glassCable]
             ]);

recipes.remove(meCraftingTerminal);
recipes.addShaped(meCraftingTerminal,
             [[<ore:platePlastic>,     glassCable,          <ore:platePlastic>],
              [calcProcessor,          meTerminal,           calcProcessor],
              [<ore:platePlastic>,     craftTable,          <ore:platePlastic>]
             ]);

recipes.remove(meCraftingUnit);

recipes.remove(meStorageHousing);
recipes.addShaped(meCraftingTerminal,
             [[<ore:platePlastic>,          basicCircuit,        <ore:platePlastic>],
              [<ore:cableGtSingleCopper>,   <ore:blockRedstone>, <ore:cableGtSingleCopper>],
              [<ore:platePlastic>,          null,                <ore:platePlastic>]
             ]);


recipes.remove(storageComponent4k);
recipes.remove(mePatternTerminal);