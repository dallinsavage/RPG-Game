import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Display_Game extends Application {
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Player player1 = new Player();
		GridPane spawnPlayerPane = new GridPane();
		GridPane spawnEnemiesPane = new GridPane();
		BorderPane campPane = new BorderPane();
		Pane combatPane = new Pane();
		GridPane shopPane = new GridPane();
		Scene spawnEnemies = new Scene(spawnEnemiesPane, 750, 500);
		Scene spawnPlayer = new Scene(spawnPlayerPane, 750, 500);
		Scene camp = new Scene(campPane, 750, 500);
		Scene combat = new Scene(combatPane, 750, 500);
		Scene shop = new Scene(shopPane, 750, 500);
		
		
		//Spawn Player

		Label pointsLeft = new Label("Points left");
		TextField points = new TextField("25");
		points.setEditable(false);
		Label armor = new Label("Armor");
		Label damage = new Label("Damage");
		Label attackBonus = new Label("Attack Bonus");
		Label endurance = new Label("Endurance");
		Button plusArmor = new Button("+");
		Button plusDamage = new Button("+");
		Button plusAttackBonus = new Button("+");
		Button plusEndurance = new Button("+");
		spawnPlayerPane.addColumn(0, pointsLeft, armor, damage, attackBonus, endurance);
		spawnPlayerPane.addColumn(1, points, plusArmor, plusDamage, plusAttackBonus, plusEndurance);
		
		
		//Spawn Player events
		
		plusArmor.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseArmor(1);
			points.setText(countDown(points.getText()));
		}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusDamage.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseDamage(1);
			points.setText(countDown(points.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusAttackBonus.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseAttackBonus(1);
			points.setText(countDown(points.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusEndurance.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseEndurance(1);
			points.setText(countDown(points.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});

		//Spawn Enemies
		
		Button spawn = new Button("Spawn");
		Label skeleton = new Label("Skeleton");
		TextField skeletonNum = new TextField("0");
		Label zombie = new Label("Zombie");
		TextField zombieNum = new TextField("0");
		Label tank = new Label("Tank");
		TextField tankNum = new TextField("0");
		Label boss = new Label("Boss");
		TextField bossNum = new TextField("0");
		spawnEnemiesPane.setAlignment(Pos.CENTER);
		spawnEnemiesPane.addColumn(0, skeleton, zombie, tank, boss, spawn);
		spawnEnemiesPane.addColumn(1, skeletonNum, zombieNum, tankNum, bossNum);
		
		//Spawn Enemies events
		
		ArrayList<Character> enemies = new ArrayList<Character>();
		spawn.setOnAction(e -> {
		int numOfSkeletons =	Integer.parseInt(skeletonNum.getText());
		int numOfZombies =	Integer.parseInt(zombieNum.getText());
		int numOfTanks =	Integer.parseInt(tankNum.getText());
		int numOfBoss =	Integer.parseInt(bossNum.getText());
		for (int s = 0; s < numOfSkeletons; s++) {
			enemies.add(new Skeleton());
		}
		for (int z = 0; z < numOfZombies; z++) {
			enemies.add(new Zombie());
		}
		for (int t = 0; t < numOfTanks; t++) {
			enemies.add(new Tank());
		}
		for (int b = 0; b < numOfBoss; b++) {
			enemies.add(new Boss());
		}
		primaryStage.setScene(combat);
		});

		
		//Camp
		
		Button btRest = new Button("Rest");
		Button btShop = new Button("Shop");
		Button btCombat = new Button("Continue Adventure");
		HBox campHBox = new HBox(200);
		campHBox.setAlignment(Pos.CENTER);
		campHBox.getChildren().addAll(btRest, btShop, btCombat);
		
		
		//Camp events
		
		btRest.setOnAction(e -> {
			player1.rest();
		});
		btShop.setOnAction(e -> {
			primaryStage.setScene(shop);
			primaryStage.setTitle("Shop");
		});
		btCombat.setOnAction(e -> {
			primaryStage.setScene(spawnEnemies);
			primaryStage.setTitle("Adventure");
		});
		
		
		
		//Shop

		Button btEndurance = new Button("Potion of Endurance (50g)");
		Button btArmor = new Button("Better Armor (100g)");
		Button btStrength = new Button("Potion of strength (50g)");
		Button btAttack = new Button("Better Weapon (100g)");
		Button back = new Button("Back");
		shopPane.addColumn(0, btEndurance, btArmor);
		shopPane.addColumn(1, btStrength, btAttack, back);

		shopPane.setAlignment(Pos.CENTER);
		shopPane.setHgap(50);
		shopPane.setVgap(50);
		
		//Shop events
		
		btEndurance.setOnAction(e -> {
			if (player1.getGold() >= 50) {
				player1.increaseEndurance(1);
			}
		});
		btArmor.setOnAction(e -> {
			if (player1.getGold() >= 100) {
				player1.increaseArmor(1);
			}
		});
		btStrength.setOnAction(e -> {
			if (player1.getGold() >= 50) {
				player1.increaseDamage(1);
			}
		});
		btAttack.setOnAction(e -> {
			if (player1.getGold() >= 100) {
				player1.increaseAttackBonus(1);
			}
		});
		back.setOnAction(e -> {
			primaryStage.setScene(camp);
			primaryStage.setTitle("Camp");
		});
	
		
		
		//Combat
		combatPane.getChildren().add(player1.drawPlayer());
		
		campPane.setCenter(campHBox);
		primaryStage.setTitle("Spawn");
		primaryStage.setScene(spawnPlayer);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
	public String countDown(String currentNum) {
		int intValue = Integer.parseInt(currentNum);
		intValue = intValue - 1;
		String newNum = String.valueOf(intValue);
		return newNum;
	}
	public String countUp(String currentNum) {
		int intValue = Integer.parseInt(currentNum);
		intValue = intValue + 1;
		String newNum = String.valueOf(intValue);
		return newNum;
	}

}
