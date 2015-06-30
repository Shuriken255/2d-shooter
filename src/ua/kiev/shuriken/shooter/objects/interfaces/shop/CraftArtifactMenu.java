package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.gamestates.game.GamingProcess;
import ua.kiev.shuriken.shooter.objects.hero.Crafter;
import ua.kiev.shuriken.shooter.objects.hero.Item;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;

public class CraftArtifactMenu extends BasicMenu{
	int currentPage;
	
	public CraftArtifactMenu(String layer, int currentPage) {
		super(layer);
		this.currentPage = currentPage;
		buildThis();
	}
	
	public void buildThis(){


		for(int i = 0; i<5; i++){
			if(currentPage*10+i < Crafter.artifactCraftItems.size()){
				Item item = Crafter.artifactCraftItems.get(i+currentPage*10).getThis();
				Layers.addObject(new IconButton(layer, 64+i*80, 112, 64,
						item){
					@Override
					public void action(){
						Menu.createWindow(new ArtifactCraftItemMenu("menu_layer_" + 
								Integer.toString((Menu.currentLayer+1)),
								(Artifact) item));
					}
				});
				if(!Crafter.canBeCrafted(item, GamingProcess.hero)){
					Layers.addObject(new RedLight(layer, 64+i*80, 112, 64));
				} else {
					Layers.addObject(new GreenLight(layer, 64+i*80, 112, 64));
				}
			} else {
				Layers.addObject(new IconButton(layer, 64+i*80, 112, 64, null));
			}
			
			if(currentPage*10+i+5 < Crafter.artifactCraftItems.size()){
				Item item = Crafter.artifactCraftItems.get(i+5+currentPage*10).getThis();
				Layers.addObject(new IconButton(layer, 64+i*80, 192, 64,
						item){
					@Override
					public void action(){
						Menu.createWindow(new ArtifactCraftItemMenu("menu_layer_" + 
								Integer.toString((Menu.currentLayer+1)),
								(Artifact) item));
					}
				});
				if(!Crafter.canBeCrafted(item, GamingProcess.hero)){
					Layers.addObject(new RedLight(layer, 64+i*80, 192, 64));
				} else {
					Layers.addObject(new GreenLight(layer, 64+i*80, 192, 64));
				}
			} else {
				Layers.addObject(new IconButton(layer, 64+i*80, 192, 64, null));
			}
		}
		
		TextButton back;
		TextButton next;
		if(currentPage != 0){
			back = new TextButton(layer, 64, 272, 176, 32, 0.6f,
					"BACK", Text.CENTER, Text.CENTER){
				@Override
				public void action(){
					Menu.changeMainMenu(Menu.CRAFT_ARTIFACT, currentPage-1);
				}
			};
		} else {
			back = new TextButton(layer, 64, 272, 176, 32, 0.6f,
					"BACK", Text.CENTER, Text.CENTER);
			back.active = false;
		}
		Layers.addObject(back);
		if((currentPage+1)*10 < Crafter.artifactCraftItems.size()){
			next = new TextButton(layer, 272, 272, 176, 32, 0.6f,
					"NEXT", Text.CENTER, Text.CENTER){
				@Override
				public void action(){
					Menu.changeMainMenu(Menu.CRAFT_ARTIFACT, currentPage+1);
				}
			};
		} else {
			next = new TextButton(layer, 272, 272, 176, 32, 0.6f,
					"NEXT", Text.CENTER, Text.CENTER);
			next.active = false;
		}
		Layers.addObject(next);
		
		// Top buttons
		Layers.addObject(new MainSwitchButton(layer, 256, 16, 240, 32, 0.4f, "CRAFT", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Menu.changeMainMenu(3, currentPage);
				return;
			}
		});
		Layers.addObject(new MainSwitchButton(layer, 256, 64, 224, 32, 0.4f, "ARTIFACTS", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Menu.changeMainMenu(3, currentPage);
				return;
			}
		});
		Layers.addObject(new MainSwitchButton(layer, 16, 16, 240, 32, 0.4f, "SHOP", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Menu.changeMainMenu(1, 0);
				return;
			}
		});
		Layers.addObject(new MainSwitchButton(layer, 32, 64, 224, 32, 0.4f, "WEAPONS", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Menu.changeMainMenu(2, 0);
				return;
			}
		});
	}

	@Override
	public void build(){
		Layers.addObject(new Frame(layer, 4));
	}
	
	@Override public void refresh(){
		Layers.clearLayer(layer);
		build();
		buildThis();
	}
	
	@Override
	public void logic(){
		
	}
}
