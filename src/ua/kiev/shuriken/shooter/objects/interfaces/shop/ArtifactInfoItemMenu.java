package ua.kiev.shuriken.shooter.objects.interfaces.shop;

import ua.kiev.shuriken.game.Layers;
import ua.kiev.shuriken.game.text.Text;
import ua.kiev.shuriken.shooter.objects.hero.Artifact;
import ua.kiev.shuriken.shooter.objects.hero.Weapon;

public class ArtifactInfoItemMenu extends BasicMenu{
Artifact artifact;
	
	MenuLinearText artifactName;
	
	MenuMultilineText description;
	
	
	public ArtifactInfoItemMenu(String layer, Artifact artifact) {
		super(layer);
		this.artifact = artifact;
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
	}

	@Override
	public void build() {
		Layers.addObject(new Frame(layer, 6));
		Layers.addObject(new TextButton(layer, 320, 304, 176, 32, 0.4f,
				"BACK", Text.CENTER, Text.CENTER){
			@Override
			public void action(){
				Menu.deleteLastWindow();
			}
		});
	}
	
	@Override public void refresh(){
		Layers.clearLayer(layer);
		build();
		artifactName = new MenuLinearText(layer, artifact.textName, 256, 12, 0.6f, Text.CENTER);
		artifact.drawIcon(layer, 192, 48, 128, true);
		description = new MenuMultilineText(layer,
				Weapon.descriptions.get(artifact.name), 18, 186, 0.4f, 38);
	}

	@Override
	public void logic() {
		artifactName.changePosition();
		artifactName.changeAlpha();
		description.changePosition();
		description.changeAlpha();
	}
}
